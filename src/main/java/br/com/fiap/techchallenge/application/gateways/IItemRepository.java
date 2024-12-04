package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Item;

public interface IItemRepository {

    Item findById(Long id);

}
