package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.payment.PaymentResponse;
import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.model.PaymentResponseDTO;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.model.ProductionResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentMapperTest {

    private final PaymentMapper paymentMapper = new PaymentMapper();

    @Test
    void shouldConvertDTOToDomain() {
        // Arrange
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setExternalOrderId("123");
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
