package br.com.fiap.techchallenge.infra.entrypoints.queue.payment;

import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.model.PaymentResponseDTO;
import br.com.fiap.techchallenge.infra.mapper.PaymentMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ResponsePaymentQueueListener {

    private final PaymentMapper paymentMapper;
    private final UpdateOrderUseCase updateOrderUseCase;

    public ResponsePaymentQueueListener(PaymentMapper paymentMapper, UpdateOrderUseCase updateOrderUseCase) {
        this.paymentMapper = paymentMapper;
        this.updateOrderUseCase = updateOrderUseCase;
    }

    @SqsListener("${aws.sqs.order-payment-updates-queue}")
    public void listen(String paymentResponseDTO) {
        log.info("Mensagem recebida da fila de pagamento {}", paymentResponseDTO);
//        updateOrderUseCase.updatePaymentStatus(
//                paymentMapper.fromDataTransferObjetToDomain(paymentResponseDTO));
    }

}
