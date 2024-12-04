package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.ItemClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.model.ItemResponse;
import br.com.fiap.techchallenge.infra.exception.IntegrationException;
import br.com.fiap.techchallenge.infra.mapper.ItemMapper;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class ItemRepository implements IItemRepository {

    private final ItemClient itemClient;

    private final ItemMapper itemMapper;

    public ItemRepository(ItemClient itemClient, ItemMapper itemMapper) {
        this.itemClient = itemClient;
        this.itemMapper = itemMapper;
    }

    @Override
    public Item findById(Long id) {
        ResponseEntity<ItemResponse> response = itemClient.findById(id);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IntegrationException("Erro ao buscar dados do item");
        }
        return itemMapper.fromDataTransferObjetToDomain(Objects.requireNonNull(response.getBody()));
    }
}
