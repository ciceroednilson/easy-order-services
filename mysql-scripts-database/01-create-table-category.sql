CREATE TABLE IF NOT EXISTS tb_category (
    id_category INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ds_category VARCHAR(150) NOT NULL,
    dt_created DATETIME NOT NULL,
    fl_active BOOLEAN NOT NULL
);
