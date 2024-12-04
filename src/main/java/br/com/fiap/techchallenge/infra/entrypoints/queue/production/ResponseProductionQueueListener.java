package br.com.fiap.techchallenge.infra.entrypoints.queue.production;

import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.model.ProductionResponseDTO;
import br.com.fiap.techchallenge.infra.mapper.ProductionMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResponseProductionQueueListener {

    private final ProductionMapper productionMapper;
    private final UpdateOrderUseCase updateOrderUseCase;

    public ResponseProductionQueueListener(ProductionMapper productionMapper, UpdateOrderUseCase updateOrderUseCase) {
        this.productionMapper = productionMapper;
        this.updateOrderUseCase = updateOrderUseCase;
    }

//    @SqsListener("${aws.sqs.production_order_requests_queue}")
    public void listen(ProductionResponseDTO productionResponseDTO) {
        log.info("Mensagem recebida da fila de produção {}", productionResponseDTO);
        updateOrderUseCase.updateStatus(
                productionMapper.fromDataTransferObjetToDomain(productionResponseDTO));
    }

}
