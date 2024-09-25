package br.com.infnet.almoxarifado_service.infra.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import br.com.infnet.almoxarifado_service.domain.ItemRegistro;
import br.com.infnet.almoxarifado_service.domain.Registro;
import br.com.infnet.almoxarifado_service.events.RegistroAlterado;
import br.com.infnet.almoxarifado_service.infra.client.Pedido;
import br.com.infnet.almoxarifado_service.infra.repository.RegistroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegistroService {

    private static final Logger LOG = LoggerFactory.getLogger(RegistroService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    JacksonPubSubMessageConverter converter;

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private PedidoService pedidoService;

    public Registro save(Registro registro) {
        try {
            return registroRepository.save(registro);
        } catch (Exception e) {
            LOG.error("**** ERRO **** " + e.getMessage());
        }
        return null;
    }

    private void createRegistro(Pedido pedido) {
        Registro registro = new Registro(pedido.getID(), "Registro do Pedido " + pedido.getID());
        for(ItemRegistro item : pedido.getItemList()) {
            registro.adicionarItem(item.getProductId(), item.getQuantity());
        }
        this.save(registro);
    }

    public void processEvent(RegistroAlterado event) {
        if(Objects.equals(event.getStatus(), "FECHADO")) {
            Pedido pedido = pedidoService.getPedidoById(event.getPedidoID());
            this.createRegistro(pedido);
            this.send(new RegistroAlterado(event.getPedidoID(), "EM_PREPARACAO"));
        }
    }

    private void send(RegistroAlterado estado) {
        pubSubTemplate.setMessageConverter(converter);
        pubSubTemplate.publish("teste-dr4", estado);
        LOG.info("***** Mensagem Publicada ---> " + estado);
    }

    @ServiceActivator(inputChannel = "inputMessageChannel")
    private void receive(RegistroAlterado payload,
                         @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<RegistroAlterado> message) {

        LOG.info("***** Mensagem Recebida ---> " + payload);
        message.ack();
        this.processEvent(payload);
    }

    public Registro findById(Long id) {return registroRepository.getReferenceById(id);}

    public Registro concluir(Long id) {
        Registro registro = this.findById(id);
        registro.concluirRegistro();
        registro = registroRepository.save(registro);
        this.send(new RegistroAlterado(registro.getPedidoID(), "EM_TRANSITO"));
        return registro;
    }

    public List<Registro> findAll() {
        return registroRepository.findAll();
    }
}
