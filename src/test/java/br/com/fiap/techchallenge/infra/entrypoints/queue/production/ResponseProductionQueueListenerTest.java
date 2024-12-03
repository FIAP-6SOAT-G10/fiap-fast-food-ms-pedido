package br.com.fiap.techchallenge.infra.entrypoints.queue.production;

import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.model.ProductionResponseDTO;
import br.com.fiap.techchallenge.infra.mapper.ProductionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResponseProductionQueueListenerTest {

    @Mock
    private ProductionMapper productionMapper;

    @Mock
    private UpdateOrderUseCase updateOrderUseCase;

    @InjectMocks
    private ResponseProductionQueueListener responseProductionQueueListener;

    private ProductionResponseDTO productionResponseDTO;

    @BeforeEach
    void setUp() {
        productionResponseDTO = new ProductionResponseDTO();
        productionResponseDTO.setStatus("Completed");
    }

    @Test
    void shouldListenToProductionQueueAndUpdateOrder() {
        // Arrange
        Mockito.when(productionMapper.fromDataTransferObjetToDomain(productionResponseDTO))
                .thenReturn(new ProductionResponse());

        // Act
        responseProductionQueueListener.listen(productionResponseDTO);

        // Assert
        verify(updateOrderUseCase).updateStatus(Mockito.any(ProductionResponse.class));
    }

    @Test
    void shouldLogMessageWhenProductionResponseReceived() {
        // Arrange
        Mockito.when(productionMapper.fromDataTransferObjetToDomain(productionResponseDTO))
                .thenReturn(new ProductionResponse());

        // Act & Assert
        responseProductionQueueListener.listen(productionResponseDTO);
    }
}
