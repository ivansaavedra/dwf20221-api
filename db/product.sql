DROP TABLE IF EXISTS product;

CREATE TABLE product(
	id_product INT NOT NULL AUTO_INCREMENT,
    gtin CHAR(13) UNIQUE NOT NULL,
    product VARCHAR(100) NOT NULL,
    description VARCHAR(255),
	price FLOAT NOT NULL,
	stock INT NOT NULL,
	id_category INT NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_product),
    FOREIGN KEY (id_category) REFERENCES category(id_category)
);

INSERT INTO product VALUES(1,'7501055311453','Paracetamol 500mg','Paracetamol 500mg, 20 tabletas',20.00,100,2,1);
INSERT INTO product VALUES(2,'7898917592649','Coca-Cola 600ml','Coca-Cola 600ml, no retornable',15.00,500,3,1);

SELECT * FROM product;