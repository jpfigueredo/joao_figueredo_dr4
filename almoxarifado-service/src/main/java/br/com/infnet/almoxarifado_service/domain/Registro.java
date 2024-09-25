package br.com.infnet.almoxarifado_service.domain;

import br.com.infnet.almoxarifado_service.infra.repository.DescricaoConverter;
import br.com.infnet.almoxarifado_service.infra.repository.RegistroStatusConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
public class Registro implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private long pedidoID;

    @Convert(converter = DescricaoConverter.class)
    private Descricao descricao;

    @Convert(converter = RegistroStatusConverter.class)
    private RegistroStatus status;

    @OneToMany(mappedBy = "registroId", cascade = CascadeType.ALL)
    private List<ItemRegistro> itens;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataConclusao;

    public Registro() {
        this.status = RegistroStatus.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
    }
    public Registro(long pedidoID, String descricao) {
        this.pedidoID = pedidoID;
        this.descricao = new Descricao(descricao);
        this.status = RegistroStatus.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
    }

    public Registro(Long id, long pedidoID, String descricao, List<ItemRegistro> itens) {
        this.id = id;
        this.pedidoID = pedidoID;
        this.descricao = new Descricao(descricao);
        this.status = RegistroStatus.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
        this.itens = itens;
    }

    public void adicionarItem(Long productId, int quantidade) {
        if (this.status != RegistroStatus.PENDENTE) {throw new IllegalStateException("Registro em andamento.");}
        if (this.itens == null) {this.itens = new ArrayList<>();}
        ItemRegistro itemRegistro = new ItemRegistro();
        itemRegistro.setRegistroId(this);
        itemRegistro.setProductId(productId);
        itemRegistro.setQuantity(quantidade);
        this.itens.add(itemRegistro);
    }

    public void concluirRegistro() {
        if (this.status == RegistroStatus.PENDENTE) {
            this.status = RegistroStatus.CONCLUIDO;
            this.dataConclusao = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Registro de viagem já está concluída ou cancelada.");
        }
    }

    public void cancelarRegistro() {
        if (this.status == RegistroStatus.PENDENTE) {
            this.status = RegistroStatus.CANCELADO;
        } else {
            throw new IllegalStateException("Ordem de serviço já está concluída ou cancelada.");
        }
    }
}
