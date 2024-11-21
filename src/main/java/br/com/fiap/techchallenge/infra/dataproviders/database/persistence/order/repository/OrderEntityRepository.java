package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByStatusOrderByCreationDateDesc(String status);

    List<OrderEntity> findByPaymentStatusOrderByCreationDateDesc(String paymentStatus);

    List<OrderEntity> findAllByOrderByCreationDateDesc();
}