package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.pagamento.PaymentResponse;
import br.com.fiap.techchallenge.infra.entrypoints.queue.payment.model.PaymentResponseDTO;

public class PaymentMapper {

    public PaymentResponse fromDataTransferObjetToDomain(PaymentResponseDTO paymentResponseDTO) {
        return new PaymentResponse(
                paymentResponseDTO.getExternalOrderId(),
                paymentResponseDTO.getInternalOrderId(),
                paymentResponseDTO.getPayer(),
                paymentResponseDTO.getPaymentAmount(),
                paymentResponseDTO.getPaymentDate(),
                paymentResponseDTO.getPaymentStatus());
    }

}
