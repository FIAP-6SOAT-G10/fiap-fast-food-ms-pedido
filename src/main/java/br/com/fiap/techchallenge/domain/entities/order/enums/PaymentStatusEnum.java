package br.com.fiap.techchallenge.domain.entities.order.enums;

public enum PaymentStatusEnum {
    WAITING("waiting"),
    WAITING_AUTHORIZATION("waiting_authorization"),
    PAID("paid"),
    DENIED("denied");

    private final String nominalStatus;

    private PaymentStatusEnum(final String nominalStatus) {
        this.nominalStatus = nominalStatus;
    }

    public String getNominalStatus() {
        return nominalStatus;
    }

    public static PaymentStatusEnum fromString(String status) {
        for (PaymentStatusEnum paymentStatus : PaymentStatusEnum.values()) {
            if (paymentStatus.nominalStatus.equalsIgnoreCase(status)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }
}
