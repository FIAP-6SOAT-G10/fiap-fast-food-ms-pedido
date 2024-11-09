package br.com.fiap.techchallenge.domain.entities.order.enums;

public enum OrderStatusEnum {
    RECEIVED("received"),
    IN_PREPARATION("in_preparation"),
    READY("ready"),
    COMPLETED("completed"),
    CANCELED("canceled");

    private final String status;

    private OrderStatusEnum(final String nominalStatus) {
        this.status = nominalStatus;
    }

    public String getStatus() {
        return status;
    }

}
