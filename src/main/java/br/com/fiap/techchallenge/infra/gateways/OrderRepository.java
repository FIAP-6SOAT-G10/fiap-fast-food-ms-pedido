package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.cliente.Cliente;
import br.com.fiap.techchallenge.domain.entities.pedido.Order;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.exception.PedidoException;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
public class OrderRepository implements IOrderRepository {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderMapper orderMapper;

    public OrderRepository(OrderEntityRepository orderEntityRepository, OrderMapper orderMapper) {
        this.orderEntityRepository = orderEntityRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order findOrderById(String id) {
        OrderEntity order = this.orderEntityRepository.findById(id).orElseThrow(() -> new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO));
        return orderMapper.fromEntityToDomain(order);
    }

    @Override
    public List<Order> listOrders() {
        List<OrderEntity> listOrder = orderEntityRepository.findAll(Sort.by("creationDate").descending());
        return orderMapper.fromListEntityToListDTO(listOrder);
    }

    @Override
    public List<Order> findOrderByStatus(String status) {
        List<OrderEntity> listOrder = orderEntityRepository.findByStatus(status, Sort.by("creationDate").descending());
        return orderMapper.fromListEntityToListDTO(listOrder);
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = orderEntityRepository.save(orderMapper.fromDomainToEntity(order));
        return orderMapper.fromEntityToDomain(orderEntity);
    }

//    @Override
//    public Order atualizarStatusDoPedido(Order pedidoAtualizado) {
////        log.info("Atualizando status do pedido.");
//        definirDataFinalizacaoPedido(pedidoAtualizado);
//        return orderMapper.fromEntityToDomain(orderEntityRepository.save(orderMapper.fromDomainToEntity(pedidoAtualizado)));
//    }

    private Cliente buscarCliente(Order request) {
//        if (request.getCliente() == null || request.getCliente().getCpf().isBlank() || request.getCliente().getCpf().isEmpty()) {
//            return null;
//        }
//        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findByCpf(request.getCliente().getCpf());
//        if (clienteEntity.isEmpty()) {
//            throw new ClienteException(ErrosEnum.CLIENTE_CPF_NAO_EXISTE);
//        }
//        return new ClienteMapper().fromEntityToDomain(clienteEntity.get());
        return new Cliente();
    }

    private void definirDataFinalizacaoPedido(Order novo) {
//        StatusPedidoEnum statusNovo = StatusPedidoEnum.byId(novo.getStatus().getId());
//        if (StatusPedidoEnum.FINALIZADO.equals(statusNovo)) {
//            novo.setDataFinalizacao(LocalDateTime.now());
//        }
    }

//    @Override
//    public Order atualizarPagamentoDoPedido(Order pedido) {
////        log.info("Atualizando status de pagamento do pedido.");
//        return orderMapper.fromEntityToDomain(orderEntityRepository.save(orderMapper.fromDomainToEntity(pedido)));
//    }

    private List<OrderEntity> obterPedidosOrdenadosPeloStatus() {
        return orderEntityRepository.findAll(Sort.by("status.id").descending());
    }

    private List<OrderEntity> removerPedidosFinalizadosCancelados(List<OrderEntity> listaPedido) {
//        Predicate<PedidoEntity> exceptDone = pedidoEntity -> !(pedidoEntity.getStatus().getId().equals(StatusPedidoEnum.FINALIZADO.getId()));
//        Predicate<PedidoEntity> exceptCancelled = pedidoEntity -> !(pedidoEntity.getStatus().getId().equals(StatusPedidoEnum.CANCELADO.getId()));
//        return listaPedido.stream().filter(exceptDone.and(exceptCancelled)).sorted(this::ordenarPorHorarioRecebimento).sorted(this::ordernarPorStatus).toList();
        return null;
    }

    private int ordenarPorHorarioRecebimento(OrderEntity pedidoEntity, OrderEntity pedidoEntity1) {
        return pedidoEntity.getCreationDate().compareTo(pedidoEntity1.getCreationDate());
    }

}
