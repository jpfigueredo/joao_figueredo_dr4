package br.com.infnet.transporte_service.domain;

import br.com.infnet.transporte_service.infra.repository.EnderecoConverter;
import br.com.infnet.transporte_service.infra.repository.StatusEntregaConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Entrega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Long pedidoId;
    @Convert(converter = EnderecoConverter.class)
    private Endereco endereco;
    @Convert(converter = StatusEntregaConverter.class)
    private Status status;
    @JoinColumn(name = "manifesto_id", referencedColumnName = "id")
    @ManyToOne
    private ManifestoDeCarga manifestoId;

    public Entrega() {this.status = Status.EM_ANDAMENTO;}

    public Entrega(Long id, Long pedidoId, Endereco endereco, ManifestoDeCarga manifestoId) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.endereco = endereco;
        this.manifestoId = manifestoId;
        this.status = Status.EM_ANDAMENTO;
    }

    public void concluirEntrega() {
        if (this.status == Status.EM_ANDAMENTO) {
            this.status = Status.CONCLUIDO;
        } else {
            throw new IllegalStateException("Entrega já está concluído ou cancelado.");
        }
    }

    public void cancelarManifesto() {
        if (this.status == Status.EM_ANDAMENTO) {
            this.status = Status.CANCELADO;
        } else {
            throw new IllegalStateException("Manifesto de transporte já está concluído ou cancelado.");
        }
    }
}
