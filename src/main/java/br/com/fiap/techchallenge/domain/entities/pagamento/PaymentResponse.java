package br.com.fiap.techchallenge.domain.entities.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private String externalOrderId;
    private String internalOrderId;
    private String payer;
    private BigDecimal paymentAmount;
    private LocalDateTime paymentDate;
    private String paymentStatus;

    public PaymentResponse() {
    }

    public PaymentResponse(String externalOrderId, String internalOrderId, String payer, BigDecimal paymentAmount, LocalDateTime paymentDate, String paymentStatus) {
        this.externalOrderId = externalOrderId;
        this.internalOrderId = internalOrderId;
        this.payer = payer;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
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

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
