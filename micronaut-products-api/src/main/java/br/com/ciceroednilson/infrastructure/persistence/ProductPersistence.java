package br.com.ciceroednilson.infrastructure.persistence;

import br.com.ciceroednilson.infrastructure.persistence.entity.ProductEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ProductPersistence extends JpaRepository<ProductEntity, Long> {
}
