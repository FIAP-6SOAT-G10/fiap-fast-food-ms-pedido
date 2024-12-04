package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.exception.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.com.fiap.techchallenge.domain.ErrosEnum.PEDIDO_SEM_CLIENTE_E_ITEMS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateOrderUseCaseTest {

    @Mock
    private IOrderRepository orderRepository;
    @Mock
    private IItemRepository itemRepository;
    @Mock
    private ICustomerRepository customerRepository;
    @Mock
    private INotificationRepository notificationRepository;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrderWithValidOrder() {
        // Arrange
        Order order = new Order();
        order.setCpf("12345678901");

        Item item1 = new Item();
        item1.setItemId(1L);
        item1.setQuantity(2L);

        Item item2 = new Item();
        item2.setItemId(2L);
        item2.setQuantity(1L);

        order.setItems(Arrays.asList(item1, item2));

        when(customerRepository.findByCpf("12345678901")).thenReturn(new Customer()); // Mock customer
        when(itemRepository.findById(1L)).thenReturn(new Item(1L, BigDecimal.valueOf(10)));
        when(itemRepository.findById(2L)).thenReturn(new Item(2L, BigDecimal.valueOf(20)));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        Order createdOrder = createOrderUseCase.createOrder(order);

        // Assert
        assertNotNull(createdOrder);
        assertEquals(BigDecimal.valueOf(40), createdOrder.getAmount()); // 2*10 + 1*20
        verify(notificationRepository, times(1)).sendNotification(createdOrder);
    }

    @Test
    void testCreateOrderWithoutItemsThrowsException() {
        // Arrange
        Order order = new Order();

        // Act & Assert
        OrderException exception = assertThrows(OrderException.class, () -> createOrderUseCase.createOrder(order));
        assertEquals(PEDIDO_SEM_CLIENTE_E_ITEMS, exception.getError());
    }

    @Test
    void testCreateOrderWithoutCpf() {
        // Arrange
        Order order = new Order();

        Item item1 = new Item();
        item1.setItemId(1L);
        item1.setQuantity(2L);

        order.setItems(Collections.singletonList(item1));

        when(itemRepository.findById(1L)).thenReturn(new Item(1L, BigDecimal.valueOf(10)));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        Order createdOrder = createOrderUseCase.createOrder(order);

        // Assert
        assertNotNull(createdOrder);
        assertEquals(BigDecimal.valueOf(20), createdOrder.getAmount());
        verify(notificationRepository, times(1)).sendNotification(createdOrder);
        verify(customerRepository, never()).findByCpf(anyString());
    }

    @Test
    void testCalculateTotalAmount() {
        // Arrange
        Item item1 = new Item();
        item1.setPrice(BigDecimal.valueOf(10));
        item1.setQuantity(2L);

        Item item2 = new Item();
        item2.setPrice(BigDecimal.valueOf(15));
        item2.setQuantity(3L);

        List<Item> items = Arrays.asList(item1, item2);

        // Act
        BigDecimal totalAmount = createOrderUseCase.calculateTotalAmount(items);

        // Assert
        assertEquals(BigDecimal.valueOf(65), totalAmount);
    }

}
