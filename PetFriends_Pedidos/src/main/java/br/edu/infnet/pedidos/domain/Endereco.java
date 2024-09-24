package br.edu.infnet.pedidos.domain;

import lombok.Data;

@Data
public class Endereco {
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;
    private String cep;
}
