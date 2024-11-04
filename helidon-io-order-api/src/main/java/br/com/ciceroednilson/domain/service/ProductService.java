package br.com.ciceroednilson.domain.service;

import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.domain.ports.KafkaMessageProducerPort;
import br.com.ciceroednilson.domain.ports.ProductRestPort;
import br.com.ciceroednilson.domain.ports.ProductServicePort;
import br.com.ciceroednilson.infrastructure.rest.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class ProductService implements ProductServicePort {

    private final ProductRestPort productRestPort;
    private final KafkaMessageProducerPort kafkaMessageProducerPort;

    @Inject
    public ProductService(ProductRestPort productRestPort,
                          KafkaMessageProducerPort kafkaMessageProducerPort) {
        this.productRestPort = productRestPort;
        this.kafkaMessageProducerPort = kafkaMessageProducerPort;
    }

    @Override
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

    @Override
    public void reduceStock(final long order, final List<Long> ids) throws IOException {
        this.kafkaMessageProducerPort.publishMessage(order, ids);
    }
}
