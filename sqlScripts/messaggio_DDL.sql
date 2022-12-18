CREATE TABLE messaggio(
idMessaggio int PRIMARY KEY AUTO_INCREMENT,
testo text NOT NULL,
ora varchar(25) NOT NULL,
FKutente int,
FKchat int,
stato varchar(8),
FOREIGN KEY(FKutente) REFERENCES utente(idUtente),
FOREIGN KEY(FKchat) REFERENCES chat(idChat)
);