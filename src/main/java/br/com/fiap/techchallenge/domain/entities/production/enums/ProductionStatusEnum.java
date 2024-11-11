package br.com.fiap.techchallenge.domain.entities.production.enums;

public enum ProductionStatusEnum {
    RECEIVED("received"),
    IN_PREPARATION("in_preparation"),
    READY("ready"),
    COMPLETED("completed"),
    CANCELED("canceled");

    private final String nominalStatus;

    private ProductionStatusEnum(final String nominalStatus) {
        this.nominalStatus = nominalStatus;
    }

    public String getNominalStatusStatus() {
        return nominalStatus;
    }

    public static ProductionStatusEnum fromString(String status) {
        for (ProductionStatusEnum productionStatusEnum : ProductionStatusEnum.values()) {
            if (productionStatusEnum.nominalStatus.equalsIgnoreCase(status)) {
                return productionStatusEnum;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }

}
