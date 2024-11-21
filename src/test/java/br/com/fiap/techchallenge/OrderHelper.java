package br.com.fiap.techchallenge;

import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderHelper {

    public static Order buildOrder() {
        Order order = new Order();
        order.setCpf(RandomStringUtils.random(11, false, true));
        order.setAmount(BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.CEILING));
        order.setItems(List.of(
                new Item(1L, BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.CEILING), 1L),
                new Item(2L, BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.CEILING), 1L)
        ));
        return order;
    }

}
