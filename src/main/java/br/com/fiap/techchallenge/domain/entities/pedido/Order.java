package br.com.fiap.techchallenge.domain.entities.pedido;

import br.com.fiap.techchallenge.domain.entities.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {

    private String id;
    private String cpf;
    private String status;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private LocalDateTime cancellationDate;
    private String paymentStatus;
    private List<ProdutoPedido> produtoPedidos;
    private Item items;

    public Order() {}

    public Order(Item items) {
        if (items == null) {
            throw new IllegalArgumentException("Items é um campo obrigatório no cadastro de novos pedidos.");
        }
        this.items = items;
        this.produtoPedidos = new ArrayList<>();
        this.amount = BigDecimal.ZERO;
    }

    public Order(BigDecimal amount, List<ProdutoPedido> produtoPedidos) {
        this.amount = amount;
        this.produtoPedidos = produtoPedidos;
    }

    public Order(String cpf , BigDecimal amount, List<ProdutoPedido> produtoPedidos) {
        this.cpf = cpf;
        this.amount = amount;
        this.produtoPedidos = produtoPedidos;
    }

    public Order(String id, String cpf, String status, BigDecimal amount, LocalDateTime creationDate, LocalDateTime completionDate, LocalDateTime cancellationDate, String paymentStatus, List<ProdutoPedido> produtoPedidos, Item items) {
        this(cpf, status, amount, creationDate, completionDate, cancellationDate, paymentStatus, produtoPedidos, items);
        this.id = id;
    }

    public Order(String cpf, String status, BigDecimal amount, LocalDateTime creationDate, LocalDateTime completionDate, LocalDateTime cancellationDate, String paymentStatus, List<ProdutoPedido> produtoPedidos, Item items) {
        if (status == null) {
            throw new IllegalArgumentException("Status é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (amount == null || amount.doubleValue() <= 0) {
            throw new IllegalArgumentException("Valor deve ser preenchido com valores acima de R$ 0,00 no cadastro de novos pedidos.");
        }

        if (creationDate == null) {
            throw new IllegalArgumentException("Data de criação é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (paymentStatus == null) {
            throw new IllegalArgumentException("Status de pagamento é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (produtoPedidos == null) {
            throw new IllegalArgumentException("Produtos do pedido é um campo obrigatório no cadastro de novos pedidos.");
        }

        if (items == null) {
            throw new IllegalArgumentException("Items é um campo obrigatório no cadastro de novos pedidos.");
        }

        this.cpf = cpf;
        this.status = status;
        this.amount = amount;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.cancellationDate = cancellationDate;
        this.paymentStatus = paymentStatus;
        this.produtoPedidos = produtoPedidos;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDateTime cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        this.amount = this.amount.add(subtotal);
    }

}