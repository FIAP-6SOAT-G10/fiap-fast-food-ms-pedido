package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderResponseDTO;

import java.util.List;
import java.util.Objects;

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

    public Order fromDTOToDomain(OrderRequestDTO orderRequestDTO) {
        Order order = new Order(orderRequestDTO.getCpf());
        if (!orderRequestDTO.getItems().isEmpty()) {
            order.setItems(itemMapper.fromListDTOToListDomain(orderRequestDTO.getItems()));
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
        orderEntity.setCancellationDate(order.getCancellationDate());
        orderEntity.setCompletionDate(order.getCompletionDate());
        orderEntity.setItems(itemMapper.fromListDomainToListEntity(order.getItems()));
        return orderEntity;
    }

    public OrderResponseDTO fromDomainToResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getCpf(),
                order.getStatus(),
                order.getAmount(),
                order.getCreationDate(),
                order.getCompletionDate(),
                order.getCancellationDate(),
                order.getPaymentStatus(),
                itemMapper.fromListDomainToListResponseDTO(order.getItems()));
    }

    public List<Order> fromListEntityToListDTO(List<OrderEntity> list) {
        return list.stream().map(this::fromEntityToDomain).toList();
    }

    public PaymentRequest fromDomainToPaymentRequest(Order order) {
        String email = Objects.isNull(order.getCustomer()) ? null : order.getCustomer().getEmail();
        return new PaymentRequest(
                order.getId(),
                email,
                order.getAmount());
    }

}
