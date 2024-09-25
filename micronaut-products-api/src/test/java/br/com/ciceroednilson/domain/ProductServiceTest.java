package br.com.ciceroednilson.domain;

import br.com.ciceroednilson.application.services.CategoryServiceProvider;
import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.domain.service.ProductService;
import br.com.ciceroednilson.infrastructure.client.entity.Category;
import br.com.ciceroednilson.infrastructure.persistence.ProductPersistence;
import br.com.ciceroednilson.infrastructure.persistence.entity.ProductEntity;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@MicronautTest
class ProductServiceTest {

    @Inject
    ProductService productService;

    @Inject
    ProductPersistence productPersistence;

    @Inject
    CategoryServiceProvider categoryServiceProvider;

    @MockBean(ProductPersistence.class)
    ProductPersistence mockProductPersistence() {
        return Mockito.mock(ProductPersistence.class);
    }

    @MockBean(CategoryServiceProvider.class)
    CategoryServiceProvider mockCategoryServiceProvider() {
        return Mockito.mock(CategoryServiceProvider.class);
    }

    ProductModel buildProductModel() {
        final ProductModel model = new ProductModel();
        model.setIdCategory(1L);
        model.setProduct("Mario Bros");
        model.setPrice(BigDecimal.ONE);
        model.setCreated(LocalDateTime.now());
        model.setUpdated(LocalDateTime.now());
        model.setActive(Boolean.TRUE);
        return model;
    }

    ProductEntity buildProductEntity() {
        final ProductEntity entity = new ProductEntity();
        entity.setIdCategory(1L);
        entity.setProduct("Mario Bros");
        entity.setPrice(BigDecimal.ONE);
        entity.setCreated(LocalDateTime.now());
        entity.setUpdated(LocalDateTime.now());
        entity.setActive(Boolean.TRUE);
        return entity;
    }

    Category buildCategory() {
        final Category category = new Category();
        category.setId(1L);
        category.setName("Games");
        return category;
    }

    @Test
    void testSaveProduct() throws Exception {
        final ProductModel productModel = buildProductModel();
        final ProductEntity productEntity = buildProductEntity();
        when(categoryServiceProvider.findById(anyLong())).thenReturn(buildCategory());
        when(productPersistence.save(any(ProductEntity.class))).thenReturn(productEntity);
        productService.save(productModel);
        verify(categoryServiceProvider, times(1)).findById(anyLong());
        verify(productPersistence, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdateProduct() {
        when(productPersistence.update(any(ProductEntity.class))).thenReturn(buildProductEntity());
        productService.update(buildProductModel());
        verify(productPersistence, times(1)).update(any(ProductEntity.class));
    }

    @Test
    void testDeleteProduct() {
        when(productPersistence.findById(anyLong())).thenReturn(Optional.of(buildProductEntity()));
        doNothing().when(productPersistence).delete(any(ProductEntity.class));
        productService.delete(1L);
        verify(productPersistence, times(1)).findById(anyLong());
        verify(productPersistence, times(1)).delete(any(ProductEntity.class));
    }

    @Test
    void testFindById() {
        when(productPersistence.findById(anyLong())).thenReturn(Optional.of(buildProductEntity()));
        ProductModel productModel = productService.findById(1L);
        Assertions.assertNotNull(productModel);
        Assertions.assertEquals(productModel.getProduct(), buildProductEntity().getProduct());
        verify(productPersistence, times(BigInteger.ONE.intValue())).findById(anyLong());
    }

    @Test
    void testFindAllProducts() {
        when(productPersistence.findAll()).thenReturn(List.of(buildProductEntity()));
        List<ProductModel> products = productService.findAll();
        Assertions.assertEquals(BigInteger.ONE.intValue(), products.size());
        verify(productPersistence, times(1)).findAll();
    }
}