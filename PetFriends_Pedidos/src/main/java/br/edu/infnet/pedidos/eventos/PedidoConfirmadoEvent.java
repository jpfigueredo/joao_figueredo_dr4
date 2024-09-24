package br.edu.infnet.pedidos.eventos;

import br.edu.infnet.pedidos.domain.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class PedidoConfirmadoEvent {
    private String pedidoId;
    private String clienteId;
    private Endereco enderecoEntrega;
    private List<ItemPedido> itens;

    // Construtores, getters e setters

    @Data
    public static class ItemPedido {
        private String produtoId;
        private int quantidade;

        // Construtores, getters e setters
    }

}
