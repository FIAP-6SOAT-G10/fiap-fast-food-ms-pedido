package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.pagamento.PaymentRequest;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.ItemEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {

    private final OrderMapper orderMapper = new OrderMapper();

    private static final String CREATED = "CREATED";
    private static final String PAID = "PAID";
    private static final String COMPLETED = "COMPLETED";

    @Test
    void shouldConvertEntityToDomain() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId("123");
        orderEntity.setCpf("11111111111");
        orderEntity.setStatus(CREATED);
        orderEntity.setAmount(BigDecimal.valueOf(100));
        orderEntity.setCreationDate(now);
        orderEntity.setItems(List.of(new ItemEntity(), new ItemEntity()));

        // Act
        Order order = orderMapper.fromEntityToDomain(orderEntity);

        // Assert
        assertThat(order).isNotNull();
        assertEquals("123", order.getId());
        assertEquals("11111111111", order.getCpf());
        assertEquals(CREATED, order.getStatus());
        assertEquals(BigDecimal.valueOf(100), order.getAmount());
        assertEquals(now, order.getCreationDate());
        assertThat(order.getItems()).hasSize(2);
    }

    @Test
    void shouldConvertDTOToDomain() {
        // Arrange
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setCpf("123");
        orderRequestDTO.setItems(List.of(new ItemRequestDTO(), new ItemRequestDTO()));

        // Act
        Order order = orderMapper.fromDTOToDomain(orderRequestDTO);

        // Assert
        assertThat(order).isNotNull();
        assertEquals("123", order.getCpf());
        assertThat(order.getItems()).hasSize(2);
    }

    @Test
    void shouldConvertDomainToEntity() {
        // Arrange
        Order order = new Order();
        order.setId("123");
        order.setCpf("456");
        order.setStatus(COMPLETED);
        order.setAmount(BigDecimal.valueOf(200));
        order.setCreationDate(LocalDateTime.now());
        order.setCompletionDate(null);
        order.setCancellationDate(null);
        order.setPaymentStatus(PAID);
        order.setItems(List.of(new Item(), new Item()));

        // Act
        OrderEntity orderEntity = orderMapper.fromDomainToEntity(order);

        // Assert
        assertThat(orderEntity).isNotNull();
        assertEquals("123", orderEntity.getId());
        assertEquals("456", orderEntity.getCpf());
        assertEquals(COMPLETED, orderEntity.getStatus());
        assertEquals(PAID, orderEntity.getPaymentStatus());
        assertEquals(BigDecimal.valueOf(200), orderEntity.getAmount());
        assertThat(order.getItems()).hasSize(2);
    }

    @Test
    void shouldConvertDomainToResponseDTO() {
        // Arrange
        Order order = new Order();
        order.setId("123");
        order.setCpf("456");
        order.setStatus(COMPLETED);
        order.setAmount(BigDecimal.valueOf(200));
        order.setCreationDate(LocalDateTime.now());
        order.setCompletionDate(null);
        order.setCancellationDate(null);
        order.setPaymentStatus(PAID);
        order.setItems(List.of(new Item(), new Item()));

        // Act
        OrderResponseDTO responseDTO = orderMapper.fromDomainToResponseDTO(order);

        // Assert
        assertThat(responseDTO).isNotNull();
        assertEquals("123", responseDTO.getId());
        assertEquals("456", responseDTO.getCpf());
        assertEquals(COMPLETED, responseDTO.getStatus());
        assertEquals(PAID, responseDTO.getPaymentStatus());
        assertEquals(BigDecimal.valueOf(200), responseDTO.getAmount());
        assertThat(order.getItems()).hasSize(2);
    }

    @Test
    void shouldConvertDomainToPaymentRequest() {
        // Arrange
        Order order = new Order();
        order.setId("123");
        order.setCpf("456");
        order.setStatus(COMPLETED);
        order.setAmount(BigDecimal.valueOf(200));
        order.setCreationDate(LocalDateTime.now());
        order.setCompletionDate(null);
        order.setCancellationDate(null);
        order.setPaymentStatus(PAID);
        order.setItems(List.of(new Item(), new Item()));

        // Act
        PaymentRequest paymentRequest = orderMapper.fromDomainToPaymentRequest(order);

        // Assert
        assertThat(paymentRequest).isNotNull();
        assertEquals("123", paymentRequest.getExternalOrderId());
        assertEquals(BigDecimal.valueOf(200), paymentRequest.getPaymentAmount());
    }
}
