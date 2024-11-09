package br.com.fiap.techchallenge.infra.config;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.application.usecases.order.CreateOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.NotifyOrderConsumerUseCase;
import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.CustomerClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.ItemClient;
import br.com.fiap.techchallenge.infra.gateways.CustomerRepository;
import br.com.fiap.techchallenge.infra.gateways.NotificationRepository;
import br.com.fiap.techchallenge.infra.gateways.OrderRepository;
import br.com.fiap.techchallenge.infra.gateways.ItemRepository;
import br.com.fiap.techchallenge.infra.mapper.CustomerMapper;
import br.com.fiap.techchallenge.infra.mapper.ItemMapper;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntityRepository;
import br.com.fiap.techchallenge.infra.mapper.PaymentMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public IOrderRepository createOrderRepository(OrderEntityRepository orderEntityRepository, OrderMapper orderMapper) {
        return new OrderRepository(orderEntityRepository, orderMapper);
    }

    @Bean
    public IItemRepository createItemRepository(ItemClient itemClient) {
        return new ItemRepository(itemClient);
    }

    @Bean
    public ICustomerRepository createCustomerRepository(CustomerClient customerClient) {
        return new CustomerRepository(customerClient);
    }

    @Bean
    public INotificationRepository createNotificationRepository(SqsTemplate sqsTemplate) {
        return new NotificationRepository(sqsTemplate);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(IOrderRepository orderRepository, IItemRepository itemRepository, ICustomerRepository customerRepository) {
        return new CreateOrderUseCase(orderRepository, itemRepository, customerRepository);
    }

    @Bean
    public GetOrderUseCase createGetOrderUseCase(IOrderRepository orderRepository) {
        return new GetOrderUseCase(orderRepository);
    }

    @Bean
    public UpdateOrderUseCase createUpdateOrderUseCase(IOrderRepository orderRepository) {
        return new UpdateOrderUseCase(orderRepository);
    }

    @Bean
    public NotifyOrderConsumerUseCase createINotificationRepository(INotificationRepository notificationRepository) {
        return new NotifyOrderConsumerUseCase(notificationRepository);
    }

    @Bean
    public CustomerMapper createCustomerMapper() {
        return new CustomerMapper();
    }

    @Bean
    public OrderMapper createOrderMapper() {
        return new OrderMapper();
    }

    @Bean
    public ItemMapper createItemMapper() {
        return new ItemMapper();
    }

    @Bean
    public PaymentMapper createPaymentMapper() {
        return new PaymentMapper();
    }

}
