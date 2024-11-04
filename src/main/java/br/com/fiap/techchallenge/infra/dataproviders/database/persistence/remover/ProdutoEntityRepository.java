package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover;

import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoEntityRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<List<ProdutoEntity>> findAllByCategoriaEntityId(Long idCategoria);

    Optional<List<ProdutoEntity>> findByNomeOrDescricaoOrPreco(String nome, String descricao, BigDecimal preco);

}