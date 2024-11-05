package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import io.swagger.v3.oas.annotations.media.Schema;


public class CategoriaDTO {
    @Schema(description = "O nome da categoria que será criado.", example = "Bebida")
    private String nome;

    @Schema(description = "A descrição que será exibida da categoria.", example = "Produtos orgânicos e sem glúten.")
    private String descricao;

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }

    public CategoriaDTO(String nome, String descricao) {
        this(nome);
        this.descricao = descricao;
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
}
