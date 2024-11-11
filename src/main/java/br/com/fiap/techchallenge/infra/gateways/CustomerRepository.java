package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.CustomerClient;

public class CustomerRepository implements ICustomerRepository {

    private final CustomerClient customerClient;

    public CustomerRepository(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Customer findByCpf(String cpf) {
//        ResponseEntity<CustomerResponse> response = customerClient.findByCpf(cpf);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Erro ao buscar dados do cliente");
//        }
//        return response.getBody();

        return new Customer(Long.parseLong("1"), "João da Silva", cpf, "teste@teste.com");
    }

}
