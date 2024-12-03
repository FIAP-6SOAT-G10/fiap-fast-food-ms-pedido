package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class NotificationRepositoryTest {

    @Mock
    private SqsTemplate sqsTemplate;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private Order order;

    private static final String DESTINATION = "test-queue";

    private NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationRepository = new NotificationRepository(sqsTemplate, orderMapper, DESTINATION);
    }

    @Test
    void testSendNotification() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        when(orderMapper.fromDomainToPaymentRequest(order)).thenReturn(paymentRequest);

        // Act
        notificationRepository.sendNotification(order);

        // Assert
        verify(orderMapper, times(1)).fromDomainToPaymentRequest(order);
        verify(sqsTemplate, times(1)).send(DESTINATION, paymentRequest);
    }
}
