package br.com.fiap.techchallenge.application.usecases.order;

import br.com.fiap.techchallenge.application.gateways.ICustomerRepository;
import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.application.gateways.IItemRepository;
import br.com.fiap.techchallenge.domain.entities.order.Item;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.exception.PedidoException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

import static br.com.fiap.techchallenge.domain.ErrosEnum.PEDIDO_SEM_CLIENTE_E_ITEMS;

@Slf4j
public class CreateOrderUseCase {

    private final IOrderRepository orderRepository;
    private final IItemRepository itemRepository;
    private final ICustomerRepository customerRepository;

    public CreateOrderUseCase(IOrderRepository orderRepository, IItemRepository itemRepository, ICustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(Order order) {

        if (order.getCpf().isEmpty() && order.getItems() == null) {
            throw new PedidoException(PEDIDO_SEM_CLIENTE_E_ITEMS);
        }

        if (!order.getCpf().isEmpty()) {
            customerRepository.findByCpf(order.getCpf());
        }

        order.getItems().forEach(item -> {
            Item itemResponse = itemRepository.findById(item.getItemId());
            item.setPrice(itemResponse.getPrice());
        });

        order.setAmount(calculateTotalAmount(order.getItems()));
        order.setStatus("recebido");
        order.setPaymentStatus("pendente");
        return orderRepository.createOrder(order);
    }

    public BigDecimal calculateTotalAmount(List<Item> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))) // Multiplica price por quantity
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Soma os valores multiplicados
    }

//    private BigDecimal sumItemsAmount(List<Item> items) {
//        Map<Long, List<ItemPedido>> items = Stream.of(
//                        item.getLanches(),
//                        item.getAcompanhamento(),
//                        item.getBebida(),
//                        item.getSobremesa())
//                .reduce(this::unificarItens)
//                .get()
//                .stream()
//                .collect(Collectors.groupingBy(ItemPedido::getId));
//
//        return items.entrySet().stream().map(this::criarListaDeProdutosPedido)
//                .reduce(this::unificarProdutos)
//                .stream()
//                .findAny()
//                .orElse(Collections.emptyList());
//    }
//
//    private List<ItemPedido> unificarItens(List<ItemPedido> primeira, List<ItemPedido> segunda) {
//        primeira.addAll(segunda);
//        return primeira;
//    }

//    private List<OrderProduct> criarListaDeProdutosPedido(Map.Entry<Long, List<ItemPedido>> entry) {
//        Long id = entry.getKey();
//        List<ItemPedido> itens = entry.getValue();
//
//        long quantidade = itens.stream().map(ItemPedido::getQuantidade).reduce(0L, Long::sum);
//        Produto produto = produtoRepository.findById(id);
//
//        OrderProduct orderProduct = new OrderProduct(produto, produto.getPreco().multiply(BigDecimal.valueOf(quantidade)), BigInteger.valueOf(quantidade));
//
//        return List.of(orderProduct);
//    }

//    private List<Product> unificarProdutos(List<Product> primeira, List<Product> segunda) {
//        List<Product> produtos = new ArrayList<>();
//        produtos.addAll(primeira);
//        produtos.addAll(segunda);
//
//        return produtos;
//    }

}
