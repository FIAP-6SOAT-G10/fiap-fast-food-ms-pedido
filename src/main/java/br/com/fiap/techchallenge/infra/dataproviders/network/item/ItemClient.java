package br.com.fiap.techchallenge.infra.dataproviders.network.item;

import br.com.fiap.techchallenge.infra.dataproviders.network.item.model.ItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "item-client", url = "${feign.client.item.url}")
public interface ItemClient {

    @GetMapping(path = "/api/produtos/{itemId}")
    ResponseEntity<ItemResponse> findById(@PathVariable("itemId") Long itemId);

}
