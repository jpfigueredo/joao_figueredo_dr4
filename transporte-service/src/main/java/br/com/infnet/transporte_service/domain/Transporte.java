package br.com.infnet.transporte_service.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Transporte", catalog = "DR4_1", schema = "PUBLIC")
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String veiculo;
    private boolean disponivel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entrega> entregas = new HashSet<>();

    // Construtores, getters e setters
}
