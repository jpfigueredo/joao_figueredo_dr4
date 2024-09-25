package br.com.infnet.transporte_service.infra.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import br.com.infnet.transporte_service.domain.Endereco;
import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.domain.ManifestoDeCarga;
import br.com.infnet.transporte_service.eventos.EstadoEntregaMudou;
import br.com.infnet.transporte_service.infra.client.Pedido;
import br.com.infnet.transporte_service.infra.repository.EntregaRepository;
import br.com.infnet.transporte_service.infra.repository.ManifestoDeTransporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TransporteService {

    private static final Logger LOG = LoggerFactory.getLogger(TransporteService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    JacksonPubSubMessageConverter converter;

    @Autowired
    private ManifestoDeTransporteRepository repository;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    private void criarManifesto(Pedido pedido) {
        ManifestoDeCarga manifesto;
        Optional<ManifestoDeCarga> manifestoDb = this.obterManifestosEmEdicao();

        if (manifestoDb.isPresent()) {
            manifesto = manifestoDb.get();
        } else {
            manifesto = getManifestoDetails();
        }

        manifesto.adicionarEntrega(pedido.getID(), clienteService.getEnderecoByCustomerId(pedido.getCustomerId()));
        repository.save(manifesto);
    }

    public void processarEvento(EstadoEntregaMudou evento) {
        if(Objects.equals(evento.getEstado(), "EM_TRANSITO")) {
            Pedido pedido = pedidoService.getPedidoById(evento.getIdPedido());
            this.criarManifesto(pedido);
        }
    }

    private void enviar(EstadoEntregaMudou estado) {
        pubSubTemplate.setMessageConverter(converter);
        pubSubTemplate.publish("teste-dr4", estado);
        LOG.info("***** Mensagem Publicada ---> " + estado);
    }

    @ServiceActivator(inputChannel = "inputMessageChannel")
    private void receber(EstadoEntregaMudou payload,
                         @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<EstadoEntregaMudou> message) {

        LOG.info("***** Mensagem Recebida ---> " + payload);
        message.ack();
        this.processarEvento(payload);
    }

    public Entrega concluirEntrega(long entregaId) {
        Entrega entrega = entregaRepository.getReferenceById(entregaId);
        entrega.concluirEntrega();
        entrega = entregaRepository.save(entrega);
        this.enviar(new EstadoEntregaMudou(entrega.getPedidoId(), "ENTREGUE"));
        return entrega;
    }

    public ManifestoDeCarga obterPorId(long id) {
        return repository.getReferenceById(id);
    }

    public ManifestoDeCarga concluirEdicao(long id) {
        ManifestoDeCarga manifesto = this.obterPorId(id);
        manifesto.setEmEdicao(false);
        return repository.save(manifesto);
    }

    public Optional<ManifestoDeCarga> obterManifestosEmEdicao() {
        return repository.findEmEdicao();
    }

    private ManifestoDeCarga getManifestoDetails() {
        Endereco origem = new Endereco("Filial RJ", "Rio de Janeiro", "RJ", "20041-005");
        Endereco destino = new Endereco("Filial SP", "São Paulo", "SP", "04756-050");
        return new ManifestoDeCarga("Motorista Fulano", "XXXXXX", origem, destino);
    }
}
