package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends MongoRepository<OrderEntity, String> {

}