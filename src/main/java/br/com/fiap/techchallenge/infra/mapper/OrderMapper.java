package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.*;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderDTO;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;

import java.util.List;

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
        orderDTO.setCreationDate(order.getCreationDate());
        orderDTO.setCancellationDate(order.getCancellationDate());
        orderDTO.setCompletionDate(order.getCompletionDate());
        orderDTO.setItems(itemMapper.fromListDomainToListDTO(order.getItems()));
        return orderDTO;
    }

    public List<Order> fromListEntityToListDTO(List<OrderEntity> list) {
        return list.stream().map(this::fromEntityToDomain).toList();
    }

}
