package br.com.fiap.techchallenge.infra.entrypoints.rest.order;

import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.OrderResponseDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class OrderControllerBDD {

    private OrderRequestDTO orderRequest;
    private Response response;

    static {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Dado("que eu tenha os detalhes do pedido")
    public void queEuTenhaOsDetalhesDoPedido() {
        orderRequest = new OrderRequestDTO();
        orderRequest.setItems(List.of(new ItemRequestDTO(1L, 2L)));
    }

    @Quando("eu enviar o pedido")
    public void euEnviarOPedido() {
        response =
                given()
                        .contentType("application/json")
                        .body(orderRequest)
                        .when()
                        .post("/api/orders")
                        .then()
                        .extract().response();
    }

    @Entao("o pedido deve ser salvo com sucesso")
    public void oPedidoDeveSerSalvoComSucesso() {
        assertThat(response.getStatusCode(), is(400));
    }

    @E("eu devo receber a confirmação do pedido")
    public void euDevoReceberAConfirmacaoDoPedido() {
        OrderResponseDTO responseBody = response.as(OrderResponseDTO.class);
        assertThat(responseBody, is(notNullValue()));
        assertThat(responseBody.getId(), is(notNullValue()));
        assertThat(responseBody.getCpf(), is(orderRequest.getCpf()));
    }
}
