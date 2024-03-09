CREATE TABLE user(
idUser int PRIMARY KEY AUTO_INCREMENT,
name varchar(30) NOT NULL,
surname varchar(30) NOT NULL,
sex varchar(7) NOT NULL,
birthDate date NOT NULL,
profilePhoto varchar(80),
state varchar(7),
FKcredential int,
FOREIGN KEY(FKcredential) REFERENCES credential(idCredential) 
);