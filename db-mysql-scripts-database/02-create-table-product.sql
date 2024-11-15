
CREATE TABLE IF NOT EXISTS tb_product (
    id_product  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ds_product  VARCHAR(150) NOT NULL,
    id_category BIGINT NOT NULL,
    vl_product  DECIMAL(10,2) NOT NULL,
    qt_product  INT NOT NULL,
    dt_created  DATETIME NOT NULL,
    dt_updated  DATETIME NOT NULL,
    fl_active   BOOLEAN NOT NULL,
    FOREIGN KEY (id_category) REFERENCES tb_category(id_category)
);