package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentResponse;
import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateOrderUseCaseTest {

    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private UpdateOrderUseCase updateOrderUseCase;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId("order123");
    }

    @Test
    void testUpdatePaymentStatus() {
        PaymentResponse paymentResponse = new PaymentResponse(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "teste@teste.com",
                BigDecimal.ONE,
                LocalDateTime.now(),
                "PAID");

        when(orderRepository.findById(paymentResponse.getExternalOrderId())).thenReturn(order);

        updateOrderUseCase.updatePaymentStatus(paymentResponse);

        assertEquals("PAID", order.getPaymentStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void testUpdateStatusToReceived() {
        ProductionResponse productionResponse = new ProductionResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString(), LocalDateTime.now(), ProductionStatusEnum.PENDING);

        when(orderRepository.findById(productionResponse.getExternalOrderId())).thenReturn(order);

        updateOrderUseCase.updateStatus(productionResponse);

        assertEquals(ProductionStatusEnum.PENDING.getNominalStatusStatus(), order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void testUpdateStatusToInPreparation() {
        ProductionResponse productionResponse = new ProductionResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString(), LocalDateTime.now(), ProductionStatusEnum.IN_PROGRESS);

        when(orderRepository.findById(productionResponse.getExternalOrderId())).thenReturn(order);

        updateOrderUseCase.updateStatus(productionResponse);

        assertEquals(ProductionStatusEnum.IN_PROGRESS.getNominalStatusStatus(), order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void testUpdateStatusToCompleted() {
        ProductionResponse productionResponse = new ProductionResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString(), LocalDateTime.now(), ProductionStatusEnum.COMPLETED);

        when(orderRepository.findById(productionResponse.getExternalOrderId())).thenReturn(order);

        updateOrderUseCase.updateStatus(productionResponse);

        assertEquals(ProductionStatusEnum.COMPLETED.getNominalStatusStatus(), order.getStatus());
        assertEquals(productionResponse.getDate(), order.getCompletionDate());
        verify(orderRepository).save(order);
    }

    @Test
    void testUpdateStatusToCanceled() {
        ProductionResponse productionResponse = new ProductionResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString(), LocalDateTime.now(), ProductionStatusEnum.FAILED);

        when(orderRepository.findById(productionResponse.getExternalOrderId())).thenReturn(order);

        updateOrderUseCase.updateStatus(productionResponse);

        assertEquals(ProductionStatusEnum.FAILED.getNominalStatusStatus(), order.getStatus());
        assertEquals(productionResponse.getDate(), order.getCancellationDate());
        verify(orderRepository).save(order);
    }
}
