CREATE DATABASE IF NOT EXISTS laptopecommerce;

USE laptopecommerce;

DROP TABLE IF EXISTS customers, permission, orders, shipments, order_details, products; 

CREATE TABLE customers (
  customer_id INT NOT NULL AUTO_INCREMENT,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  role_id INT DEFAULT 1,
  PRIMARY KEY (customer_id)
);

INSERT INTO customers (password, name, phone, email, address, country, role_id) 
  VALUES ('admin', 'admin', '0987123456', 'admin@laptop.com', 'NY', 'USA', 0);
INSERT INTO customers (password, name, phone, email, address, country, role_id) 
  VALUES ('123qwe', 'vu_dung', '0343435343', 'vuvandung@gmail.com', 'HCMC', 'VN', 1);


CREATE TABLE permission (
  role_id INT NOT NULL,
  permission VARCHAR(255) NOT NULL,
  PRIMARY KEY (role_id)
);

INSERT INTO permission VALUES (0, "view_make");
INSERT INTO permission VALUES (1, "view_create");

CREATE TABLE orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  customer_id INT NOT NULL,
  shipment_id INT NOT NULL,
  amount DOUBLE(8,2) NOT NULL,
  description VARCHAR(255) NOT NULL,
  shipping_addr VARCHAR(255) NOT NULL,
  order_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  order_status VARCHAR(255) DEFAULT "NAN",
  discount DOUBLE(8,2) NOT NULL,
  PRIMARY KEY (order_id)
);

CREATE TABLE shipments (
  shipment_id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  shipment_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  shipment_status VARCHAR(255) NOT NULL,
  PRIMARY KEY (shipment_id)
);

CREATE TABLE order_details (
  idx INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  price DOUBLE(8,2) NOT NULL,
  quantity INT DEFAULT 1,
  PRIMARY KEY (idx)
);

CREATE TABLE products (
  product_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  price DOUBLE(8,2) NOT NULL,
  description VARCHAR(255) NOT NULL,
  image VARCHAR(255) NOT NULL,
  create_date DATE NOT NULL,
  category VARCHAR(255) NOT NULL,
  PRIMARY KEY (product_id)
);