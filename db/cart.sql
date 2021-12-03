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

INSERT INTO cart VALUES(1,3,1,10,1);
INSERT INTO cart VALUES(2,3,4,1,1);

INSERT INTO cart VALUES(3,1,4,1,1);

SELECT * FROM cart;