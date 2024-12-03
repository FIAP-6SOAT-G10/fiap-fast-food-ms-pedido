package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.domain.entities.production.ProductionResponse;
import br.com.fiap.techchallenge.domain.entities.production.enums.ProductionStatusEnum;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.CustomerEntity;
import br.com.fiap.techchallenge.infra.entrypoints.queue.production.model.ProductionResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void shouldConvertDomainToEntity() {
        // Arrange
        Customer customer = new Customer(1L, "teste", "11111111111", "teste@teste.com");

        // Act
        CustomerEntity customerEntity = customerMapper.fromDomainToEntity(customer);

        // Assert
        assertThat(customerEntity).isNotNull();
        assertEquals(1L, customerEntity.getCustomerId());
        assertEquals("teste", customerEntity.getName());
        assertEquals("11111111111", customerEntity.getCpf());
        assertEquals("teste@teste.com", customerEntity.getEmail());
    }
}
