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
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setCpf(orderEntity.getCpf());
        order.setStatus(orderEntity.getStatus());
        order.setAmount(orderEntity.getAmount());
        order.setCreationDate(orderEntity.getCreationDate());
        order.setCompletionDate(orderEntity.getCompletionDate());
        order.setCancellationDate(orderEntity.getCancellationDate());
        order.setPaymentStatus(orderEntity.getPaymentStatus());
        order.setItems(itemMapper.fromListEntityToListDomain(orderEntity.getItems()));
        return order;
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
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setCpf(order.getCpf());
        orderResponseDTO.setStatus(order.getStatus());
        orderResponseDTO.setAmount(order.getAmount());
        orderResponseDTO.setCreationDate(order.getCreationDate());
        orderResponseDTO.setCompletionDate(order.getCompletionDate());
        orderResponseDTO.setCancellationDate(order.getCancellationDate());
        orderResponseDTO.setPaymentStatus(order.getPaymentStatus());
        orderResponseDTO.setItems(itemMapper.fromListDomainToListResponseDTO(order.getItems()));
        return orderResponseDTO;
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
