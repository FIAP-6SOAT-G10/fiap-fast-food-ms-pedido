package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

public class DadosPagamentoDTO {

    private Long id;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}