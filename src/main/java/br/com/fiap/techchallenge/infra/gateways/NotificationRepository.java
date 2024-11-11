package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationRepository implements INotificationRepository {

    @Value("${aws.sqs.payment-request-queue}")
    private String destination;

    private final SqsTemplate sqsTemplate;
    private final OrderMapper orderMapper;

    public NotificationRepository(SqsTemplate sqsTemplate, OrderMapper orderMapper) {
        this.sqsTemplate = sqsTemplate;
        this.orderMapper = orderMapper;
    }

    @Override
    public void sendNotification(Order order) {
        sqsTemplate.send(destination, orderMapper.fromDomainToPaymentRequest(order));
    }
}
