package br.com.fiap.techchallenge.infra.mapper.produto;

public class ProdutoMapper {

//    public ProdutoEntity fromDomainToEntity(Produto produto) {
//        CategoriaEntity categoriaEntity = new CategoriaEntity();
//        categoriaEntity.setNome(produto.getCategoria().getNome());
//        categoriaEntity.setDescricao(produto.getCategoria().getDescricao());
//
//        ProdutoEntity produtoEntity = new ProdutoEntity();
//        produtoEntity.setId(produto.getId());
//        produtoEntity.setNome(produto.getNome());
//        produtoEntity.setDescricao(produto.getDescricao());
//        produtoEntity.setCategoriaEntity(categoriaEntity);
//        produtoEntity.setPreco(produto.getPreco());
//        produtoEntity.setImagem(produto.getImagem());
//
//        return produtoEntity;
//    }
//
//    public Produto fromEntityToDomain(ProdutoEntity produtoEntity) {
//        CategoriaEntity categoriaEntity = produtoEntity.getCategoriaEntity();
//        Categoria categoria = new Categoria(categoriaEntity.getNome(), categoriaEntity.getDescricao());
//        return new Produto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), categoria, produtoEntity.getPreco(), produtoEntity.getImagem());
//    }
//
//    public List<Produto> fromListEntityToListDomain(List<ProdutoEntity> produtos) {
//        return produtos.stream().map(this::fromEntityToDomain).toList();
//    }

}