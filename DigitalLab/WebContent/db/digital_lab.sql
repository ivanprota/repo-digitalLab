DROP DATABASE IF EXISTS digital_lab;
CREATE DATABASE digital_lab;

USE digital_lab;

-- ENTITA'

DROP TABLE IF EXISTS administrator;
CREATE TABLE administrator
(
	administrator_username		char(20) PRIMARY KEY,
    administrator_name			char(20) NOT NULL,
    administrator_surname		char(20) NOT NULL,
    administrator_password		char(200) NOT NULL
);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
	customer_username			char(20) PRIMARY KEY,
    customer_email				char(40) NOT NULL,
    customer_password			char(200) NOT NULL,
    customer_name				char(20) NOT NULL,
    customer_surname			char(20) NOT NULL,
    customer_phone				char(10)
);

DROP TABLE IF EXISTS shipping_address;
CREATE TABLE shipping_address
(
    shipping_address_id						int PRIMARY KEY AUTO_INCREMENT,
    shipping_address_province				char(15) NOT NULL,
    shipping_address_street					char(20) NOT NULL,
    shipping_address_city					char(20) NOT NULL,
    shipping_address_zip					char(10) NOT NULL,
    shipping_address_street_number			int NOT NULL,
    shipping_address_customer_username		char(20) NOT NULL,
    
    FOREIGN KEY (shipping_address_customer_username) REFERENCES customer(customer_username)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS payment_method;
CREATE TABLE payment_method
(
	payment_method_pan					char(16) PRIMARY KEY,
    payment_method_owner				char(40) NOT NULL,
    payment_method_cvv					char(3) NOT NULL,
    payment_method_expiration_date		DATE NOT NULL,
    payment_method_customer_username	char(20) NOT NULL,
    
    FOREIGN KEY (payment_method_customer_username) REFERENCES customer(customer_username)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS shopping_cart;
CREATE TABLE shopping_cart
(
	shopping_cart_customer_username		char(20) PRIMARY KEY,
    shopping_cart_size					int NOT NULL,
    
    FOREIGN KEY (shopping_cart_customer_username) REFERENCES customer(customer_username)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS customer_order;
CREATE TABLE customer_order
(
	customer_order_code						int PRIMARY KEY,
    customer_order_status					char(15) NOT NULL,
    customer_order_total_amount				double NOT NULL,
    customer_order_payment_date				DATE NOT NULL,
    customer_order_customer_username		char(20) NOT NULL,
    customer_order_shipping_address_id		int NOT NULL,
    customer_order_payment_method_pan		char(16) NOT NULL,
    
    FOREIGN KEY (customer_order_customer_username) REFERENCES customer(customer_username)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (customer_order_shipping_address_id) REFERENCES shipping_address(shipping_address_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (customer_order_payment_method_pan) REFERENCES payment_method(payment_method_pan)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
	product_code			int PRIMARY KEY AUTO_INCREMENT,
    product_quantity		int NOT NULL,
    product_description		char(255) NOT NULL,
    product_price			double(8, 2) NOT NULL,
    product_brand			char(20) NOT NULL,
    product_model			char(25) NOT NULL,
    product_category		char(20) NOT NULL
);

DROP TABLE IF EXISTS picture;
CREATE TABLE picture
(
	picture_product_code	int NOT NULL, 
    picture_image			longblob NOT NULL,
    picture_file_name		char(50) NOT NULL,
    
    FOREIGN KEY (picture_product_code) REFERENCES product(product_code)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS review;
CREATE TABLE review
(
	review_product_code		int,
    review_description		char(255),
    review_assessment		int NOT NULL,
    
    FOREIGN KEY (review_product_code) REFERENCES product(product_code)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);





-- RELAZIONI
DROP TABLE IF EXISTS shopping_cart_contains;
CREATE TABLE shopping_cart_contains
(
	contains_shopping_cart_customer_username	char(20) NOT NULL,
    contains_product_code						int NOT NULL,
    contains_product_quantity					int NOT NULL,
    
    PRIMARY KEY (contains_shopping_cart_customer_username, contains_product_code),
    FOREIGN KEY (contains_shopping_cart_customer_username) REFERENCES shopping_cart(shopping_cart_customer_username)
    ON UPDATE CASCADE 
    ON DELETE RESTRICT,
	FOREIGN KEY (contains_product_code) REFERENCES product(product_code)
    ON UPDATE CASCADE 
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS composes;
CREATE TABLE composes
(
	composes_product_code	int NOT NULL,
    composes_order_code		int NOT NULL,
    composes_product_quantity int NOT NULL,
    
    PRIMARY KEY (composes_product_code, composes_order_code),
    FOREIGN KEY (composes_product_code) REFERENCES product(product_code)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (composes_order_code) REFERENCES customer_order(customer_order_code)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);
