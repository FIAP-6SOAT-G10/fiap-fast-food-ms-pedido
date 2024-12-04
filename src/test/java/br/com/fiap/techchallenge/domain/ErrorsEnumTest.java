package br.com.fiap.techchallenge.domain;

import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorsEnumTest {

    @Test
    void shouldReturnCorrectValuesForPedidoCodigoIdentificadorInvalido() {
        ErrosEnum erro = ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO;

        assertThat(erro.getCode()).isEqualTo("402");
        assertThat(erro.getMessage()).isEqualTo("O identificador do pedido é inválido.");
        assertThat(erro.getLogLevel()).isEqualTo(Level.ERROR);
        assertThat(erro.getHttpStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnCorrectValuesForPedidoSemClienteEItems() {
        ErrosEnum erro = ErrosEnum.PEDIDO_SEM_CLIENTE_E_ITEMS;

        assertThat(erro.getCode()).isEqualTo("404");
        assertThat(erro.getMessage()).isEqualTo("Pedido sem cliente e items");
        assertThat(erro.getLogLevel()).isEqualTo(Level.ERROR);
        assertThat(erro.getHttpStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
