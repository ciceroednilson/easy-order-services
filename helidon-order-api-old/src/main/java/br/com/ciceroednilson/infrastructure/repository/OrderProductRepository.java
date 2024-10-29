package br.com.ciceroednilson.infrastructure.repository;

import br.com.ciceroednilson.domain.ports.OrderProductRepositoryPort;
import br.com.ciceroednilson.infrastructure.entity.KeyCompostProductXOrder;
import br.com.ciceroednilson.infrastructure.entity.OrderXProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderProductRepository implements OrderProductRepositoryPort {

    @PersistenceContext(unitName="orderPU")
    private EntityManagerFactory entityManagerFactory;

    @Inject
    public OrderProductRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Transactional
    public void persist(final OrderXProductEntity orderXProduct) {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(orderXProduct);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<OrderXProductEntity> byKey(final Long idOrder, final Long idProduct) {
        final EntityManager em = getEntityManager();
        final OrderXProductEntity orderProduct = em.find(OrderXProductEntity.class, new KeyCompostProductXOrder(idOrder, idProduct));
        em.close();
        return orderProduct != null ? Optional.of(orderProduct) : Optional.empty();
    }

    @Override
    public List<OrderXProductEntity> findAllByOrderId(final Long idOrder) {
        final EntityManager em = getEntityManager();
        final List<OrderXProductEntity> orderProducts = em.createQuery(
                        "SELECT op FROM OrderXProductEntity op WHERE op.idOrder = :idOrder", OrderXProductEntity.class)
                .setParameter("idOrder", idOrder)
                .getResultList();
        em.close();
        return orderProducts;
    }
}
