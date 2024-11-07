CREATE TABLE IF NOT EXISTS tb_order_bi (	
    id_order     BIGINT NOT NULL,
    id_product   BIGINT NOT NULL,
    ds_product   VARCHAR(150) NOT NULL,
    vl_product   DECIMAL(10,2) NOT NULL,
    id_category  BIGINT NOT NULL,
    ds_category  VARCHAR(150) NOT NULL,  
    dt_created   DATETIME NOT NULL,
    dt_migrated  DATETIME NOT NULL,	
	PRIMARY KEY (id_order, id_product)
);