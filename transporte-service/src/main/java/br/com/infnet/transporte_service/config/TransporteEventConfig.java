package br.com.infnet.transporte_service.config;

import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.repository.TransporteRepository;
import br.com.infnet.transporte_service.service.TransporteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class TransporteEventConfig {

    private final TransporteService transporteService;
    private final TransporteRepository transporteRepository;

    public TransporteEventConfig(TransporteService transporteService, TransporteRepository transporteRepository) {
        this.transporteService = transporteService;
        this.transporteRepository = transporteRepository;
    }

    @Bean
    public Consumer<Entrega> pedidoEventConsumer() {
        return pedidoEvent -> {
            System.out.println("Evento recebido no Transporte: " + pedidoEvent.toString());
            transporteService.processarPedido(pedidoEvent);
        };
    }
}
