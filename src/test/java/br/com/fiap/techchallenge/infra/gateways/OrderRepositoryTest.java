package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntityRepository;
import br.com.fiap.techchallenge.infra.exception.OrderException;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderRepositoryTest {

    @Mock
    private OrderEntityRepository orderEntityRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdSuccess() {
        // Arrange
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();
        when(orderEntityRepository.findById("1")).thenReturn(Optional.of(orderEntity));
        when(orderMapper.fromEntityToDomain(orderEntity)).thenReturn(order);

        // Act
        Order result = orderRepository.findById("1");

        // Assert
        assertNotNull(result);
        assertEquals(order, result);
        verify(orderEntityRepository, times(1)).findById("1");
        verify(orderMapper, times(1)).fromEntityToDomain(orderEntity);
    }

    @Test
    void testFindByIdThrowsOrderException() {
        // Arrange
        when(orderEntityRepository.findById("1")).thenReturn(Optional.empty());

        // Act
        OrderException exception = assertThrows(OrderException.class, () -> orderRepository.findById("1"));
        assertEquals(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO, exception.getError());

        // Assert
        verify(orderEntityRepository, times(1)).findById("1");
        verify(orderMapper, never()).fromEntityToDomain(any(OrderEntity.class));
    }

    @Test
    void testListOrders() {
        // Arrange
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();
        List<OrderEntity> orderEntities = List.of(orderEntity);
        List<Order> orders = List.of(order);
        when(orderEntityRepository.findAllByOrderByCreationDateDesc()).thenReturn(orderEntities);
        when(orderMapper.fromListEntityToListDTO(orderEntities)).thenReturn(orders);

        // Act
        List<Order> result = orderRepository.list();

        // Assert
        assertNotNull(result);
        assertEquals(orders, result);
        verify(orderEntityRepository, times(1)).findAllByOrderByCreationDateDesc();
        verify(orderMapper, times(1)).fromListEntityToListDTO(orderEntities);
    }

    @Test
    void testSaveOrder() {
        // Arrange
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();
        when(orderMapper.fromDomainToEntity(order)).thenReturn(orderEntity);
        when(orderEntityRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderMapper.fromEntityToDomain(orderEntity)).thenReturn(order);

        // Act
        Order result = orderRepository.save(order);

        // Assert
        assertNotNull(result);
        assertEquals(order, result);
        verify(orderMapper, times(1)).fromDomainToEntity(order);
        verify(orderEntityRepository, times(1)).save(orderEntity);
        verify(orderMapper, times(1)).fromEntityToDomain(orderEntity);
    }
}
