package br.com.infnet.almoxarifado_service.config;

import br.com.infnet.almoxarifado_service.domain.Produto;
import br.com.infnet.almoxarifado_service.service.AlmoxarifadoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class AlmoxarifadoEventConfig {

    private final AlmoxarifadoService almoxarifadoService;

    public AlmoxarifadoEventConfig(AlmoxarifadoService almoxarifadoService) {
        this.almoxarifadoService = almoxarifadoService;
    }

    @Bean
    public Consumer<Produto> pedidoEventConsumer() {
        return pedidoEvent -> {
            System.out.println("Evento recebido no Almoxarifado: " + pedidoEvent.toString());
            almoxarifadoService.processarPedido(pedidoEvent);
        };
    }
}
