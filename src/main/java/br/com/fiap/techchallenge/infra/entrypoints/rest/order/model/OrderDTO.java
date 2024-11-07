package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
