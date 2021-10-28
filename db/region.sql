DROP TABLE IF EXISTS region;

CREATE TABLE region(
	id_region INT NOT NULL AUTO_INCREMENT,
    region VARCHAR(100) UNIQUE NOT NULL,
    status TINYINT NOT NULL,
    PRIMARY KEY (id_region)
);

INSERT INTO region(region,status) VALUES("Sur",1);
INSERT INTO region(region,status) VALUES("Norte",1);
INSERT INTO region(region,status) VALUES("Centro",1);

SELECT * FROM region;