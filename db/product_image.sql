DROP TABLE IF EXISTS product_image;

CREATE TABLE product_image(
	id_product_image INT NOT NULL AUTO_INCREMENT,
    id_product INT NOT NULL,
    image TEXT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_product_image),
    FOREIGN KEY (id_product) REFERENCES product(id_product)
);

INSERT INTO product_image(id_product_image, id_product, image, status) VALUES(1, 1, 'img1', 1);
INSERT INTO product_image(id_product_image, id_product, image, status) VALUES(2, 2, 'img2', 1);

SELECT * FROM product_image;
