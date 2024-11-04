package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IProdutoRepository;
import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.produto.Produto;
import br.com.fiap.techchallenge.infra.exception.ProdutoException;
import br.com.fiap.techchallenge.infra.mapper.produto.ProdutoMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.ProdutoEntityRepository;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.ProdutoEntity;

public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoEntityRepository produtoEntityRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoRepository(ProdutoEntityRepository produtoEntityRepository, ProdutoMapper produtoMapper) {
        this.produtoEntityRepository = produtoEntityRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Produto findById(Long id) {
        ProdutoEntity produtoEntity = produtoEntityRepository.findById(id).orElseThrow(() -> new ProdutoException(ErrosEnum.PRODUTO_NAO_ENCONTRADO));
        return produtoMapper.fromEntityToDomain(produtoEntity);
    }

}
