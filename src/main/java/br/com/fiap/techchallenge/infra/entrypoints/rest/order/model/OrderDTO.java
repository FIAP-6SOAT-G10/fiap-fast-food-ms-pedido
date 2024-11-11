package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Objeto que representa um pedido dentro do sistema")
public class OrderDTO {

    @Schema(description = "Campo identificador único de pedido", example = "12345678900")
    private String id;

    @Schema(description = "O cpf do cliente que será criado.", example = "123.123.123-12")
    private String cpf;

    @Schema(description = "Campo que informa o status do pedido", example = "Em Preparo")
    private String status;

    @Schema(description = "Campo que informa o valor totla do pedido", example = "89.25")
    private BigDecimal amount;

    @Schema(description = "Data de criação do pedido", example = "02/05/2024")
    private LocalDateTime creationDate;

    @Schema(description = "Data de finalização do pedido", example = "02/05/2024")
    private LocalDateTime completionDate;

    @Schema(description = "Data de cancelamento do pedido", example = "02/05/2024")
    private LocalDateTime cancellationDate;

    @Schema(description = "Campo que informa o status de pagamento do pedido", example = "Pago")
    private String paymentStatus;

    @Schema(description = "Campo que informa os produtos do pedido")
    private List<ItemDTO> items;

    public OrderDTO() {

    }

    public OrderDTO(String id, String cpf, String status, BigDecimal amount, LocalDateTime creationDate, LocalDateTime completionDate, LocalDateTime cancellationDate, String paymentStatus, List<ItemDTO> items) {
        this.id = id;
        this.cpf = cpf;
        this.status = status;
        this.amount = amount;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.cancellationDate = cancellationDate;
        this.paymentStatus = paymentStatus;
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

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
