package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.pedido.*;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderDTO;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapper {

    private final ItemMapper itemMapper = new ItemMapper();

    public Order fromEntityToDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getCpf(),
                orderEntity.getStatus(),
                orderEntity.getAmount(),
                orderEntity.getCreationDate(),
                orderEntity.getCompletionDate(),
                orderEntity.getCancellationDate(),
                orderEntity.getPaymentStatus(),
                itemMapper.fromListEntityToListDomain(orderEntity.getItems()));
    }

    public Order fromDTOToDomain(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getCpf());
        if(!orderDTO.getItems().isEmpty()) {
            order.setItems(itemMapper.fromListDTOToListDomain(orderDTO.getItems()));
        }
        return order;
    }

//    public OrderDTO fromDTOToDomain(Order order) {
//        OrderDTO orderDTO = new OrderDTO();
//        if(!orderDTO.getItems().isEmpty()) {
//            order.setItems(itemMapper.fromListDTOToListDomain(orderDTO.getItems()));
//        }
//        return order;
//    }

    public OrderEntity fromDomainToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setPaymentStatus(order.getPaymentStatus());
        orderEntity.setAmount(order.getAmount());
        orderEntity.setCpf(order.getCpf());
        orderEntity.setItems(itemMapper.fromListDomainToListEntity(order.getItems()));
        return orderEntity;
    }

    public OrderDTO fromDomainToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setCpf(order.getCpf());
        orderDTO.setItems(itemMapper.fromListDomainToListDTO(order.getItems()));
        return orderDTO;
    }

    public List<Order> fromListEntityToListDTO(List<OrderEntity> pedidos) {
        return pedidos.stream().map(this::fromEntityToDomain).toList();
    }

    public List<OrderEntity> fromListDTOToListEntity(List<Order> pedidos) {
        return pedidos.stream().map(this::fromDomainToEntity).toList();
    }

    private static Map<Long, List<ItemPedido>> buildMapItemPedido(OrderEntity pedidoEntity, List<Product> produtosPedidos) {
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
