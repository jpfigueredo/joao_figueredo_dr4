package br.com.infnet.almoxarifado_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@AllArgsConstructor
public class ItemRegistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Long productId;

    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "registroID", referencedColumnName = "ID")
    private Registro registroId;

    public ItemRegistro() {}
}
