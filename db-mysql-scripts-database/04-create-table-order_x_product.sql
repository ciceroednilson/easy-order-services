CREATE TABLE IF NOT EXISTS tb_order_x_product (
	id_order    BIGINT NOT NULL,
	id_product  BIGINT NOT NULL,
	dt_created  DATETIME NOT NULL,
	dt_updated  DATETIME NOT NULL,
    fl_migrated tinyint(1) NOT NULL DEFAULT '0',
	FOREIGN KEY (id_product) REFERENCES tb_product(id_product),
	FOREIGN KEY (id_order) REFERENCES tb_order(id_order),
	CONSTRAINT PK_id_order_x_id_product PRIMARY KEY (id_order, id_product)
);