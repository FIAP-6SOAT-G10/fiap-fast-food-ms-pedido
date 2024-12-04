package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentResponse;
import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;

public class UpdateOrderUseCase {

    private final IOrderRepository orderRepository;

    public UpdateOrderUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updatePaymentStatus(PaymentResponse paymentResponse) {
        Order order = orderRepository.findById(paymentResponse.getExternalOrderId());
        order.setPaymentStatus(paymentResponse.getPaymentStatus());
        orderRepository.save(order);
    }

    public void updateStatus(ProductionResponse productionResponse) {
        Order order = orderRepository.findById(productionResponse.getExternalOrderId());
        order.setStatus(productionResponse.getStatus().getNominalStatusStatus());

        if (ProductionStatusEnum.FAILED == productionResponse.getStatus()) {
            order.setCancellationDate(productionResponse.getDate());
        }

        if (ProductionStatusEnum.COMPLETED == productionResponse.getStatus()) {
            order.setCompletionDate(productionResponse.getDate());
        }
        orderRepository.save(order);
    }

}
