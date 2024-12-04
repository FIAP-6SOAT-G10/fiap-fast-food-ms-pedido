package br.com.fiap.techchallenge.domain.entities.production;

import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;

import java.time.LocalDateTime;

public class ProductionResponse {

    private String externalOrderId;
    private String internalOrderId;
    private LocalDateTime date;
    private ProductionStatusEnum status;

    public ProductionResponse() {
    }

    public ProductionResponse(String externalOrderId, String internalOrderId, LocalDateTime date, ProductionStatusEnum status) {
        this.externalOrderId = externalOrderId;
        this.internalOrderId = internalOrderId;
        this.date = date;
        this.status = status;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public String getInternalOrderId() {
        return internalOrderId;
    }

    public void setInternalOrderId(String internalOrderId) {
        this.internalOrderId = internalOrderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ProductionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductionStatusEnum status) {
        this.status = status;
    }
}
