package br.com.infnet.transporte_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Entrega", catalog = "DR4_1", schema = "PUBLIC")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime dataEntrega;
    private Long transporteId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "endereco", column = @Column(name = "entrega_endereco"))
    })
    private Endereco endereco;

    // Construtores, getters e setters
}