package br.com.fiap.techchallenge.infra.config;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.application.usecases.order.CreateOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntityRepository;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.CustomerClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.ItemClient;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.ResponsePaymentQueueListener;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.ResponseProductionQueueListener;
import br.com.fiap.techchallenge.infra.gateways.CustomerRepository;
import br.com.fiap.techchallenge.infra.gateways.ItemRepository;
import br.com.fiap.techchallenge.infra.gateways.NotificationRepository;
import br.com.fiap.techchallenge.infra.gateways.OrderRepository;
import br.com.fiap.techchallenge.infra.mapper.*;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Value("${aws.sqs.payment-requests-queue}")
    private String destination;

    @Bean
    public ICustomerRepository buildCustomerRepository(CustomerClient customerClient, CustomerMapper customerMapper) {
        return new CustomerRepository(customerClient, customerMapper);
    }

    @Bean
    public IItemRepository buildItemRepository(ItemClient itemClient, ItemMapper itemMapper) {
        return new ItemRepository(itemClient, itemMapper);
    }

    @Bean
    public IOrderRepository buildOrderRepository(OrderEntityRepository orderEntityRepository, OrderMapper orderMapper) {
        return new OrderRepository(orderEntityRepository, orderMapper);
    }

    @Bean
    public INotificationRepository buildNotificationRepository(SqsTemplate sqsTemplate, OrderMapper orderMapper) {
        return new NotificationRepository(sqsTemplate, orderMapper, destination);
    }

    @Bean
    public ResponsePaymentQueueListener buildResponsePaymentQueueListener(PaymentMapper paymentMapper, UpdateOrderUseCase updateOrderUseCase) {
        return new ResponsePaymentQueueListener(paymentMapper, updateOrderUseCase);
    }

    @Bean
    public ResponseProductionQueueListener buildResponseProductionQueueListener(ProductionMapper productionMapper, UpdateOrderUseCase updateOrderUseCase) {
        return new ResponseProductionQueueListener(productionMapper, updateOrderUseCase);
    }

    @Bean
    public CreateOrderUseCase buildOrderUseCase(IOrderRepository orderRepository, IItemRepository itemRepository, ICustomerRepository customerRepository, INotificationRepository notificationRepository) {
        return new CreateOrderUseCase(orderRepository, itemRepository, customerRepository, notificationRepository);
    }

    @Bean
    public GetOrderUseCase buildGetOrderUseCase(IOrderRepository orderRepository) {
        return new GetOrderUseCase(orderRepository);
    }

    @Bean
    public UpdateOrderUseCase buildUpdateOrderUseCase(IOrderRepository orderRepository) {
        return new UpdateOrderUseCase(orderRepository);
    }

    @Bean
    public CustomerMapper buildCustomerMapper() {
        return new CustomerMapper();
    }

    @Bean
    public ItemMapper buildItemMapper() {
        return new ItemMapper();
    }

    @Bean
    public OrderMapper buildOrderMapper() {
        return new OrderMapper();
    }

    @Bean
    public PaymentMapper buildPaymentMapper() {
        return new PaymentMapper();
    }

    @Bean
    public ProductionMapper buildProductionMapper() {
        return new ProductionMapper();
    }
}
