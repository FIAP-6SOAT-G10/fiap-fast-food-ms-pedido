package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.model.ProductionResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductionMapperTest {

    private final ProductionMapper productionMapper = new ProductionMapper();

    @Test
    void shouldConvertDTOToDomain() {
        // Arrange
        ProductionResponseDTO productionResponseDTO = new ProductionResponseDTO();
        productionResponseDTO.setExternalOrderId("123");
        productionResponseDTO.setInternalOrderId("456");
        productionResponseDTO.setDate(LocalDateTime.now());
        productionResponseDTO.setStatus("RECEIVED");

        // Act
        ProductionResponse productionResponse = productionMapper.fromDataTransferObjetToDomain(productionResponseDTO);

        // Assert
        assertThat(productionResponse).isNotNull();
        assertEquals("123", productionResponse.getExternalOrderId());
        assertEquals("456", productionResponse.getInternalOrderId());
        assertEquals(ProductionStatusEnum.RECEIVED, productionResponse.getStatus());
    }
}
