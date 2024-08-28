package br.com.ciceroednilson.application.mapper;

import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.infrastructure.persistence.entity.ProductEntity;
import br.com.ciceroednilson.infrastructure.web.request.ProductRequest;
import br.com.ciceroednilson.infrastructure.web.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductEntity toEntity(final ProductModel model){
        final ProductEntity entity = new ProductEntity();
        entity.setActive(model.getActive());
        entity.setProduct(model.getProduct());
        entity.setCreated(model.getCreated());
        entity.setUpdated(model.getUpdated());
        entity.setPrice(model.getPrice());
        entity.setTotal(model.getTotal());
        entity.setIdCategory(model.getIdCategory());
        return entity;
    }

    public static ProductModel toModel(final ProductEntity entity) {
        final ProductModel model = new ProductModel();
        model.setActive(entity.getActive());
        model.setProduct(entity.getProduct());
        model.setCreated(entity.getCreated());
        model.setUpdated(entity.getUpdated());
        model.setPrice(entity.getPrice());
        model.setTotal(entity.getTotal());
        model.setIdCategory(entity.getIdCategory());
        model.setId(entity.getId());
        return model;
    }

    public static ProductModel toModel(final ProductRequest request) {
        final ProductModel model = new ProductModel();
        model.setActive(request.getActive());
        model.setProduct(request.getProduct());
        model.setCreated(request.getCreated());
        model.setUpdated(request.getUpdated());
        model.setPrice(request.getPrice());
        model.setTotal(request.getTotal());
        model.setIdCategory(request.getIdCategory());
        model.setId(request.getId());
        return model;
    }

    public static ProductResponse toResponse(final ProductModel model) {
        final ProductResponse response = new ProductResponse();
        response.setActive(model.getActive());
        response.setProduct(model.getProduct());
        response.setCreated(model.getCreated());
        response.setUpdated(model.getUpdated());
        response.setPrice(model.getPrice());
        response.setTotal(model.getTotal());
        response.setIdCategory(model.getIdCategory());
        response.setId(model.getId());
        return response;
    }

    public static List<ProductResponse> toListResponse(final List<ProductModel> listModel) {
        final List<ProductResponse> list = new ArrayList<>();
        listModel.forEach(productModel -> {
            list.add(toResponse(productModel));
        });
        return list;
    }

    public static List<ProductModel> toListModel(final List<ProductEntity> entities) {
        final List<ProductModel> list = new ArrayList<>();
        entities.forEach(entity -> {
            list.add(toModel(entity));
        });
        return list;
    }
}
