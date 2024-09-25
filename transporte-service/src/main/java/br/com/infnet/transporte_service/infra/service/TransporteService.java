package br.com.infnet.transporte_service.infra.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import br.com.infnet.transporte_service.domain.Endereco;
import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.domain.ManifestoDeCarga;
import br.com.infnet.transporte_service.eventos.RegistroAlterado;
import br.com.infnet.transporte_service.infra.client.Pedido;
import br.com.infnet.transporte_service.infra.repository.EntregaRepository;
import br.com.infnet.transporte_service.infra.repository.RegistroTransporteRepository;
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
    private RegistroTransporteRepository repository;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    private void createManifesto(Pedido pedido) {
        ManifestoDeCarga manifesto;
        Optional<ManifestoDeCarga> manifestoDb = this.findManifestosEmEdicao();

        if (manifestoDb.isPresent()) {
            manifesto = manifestoDb.get();
        } else {
            manifesto = getManifestoDetails();
        }

        manifesto.addEntrega(pedido.getID(), clienteService.getEnderecoByCustomerId(pedido.getCustomerId()));
        repository.save(manifesto);
    }

    public void processEvent(RegistroAlterado event) {
        if(Objects.equals(event.getStatus(), "EM_TRANSITO")) {
            Pedido pedido = pedidoService.getPedidoById(event.getPedidoID());
            this.createManifesto(pedido);
        }
    }

    private void send(RegistroAlterado status) {
        pubSubTemplate.setMessageConverter(converter);
        pubSubTemplate.publish("teste-dr4", status);
        LOG.info("***** Mensagem Publicada ---> " + status);
    }

    @ServiceActivator(inputChannel = "inputMessageChannel")
    private void receive(RegistroAlterado payload,
                         @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<RegistroAlterado> message) {

        LOG.info("***** Mensagem Recebida ---> " + payload);
        message.ack();
        this.processEvent(payload);
    }

    public Entrega encerrarEntrega(long entregaId) {
        Entrega entrega = entregaRepository.getReferenceById(entregaId);
        entrega.encerrarEntrega();
        entrega = entregaRepository.save(entrega);
        this.send(new RegistroAlterado(entrega.getPedidoID(), "ENTREGUE"));
        return entrega;
    }

    public ManifestoDeCarga findManifestoDeCargaById(long id) {
        return repository.getReferenceById(id);
    }

    public ManifestoDeCarga encerrarEdicao(long id) {
        ManifestoDeCarga manifesto = this.findManifestoDeCargaById(id);
        manifesto.setEmEdicao(false);
        return repository.save(manifesto);
    }

    public Optional<ManifestoDeCarga> findManifestosEmEdicao() {
        return repository.findEmEdicao();
    }

    private ManifestoDeCarga getManifestoDetails() {
        Endereco origem = new Endereco("Filial RJ", "Rio de Janeiro", "RJ", "20041-005");
        Endereco destino = new Endereco("Filial SP", "São Paulo", "SP", "04756-050");
        return new ManifestoDeCarga("Motorista Fulano", "XXXXXX", origem, destino);
    }
}
