package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.ItemEntity;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.model.ItemResponse;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemRequestDTO;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemResponseDTO;

import java.util.List;

public class ItemMapper {

    public Item fromDTOToDomain(ItemRequestDTO itemRequestDTO) {
        return new Item(itemRequestDTO.getId(), itemRequestDTO.getQuantity());
    }

    public List<Item> fromListDTOToListDomain(List<ItemRequestDTO> list) {
        return list.stream().map(this::fromDTOToDomain).toList();
    }

    public ItemEntity fromDomainToEntity(Item item) {
        return new ItemEntity(item.getItemId(), item.getPrice(), item.getQuantity());
    }

    public List<ItemEntity> fromListDomainToListEntity(List<Item> list) {
        return list.stream().map(this::fromDomainToEntity).toList();
    }

    public ItemResponseDTO fromDomainToResponseDTO(Item item) {
        return new ItemResponseDTO(item.getItemId(), item.getPrice(), item.getQuantity());
    }

    public List<ItemResponseDTO> fromListDomainToListResponseDTO(List<Item> list) {
        return list.stream().map(this::fromDomainToResponseDTO).toList();
    }

    public Item fromEntityToDomain(ItemEntity item) {
        return new Item(item.getItemId(), item.getPrice(), item.getQuantity());
    }

    public List<Item> fromListEntityToListDomain(List<ItemEntity> list) {
        return list.stream().map(this::fromEntityToDomain).toList();
    }

    public Item fromDataTransferObjetToDomain(ItemResponse itemResponse) {
        return new Item(
                itemResponse.getId(),
                itemResponse.getPreco());
    }

}
