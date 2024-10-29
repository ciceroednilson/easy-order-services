package br.com.ciceroednilson.domain.model;

import java.util.List;

public class OrderModel {

    private List<Long> products;

    public OrderModel(List<Long> products) {
        this.products = products;
    }

    public List<Long> getProducts() {
        return products;
    }

}
