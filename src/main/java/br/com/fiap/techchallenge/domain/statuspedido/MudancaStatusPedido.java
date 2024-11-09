package br.com.fiap.techchallenge.domain.statuspedido;

import br.com.fiap.techchallenge.domain.entities.order.enums.OrderStatusEnum;

public interface MudancaStatusPedido {

    void validarMudancaDeStatus(OrderStatusEnum atual, OrderStatusEnum novo);

}
