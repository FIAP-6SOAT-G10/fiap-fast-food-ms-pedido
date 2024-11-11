package br.com.fiap.techchallenge.application.usecases.order;


import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;

import java.util.List;

public class GetOrderUseCase {

    private final IOrderRepository orderRepository;

    public GetOrderUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> listOrders() {
        return orderRepository.list();
    }

    public Order findOrderById(String id) {
        return orderRepository.findById(id);
    }
}
