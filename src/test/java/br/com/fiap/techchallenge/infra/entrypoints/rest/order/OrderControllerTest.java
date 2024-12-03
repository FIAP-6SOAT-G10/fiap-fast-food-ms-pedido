package br.com.fiap.techchallenge.infra.entrypoints.rest.order;

import br.com.fiap.techchallenge.application.usecases.order.CreateOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderResponseDTO;
import br.com.fiap.techchallenge.infra.exception.BaseException;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private GetOrderUseCase getOrderUseCase;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order());
        when(getOrderUseCase.listOrders()).thenReturn(mockOrders);

        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        assertNotNull(response);
        assertEquals(mockOrders, response.getBody());
        verify(getOrderUseCase, times(1)).listOrders();
    }

    @Test
    void testFindByIdSuccess() {
        String orderId = "1";
        Order mockOrder = new Order();
        when(getOrderUseCase.findOrderById(orderId)).thenReturn(mockOrder);

        ResponseEntity<Order> response = orderController.findById(orderId);

        assertNotNull(response);
        assertEquals(mockOrder, response.getBody());
        verify(getOrderUseCase, times(1)).findOrderById(orderId);
    }

    @Test
    void testFindByIdNotFound() {
        String orderId = "1";
        when(getOrderUseCase.findOrderById(orderId)).thenReturn(null);

        ResponseEntity<Order> response = orderController.findById(orderId);

        assertNotNull(response);
        verify(getOrderUseCase, times(1)).findOrderById(orderId);
    }

    @Test
    void testCreateOrderSuccess() throws BaseException {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        Order mockOrder = new Order(); // Mock domain object
        OrderResponseDTO responseDTO = new OrderResponseDTO();

        when(orderMapper.fromDTOToDomain(requestDTO)).thenReturn(mockOrder);
        when(createOrderUseCase.createOrder(mockOrder)).thenReturn(mockOrder);
        when(orderMapper.fromDomainToResponseDTO(mockOrder)).thenReturn(responseDTO);

        ResponseEntity<OrderResponseDTO> response = orderController.createOrder(requestDTO);

        assertNotNull(response);
        assertEquals(responseDTO, response.getBody());
        verify(orderMapper, times(1)).fromDTOToDomain(requestDTO);
        verify(createOrderUseCase, times(1)).createOrder(mockOrder);
        verify(orderMapper, times(1)).fromDomainToResponseDTO(mockOrder);
    }

    @Test
    void testCreateOrderException() throws BaseException {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        Order mockOrder = new Order();

        when(orderMapper.fromDTOToDomain(requestDTO)).thenReturn(mockOrder);
        when(createOrderUseCase.createOrder(mockOrder)).thenThrow(new RuntimeException("Error creating order"));

        ResponseEntity<OrderResponseDTO> response = orderController.createOrder(requestDTO);

        assertNotNull(response);
        verify(orderMapper, times(1)).fromDTOToDomain(requestDTO);
        verify(createOrderUseCase, times(1)).createOrder(mockOrder);
    }

}
