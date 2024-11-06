package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoEntityRepository extends MongoRepository<OrderEntity, String> {

//    @Query(value = "SELECT p FROM PedidoEntity p JOIN FETCH p.produtos WHERE p.id = :id")
//    Optional<PedidoEntity> loadPedidoById(@Param("id") Long id);

}