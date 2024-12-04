package br.com.fiap.techchallenge.domain.entities.order;

import java.math.BigDecimal;

public class Item {

    private Long itemId;
    private BigDecimal price;
    private Long quantity;

    public Item() {
    }

    public Item(Long itemId, BigDecimal price) {
        this.itemId = itemId;
        this.price = price;
    }

    public Item(Long itemId, Long quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Item(Long itemId, BigDecimal price, Long quantity) {
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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