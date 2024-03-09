CREATE TABLE credential(
idCredential int PRIMARY KEY AUTO_INCREMENT,
username varchar(30) NOT NULL UNIQUE KEY,
password varchar(128) NOT NULL,
role varchar(5) DEFAULT 'USER',
signupDate varchar(25),
blockedAccount varchar(1) DEFAULT 'N'
);