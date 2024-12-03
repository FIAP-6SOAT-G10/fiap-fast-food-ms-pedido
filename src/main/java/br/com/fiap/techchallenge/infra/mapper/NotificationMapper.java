package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;

public class NotificationMapper {

    public PaymentRequest fromDomainToDataTransferObjet(Order order) {
        return new PaymentRequest(
                order.getId(),
                order.getCustomer().getEmail(),
                order.getAmount());
    }

}
