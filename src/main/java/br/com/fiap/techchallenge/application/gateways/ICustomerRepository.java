package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Customer;

public interface ICustomerRepository {

    Customer findByCpf(String cpf);

}
