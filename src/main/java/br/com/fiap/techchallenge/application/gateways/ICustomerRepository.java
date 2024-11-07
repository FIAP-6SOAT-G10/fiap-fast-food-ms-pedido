package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.pedido.Customer;

public interface ICustomerRepository {

    Customer findByCpf(String cpf);

}
