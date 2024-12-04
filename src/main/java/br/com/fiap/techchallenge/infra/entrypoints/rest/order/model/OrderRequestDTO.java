package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequestDTO {

    private String cpf;
    private List<ItemRequestDTO> items;

    public OrderRequestDTO() {
        this.cpf = null;
        this.items = new ArrayList<>();
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
