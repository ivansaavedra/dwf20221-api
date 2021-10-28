DROP TABLE IF EXISTS customer_image;

CREATE TABLE customer_image(
	id_customer_image INT NOT NULL AUTO_INCREMENT,
    image TEXT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_customer_image)
);

INSERT INTO customer_image(id_customer_image, image, status) VALUES(1, 'img1', 1);
INSERT INTO customer_image(id_customer_image, image, status) VALUES(2, 'img2', 1);

SELECT * FROM customer_image;