package br.com.fiap.techchallenge.domain.statuspedido;//package br.com.fiap.techchallenge.naousar.domain.cors.statuspedido;

import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.order.enums.OrderStatusEnum;
import br.com.fiap.techchallenge.infra.exception.PedidoException;

public class MudancaStatusPedidoRecebido implements MudancaStatusPedido {

    private final MudancaStatusPedido next;

    public MudancaStatusPedidoRecebido(MudancaStatusPedido next) {
        this.next = next;
    }

    @Override
    public void validarMudancaDeStatus(OrderStatusEnum atual, OrderStatusEnum novo) {
        if (OrderStatusEnum.RECEIVED.equals(atual) && !OrderStatusEnum.IN_PREPARATION.equals(novo)) {
            throw new PedidoException(ErrosEnum.PEDIDO_STATUS_RECEBIDO_EM_PREPARACAO);
        } else {
            this.next.validarMudancaDeStatus(atual, novo);
        }
    }
}
