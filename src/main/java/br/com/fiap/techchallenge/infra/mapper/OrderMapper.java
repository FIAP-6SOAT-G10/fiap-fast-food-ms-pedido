package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderDTO;

import java.util.List;

public class OrderMapper {

    private final ItemMapper itemMapper = new ItemMapper();
    private final CustomerMapper customerMapper = new CustomerMapper();

    public Order fromEntityToDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getCpf(),
                orderEntity.getStatus(),
                orderEntity.getAmount(),
                orderEntity.getCreationDate(),
                orderEntity.getCompletionDate(),
                orderEntity.getCancellationDate(),
                orderEntity.getStatus(),
                itemMapper.fromListEntityToListDomain(orderEntity.getItems()));
    }

    public Order fromDTOToDomain(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getCpf());
        if (!orderDTO.getItems().isEmpty()) {
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
        return new OrderDTO(
                order.getId(),
                order.getCpf(),
                order.getStatus(),
                order.getAmount(),
                order.getCreationDate(),
                order.getCompletionDate(),
                order.getCancellationDate(),
                order.getPaymentStatus(),
                itemMapper.fromListDomainToListDTO(order.getItems()));
    }

    public List<Order> fromListEntityToListDTO(List<OrderEntity> list) {
        return list.stream().map(this::fromEntityToDomain).toList();
    }

    public PaymentRequest fromDomainToPaymentRequest(Order order) {
        return new PaymentRequest(
                order.getId(),
                order.getCustomer().getEmail(),
                order.getAmount());
    }

}
