package br.com.ciceroednilson.application.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {

    private BigDecimal vlTotal;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
    private boolean flActive;
    private List<Integer> products;

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public BigDecimal getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(BigDecimal vlTotal) {
        this.vlTotal = vlTotal;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(LocalDateTime dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public boolean isFlActive() {
        return flActive;
    }

    public void setFlActive(boolean flActive) {
        this.flActive = flActive;
    }
}