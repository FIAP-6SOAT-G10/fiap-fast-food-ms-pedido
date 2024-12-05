package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.pagamento.PaymentResponse;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.model.PaymentResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentMapperTest {

    private final PaymentMapper paymentMapper = new PaymentMapper();

    @Test
    void shouldConvertDTOToDomain() {
        // Arrange
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setExternalId("123");
        paymentResponseDTO.setInternalOrderId("456");
        paymentResponseDTO.setPayer("teste@teste.com");
        paymentResponseDTO.setPaymentAmount(BigDecimal.valueOf(100));
        paymentResponseDTO.setPaymentDate(LocalDateTime.now());
        paymentResponseDTO.setPaymentStatus("PAID");

        // Act
        PaymentResponse paymentResponse = paymentMapper.fromDataTransferObjetToDomain(paymentResponseDTO);

        // Assert
        assertThat(paymentResponse).isNotNull();
        assertEquals("123", paymentResponse.getExternalOrderId());
        assertEquals("456", paymentResponse.getInternalOrderId());
        assertEquals("teste@teste.com", paymentResponse.getPayer());
        assertEquals(BigDecimal.valueOf(100), paymentResponse.getPaymentAmount());
        assertEquals("PAID", paymentResponse.getPaymentStatus());
    }
}
