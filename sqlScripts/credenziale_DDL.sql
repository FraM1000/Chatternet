CREATE TABLE credenziale(
idCredenziale int PRIMARY KEY AUTO_INCREMENT,
username varchar(30) NOT NULL UNIQUE KEY,
password varchar(128) NOT NULL,
ruolo varchar(5) DEFAULT 'USER',
dataRegistrazione varchar(25)
);