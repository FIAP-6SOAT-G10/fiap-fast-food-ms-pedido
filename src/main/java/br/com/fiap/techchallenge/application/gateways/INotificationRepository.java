package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Order;

public interface INotificationRepository {

    void sendNotification(Order order);

}