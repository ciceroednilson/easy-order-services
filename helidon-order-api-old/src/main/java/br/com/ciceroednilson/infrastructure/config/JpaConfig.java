package br.com.ciceroednilson.infrastructure.config;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class JpaConfig {

    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("orderPU");
    }

    @Produces
    @ApplicationScoped
    public EntityManagerFactory produceEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Produces
    @ApplicationScoped
    public EntityManager produceEntityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }
}
