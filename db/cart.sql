DROP TABLE IF EXISTS cart;

CREATE TABLE cart(
	id_cart INT NOT NULL AUTO_INCREMENT,
    rfc VARCHAR(13) NOT NULL,
    id_product INT NOT NULL,
	quantity INT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_cart),
    FOREIGN KEY (rfc) REFERENCES customer(rfc),
    FOREIGN KEY (id_product) REFERENCES product(id_product)
);

SELECT * FROM cart;