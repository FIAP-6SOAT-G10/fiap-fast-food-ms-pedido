package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository;


import org.springframework.data.mongodb.core.mapping.Field;

public class CustomerEntity {

    @Field("customer_id")
    private Long customerId;

    @Field("name")
    private String name;

    @Field("cpf")
    private String cpf;

    @Field("email")
    private String email;

    public CustomerEntity() { }

    public CustomerEntity(Long customerId, String name, String cpf, String email) {
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