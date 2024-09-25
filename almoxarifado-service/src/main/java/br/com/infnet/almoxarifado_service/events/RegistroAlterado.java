package br.com.infnet.almoxarifado_service.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAlterado implements Serializable {

    private Long idPedido;
    private String estado;
    private Date momento;

    public RegistroAlterado(long idPedido, String estado) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = new Date();
    }

}
