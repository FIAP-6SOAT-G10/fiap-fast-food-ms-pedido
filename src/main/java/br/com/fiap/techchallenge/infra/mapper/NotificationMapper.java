package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentResponse;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.model.PaymentResponseDTO;

public class NotificationMapper {

    public PaymentRequest fromDomainToDataTransferObjet(Order order) {
        return new PaymentRequest(
                order.getId(),
                order.getCustomer().getEmail(),
                order.getAmount());
    }

}
