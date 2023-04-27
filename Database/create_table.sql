USE laptopecommcerce;

CREATE TABLE orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  shipment_id INT NOT NULL,
  amount DECIMAL(8,2) NOT NULL,
  description VARCHAR(255) NOT NULL,
  shipping_addr VARCHAR(255) NOT NULL,
  order_date DATETIME NOT NULL,
  order_status VARCHAR(255) NOT NULL,
  discount DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id)
);

CREATE TABLE shipments (
  shipment_id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  shipment_date DATETIME NOT NULL,
  shipment_status VARCHAR(255) NOT NULL,
  PRIMARY KEY (shipment_id)
  FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE order_details (
  idx INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  quantity INT DEFAULT 1,
  PRIMARY KEY (idx),
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE users (
  user_id INT NOT NULL AUTO_INCREMENT,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  phone INT NOT NULL,
  email VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id),
  FOREIGN KEY (role_id) REFERENCES permission(role_id)
);

CREATE TABLE permission (
  role_id INT NOT NULL,
  permission INT NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE products (
  product_id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  description VARCHAR(255) NOT NULL,
  image VARCHAR(255) NOT NULL,
  create_date DATETIME NOT NULL,
  category VARCHAR(255) NOT NULL,
  PRIMARY KEY (product_id)
);

CREATE TABLE product_options (
  product_id VARCHAR(255) NOT NULL,
  option_id INT NOT NULL,
  diff_price DECIMAL(8,2),
  PRIMARY KEY (option_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);


