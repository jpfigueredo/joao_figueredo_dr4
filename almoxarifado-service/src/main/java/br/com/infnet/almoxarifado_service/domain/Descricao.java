package br.com.infnet.almoxarifado_service.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
public class Descricao {
    private final String valor;

    public Descricao(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new RuntimeException("A descrição não pode ser nula ou vazia.");
        }
        this.valor = valor;
    }
}
