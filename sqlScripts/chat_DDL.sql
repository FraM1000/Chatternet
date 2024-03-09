CREATE TABLE chat(
idChat int PRIMARY KEY AUTO_INCREMENT,
FKfirstUser int,
FKsecondUser int,
lastTextDate varchar(25),
FOREIGN KEY(FKfirstUser) REFERENCES user(idUser),
FOREIGN KEY(FKsecondUser) REFERENCES user(idUser)
);