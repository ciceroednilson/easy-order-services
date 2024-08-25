CREATE TABLE IF NOT EXISTS tb_order_x_product (
	id_order    BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_product  BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	dt_created  DATETIME NOT NULL,
	dt_updated  DATETIME NOT NULL,
	FOREIGN KEY (id_product) REFERENCES tb_product(id_product),
	FOREIGN KEY (id_order) REFERENCES tb_order(id_order),
	CONSTRAINT PK_id_order_x_id_product PRIMARY KEY (id_order, id_product)
);