package br.com.fiap.techchallenge.domain.entities.pedido;

import br.com.fiap.techchallenge.domain.entities.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Pedido {

    private Long id;
    private Cliente cliente;
    private String status;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;
    private LocalDateTime dataCancelamento;
    private String statusPagamento;
    private List<ProdutoPedido> produtoPedidos;
    private Item items;

    public Pedido() {}

    public Pedido(Cliente cliente, Item items) {
        this(items);
        this.cliente = cliente;
    }

    public Pedido(Item items) {
        if (items == null) {
            throw new IllegalArgumentException("Items é um campo obrigatório no cadastro de novos pedidos.");
        }
        this.items = items;
        this.produtoPedidos = new ArrayList<>();
        this.valor = BigDecimal.ZERO;
    }

    public Pedido(BigDecimal valor, List<ProdutoPedido> produtoPedidos) {
        this.valor = valor;
        this.produtoPedidos = produtoPedidos;
    }

    public Pedido(Cliente cliente , BigDecimal valor, List<ProdutoPedido> produtoPedidos) {
        this.cliente = cliente;
        this.valor = valor;
        this.produtoPedidos = produtoPedidos;
    }

    public Pedido(Long id, Cliente cliente, String status, BigDecimal valor, LocalDateTime dataCriacao, LocalDateTime dataFinalizacao, LocalDateTime dataCancelamento, String statusPagamento, List<ProdutoPedido> produtoPedidos, Item items) {
        this(cliente, status, valor, dataCriacao, dataFinalizacao, dataCancelamento, statusPagamento, produtoPedidos, items);
        this.id = id;
    }

    public Pedido(Cliente cliente, String status, BigDecimal valor, LocalDateTime dataCriacao, LocalDateTime dataFinalizacao, LocalDateTime dataCancelamento, String statusPagamento, List<ProdutoPedido> produtoPedidos, Item items) {
        if (status == null) {
            throw new IllegalArgumentException("Status é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (valor == null || valor.doubleValue() <= 0) {
            throw new IllegalArgumentException("Valor deve ser preenchido com valores acima de R$ 0,00 no cadastro de novos pedidos.");
        }

        if (dataCriacao == null) {
            throw new IllegalArgumentException("Data de criação é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (statusPagamento == null) {
            throw new IllegalArgumentException("Status de pagamento é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (produtoPedidos == null) {
            throw new IllegalArgumentException("Produtos do pedido é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (items == null) {
            throw new IllegalArgumentException("Items é um campo obrigatório no cadastro de novos pedidos.");
        }

        this.cliente = cliente;
        this.status = status;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
        this.dataFinalizacao = dataFinalizacao;
        this.dataCancelamento = dataCancelamento;
        this.statusPagamento = statusPagamento;
        this.produtoPedidos = produtoPedidos;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public List<ProdutoPedido> getProdutoPedidos() {
        return produtoPedidos;
    }

    public void setProdutoPedidos(List<ProdutoPedido> produtoPedidos) {
        this.produtoPedidos = produtoPedidos;
    }

    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }

    public void totalizar(BigDecimal subtotal) {
        this.valor = this.valor.add(subtotal);
    }

}