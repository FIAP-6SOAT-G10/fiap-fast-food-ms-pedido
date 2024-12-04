package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;


import java.math.BigDecimal;

public class ItemResponseDTO {

    private Long id;
    private BigDecimal price;
    private Long quantity;

    public ItemResponseDTO(Long id, BigDecimal price, Long quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}