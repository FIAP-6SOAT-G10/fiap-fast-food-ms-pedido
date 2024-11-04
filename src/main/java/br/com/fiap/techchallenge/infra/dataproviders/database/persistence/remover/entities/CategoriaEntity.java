package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "categoria")
@SequenceGenerator(name = "categoria_seq" , sequenceName = "categoria_id_seq" , allocationSize = 1)
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 150)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "categoriaEntity")
    private Set<ProdutoEntity> produtoEntities;

    public CategoriaEntity() {

    }

    public CategoriaEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<ProdutoEntity> getProdutoEntities() {
        return produtoEntities;
    }

    public void setProdutoEntities(Set<ProdutoEntity> produtoEntities) {
        this.produtoEntities = produtoEntities;
    }
}
