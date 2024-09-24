package br.com.infnet.almoxarifado_service.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Produto", catalog = "DR4_1", schema = "PUBLIC")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int quantidade;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "valor", column = @Column(name = "preco_valor"))
    })
    private Preco preco;

    // Construtores, getters e setters
}