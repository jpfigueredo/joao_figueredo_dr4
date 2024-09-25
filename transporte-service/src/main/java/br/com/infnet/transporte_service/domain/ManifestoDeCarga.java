package br.com.infnet.transporte_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.infnet.transporte_service.infra.repository.EnderecoConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ManifestoDeCarga implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDate saida;

    private String motorista;

    private String placa;

    @Convert(converter = EnderecoConverter.class)
    private Endereco origem;

    @Convert(converter = EnderecoConverter.class)
    private Endereco destinoFinal;

    @JsonIgnoreProperties(value = "manifestoId")
    @OneToMany(mappedBy = "manifestoId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Entrega> entregas;

    private boolean emEdicao;

    public ManifestoDeCarga() {
        this.saida = LocalDate.now();
        this.emEdicao = true;
    }

    public ManifestoDeCarga(String motorista, String placa, Endereco origem, Endereco destinoFinal) {
        this.motorista = motorista;
        this.placa = placa;
        this.origem = origem;
        this.destinoFinal = destinoFinal;
        this.saida = LocalDate.now();
        this.emEdicao = true;
    }

    public ManifestoDeCarga(Long id, String motorista, String placa, Endereco origem, Endereco destinoFinal, List<Entrega> entregas) {
        this.id = id;
        this.motorista = motorista;
        this.placa = placa;
        this.origem = origem;
        this.destinoFinal = destinoFinal;
        this.entregas = entregas;
        this.saida = LocalDate.now();
        this.emEdicao = true;
    }

    public void addEntrega(Long idPedido, Endereco destino) {
        if (idPedido == null) {
            throw new IllegalArgumentException("Pedido inválido");
        }
        if (destino == null) {
            throw new IllegalArgumentException("Endereço inválido");
        }

        Entrega entrega = new Entrega();
        entrega.setPedidoID(idPedido);
        entrega.setEndereco(destino);
        entrega.setManifestoId(this);
        if (this.entregas == null) {
            this.entregas = new ArrayList<>();
        }
        this.entregas.add(entrega);
    }
}
