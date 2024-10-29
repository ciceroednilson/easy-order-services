package br.com.ciceroednilson.domain.ports;

import br.com.ciceroednilson.infrastructure.rest.entity.ProductEntity;

public interface ProductRestPort {

    ProductEntity findById(final Long id);
}
