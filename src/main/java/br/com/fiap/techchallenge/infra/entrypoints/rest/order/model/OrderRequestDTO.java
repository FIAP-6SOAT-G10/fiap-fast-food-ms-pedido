package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Objeto que representa um pedido dentro do sistema")
public class OrderRequestDTO {

    @Schema(description = "O cpf do cliente que será criado.", example = "123.123.123-12")
    private String cpf;

    @Schema(description = "Campo que informa os produtos do pedido")
    private List<ItemRequestDTO> items;

    public OrderRequestDTO() {

    }

    public OrderRequestDTO(String cpf, List<ItemRequestDTO> items) {
        this.cpf = cpf;
        this.items = items;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemRequestDTO> items) {
        this.items = items;
    }
}
