package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.application.gateways.INotificationRepository;
import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.exception.OrderException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static br.com.fiap.techchallenge.domain.ErrosEnum.PEDIDO_SEM_CLIENTE_E_ITEMS;

public class CreateOrderUseCase {

    private final IItemRepository itemRepository;
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final INotificationRepository notificationRepository;

    public CreateOrderUseCase(IOrderRepository orderRepository, IItemRepository itemRepository, ICustomerRepository customerRepository, INotificationRepository notificationRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.notificationRepository = notificationRepository;
    }

    public Order createOrder(Order order) {

        if (order.getItems() == null) {
            throw new OrderException(PEDIDO_SEM_CLIENTE_E_ITEMS);
        }

        if (StringUtils.hasText(order.getCpf())) {
            order.setCustomer(customerRepository.findByCpf(order.getCpf()));
        }

        order.getItems().forEach(item -> {
            Item itemResponse = itemRepository.findById(item.getItemId());
            item.setPrice(itemResponse.getPrice());
        });

        order.setAmount(calculateTotalAmount(order.getItems()));
        Order orderCreated = orderRepository.save(order);
        notificationRepository.sendNotification(orderCreated);
        return orderCreated;
    }

    public BigDecimal calculateTotalAmount(List<Item> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
