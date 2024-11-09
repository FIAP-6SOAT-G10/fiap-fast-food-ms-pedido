package br.com.fiap.techchallenge.infra.entrypoints.queue;

import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.infra.entrypoints.queue.model.PaymentRequestDTO;
import br.com.fiap.techchallenge.infra.mapper.PaymentMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestPaymentQueueListener {

    private final PaymentMapper paymentMapper;
    private final UpdateOrderUseCase updateOrderUseCase;

    @SqsListener("${aws.sqs.order-payment-updates-queue}")
    public void listen(PaymentRequestDTO paymentRequestDTO) {
        log.info("Mensagem recebida da fila {}", paymentRequestDTO);

        PaymentRequest paymentRequest = paymentMapper.fromDataTransferObjetToDomain(paymentRequestDTO);
        updateOrderUseCase.updatePaymentStatus(paymentRequest);
    }

}
