package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationRepository implements INotificationRepository {

    private final SqsTemplate sqsTemplate;
    private final OrderMapper orderMapper;
    private final String destination;

    public NotificationRepository(SqsTemplate sqsTemplate, OrderMapper orderMapper, String destination) {
        this.sqsTemplate = sqsTemplate;
        this.orderMapper = orderMapper;
        this.destination = destination;
    }

    @Override
    public void sendNotification(Order order) {
        sqsTemplate.send(destination, orderMapper.fromDomainToPaymentRequest(order));
    }
}
