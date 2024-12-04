package br.com.fiap.techchallenge.domain.entities.production.enums;

public enum ProductionStatusEnum {
    PENDING("Pending"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    FAILED("Failed");

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
