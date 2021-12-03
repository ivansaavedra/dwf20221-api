DROP TABLE IF EXISTS cart;

CREATE TABLE cart(
	id_cart INT NOT NULL AUTO_INCREMENT,
    id_customer INT NOT NULL,
    id_product INT NOT NULL,
	quantity INT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_cart),
    FOREIGN KEY (id_customer) REFERENCES customer(id_customer),
    FOREIGN KEY (id_product) REFERENCES product(id_product)
);

SELECT * FROM cart;