package br.edu.infnet.pedidos.eventos;

import br.edu.infnet.pedidos.domain.PedidoStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EstadoPedidoMudou implements Serializable {
    
    private Long idPedido;
    private PedidoStatus estado;
    private Date momento;

    public EstadoPedidoMudou() {
    }

    public EstadoPedidoMudou(Long idPedido, PedidoStatus estado) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = new Date();
    }

    public EstadoPedidoMudou(Long idPedido, PedidoStatus estado, Date momento) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = momento;
    }

    @Override
    public String toString() {
        return "EstadoPedidoMudou{" + "idPedido=" + idPedido + ", estado=" + estado + ", momento=" + momento + '}';
    }
}
