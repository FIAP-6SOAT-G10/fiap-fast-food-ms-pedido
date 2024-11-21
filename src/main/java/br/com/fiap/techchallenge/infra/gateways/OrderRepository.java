package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IOrderRepository;
import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.order.Order;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.order.repository.OrderEntityRepository;
import br.com.fiap.techchallenge.infra.exception.OrderException;
import br.com.fiap.techchallenge.infra.mapper.OrderMapper;
import org.springframework.data.domain.Sort;

import java.util.List;

public class OrderRepository implements IOrderRepository {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderMapper orderMapper;

    public OrderRepository(OrderEntityRepository orderEntityRepository, OrderMapper orderMapper) {
        this.orderEntityRepository = orderEntityRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order findById(String id) {
        OrderEntity order = this.orderEntityRepository.findById(id).orElseThrow(() -> new OrderException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO));
        return orderMapper.fromEntityToDomain(order);
    }

    @Override
    public List<Order> findByStatus(String status) {
        List<OrderEntity> listOrder = orderEntityRepository.findByStatusOrderByCreationDateDesc(status);
        return orderMapper.fromListEntityToListDTO(listOrder);
    }

    @Override
    public List<Order> findByPaymentStatus(String paymentStatus) {
        List<OrderEntity> listOrder = orderEntityRepository.findByPaymentStatusOrderByCreationDateDesc(paymentStatus);
        return orderMapper.fromListEntityToListDTO(listOrder);
    }

    @Override
    public List<Order> list() {
        List<OrderEntity> listOrder = orderEntityRepository.findAllByOrderByCreationDateDesc();
        return orderMapper.fromListEntityToListDTO(listOrder);
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderEntityRepository.save(orderMapper.fromDomainToEntity(order));
        return orderMapper.fromEntityToDomain(orderEntity);
    }
}
