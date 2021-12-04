DROP TABLE IF EXISTS item;

CREATE TABLE item(
	id_item INT NOT NULL AUTO_INCREMENT,
    id_invoice INT NOT NULL,
    gtin CHAR(13) UNIQUE NOT NULL,
    quantity INT NOT NULL,
	unit_price FLOAT NOT NULL,
	subtotal FLOAT NOT NULL,
	taxes FLOAT NOT NULL,
	total FLOAT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_item),
    FOREIGN KEY (id_invoice) REFERENCES invoice(id_invoice)
);

SELECT * FROM item;