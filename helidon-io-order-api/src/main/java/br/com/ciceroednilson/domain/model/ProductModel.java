package br.com.ciceroednilson.domain.model;

import java.math.BigDecimal;

public class ProductModel {

    private Long id;
    private BigDecimal price;
    private boolean active;

    public ProductModel(Long id, BigDecimal price, boolean active) {
        this.id = id;
        this.price = price;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
