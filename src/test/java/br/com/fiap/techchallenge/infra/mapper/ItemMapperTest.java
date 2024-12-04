package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.ItemEntity;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.model.ItemRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemMapperTest {

    private final ItemMapper itemMapper = new ItemMapper();

    @Test
    void shouldConvertDTOToDomain() {
        // Arrange
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO(1L, 2L);

        // Act
        Item item = itemMapper.fromDTOToDomain(itemRequestDTO);

        // Assert
        assertThat(item).isNotNull();
        assertEquals(1L, item.getItemId());
        assertEquals(2L, item.getQuantity());
    }

    @Test
    void shouldConvertDomainToEntity() {
        // Arrange
        Item item = new Item(1L, BigDecimal.valueOf(20), 2L);

        // Act
        ItemEntity itemEntity = itemMapper.fromDomainToEntity(item);

        // Assert
        assertThat(itemEntity).isNotNull();
        assertEquals(1L, itemEntity.getItemId());
        assertEquals(BigDecimal.valueOf(20), itemEntity.getPrice());
        assertEquals(2L, itemEntity.getQuantity());
    }

    @Test
    void shouldConvertEntityToDomain() {
        // Arrange
        ItemEntity itemEntity = new ItemEntity(1L, BigDecimal.valueOf(20), 2L);

        // Act
        Item item = itemMapper.fromEntityToDomain(itemEntity);

        // Assert
        assertThat(item).isNotNull();
        assertEquals(1L, item.getItemId());
        assertEquals(BigDecimal.valueOf(20), item.getPrice());
        assertEquals(2L, item.getQuantity());
    }

    @Test
    void shouldHandleEmptyLists() {
        // Act & Assert
        assertThat(itemMapper.fromListDTOToListDomain(Collections.emptyList())).isEmpty();
        assertThat(itemMapper.fromListDomainToListEntity(Collections.emptyList())).isEmpty();
        assertThat(itemMapper.fromListEntityToListDomain(Collections.emptyList())).isEmpty();
        assertThat(itemMapper.fromListDomainToListResponseDTO(Collections.emptyList())).isEmpty();
    }

}
