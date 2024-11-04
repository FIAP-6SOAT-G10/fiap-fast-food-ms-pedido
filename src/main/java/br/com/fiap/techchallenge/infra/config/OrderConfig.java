package br.com.fiap.techchallenge.infra.config;

import br.com.fiap.techchallenge.application.gateways.IPedidoRepository;
import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.infra.gateways.PedidoRepository;
import br.com.fiap.techchallenge.infra.mapper.pedido.PedidoMapper;
import br.com.fiap.techchallenge.infra.mapper.produtopedido.ProdutoPedidoMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.ClienteEntityRepository;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.PedidoEntityRepository;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.ProdutoPedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public IPedidoRepository criarPedidoProdutoRepository(PedidoEntityRepository pedidoEntityRepository, PedidoMapper pedidoMapper, ClienteEntityRepository clienteRepository, ProdutoPedidoRepository produtoPedidoRepository, ProdutoPedidoMapper produtoPedidoMapper) {
        return new PedidoRepository(pedidoEntityRepository, pedidoMapper, clienteRepository, produtoPedidoRepository, produtoPedidoMapper);
    }

    @Bean
    public GetOrderUseCase criarGetPedidoUseCase(IPedidoRepository pedidoRepository) {
        return new GetOrderUseCase(pedidoRepository);
    }

    @Bean
    public UpdateOrderUseCase criarPatchPedidoUseCase(IPedidoRepository pedidoRepository) {
        return new UpdateOrderUseCase(pedidoRepository);
    }

    @Bean
    public PedidoMapper criarPedidoMapper() {
        return new PedidoMapper();
    }

    @Bean
    public ProdutoPedidoMapper criarProdutoPedidoMapper() {
        return new ProdutoPedidoMapper();
    }

}
