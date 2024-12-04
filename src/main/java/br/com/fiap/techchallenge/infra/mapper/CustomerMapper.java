package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.CustomerEntity;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.model.CustomerResponse;

public class CustomerMapper {

    public CustomerEntity fromDomainToEntity(Customer customer) {
        return new CustomerEntity(
                customer.getCustomerId(),
                customer.getName(),
                customer.getCpf(),
                customer.getEmail());
    }

    public Customer fromDataTransferObjetToDomain(CustomerResponse customerResponse) {
        return new Customer(
                customerResponse.getId(),
                customerResponse.getName(),
                customerResponse.getCpf(),
                customerResponse.getEmail());
    }

}
