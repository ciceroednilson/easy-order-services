package br.com.ciceroednilson.infrastructure.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeyCompostProductXOrder implements Serializable {

    private Long idOrder;
    private Long idProduct;

    public KeyCompostProductXOrder(){

    }

    public KeyCompostProductXOrder(Long idOrder, Long idProduct) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}
