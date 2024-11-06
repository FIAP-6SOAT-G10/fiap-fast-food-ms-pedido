package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "pedido")
public class OrderEntity {

    @Id
    private String id;

    @Field("cpf")
    private String cpf;

    @Field("status")
    private String status;

    @Field("amount")
    private BigDecimal amount;

    @Field("creationDate")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Field("completionDate")
    private LocalDateTime completionDate;

    @Field("cancellationDate")
    private LocalDateTime cancellationDate;

    @Field("paymentStatus")
    private String paymentStatus;

    @Field("product_ids")
    private List<Long> productIds;

    public OrderEntity() {
    }

    public OrderEntity(String id, String cpf, String status, BigDecimal amount, LocalDateTime creationDate, LocalDateTime completionDate, LocalDateTime cancellationDate, String paymentStatus, List<Long> productIds) {
        this.id = id;
        this.cpf = cpf;
        this.status = status;
        this.amount = amount;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.cancellationDate = cancellationDate;
        this.paymentStatus = paymentStatus;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDateTime cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

}
