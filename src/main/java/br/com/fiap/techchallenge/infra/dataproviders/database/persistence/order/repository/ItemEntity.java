package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

public class ItemEntity {

    @Field("item_id")
    private Long itemId;

    @Field("price")
    private BigDecimal price;

    @Field("quantity")
    private Long quantity;

    public ItemEntity() { }

    public ItemEntity(Long itemId, Long quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public ItemEntity(Long itemId, BigDecimal price, Long quantity) {
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




