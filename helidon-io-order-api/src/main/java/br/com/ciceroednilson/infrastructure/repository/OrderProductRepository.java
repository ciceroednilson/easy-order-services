package br.com.ciceroednilson.infrastructure.repository;

import br.com.ciceroednilson.domain.ports.OrderProductRepositoryPort;
import br.com.ciceroednilson.infrastructure.repository.entity.KeyCompostProductXOrder;
import br.com.ciceroednilson.infrastructure.repository.entity.OrderXProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderProductRepository implements OrderProductRepositoryPort {

    @PersistenceContext(unitName = "orderPU")
    private EntityManager entityManager;

    @Transactional
    public void persist(final OrderXProductEntity orderXProduct) {        ;
        entityManager.persist(orderXProduct);
    }

    @Override
    public Optional<OrderXProductEntity> byKey(final Long idOrder, final Long idProduct) {
        final OrderXProductEntity orderProduct = entityManager.find(OrderXProductEntity.class, new KeyCompostProductXOrder(idOrder, idProduct));
        entityManager.close();
        return orderProduct != null ? Optional.of(orderProduct) : Optional.empty();
    }

    @Override
    public List<OrderXProductEntity> findAllByOrderId(final Long idOrder) {
        final List<OrderXProductEntity> orderProducts = entityManager.createQuery(
                        "SELECT op FROM OrderXProductEntity op WHERE op.idOrder = :idOrder", OrderXProductEntity.class)
                .setParameter("idOrder", idOrder)
                .getResultList();
        entityManager.close();
        return orderProducts;
    }
}
