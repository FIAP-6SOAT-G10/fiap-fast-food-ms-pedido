package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationRepository implements INotificationRepository {

    @Value("${payment-request-queue}")
    private String destination;

    private final SqsTemplate sqsTemplate;

    public NotificationRepository(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    @Override
    public void sendNotification(Order order) {
        sqsTemplate.send(destination, order);
    }
}
