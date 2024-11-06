package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IPedidoRepository;
import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.cliente.Cliente;
import br.com.fiap.techchallenge.domain.entities.pagamento.StatusPagamento;
import br.com.fiap.techchallenge.domain.entities.pedido.Order;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.exception.PedidoException;
import br.com.fiap.techchallenge.infra.mapper.pedido.PedidoMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.PedidoEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
public class PedidoRepository implements IPedidoRepository {

    private final PedidoEntityRepository pedidoEntityRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoRepository(PedidoEntityRepository pedidoEntityRepository, PedidoMapper pedidoMapper) {
        this.pedidoEntityRepository = pedidoEntityRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = pedidoEntityRepository.save(pedidoMapper.fromDomainToEntity(order));
//        List<ProdutoPedidoEntity> produtoPedidos = produtoPedidoMapper.fromListDomainToListEntity(pedido.getProdutoPedidos());
//        produtoPedidos.parallelStream().forEach(item -> item.setPedidoEntity(pedidoEntity));
//        pedidoEntity.setProdutos(produtoPedidos);
//        produtoPedidoRepository.saveAllAndFlush(produtoPedidos);
        return pedidoMapper.fromEntityToDomain(orderEntity);
    }

    @Override
    public Order atualizarStatusDoPedido(Order pedidoAtualizado) {
//        log.info("Atualizando status do pedido.");
        definirDataFinalizacaoPedido(pedidoAtualizado);
        return pedidoMapper.fromEntityToDomain(pedidoEntityRepository.save(pedidoMapper.fromDomainToEntity(pedidoAtualizado)));
    }

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

    @Override
    public Order atualizarPagamentoDoPedido(Order pedido) {
//        log.info("Atualizando status de pagamento do pedido.");
        return pedidoMapper.fromEntityToDomain(pedidoEntityRepository.save(pedidoMapper.fromDomainToEntity(pedido)));
    }

    @Override
    public StatusPagamento consultarStatusPagamentoDoPedido(String id) {
//        log.info("Consultar status de pagamento do pedido.");
//        Optional<PedidoEntity> pedidoOptional = pedidoEntityRepository.findById(id);
//        if (pedidoOptional.isEmpty()) {
//            throw new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO);
//        }
//        Pedido pedido = pedidoMapper.fromEntityToDomain(pedidoOptional.get());
//        return pedido.getStatusPagamento();
        return null;
    }

    @Override
    public Order buscarPedidoPorId(String id) {
//        log.info("buscarPedidoPorId");
        try {
            OrderEntity pedido = this.pedidoEntityRepository.findById(id).orElseThrow(() -> new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO));
            return pedidoMapper.fromEntityToDomain(pedido);
        } catch (Exception e) {
//            log.error("O identificador informado não existe no banco de dados.");
            return null;
        }
    }

    @Override
    public List<Order> listarPedidos() {
        List<OrderEntity> listPedidoEntity = pedidoEntityRepository.findAll(Sort.by("dataCriacao").descending());
        return pedidoMapper.fromListEntityToListDTO(listPedidoEntity);
    }

    private List<OrderEntity> obterPedidosOrdenadosPeloStatus() {
        return pedidoEntityRepository.findAll(Sort.by("status.id").descending());
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

//    private int ordernarPorStatus(PedidoEntity pedidoEntity, PedidoEntity pedidoEntity1) {
//        return pedidoEntity1.getStatus().getId().compareTo(pedidoEntity.getStatus().getId());
//    }

    @Override
    public List<Order> listarPedidosPorStatus(String status) {
//        StatusPedidoEnum statusPedidoEnum = StatusPedidoEnum.byStatus(status);
//
//        List<Pedido> pedidos = this.listarPedidos();
//        if (pedidos == null || pedidos.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        Predicate<Pedido> byStatus = sp -> sp.getStatus().getId().equals(statusPedidoEnum.getId());
//        return pedidos.stream().filter(byStatus).toList();
        return null;
    }

}
