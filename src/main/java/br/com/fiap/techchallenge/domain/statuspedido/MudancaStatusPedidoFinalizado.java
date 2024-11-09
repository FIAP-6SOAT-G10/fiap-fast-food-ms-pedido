package br.com.fiap.techchallenge.domain.statuspedido;//package br.com.fiap.techchallenge.naousar.domain.cors.statuspedido;

import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.order.enums.OrderStatusEnum;
import br.com.fiap.techchallenge.infra.exception.PedidoException;

public class MudancaStatusPedidoFinalizado implements MudancaStatusPedido {
    @Override
    public void validarMudancaDeStatus(OrderStatusEnum atual, OrderStatusEnum novo) {
        if (OrderStatusEnum.COMPLETED.equals(atual)) {
            throw new PedidoException(ErrosEnum.PEDIDO_STATUS_FINALIZADO);
        }
    }
}
