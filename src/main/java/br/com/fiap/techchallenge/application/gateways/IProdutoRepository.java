package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entities.produto.Produto;

public interface IProdutoRepository {

    Produto findById(Long id);

}
