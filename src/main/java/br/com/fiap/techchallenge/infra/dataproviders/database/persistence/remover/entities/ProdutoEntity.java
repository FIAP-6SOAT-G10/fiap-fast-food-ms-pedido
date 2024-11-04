package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "produto_seq" , sequenceName = "produto_id_seq" , allocationSize = 1)
public class ProdutoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 150)
    private String descricao;

    @ManyToOne
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty("categoria")
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoriaEntity;

    @Column(name = "preco", nullable = false, precision = 3, scale = 2)
    private BigDecimal preco;

    @Column(name = "imagem", nullable = false, length = 500)
    private String imagem;

    @JsonIgnore
    @JsonManagedReference("produtoEntity.pedidos")
    @OneToMany(mappedBy = "produtoEntity")
    private List<ProdutoPedidoEntity> pedidos;

    public ProdutoEntity() {}

    public ProdutoEntity(Long id, String nome, String descricao, CategoriaEntity categoriaEntity, BigDecimal preco, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaEntity = categoriaEntity;
        this.preco = preco;
        this.imagem = imagem;
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

    public CategoriaEntity getCategoriaEntity() {
        return categoriaEntity;
    }

    public void setCategoriaEntity(CategoriaEntity categoriaEntity) {
        this.categoriaEntity = categoriaEntity;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<ProdutoPedidoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ProdutoPedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }
}