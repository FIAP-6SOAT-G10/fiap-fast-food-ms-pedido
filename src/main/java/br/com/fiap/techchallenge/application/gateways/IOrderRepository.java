package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Order;

import java.util.List;

public interface IOrderRepository {

    List<Order> listOrders();

    Order findOrderById(String id);

    List<Order> findOrderByStatus(String status);

    Order createOrder(Order pedido);

//    Order atualizarStatusDoPedido(Order pedido);
//
//    Order atualizarPagamentoDoPedido(Order pedido);

}
