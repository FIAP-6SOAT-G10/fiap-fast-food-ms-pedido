package br.com.fiap.techchallenge.domain.entities.payment;

import java.math.BigDecimal;

public class PaymentRequest {

    private String externalOrderId;
    private String payer;
    private BigDecimal paymentAmount;

    public PaymentRequest() {
    }

    public PaymentRequest(String externalOrderId, String payer, BigDecimal paymentAmount) {
        this.externalOrderId = externalOrderId;
        this.payer = payer;
        this.paymentAmount = paymentAmount;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
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
}
