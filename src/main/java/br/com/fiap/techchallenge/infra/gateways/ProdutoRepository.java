package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IProdutoRepository;
import br.com.fiap.techchallenge.domain.entities.produto.Produto;
import br.com.fiap.techchallenge.infra.mapper.produto.ProdutoMapper;

public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoMapper produtoMapper;

    public ProdutoRepository(ProdutoMapper produtoMapper) {
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Produto findById(Long id) {
//        ProdutoEntity produtoEntity = produtoEntityRepository.findById(id).orElseThrow(() -> new ProdutoException(ErrosEnum.PRODUTO_NAO_ENCONTRADO));
//        return produtoMapper.fromEntityToDomain(produtoEntity);
        return new Produto();
    }

}
