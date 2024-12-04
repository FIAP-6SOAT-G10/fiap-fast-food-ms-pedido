package br.com.fiap.techchallenge.domain.entities.order;

public class Customer {

    private Long customerId;
    private String name;
    private String cpf;
    private String email;

    public Customer() {
    }

    public Customer(Long customerId, String name, String cpf, String email) {
        this.customerId = customerId;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}