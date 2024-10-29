package br.com.ciceroednilson.infrastructure.repository;

import br.com.ciceroednilson.domain.ports.OrderRepositoryPort;
import br.com.ciceroednilson.infrastructure.entity.OrderEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepository implements OrderRepositoryPort {

    @PersistenceContext(unitName="orderPU")
    private EntityManager entityManager;

    @Inject
    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void persist(final OrderEntity entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        final OrderEntity entity = entityManager.find(OrderEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Optional<OrderEntity> findById(final Long id) {
        final OrderEntity order = entityManager.find(OrderEntity.class, id);
        return order != null ? Optional.of(order) : Optional.empty();
    }

    @Override
    public List<OrderEntity> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", OrderEntity.class).getResultList();
    }
}
