package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover;

import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaEntityRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNome(String nome);

}
