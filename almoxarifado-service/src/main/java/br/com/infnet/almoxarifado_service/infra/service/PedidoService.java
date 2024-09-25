package br.com.infnet.almoxarifado_service.infra.service;

import br.com.infnet.almoxarifado_service.infra.client.Pedido;
import br.com.infnet.almoxarifado_service.infra.client.PedidoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoClient pedidoClient;

    public Pedido findPedidoById(Long id) {
        try {
            return pedidoClient.findPedidoById(id);
        } catch (Exception e) {
            LOG.error("**** ERRO **** " + e.getMessage());
        }
        return null;
    }
}
