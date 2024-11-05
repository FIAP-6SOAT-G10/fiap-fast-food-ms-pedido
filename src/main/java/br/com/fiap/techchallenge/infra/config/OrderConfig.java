package br.com.fiap.techchallenge.infra.config;

import br.com.fiap.techchallenge.application.gateways.IPedidoRepository;
import br.com.fiap.techchallenge.application.gateways.IProdutoRepository;
import br.com.fiap.techchallenge.application.usecases.order.CreateOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.GetOrderUseCase;
import br.com.fiap.techchallenge.application.usecases.order.UpdateOrderUseCase;
import br.com.fiap.techchallenge.infra.entrypoints.rest.order.PedidoController;
import br.com.fiap.techchallenge.infra.gateways.PedidoRepository;
import br.com.fiap.techchallenge.infra.gateways.ProdutoRepository;
import br.com.fiap.techchallenge.infra.mapper.pedido.PedidoMapper;
import br.com.fiap.techchallenge.infra.mapper.produto.ProdutoMapper;
import br.com.fiap.techchallenge.infra.mapper.produtopedido.ProdutoPedidoMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.PedidoEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

//    @Bean
//    public PedidoController createOrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, UpdateOrderUseCase updateOrderUseCase) {
//        return new PedidoController(createOrderUseCase, getOrderUseCase, updateOrderUseCase);
//    }

    @Bean
    public IPedidoRepository criarPedidoProdutoRepository(PedidoEntityRepository pedidoEntityRepository, PedidoMapper pedidoMapper) {
        return new PedidoRepository(pedidoEntityRepository, pedidoMapper);
    }

    @Bean
    public IProdutoRepository createProductRepository(ProdutoMapper produtoMapper) {
        return new ProdutoRepository(produtoMapper);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(IPedidoRepository pedidoRepository, IProdutoRepository produtoRepository) {
        return new CreateOrderUseCase(pedidoRepository, produtoRepository);
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
    public ProdutoMapper createProdutoMapper() {
        return new ProdutoMapper();
    }

    @Bean
    public ProdutoPedidoMapper criarProdutoPedidoMapper() {
        return new ProdutoPedidoMapper();
    }

}
