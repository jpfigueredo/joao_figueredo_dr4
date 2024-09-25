package br.com.infnet.transporte_service.domain;

import br.com.infnet.transporte_service.infra.repository.EnderecoConverter;
import br.com.infnet.transporte_service.infra.repository.RegistroStatusConverter;
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

    private long pedidoID;

    @Convert(converter = EnderecoConverter.class)
    private Endereco endereco;

    @Convert(converter = RegistroStatusConverter.class)
    private Status status;

    @JoinColumn(name = "manifesto_id", referencedColumnName = "id")
    @ManyToOne
    private ManifestoDeCarga manifestoId;

    public Entrega() {this.status = Status.EM_ANDAMENTO;}

    public Entrega(Long id, long pedidoID, Endereco endereco, ManifestoDeCarga manifestoId) {
        this.id = id;
        this.pedidoID = pedidoID;
        this.endereco = endereco;
        this.manifestoId = manifestoId;
        this.status = Status.EM_ANDAMENTO;
    }

    public void encerrarEntrega() {
        if (this.status == Status.EM_ANDAMENTO) {
            this.status = Status.CONCLUIDO;
        } else {
            throw new IllegalStateException("Entrega já foi fechada ou cancelada.");
        }
    }

    public void cancelarManifesto() {
        if (this.status == Status.EM_ANDAMENTO) {
            this.status = Status.CANCELADO;
        } else {
            throw new IllegalStateException("Manifesto de Transporte já foi fechado ou cancelado.");
        }
    }
}
