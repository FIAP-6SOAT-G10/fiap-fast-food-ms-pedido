package br.com.fiap.techchallenge.domain.cors.statuspedido;

import br.com.fiap.techchallenge.domain.entities.order.enums.OrderStatusEnum;

public interface IMudancaStatusPedido {

    void validarMudancaDeStatus(OrderStatusEnum atual, OrderStatusEnum novo);
}
