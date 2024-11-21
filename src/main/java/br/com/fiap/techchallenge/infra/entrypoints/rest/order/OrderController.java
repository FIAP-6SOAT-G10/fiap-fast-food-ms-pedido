package br.com.fiap.techchallenge.infra.entrypoints.rest.order;

import br.com.fiap.techchallenge.application.usecases.order.CreateOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderResponseDTO;
import br.com.fiap.techchallenge.infra.exception.BaseException;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
@Tag(name = "Pedidos", description = "Conjunto de operações que podem ser realizadas no contexto de pedidos.")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final OrderMapper orderMapper;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, OrderMapper orderMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.orderMapper = orderMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("Buscando todos os pedidos.");
        return ResponseEntity.ok(getOrderUseCase.listOrders());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> findById(@PathVariable("id") String id) {
        log.info("Buscando pedido por id.");
        Order order = getOrderUseCase.findOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping(path = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> listByStatus(@PathVariable("status") String status) {
        log.info("Buscando pedidos por status.");
        return ResponseEntity.ok(getOrderUseCase.findByStatus(status));
    }

    @GetMapping(path = "/payment-status/{paymentStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> listByPaymentStatus(@PathVariable("paymentStatus") String paymentStatus) {
        log.info("Buscando pedidos por status de pagamento.");
        return ResponseEntity.ok(getOrderUseCase.findByPaymentStatus(paymentStatus));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) throws BaseException {
        log.info("Criando um pedido.");
        try {
            Order order = createOrderUseCase.createOrder(orderMapper.fromDTOToDomain(orderRequestDTO));
            return ResponseEntity.status(HttpStatus.OK).body(orderMapper.fromDomainToResponseDTO(order));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
