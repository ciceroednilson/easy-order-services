package br.com.ciceroednilson.domain.service;

import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.domain.ports.ProductRestPort;
import br.com.ciceroednilson.domain.ports.ProductServicePort;
import br.com.ciceroednilson.infrastructure.rest.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductService implements ProductServicePort {

    private final ProductRestPort productRestPort;

    @Inject
    public ProductService(ProductRestPort productRestPort) {
        this.productRestPort = productRestPort;
    }

    public ProductModel findById(final Long id) {
        final ProductEntity productEntity = productRestPort.findById(id);
        if (productEntity != null) {
            return new ProductModel(
                    productEntity.getId(),
                    productEntity.getPrice(),
                    productEntity.getActive()
            );
        }
        return null;
    }

}
