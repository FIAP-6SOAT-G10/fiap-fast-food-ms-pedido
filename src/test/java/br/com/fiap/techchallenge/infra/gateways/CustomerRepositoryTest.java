package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.domain.entities.order.Customer;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.CustomerClient;
import br.com.fiap.techchallenge.infra.dataproviders.network.customer.model.CustomerResponse;
import br.com.fiap.techchallenge.infra.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRepositoryTest {

    @Mock
    private CustomerClient customerClient;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByCpfSuccess() {
        // Arrange
        Long customerId = 1L;
        String name = "João da Silva";
        String cpf = "12345678900";
        String email = "teste@teste.com";
        CustomerResponse customerResponse = new CustomerResponse(customerId, cpf, name, email);
        Customer expectedCustomer = new Customer(customerId, name, cpf, email);

        when(customerClient.findByCpf(cpf)).thenReturn(new ResponseEntity<>(customerResponse, HttpStatus.OK));
        when(customerMapper.fromDataTransferObjetToDomain(customerResponse)).thenReturn(expectedCustomer);

        // Act
        Customer actualCustomer = customerRepository.findByCpf(cpf);

        // Assert
        assertNotNull(actualCustomer);
        assertEquals(expectedCustomer.getCpf(), actualCustomer.getCpf());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        verify(customerClient, times(1)).findByCpf(cpf);
        verify(customerMapper, times(1)).fromDataTransferObjetToDomain(customerResponse);
    }

    @Test
    void testFindByCpfFailure() {
        // Arrange
        String cpf = "12345678900";

        when(customerClient.findByCpf(cpf)).thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> customerRepository.findByCpf(cpf));
        assertEquals("Erro ao buscar dados do cliente", exception.getMessage());

        // Assert
        verify(customerClient, times(1)).findByCpf(cpf);
        verifyNoInteractions(customerMapper);
    }
}
