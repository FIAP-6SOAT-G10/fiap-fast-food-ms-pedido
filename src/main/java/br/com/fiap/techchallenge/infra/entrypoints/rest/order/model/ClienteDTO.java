package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "Cliente", description = "Objeto que representa um cliente dentro do sistema")
public class ClienteDTO{

        @Schema(description = "O identificador do cliente.", example = "1")
        private Long id;

        @Schema(description = "O cpf do cliente que será criado.", example = "123.123.123-12")
        private String cpf;

        @Schema(description = "O nome do cliente que será criado.", example = "João da Silva")
        private String nome;

        @Schema(description = "O e-mail do cliente que será criado.", example = "teste@teste.com.br")
        private String email;
        
}
