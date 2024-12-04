package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.config.MongoDBConfig;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntityRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Import(MongoDBConfig.class)
class OrderRepositoryIT {

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void tearDown() {
        orderEntityRepository.deleteAll();
    }

    @Test
    void shouldSaveAndRetrieveOrder() {
        // Arrange
        Order order = buildOrder();

        // Act
        Order savedOrder = orderRepository.save(order);

        // Assert
        assertNotNull(savedOrder);
        assertEquals(order.getCpf(), savedOrder.getCpf());
        assertEquals(order.getAmount(), savedOrder.getAmount());
        assertEquals(order.getItems().size(), savedOrder.getItems().size());
    }

    @Test
    void shouldFindOrdersByCpf() {
        // Arrange
        Order order1 = buildOrder();
        Order savedOrder = orderRepository.save(order1);

        // Act
        Order findOrder = orderRepository.findById(savedOrder.getId());

        // Assert
        assertNotNull(findOrder);
        assertEquals(findOrder.getCpf(), order1.getCpf());
        assertEquals(findOrder.getAmount(), order1.getAmount());
        assertEquals(findOrder.getItems().size(), order1.getItems().size());
    }

    @Test
    void shouldFindOrdersByPaymentStatus() {
        // Arrange
        String paid = "PAID";
        Order order = buildOrder();
        order.setPaymentStatus(paid);
        Order savedOrder = orderRepository.save(order);

        // Act
        Order findOrder = orderRepository.findById(savedOrder.getId());

        // Assert
        assertNotNull(findOrder);
        assertEquals(findOrder.getCpf(), order.getCpf());
        assertEquals(findOrder.getAmount(), order.getAmount());
        assertEquals(findOrder.getItems().size(), order.getItems().size());
        assertEquals(paid, order.getPaymentStatus());
    }

    @Test
    void shouldFindOrdersByStatus() {
        // Arrange
        String finished = "FINISHED";
        Order order = buildOrder();
        order.setStatus(finished);
        Order savedOrder = orderRepository.save(order);

        // Act
        Order findOrder = orderRepository.findById(savedOrder.getId());

        // Assert
        assertNotNull(findOrder);
        assertEquals(findOrder.getCpf(), order.getCpf());
        assertEquals(findOrder.getAmount(), order.getAmount());
        assertEquals(findOrder.getItems().size(), order.getItems().size());
        assertEquals(finished, order.getStatus());
    }

    @Test
    void shouldListOrders() {
        // Arrange
        orderRepository.save(buildOrder());
        orderRepository.save(buildOrder());
        orderRepository.save(buildOrder());

        // Act
        List<Order> list = orderRepository.list();

        // Assert
        assertNotNull(list);
        assertThat(list).hasSize(3);
    }

    private Order buildOrder() {
        Order order = new Order();
        order.setCpf(RandomStringUtils.random(11, false, true));
        order.setAmount(BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.CEILING));
        order.setItems(List.of(
                new Item(1L, BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.CEILING), 1L),
                new Item(2L, BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.CEILING), 1L)
        ));
        return order;
    }

}
