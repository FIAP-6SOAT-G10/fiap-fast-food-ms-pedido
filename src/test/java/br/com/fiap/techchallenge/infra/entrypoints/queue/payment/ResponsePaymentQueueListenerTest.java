package br.com.fiap.techchallenge.infra.entrypoints.queue.payment;

import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentResponse;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.model.PaymentResponseDTO;
import br.com.fiap.techchallenge.infra.mapper.PaymentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

class ResponsePaymentQueueListenerTest {

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private UpdateOrderUseCase updateOrderUseCase;

    @InjectMocks
    private ResponsePaymentQueueListener responsePaymentQueueListener;

    private PaymentResponseDTO paymentResponseDTO;

    @BeforeEach
    void setUp() {
        paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setPaymentStatus("PAID");
        paymentResponseDTO.setPaymentDate(LocalDateTime.now());
    }

    @Test
    void shouldListenToPaymentQueueAndUpdateOrder() {
        // Arrange
        Mockito.when(paymentMapper.fromDataTransferObjetToDomain(paymentResponseDTO))
                .thenReturn(new PaymentResponse());

        // Act
        responsePaymentQueueListener.listen(paymentResponseDTO);

        // Assert
        verify(updateOrderUseCase).updatePaymentStatus(Mockito.any(PaymentResponse.class));
    }
}
