package br.com.fiap.techchallenge.infra.dataproviders.network.customer;

import br.com.fiap.techchallenge.infra.dataproviders.network.customer.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-client", url = "${feign.client.customer.url}")
public interface CustomerClient {

    @GetMapping(path = "/api/customers/cpf/{cpf}")
    ResponseEntity<CustomerResponse> findByCpf(@PathVariable("cpf") String cpf);

}
