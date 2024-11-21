package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Order;

import java.util.List;

public interface IOrderRepository {

    List<Order> list();

    Order findById(String id);

    List<Order> findByStatus(String status);

    List<Order> findByPaymentStatus(String paymentStatus);

    Order save(Order order);
}
