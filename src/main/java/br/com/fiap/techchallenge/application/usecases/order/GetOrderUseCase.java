package br.com.fiap.techchallenge.application.usecases.order;


import br.com.fiap.techchallenge.application.gateways.IPedidoRepository;
import br.com.fiap.techchallenge.domain.entities.pagamento.StatusPagamento;
import br.com.fiap.techchallenge.domain.entities.pedido.Order;

import java.util.List;

public class GetOrderUseCase {

    private final IPedidoRepository pedidoRepository;

    public GetOrderUseCase(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Order buscarPedidoPorId(String id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    public List<Order> listarPedidos() {
        return pedidoRepository.listarPedidos();
    }

    public List<Order> listarPedidosPorStatus(String status) {
        return pedidoRepository.listarPedidosPorStatus(status);
    }

    public StatusPagamento consultarStatusPagamentoDoPedido(String id) {
        return pedidoRepository.consultarStatusPagamentoDoPedido(id);
    }

}
