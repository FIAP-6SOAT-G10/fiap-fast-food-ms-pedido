package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyOrderConsumerUseCase {

    private final INotificationRepository notificationRepository;

    public NotifyOrderConsumerUseCase(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void execute(Order order) {
        log.info("Enviando notificação de criação de pedido para o consumidores");
//        this.notificationRepository.sendNotification(payment);
    }

}
