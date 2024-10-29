package br.com.ciceroednilson.domain.ports;

import br.com.ciceroednilson.infrastructure.entity.OrderXProductEntity;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepositoryPort {

    void persist(OrderXProductEntity orderXProduct);
    Optional<OrderXProductEntity> byKey(Long idOrder, Long idProduct);
    List<OrderXProductEntity> findAllByOrderId(Long idOrder);
}
