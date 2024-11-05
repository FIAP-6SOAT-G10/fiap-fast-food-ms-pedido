package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;

import io.swagger.v3.oas.annotations.media.Schema;

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

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }
}
