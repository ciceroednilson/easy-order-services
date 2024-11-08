# The lab is for learning some frameworks and tools.

![readme-files/images/banner.png](readme-files/images/banner.png)

## ⚙️ Introduction.

This lab has the objective of testing different frameworks and tools, such as Helidon, Micronaut, and Quarkus, among others. 

## 🛠 Stack.

<ol>
  <li>Java</li>
  <li>Kotlin</li>
  <li>Golang</li>
  <li>Helidon</li>
  <li>Micronaut</li>
  <li>Quarkus</li>
  <li>Spring boot</li>
  <li>Docker</li>
  <li>Docker Compose</li>
  <li>Mysql</li>
  <li>MariaDB</li>
  <li>Dbeaver</li>
  <li>Kafka</li>
  <li>Postman</li>
  <li>Intellij</li>
  <li>VsCode</li>
</ol>

## ⚙️ General Architecture.

![readme-files/images/architecture_general.drawio.png](readme-files/images/architecture_general.drawio.png)


## ⚙️ Docker Compose.

Execute the "docker-compose" command in the [docker-compose](docker-compose) folder to create your containers.

~~~~shell
sudo docker-compose up -d --build
~~~~


## ⚙️ MySQL.

To create our database and tables, we use the commands below.

#### 🛠 [scripts](db-mysql-scripts-database)

Create Data Base.
~~~~sql
CREATE DATABASE db_system;
~~~~

Create table tb_category.
~~~~sql
CREATE TABLE IF NOT EXISTS tb_category (
    id_category BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ds_category VARCHAR(150) NOT NULL,
    dt_created DATETIME NOT NULL,
    fl_active BOOLEAN NOT NULL
);
~~~~

Create table tb_product.
~~~~sql
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
~~~~

Create table tb_order.
~~~~sql
CREATE TABLE IF NOT EXISTS tb_order (
	id_order    BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	vl_total    DECIMAL(10,2) NOT NULL,
	dt_created  DATETIME NOT NULL,
	dt_updated  DATETIME NOT NULL,
	fl_active   BOOLEAN NOT NULL
);
~~~~

Create table tb_order_x_product.
~~~~sql
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
~~~~

Below, we can see our database and tables that were created.

![readme-files/images/mysql_tables.png](readme-files/images/mysql_tables.png)


## ⚙️ MariaDB.

To create our database and tables, we use the commands below.

#### 🛠 [scripts](db-mariadb-scripts-database)

Create Data Base.
~~~~sql
CREATE DATABASE db_system_bi;
~~~~

Create table tb_order_bi.
~~~~sql
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
~~~~

Below, we can see our database and tables that were created.

![readme-files/images/maria_db.png](readme-files/images/maria_db.png)

## ⚙️ Kafka.

To check if Kafka is running correctly, you can access the address [http://localhost:8080/](http://localhost:8080/).

![readme-files/images/kafka-ui.png](readme-files/images/kafka-ui.png)


## ⚙️ Quarkus.

Below, you can check the source.

#### 🚀 [quarkus-categories-api](quarkus-categories-api)

## ⚙️ Quarkus - Architecture.

![readme-files/images/quarkus-architecture.drawio.png](readme-files/images/quarkus-architecture.drawio.png)

## ⚙️ Quarkus - Project structure.

![readme-files/images/quarkus-structure.png](readme-files/images/quarkus-structure.png)

## ⚙️ Quarkus - cURL examples for testing.

#### 🚀 Collection: [quarkus-category.postman_collection.json](postman-collections/quarkus-category.postman_collection.json)


Find all categories.
~~~~shel
curl --location 'http://localhost:4000/category/all'
~~~~

Find the category by its ID
~~~~shel
curl --location 'http://localhost:4000/category/2' \
--data ''
~~~~

Create a new category.
~~~~shel
curl --location 'http://localhost:4000/category' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Livros",
    "active": true
}'
~~~~

Update the category.
~~~~shel
curl --location --request PUT 'http://localhost:4000/category/4' \
--header 'Content-Type: application/json' \
--data '    {
        "active": true,        
        "name": "Roupas Masculinas"
    },'
~~~~

Delete the category.
~~~~shel
curl --location --request DELETE 'http://localhost:4000/category/1'
~~~~

## ⚙️ Micronaut.