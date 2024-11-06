package br.com.ciceroednilson.domain.service;

import br.com.ciceroednilson.application.mapper.ProductMapper;
import br.com.ciceroednilson.application.services.CategoryServiceProvider;
import br.com.ciceroednilson.application.services.ProductServiceProvider;
import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.infrastructure.client.entity.Category;
import br.com.ciceroednilson.infrastructure.persistence.ProductPersistence;
import br.com.ciceroednilson.infrastructure.persistence.entity.ProductEntity;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService implements ProductServiceProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
    protected ProductPersistence productPersistence;
    protected CategoryServiceProvider categoryServiceProvider;

    @Inject
    public ProductService(
            final ProductPersistence productPersistence,
            final CategoryServiceProvider categoryServiceProvider
    ) {
        this.productPersistence = productPersistence;
        this.categoryServiceProvider = categoryServiceProvider;
    }

    @Override
    public void save(final ProductModel model) throws Exception {
        LOG.info("Finding a category by id");
        final Category category = this.categoryServiceProvider.findById(model.getIdCategory());
        LOG.info("Category found {}-{}",category.getId(), category.getName());

        LOG.info("saving a new product.");
        final ProductEntity entity = ProductMapper.toEntity(model);
        this.productPersistence.save(entity);
        LOG.info("product saved successfully.");
    }

    @Override
    public void update(final ProductModel model) {
        LOG.info("updating a product.");
        final ProductEntity entity = ProductMapper.toEntity(model);
        entity.setId(model.getId());
        this.productPersistence.update(entity);
        LOG.info("product updated successfully.");
    }

    @Override
    public void delete(final Long id) {
        LOG.info("deleting a product:{}", id);
        final Optional<ProductEntity> entity = this.productPersistence.findById(id);
        entity.ifPresent(productEntity -> this.productPersistence.delete(productEntity));
        LOG.info("product deleted successfully:{}", id);
    }

    @Override
    public ProductModel findById(final Long id) {
        LOG.info("find a product by id:{}", id);
        final Optional<ProductEntity> entity = this.productPersistence.findById(id);
        return entity.map(ProductMapper::toModel).orElse(null);
    }

    @Override
    public List<ProductModel> findAll() {
        LOG.info("finding all products");
        final List<ProductEntity> list = this.productPersistence.findAll();
        return ProductMapper.toListModel(list);
    }

    @Override
    public void reduceStock(final Long id) {
        LOG.info("reducing the stock of product:{}", id);
        final ProductModel model = this.findById(id);
        model.setTotal(model.getTotal() - BigInteger.ONE.intValue());
        model.setUpdated(LocalDateTime.now());
        this.update(model);
        LOG.info("reducing the stock of product:{} successfully", id);
    }
}
