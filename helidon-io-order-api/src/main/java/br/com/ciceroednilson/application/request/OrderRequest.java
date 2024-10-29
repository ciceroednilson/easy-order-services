package br.com.ciceroednilson.application.request;

import br.com.ciceroednilson.domain.model.OrderModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {

    private List<Long> products;

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public OrderModel toModel() {
        return new OrderModel(products);
    }
}