package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.ItemClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.item.model.ItemResponse;
import br.com.fiap.techchallenge.infra.mapper.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemRepositoryTest {

    @Mock
    private ItemClient itemClient;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdSuccess() {
        // Arrange
        Long itemId = 1L;
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(itemId);
        itemResponse.setPreco(BigDecimal.valueOf(100.00));

        Item expectedItem = new Item(itemId, BigDecimal.valueOf(100.00));

        when(itemClient.findById(itemId)).thenReturn(new ResponseEntity<>(itemResponse, HttpStatus.OK));
        when(itemMapper.fromDataTransferObjetToDomain(itemResponse)).thenReturn(expectedItem);

        // Act
        Item actualItem = itemRepository.findById(itemId);

        // Assert
        assertNotNull(actualItem);
        assertEquals(expectedItem.getItemId(), actualItem.getItemId());
        assertEquals(expectedItem.getPrice(), actualItem.getPrice());
        verify(itemClient, times(1)).findById(itemId);
        verify(itemMapper, times(1)).fromDataTransferObjetToDomain(itemResponse);
    }

    @Test
    void testFindByIdFailure() {
        // Arrange
        Long itemId = 1L;

        when(itemClient.findById(itemId)).thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> itemRepository.findById(itemId));
        assertEquals("Erro ao buscar dados do item", exception.getMessage());

        // Assert
        verify(itemClient, times(1)).findById(itemId);
        verifyNoInteractions(itemMapper);
    }
}
