package br.com.ciceroednilson.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_order_x_product")
public class OrderXProductEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Id
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "dt_created", nullable = false)
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated", nullable = false)
    private LocalDateTime dtUpdated;

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
}
