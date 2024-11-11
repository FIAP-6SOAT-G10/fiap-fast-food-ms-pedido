package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.CustomerEntity;

public class CustomerMapper {

    public CustomerEntity fromDomainToEntity(Customer customer) {
        return new CustomerEntity(
                customer.getCustomerId(),
                customer.getName(),
                customer.getCpf(),
                customer.getEmail());
    }

}
