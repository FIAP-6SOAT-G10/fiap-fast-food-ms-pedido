package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.domain.entities.pedido.Item;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.ItemClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.model.ItemResponse;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class ItemRepository implements IItemRepository {

    private final ItemClient itemClient;

    public ItemRepository(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

    @Override
    public Item findById(Long id) {
//        ResponseEntity<ItemResponse> response = itemClient.findById(id);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Erro ao buscar dados do item");
//        }
//        return response.getBody();

        return new Item(id, BigDecimal.valueOf(1.2));
    }

}
