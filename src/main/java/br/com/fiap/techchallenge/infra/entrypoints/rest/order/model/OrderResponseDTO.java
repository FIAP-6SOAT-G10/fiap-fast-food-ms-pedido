package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDTO {

    private String id;
    private String cpf;
    private String status;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private LocalDateTime cancellationDate;
    private String paymentStatus;
    private List<ItemResponseDTO> items;

    public OrderResponseDTO() {

    }

    public OrderResponseDTO(String id, String cpf, String status, BigDecimal amount, LocalDateTime creationDate, LocalDateTime completionDate, LocalDateTime cancellationDate, String paymentStatus, List<ItemResponseDTO> items) {
        this.id = id;
        this.cpf = cpf;
        this.status = status;
        this.amount = amount;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.cancellationDate = cancellationDate;
        this.paymentStatus = paymentStatus;
        this.items = items;
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

    public List<ItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemResponseDTO> items) {
        this.items = items;
    }
}
