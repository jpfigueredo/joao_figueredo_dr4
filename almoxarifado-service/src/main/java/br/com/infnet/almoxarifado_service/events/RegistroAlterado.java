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

    private long pedidoID;
    private String status;
    private Date momento;

    public RegistroAlterado(long pedidoID, String status) {
        this.pedidoID = pedidoID;
        this.status = status;
        this.momento = new Date();
    }

}
