package br.com.fiap.techchallenge.domain;

import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;

public enum ErrosEnum {

    PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO("402", "O identificador do pedido é inválido.", Level.ERROR, HttpStatus.BAD_REQUEST),
    PEDIDO_SEM_CLIENTE_E_ITEMS("404", "Pedido sem cliente e items", Level.ERROR, HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final Level logLevel;
    private final HttpStatus httpStatusCode;

    ErrosEnum(String code, String message, Level logLevel, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
        this.httpStatusCode = httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}
