package br.com.infnet.almoxarifado_service.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Preco {
    private double valor;

    public Preco(double valor){
        this.valor = valor;
        if (valor < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
    }
}