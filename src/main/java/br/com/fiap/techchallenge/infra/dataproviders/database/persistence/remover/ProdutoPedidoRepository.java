package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover;

import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.ProdutoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedidoEntity, Long> {
}