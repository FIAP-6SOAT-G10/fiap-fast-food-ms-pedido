package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.pagamento.StatusPagamento;
import br.com.fiap.techchallenge.domain.entities.pedido.Order;

import java.util.List;

public interface IPedidoRepository {

    Order createOrder(Order pedido);

    Order atualizarStatusDoPedido(Order pedido);

    Order atualizarPagamentoDoPedido(Order pedido);

    StatusPagamento consultarStatusPagamentoDoPedido(String id);

    Order buscarPedidoPorId(String id);

    List<Order> listarPedidos();

    List<Order> listarPedidosPorStatus(String status);

}
