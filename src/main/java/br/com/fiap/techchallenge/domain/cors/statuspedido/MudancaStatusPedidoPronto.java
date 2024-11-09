package br.com.fiap.techchallenge.domain.cors.statuspedido;


import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.order.enums.OrderStatusEnum;
import br.com.fiap.techchallenge.infra.exception.PedidoException;

public class MudancaStatusPedidoPronto implements IMudancaStatusPedido {

    private final IMudancaStatusPedido next;

    public MudancaStatusPedidoPronto(IMudancaStatusPedido next) {
        this.next = next;
    }

    @Override
    public void validarMudancaDeStatus(OrderStatusEnum atual, OrderStatusEnum novo) {
        if (OrderStatusEnum.READY.equals(atual) && !OrderStatusEnum.COMPLETED.equals(novo)) {
            throw new PedidoException(ErrosEnum.PEDIDO_STATUS_PRONTO_FINALIZADO);
        } else {
            this.next.validarMudancaDeStatus(atual, novo);
        }
    }
}