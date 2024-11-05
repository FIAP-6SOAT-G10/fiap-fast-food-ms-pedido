package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemPedidoDTO {

    private Long id;
    private Long quantidade;

    public ItemPedidoDTO() { }

    public ItemPedidoDTO(Long id, Long quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
