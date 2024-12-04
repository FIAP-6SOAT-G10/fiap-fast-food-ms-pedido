package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.model.ProductionResponseDTO;

public class ProductionMapper {

    public ProductionResponse fromDataTransferObjetToDomain(ProductionResponseDTO productionResponseDTO) {
        return new ProductionResponse(
                productionResponseDTO.getExternalOrderId(),
                productionResponseDTO.getInternalOrderId(),
                productionResponseDTO.getDate(),
                ProductionStatusEnum.fromString(productionResponseDTO.getStatus()));
    }

}
