package br.com.ciceroednilson.domain.service;

import br.com.ciceroednilson.domain.model.OrderModel;
import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.domain.ports.OrderProductRepositoryPort;
import br.com.ciceroednilson.domain.ports.OrderRepositoryPort;
import br.com.ciceroednilson.domain.ports.OrderServicePort;
import br.com.ciceroednilson.domain.ports.ProductServicePort;
import br.com.ciceroednilson.infrastructure.repository.entity.OrderEntity;
import br.com.ciceroednilson.infrastructure.repository.entity.OrderXProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javassist.NotFoundException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService implements OrderServicePort {

    private final ProductServicePort productServicePort;
    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderProductRepositoryPort orderProductRepositoryPort;

    @Inject
    public OrderService(final ProductServicePort productServicePort,
                        final OrderRepositoryPort orderRepositoryPort,
                        final OrderProductRepositoryPort orderProductRepositoryPort) {
        this.productServicePort = productServicePort;
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderProductRepositoryPort = orderProductRepositoryPort;
    }

    @Override
    public String create(final OrderModel model) throws Exception {
        final List<String> errors = validateProduct(model.getProducts());
        if (!errors.isEmpty()) {
            throw new NotFoundException(String.join(", ", errors));
        }
        final long idOrder = this.save(model);
        try {
            this.productServicePort.reduceStock(idOrder, model.getProducts());
        } catch (final IOException ex) {
            throw new IOException("Error to send a message to broker:" + ex.getMessage());
        }
        return "The order created successfully";
    }

    private long save(final OrderModel model) {
        final OrderEntity entity = buildOrderEntity(model);
        this.orderRepositoryPort.persist(entity);
        model.getProducts().forEach(product -> {
            final OrderXProductEntity orderXProduct = buildOrderXProductEntity(entity, product);
            this.orderProductRepositoryPort.persist(orderXProduct);
        });
        return entity.getId();
    }

    private OrderXProductEntity buildOrderXProductEntity(final OrderEntity entity, final long product) {
        final OrderXProductEntity orderXProductEntity = new OrderXProductEntity();
        orderXProductEntity.setIdProduct(product);
        orderXProductEntity.setIdOrder(entity.getId());
        orderXProductEntity.setDtCreated(LocalDateTime.now());
        orderXProductEntity.setDtUpdated(LocalDateTime.now());
        return orderXProductEntity;
    }

    private OrderEntity buildOrderEntity(final OrderModel model) {
        final BigDecimal value =
                model.getProducts()
                        .stream()
                        .map(id -> findProduct(id).getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        final OrderEntity entity = new OrderEntity();
        entity.setActive(Boolean.TRUE);
        entity.setCreated(LocalDateTime.now());
        entity.setUpdated(LocalDateTime.now());
        entity.setValue(value);
        return entity;
    }

    private ProductModel findProduct(final Long id) {
        return productServicePort.findById(id);
    }

    private List<String> validateProduct(final List<Long> products) {
        List<String> errors = new ArrayList<>();
        products.forEach(product -> {
            final ProductModel model = findProduct(product);
            if (model == null) {
                errors.add(String.format("The product %s is invalid!", product));
            }
            if (model != null && !model.isActive()) {
                errors.add(String.format("The product %s is inactive!", product));
            }
        });
        return errors;
    }
}
