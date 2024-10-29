package br.com.ciceroednilson.domain.ports;

import br.com.ciceroednilson.infrastructure.repository.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    void persist(OrderEntity entity);
    void delete(final Long id);
    Optional<OrderEntity> findById(Long id);
    List<OrderEntity> findAll();
}
