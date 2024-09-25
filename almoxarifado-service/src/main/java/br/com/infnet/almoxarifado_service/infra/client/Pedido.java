package br.com.infnet.almoxarifado_service.infra.client;

import br.com.infnet.almoxarifado_service.domain.ItemRegistro;
import lombok.Data;

import java.util.List;

@Data
public class Pedido {
    private Long ID;
    private List<ItemRegistro> itemList;
}