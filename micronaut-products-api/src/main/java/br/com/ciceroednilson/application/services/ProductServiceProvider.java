package br.com.ciceroednilson.application.services;

import br.com.ciceroednilson.domain.model.ProductModel;

import java.util.List;

public interface ProductServiceProvider {

    void save(ProductModel model) throws Exception;
    void update(ProductModel model);
    void delete(Long id);
    ProductModel findById(Long id);
    List<ProductModel> findAll();
    void reduceStock(Long id);
}
