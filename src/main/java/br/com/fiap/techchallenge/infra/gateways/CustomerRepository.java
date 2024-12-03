package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.CustomerClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.model.CustomerResponse;
import br.com.fiap.techchallenge.infra.exception.IntegrationException;
import br.com.fiap.techchallenge.infra.mapper.CustomerMapper;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class CustomerRepository implements ICustomerRepository {

    private final CustomerClient customerClient;

    private final CustomerMapper customerMapper;

    public CustomerRepository(CustomerClient customerClient, CustomerMapper customerMapper) {
        this.customerClient = customerClient;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer findByCpf(String cpf) {
        ResponseEntity<CustomerResponse> response = customerClient.findByCpf(cpf);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IntegrationException("Erro ao buscar dados do cliente");
        }
        return customerMapper.fromDataTransferObjetToDomain(Objects.requireNonNull(response.getBody()));
    }
}
