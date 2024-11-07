package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.pedido.Item;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.ItemEntity;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemDTO;

import java.util.List;

public class ItemMapper {

    public Item fromDTOToDomain(ItemDTO itemDTO) {
        return new Item(itemDTO.getId(), itemDTO.getQuantity());
    }

    public List<Item> fromListDTOToListDomain(List<ItemDTO> list) {
        return list.stream().map(this::fromDTOToDomain).toList();
    }

    public ItemEntity fromDomainToEntity(Item item) {
        return new ItemEntity(item.getItemId(), item.getPrice(), item.getQuantity());
    }

    public List<ItemEntity> fromListDomainToListEntity(List<Item> list) {
        return list.stream().map(this::fromDomainToEntity).toList();
    }

    public ItemDTO fromDomainToDTO(Item item) {
        return new ItemDTO(item.getItemId(), item.getPrice(), item.getQuantity());
    }

    public List<ItemDTO> fromListDomainToListDTO(List<Item> list) {
        return list.stream().map(this::fromDomainToDTO).toList();
    }

    public Item fromEntityToDomain(ItemEntity item) {
        return new Item(item.getItemId(), item.getPrice(), item.getQuantity());
    }

    public List<Item> fromListEntityToListDomain(List<ItemEntity> list) {
        return list.stream().map(this::fromEntityToDomain).toList();
    }

}
