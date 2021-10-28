DROP TABLE IF EXISTS category;

CREATE TABLE category(
	id_category INT NOT NULL AUTO_INCREMENT,
    category VARCHAR(100) UNIQUE NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_category)
);

INSERT INTO category(category,status) VALUES("Electrónica",1);
INSERT INTO category(category,status) VALUES("Farmacia",1);
INSERT INTO category(category,status) VALUES("Alimentos y Bebidas",1);

SELECT * FROM category;