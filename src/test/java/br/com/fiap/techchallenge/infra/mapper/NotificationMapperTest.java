package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.pagamento.PaymentRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationMapperTest {

    private final NotificationMapper notificationMapper = new NotificationMapper();

    @Test
    void shouldConvertDomainToDataTransferObjet() {
        // Arrange
        Order order = new Order();
        order.setId("123");
        order.setCpf("456");
        order.setStatus("COMPLETED");
        order.setAmount(BigDecimal.valueOf(200));
        order.setCreationDate(LocalDateTime.now());
        order.setCompletionDate(null);
        order.setCancellationDate(null);
        order.setPaymentStatus("PAID");
        order.setItems(List.of(new Item(), new Item()));
        order.setCustomer(new Customer(1L, "Teste", "11111111111", "teste@teste.com"));

        // Act
        PaymentRequest paymentRequest = notificationMapper.fromDomainToDataTransferObjet(order);

        // Assert
        assertThat(paymentRequest).isNotNull();
        assertEquals("123", paymentRequest.getExternalOrderId());
        assertEquals("teste@teste.com", paymentRequest.getPayer());
        assertEquals(BigDecimal.valueOf(200), paymentRequest.getPaymentAmount());
    }
}
