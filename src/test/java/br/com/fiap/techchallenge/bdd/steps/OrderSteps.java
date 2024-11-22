package br.com.fiap.techchallenge.bdd.steps;

import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderResponseDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private OrderRequestDTO orderRequest;
    private ResponseEntity<OrderResponseDTO> response;

    @Dado("que eu tenha os detalhes do pedido")
    public void queEuTenhaOsDetalhesDoPedido() {
        orderRequest = new OrderRequestDTO();
        orderRequest.setCpf("12345678900");
        orderRequest.setItems(List.of(new ItemRequestDTO(1L, 2L)));
    }

    @Quando("eu enviar o pedido")
    public void euEnviarOPedido() {
        response = restTemplate.postForEntity("/api/orders", orderRequest, OrderResponseDTO.class);
    }

    @Entao("o pedido deve ser salvo com sucesso")
    public void oPedidoDeveSerSalvoComSucesso() {
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @E("eu devo receber a confirmação do pedido")
    public void euDevoReceberAConfirmacaoDoPedido() {
        OrderResponseDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(orderRequest.getCpf(), responseBody.getCpf());
    }
}
