DROP TABLE IF EXISTS invoice;

CREATE TABLE invoice(
	id_invoice INT NOT NULL AUTO_INCREMENT,
    rfc VARCHAR(13) NOT NULL,
	subtotal FLOAT NOT NULL,
	taxes FLOAT NOT NULL,
	total FLOAT NOT NULL,
    created_at DATE NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_invoice),
    FOREIGN KEY (rfc) REFERENCES customer(rfc)
);

SELECT * FROM invoice;