# language: pt
Funcionalidade: Gerenciamento de Pedidos
  Como um cliente
  Quero criar, atualizar e visualizar pedidos
  Para garantir que meus pedidos sejam processados corretamente

  Cenário: Criar um novo pedido
    Dado que eu tenha os detalhes do pedido
    Quando eu enviar o pedido
    Então o pedido deve ser salvo com sucesso
    E eu devo receber a confirmação do pedido