DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
	id_customer INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    rfc VARCHAR(13) UNIQUE NOT NULL,
    mail VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    id_region INT NOT NULL,
    id_customer_image INT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_customer),
    FOREIGN KEY (id_region) REFERENCES region(id_region),
    FOREIGN KEY (id_customer_image) REFERENCES customer_image(id_customer_image)
);

INSERT INTO customer(name, surname, rfc, mail, address, id_region, id_customer_image, status) VALUES('Iván', 'Saavedra', 'SAIV920101A00', 'ivan.saavedra@ciencias.unam.mx', 'Av. Universidad 3000', 1, 1, 1);
INSERT INTO customer(name, surname, rfc, mail, address, id_region, id_customer_image, status) VALUES('Fernando', 'Sánchez', 'SAFE920101A00', 'cafetafer@ciencias.unam.mx', 'Periférico Sur 1400', 2, 2, 1);

SELECT * FROM customer;