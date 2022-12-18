CREATE TABLE chat(
idChat int PRIMARY KEY AUTO_INCREMENT,
FKutenteUno int,
FKutenteDue int,
dataUltimoMessaggio varchar(25),
FOREIGN KEY(FKutenteUno) REFERENCES utente(idUtente),
FOREIGN KEY(FKutenteDue) REFERENCES utente(idUtente)
);