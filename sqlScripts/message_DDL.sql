CREATE TABLE message(
idMessage int PRIMARY KEY AUTO_INCREMENT,
text text NOT NULL,
time varchar(25) NOT NULL,
FKuser int,
FKchat int,
state varchar(8),
FOREIGN KEY(FKuser) REFERENCES user(idUser),
FOREIGN KEY(FKchat) REFERENCES chat(idChat)
);