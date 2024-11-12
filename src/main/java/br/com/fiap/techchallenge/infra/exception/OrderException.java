package br.com.fiap.techchallenge.infra.exception;

import br.com.fiap.techchallenge.domain.ErrosEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderException extends BaseException {

    public OrderException(ErrosEnum error, String... violations) {
        super(error, violations);
    }
}
