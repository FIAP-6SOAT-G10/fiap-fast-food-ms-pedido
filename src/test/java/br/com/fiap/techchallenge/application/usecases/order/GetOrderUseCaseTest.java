package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetOrderUseCaseTest {

    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private GetOrderUseCase getOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listOrdersReturnsListOfOrders() {
        // Arrange
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> expectedOrders = Arrays.asList(order1, order2);

        when(orderRepository.list()).thenReturn(expectedOrders);

        // Act
        List<Order> actualOrders = getOrderUseCase.listOrders();

        // Assert
        assertEquals(expectedOrders, actualOrders);
        verify(orderRepository, times(1)).list();
    }

    @Test
    void findOrderByIdReturnsOrderWhenExists() {
        // Arrange
        String orderId = "123";
        Order expectedOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(expectedOrder);

        // Act
        Order actualOrder = getOrderUseCase.findOrderById(orderId);

        // Assert
        assertEquals(expectedOrder, actualOrder);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void findOrderByIdReturnsNullWhenOrderDoesNotExist() {
        // Arrange
        String orderId = "nonexistent-id";

        when(orderRepository.findById(orderId)).thenReturn(null);

        // Act
        Order actualOrder = getOrderUseCase.findOrderById(orderId);

        // Assert
        assertEquals(null, actualOrder);
        verify(orderRepository, times(1)).findById(orderId);
    }
}
