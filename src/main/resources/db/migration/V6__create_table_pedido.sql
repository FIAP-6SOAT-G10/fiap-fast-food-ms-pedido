-- Script para criar a tabela pedido
CREATE TABLE IF NOT EXISTS "tech-challenge"."pedido"
(
    id bigserial NOT NULL,
    cpf varchar(11),
    valor decimal(5,2),
    status varchar(50) NOT NULL,
    status_pagamento varchar(50) NOT NULL,
    data_criacao timestamp,
    data_finalizacao timestamp,
    data_cancelamento timestamp,
    CONSTRAINT pedido_pkey PRIMARY KEY (id)
);