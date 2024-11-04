package br.com.ciceroednilson.domain.ports;

import br.com.ciceroednilson.domain.model.ProductModel;

import java.io.IOException;
import java.util.List;

public interface ProductServicePort {

    ProductModel findById(final Long id);
    void reduceStock(long order, final List<Long> ids) throws IOException;
}
