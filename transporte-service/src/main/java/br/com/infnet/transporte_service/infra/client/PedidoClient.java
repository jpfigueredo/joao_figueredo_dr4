package br.com.infnet.transporte_service.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pedido-ms", url = "http://localhost:8080")
public interface PedidoClient {

    @GetMapping("/pedidos/{id}")
    Pedido getPedidoById(@PathVariable("id") Long id);
}

