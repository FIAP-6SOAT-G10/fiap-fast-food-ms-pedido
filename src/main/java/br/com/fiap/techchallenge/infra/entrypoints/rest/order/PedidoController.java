package br.com.fiap.techchallenge.infra.entrypoints.rest.order;

import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.CreateOrderUseCase;
import br.com.fiap.techchallenge.domain.entities.pedido.Item;
import br.com.fiap.techchallenge.domain.entities.pedido.Order;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderDTO;
import br.com.fiap.techchallenge.infra.exception.BaseException;
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
@Tag(name = "Pedidos", description = "Conjunto de operações que podem ser realizadas no contexto de pedidos.")
@RequestMapping("/orders")
public class PedidoController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    public PedidoController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, UpdateOrderUseCase updateOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("Buscando todos os pedidos.");
        return ResponseEntity.ok(getOrderUseCase.listarPedidos());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderDTO orderDTO) throws BaseException {
        log.info("Criando um pedido.");
//        if (orderDTO.getCpf().isEmpty() && orderDTO.getItems() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        Order pedido = null;
//        if (orderDTO.getCpf().isEmpty()) {
//            pedido = new Order(new Item(orderDTO.getItems()));
//        } else {
//            Item item = new Item(orderDTO.getItems());
//            pedido = new Order(item);
//        }
        Order order = new Order();
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(orderDTO.getStatus());
        order.setCpf(order.getCpf());
        order.setProdutoPedidos();

        return ResponseEntity.status(HttpStatus.OK).body(createOrderUseCase.criarPedido(order));
    }

//    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Pedido> findById(@PathVariable("id") String id) {
//        log.info("Buscando pedido por id.");
//        Pedido order = getOrderUseCase.buscarPedidoPorId(id);
//        if (order == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(order);
//    }
//
//    @GetMapping(path = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Pedido>> findByStatus(@PathVariable("status") String status) {
//        log.info("Buscando pedidos pelo status.");
//        List<Pedido> orders = getOrderUseCase.listarPedidosPorStatus(status);
//        if (orders == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(orders);
//    }

//    @GetMapping(path = "/{id}/payment", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StatusPagamentoDTO> checkOrderPaymentStatus(@PathVariable("id") Long id) {
//        log.info("Consulta status de pagamento do pedido.");
//        StatusPagamento paymentStatus = getOrderUseCase.consultarStatusPagamentoDoPedido(id);
//        return ResponseEntity.ok(StatusPagamentoDTO.builder().status(paymentStatus.getNome()).build());
//    }

//    @PatchMapping(path = "/{id}/status", consumes = "application/json-patch+json", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Pedido> updateOrderStatus(@PathVariable("id") String id, @RequestBody JsonPatch patch) {
//        log.info("Atualizar status do pedido.");
//        Pedido order = updateOrderUseCase.atualizarStatusDoPedido(id, patch);
//        if (order == null) {
//            return ResponseEntity.internalServerError().build();
//        }
//        return ResponseEntity.ok(order);
//    }

}
