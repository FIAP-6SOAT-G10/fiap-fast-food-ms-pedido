package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.IPedidoRepository;
import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.pagamento.PagamentoPedidoEnum;
import br.com.fiap.techchallenge.domain.entities.pedido.Order;
import br.com.fiap.techchallenge.domain.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.infra.exception.PedidoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class UpdateOrderUseCase {

    private final IPedidoRepository pedidoRepository;

    public UpdateOrderUseCase(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Order atualizarStatusDoPedido(String id, JsonPatch patch) {
        try{
            validarDados(id, patch);

            Order pedido = pedidoRepository.buscarPedidoPorId(id);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            JsonNode patched = patch.apply(objectMapper.convertValue(pedido, JsonNode.class));

            Order pedidoAtualizado = objectMapper.treeToValue(patched, Order.class);
//            pedidoAtualizado.setCliente(pedido.getCliente());

            validarMudancaDeStatus(pedido, pedidoAtualizado);

            return pedidoRepository.atualizarStatusDoPedido(pedidoAtualizado);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Order atualizarPagamentoDoPedido(String id, JsonPatch patch) {
        try {
            validarDados(id, patch);

            Order pedidoAtual = pedidoRepository.buscarPedidoPorId(id);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            JsonNode node = objectMapper.convertValue(pedidoAtual, JsonNode.class);
            JsonNode patched = patch.apply(node);

            Order pedidoAtualizado = objectMapper.treeToValue(patched, Order.class);

            validarMudancaDePagamento(pedidoAtual, pedidoAtualizado);

            return pedidoRepository.atualizarPagamentoDoPedido(pedidoAtualizado);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validarDados(String id, JsonPatch patch) {
        Pattern pattern = Pattern.compile("[^\\d+]");
        if (pattern.matcher(id).find()) {
            throw new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode request = objectMapper.convertValue(patch, JsonNode.class);

        for (int index = 0 ; index < request.size() ; index++) {

            JsonNode parent = request.get(index);
            if (parent.has("path")) {
                JsonNode path = parent.get("path");

                verificarStatus(path, parent);
                verificarStatusPagamento(path, parent);
            }

        }
    }

    private void verificarStatus(JsonNode path, JsonNode parent) {
        if (path.asText().equalsIgnoreCase("/status")) {
            String statusPedidoContent = parent.get("value").asText();
            if (statusPedidoContent == null || statusPedidoContent.isEmpty()) {
                throw new PedidoException(ErrosEnum.PEDIDO_STATUS_OBRIGATORIO);
            }

            StatusPedidoEnum statusPedidoEnum = StatusPedidoEnum.byStatus(statusPedidoContent);
            if (statusPedidoEnum == null) {
                throw new PedidoException(ErrosEnum.PEDIDO_STATUS_NAO_ENCONTRADO);
            }
        }
    }

    private void verificarStatusPagamento(JsonNode path, JsonNode parent) {
        if (path.asText().equalsIgnoreCase("/statusPagamento")) {
            String statusPagamentoPedidoContent = parent.get("value").asText();
            if (statusPagamentoPedidoContent == null || statusPagamentoPedidoContent.isEmpty()) {
                throw new PedidoException(ErrosEnum.PEDIDO_PAGAMENTO_PAGAMENTO_OBRIGATORIO);
            }

            PagamentoPedidoEnum pagamentoPedidoEnum = PagamentoPedidoEnum.byStatus(statusPagamentoPedidoContent);
            if (pagamentoPedidoEnum == null) {
                throw new PedidoException(ErrosEnum.PEDIDO_PAGAMENTO_PAGAMENTO_NAO_ENCONTRADO);
            }
        }
    }

    private void validarMudancaDeStatus(Order atual, Order novo) {
//        log.info("Validando mudança de status do pedido.");
//        StatusPedidoEnum statusAtual = StatusPedidoEnum.byId(atual.getStatus().getId());
//        StatusPedidoEnum statusNovo = StatusPedidoEnum.byId(novo.getStatus().getId());
//
//        IMudancaStatusPedido mudancaStatusPedido = new MudancaStatusPedidoRecebido(
//                new MudancaStatusPedidoEmPreparacao(
//                        new MudancaStatusPedidoPronto(
//                                new MudancaStatusPedidoFinalizado()
//                        )
//                )
//        );
//
//        mudancaStatusPedido.validarMudancaDeStatus(statusAtual, statusNovo);
    }

    private void validarMudancaDePagamento(Order atual, Order novo) {
//        log.info("Validando mudança de status de pagamento do pedido.");
//        PagamentoPedidoEnum statusPagamentoAtual = PagamentoPedidoEnum.byId(atual.getStatusPagamento().getId());
//        PagamentoPedidoEnum statusPagamentoNovo = PagamentoPedidoEnum.byId(novo.getStatusPagamento().getId());
//
//        IMudancaPagamentoPedido mudancaPagamentoPedido = new MudancaPagamentoPedidoPago(
//                new MudancaPagamentoPedidoRecusado()
//        );
//
//        mudancaPagamentoPedido.validarMudancaDePagamento(statusPagamentoAtual, statusPagamentoNovo);
    }
}
