package br.com.fiap.techchallenge.domain.entities.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String id;
    private String cpf;
    private String status;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private LocalDateTime cancellationDate;
    private String paymentStatus;
    private Customer customer;
    private List<Item> items;

    public Order() {
    }

    public Order(String cpf) {
        this.cpf = cpf;
    }

    public Order(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("Items é um campo obrigatório no cadastro de novos pedidos.");
        }
        this.items = items;
        this.amount = BigDecimal.ZERO;
    }

    public Order(BigDecimal amount) {
        this.amount = amount;
    }

    public Order(String cpf, BigDecimal amount) {
        this.cpf = cpf;
        this.amount = amount;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}