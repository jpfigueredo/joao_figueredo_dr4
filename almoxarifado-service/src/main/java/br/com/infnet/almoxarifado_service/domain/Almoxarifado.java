package br.com.infnet.almoxarifado_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Almoxarifado", catalog = "DR4_1", schema = "PUBLIC")
public class Almoxarifado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Produto> itensEstoque = new HashSet<>();

    // Construtores, getters e setters
}
