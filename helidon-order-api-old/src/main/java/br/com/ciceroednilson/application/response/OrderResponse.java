package br.com.ciceroednilson.application.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private BigDecimal vlTotal;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
    private boolean flActive;
    private List<Product> products;

    class Product {
        private Long id;
        private String description;
        private BigDecimal value;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }
    }
}
