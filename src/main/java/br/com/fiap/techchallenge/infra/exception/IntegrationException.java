package br.com.fiap.techchallenge.infra.exception;

public class IntegrationException extends RuntimeException{

    public IntegrationException(String message) {
        super(message);
    }
}
