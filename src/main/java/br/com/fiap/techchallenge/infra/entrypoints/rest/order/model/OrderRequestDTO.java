package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequestDTO {

    private String cpf;
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
