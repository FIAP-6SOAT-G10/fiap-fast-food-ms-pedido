package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.CustomerEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationMapperTest {

    private final NotificationMapper notificationMapper = new NotificationMapper();

    @Test
    void shouldConvertDomainToDataTransferObjet() {
        // Arrange
        Order order = new Order("123", "456", "COMPLETED", BigDecimal.valueOf(200), LocalDateTime.now(), null, null, "PAID", List.of(new Item(), new Item()));
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
