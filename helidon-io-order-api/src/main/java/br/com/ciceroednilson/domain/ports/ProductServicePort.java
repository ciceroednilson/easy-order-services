package br.com.ciceroednilson.domain.ports;

import br.com.ciceroednilson.domain.model.ProductModel;

public interface ProductServicePort {

    ProductModel findById(final Long id);
}
