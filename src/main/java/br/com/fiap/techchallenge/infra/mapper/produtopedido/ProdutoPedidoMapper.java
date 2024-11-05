package br.com.fiap.techchallenge.infra.mapper.produtopedido;


public class ProdutoPedidoMapper {

//    public ProdutoPedido fromEntityToDomain(ProdutoPedidoEntity produtoPedidoEntity) {
//        ProdutoEntity produtoEntity = produtoPedidoEntity.getProdutoEntity();
//
//        if (produtoEntity == null) {
//            return null;
//        }
//
//        CategoriaEntity categoriaEntity = produtoEntity.getCategoriaEntity();
//        Categoria categoria = new Categoria(categoriaEntity.getNome(), categoriaEntity.getDescricao());
//        Produto produto = new Produto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), categoria, produtoEntity.getPreco(), produtoEntity.getImagem());
//
//        return new ProdutoPedido(produtoPedidoEntity.getId(), produto, produtoPedidoEntity.getValorTotal(), produtoPedidoEntity.getQuantidade());
//    }
//
//    public ProdutoPedidoEntity fromDomainToEntity(ProdutoPedido produtoPedido) {
//        CategoriaMapper categoriaMapper = new CategoriaMapper();
//        CategoriaEntity categoriaEntity = categoriaMapper.fromDomainToEntity(CategoriaEnum.fromName(produtoPedido.getProduto().getCategoria().getNome()));
//
//        Produto produto = produtoPedido.getProduto();
//        ProdutoEntity produtoEntity = new ProdutoEntity();
//        produtoEntity.setId(produto.getId());
//        produtoEntity.setCategoriaEntity(categoriaEntity);
//        produtoEntity.setNome(produto.getNome());
//        produtoEntity.setDescricao(produto.getDescricao());
//        produtoEntity.setPreco(produto.getPreco());
//        produtoEntity.setImagem(produto.getImagem());
//
//        ProdutoPedidoEntity produtoPedidoEntity = new ProdutoPedidoEntity();
//        produtoPedidoEntity.setId(produtoPedido.getId());
//        produtoPedidoEntity.setProdutoEntity(produtoEntity);
//        produtoPedidoEntity.setQuantidade(produtoPedido.getQuantidade());
//        produtoPedidoEntity.setValorTotal(produtoPedido.getValorTotal());
//
//        return produtoPedidoEntity;
//    }
//
//    public List<ProdutoPedido> fromListEntityToListDomain(List<ProdutoPedidoEntity> produtosPedido) {
//        return produtosPedido.isEmpty() ? Collections.emptyList() : produtosPedido.stream().map(this::fromEntityToDomain).toList();
//    }
//
//    public List<ProdutoPedidoEntity> fromListDomainToListEntity(List<ProdutoPedido> produtosPedido) {
//        return produtosPedido.isEmpty() ? Collections.emptyList() : produtosPedido.stream().map(this::fromDomainToEntity).toList();
//    }

}
