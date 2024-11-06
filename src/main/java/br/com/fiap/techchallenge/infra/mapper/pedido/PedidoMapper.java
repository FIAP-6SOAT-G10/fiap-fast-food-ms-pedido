package br.com.fiap.techchallenge.infra.mapper.pedido;

import br.com.fiap.techchallenge.domain.entities.pedido.*;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderDTO;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.mapper.cliente.ClienteMapper;
import br.com.fiap.techchallenge.infra.mapper.produtopedido.ProdutoPedidoMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoMapper {

    private final ClienteMapper clienteMapper = new ClienteMapper();
    private final ProdutoPedidoMapper produtoPedidoMapper = new ProdutoPedidoMapper();

    public Order fromEntityToDomain(OrderEntity pedidoEntity) {
//        Cliente cliente = clienteMapper.fromEntityToDomain(pedidoEntity.getCliente());

//        StatusPedido status = new StatusPedido(pedidoEntity.getStatus().getId());
//        StatusPagamento statusPagamento = new StatusPagamento(pedidoEntity.getStatusPagamento().getId());

//        List<ProdutoPedido> produtosPedidos = produtoPedidoMapper.fromListEntityToListDomain(pedidoEntity.getProdutos());
//        Map<Long, List<ItemPedido>> mapItemPedido = buildMapItemPedido(pedidoEntity, produtosPedidos);
//        List<ItemPedido> lanches = mapItemPedido.get(CategoriaEnum.LANCHE.getIdCategoria());
//        List<ItemPedido> bebida = mapItemPedido.get(CategoriaEnum.BEBIDA.getIdCategoria());
//        List<ItemPedido> acompanhamento = mapItemPedido.get(CategoriaEnum.ACOMPANHAMENTO.getIdCategoria());
//        List<ItemPedido> sobremesa = mapItemPedido.get(CategoriaEnum.SOBREMESA.getIdCategoria());

//        return new Pedido(
//                pedidoEntity.getId(),
//                cliente,
//                status,
//                pedidoEntity.getValor(),
//                pedidoEntity.getDataCriacao(),
//                pedidoEntity.getDataFinalizacao(),
//                pedidoEntity.getDataCancelamento(),
//                statusPagamento,
//                produtosPedidos,
//                new Item(lanches,bebida,acompanhamento,sobremesa));
        return new Order();
    }

    public Order fromDTOToDomain(OrderDTO orderDTO) {
//        Cliente cliente = new ClienteMapper().fromDTOToDomain(pedidoDTO.getCliente());
//        List<ProdutoPedido> produtosPedidos = pedidoDTO.getProdutos();
//        return new Pedido(cliente, pedidoDTO.getValor(), produtosPedidos);
        return new Order();
    }

    public OrderEntity fromDomainToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();

//        if (pedido.getCliente() != null) {
//            Cliente cliente = pedido.getCliente();
//            pedidoEntity.setCliente(new ClienteEntity(cliente.getId(), cliente.getCpf(), cliente.getNome(), cliente.getEmail()));
//        }

//        StatusPedido status = pedido.getStatus();
//        StatusPedidoEntity statusPedidoEntity = new StatusPedidoEntity();
//        statusPedidoEntity.setId(status.getId());
//        statusPedidoEntity.setNome(status.getNome());
//
//        StatusPagamento statusPagamento = pedido.getStatusPagamento();
//        StatusPagamentoEntity statusPagamentoEntity = new StatusPagamentoEntity();
//        statusPagamentoEntity.setId(statusPagamento.getId());
//        statusPagamentoEntity.setNome(statusPagamento.getNome());

        orderEntity.setId(order.getId());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setPaymentStatus(order.getPaymentStatus());
        orderEntity.setAmount(order.getAmount());
        orderEntity.setCpf(order.getCpf());
//        pedidoEntity.setProdutos(produtoPedidoMapper.fromListDomainToListEntity(pedido.getProdutoPedidos()));

        return orderEntity;
    }

    public List<Order> fromListEntityToListDTO(List<OrderEntity> pedidos) {
        return pedidos.stream().map(this::fromEntityToDomain).toList();
    }

    public List<OrderEntity> fromListDTOToListEntity(List<Order> pedidos) {
        return pedidos.stream().map(this::fromDomainToEntity).toList();
    }

    private static Map<Long, List<ItemPedido>> buildMapItemPedido(OrderEntity pedidoEntity, List<ProdutoPedido> produtosPedidos) {
        Map<Long, List<ItemPedido>> mapItemPedido = new HashMap<>();
//        produtosPedidos.forEach(it -> {
//
//            ItemPedido itemPedido = new ItemPedido(pedidoEntity.getId(), it.getQuantidade().longValue());
//            CategoriaEnum categoriaEnum = CategoriaEnum.fromName(it.getProduto().getCategoria().getNome());
//
//            if(mapItemPedido.isEmpty()) {
//                List<ItemPedido> list = new ArrayList<>();
//                list.add(itemPedido);
//                mapItemPedido.put(categoriaEnum.getIdCategoria(), list);
//            }
//
//            if(mapItemPedido.containsKey(categoriaEnum.getIdCategoria())) {
//                mapItemPedido.get(categoriaEnum.getIdCategoria()).add(itemPedido);
//            }else {
//                List<ItemPedido> list = new ArrayList<>();
//                list.add(itemPedido);
//                mapItemPedido.put(categoriaEnum.getIdCategoria(), list);
//            }
//        });
        return mapItemPedido;
    }

}
