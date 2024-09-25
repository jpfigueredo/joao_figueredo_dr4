package br.edu.infnet.pedidos.eventos;

import br.edu.infnet.pedidos.domain.PedidoStatus;
import java.io.Serializable;
import java.util.Date;

public class RegistroAlterado implements Serializable {
    
    private long pedidoID;
    private PedidoStatus status;
    private Date momento;

    public RegistroAlterado() {
    }

    public RegistroAlterado(long pedidoID, PedidoStatus status) {
        this.pedidoID = pedidoID;
        this.status = status;
        this.momento = new Date();
    }
    
    public RegistroAlterado(long pedidoID, PedidoStatus status, Date momento) {
        this.pedidoID = pedidoID;
        this.status = status;
        this.momento = momento;
    }

    public long getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(long pedidoID) {
        this.pedidoID = pedidoID;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    @Override
    public String toString() {
        return "EstadoPedidoMudou{" + "idPedido=" + pedidoID + ", estado=" + status + ", momento=" + momento + '}';
    }
}
