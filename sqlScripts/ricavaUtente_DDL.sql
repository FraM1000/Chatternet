DELIMITER //
CREATE PROCEDURE ricavaUtente(username varchar(30))
BEGIN
SELECT u.idUtente, u.nome, u.cognome, u.sesso, u.dataNascita, u.fotoProfilo, u.stato, c.ruolo
FROM credenziale c,utente u
WHERE c.username = username
AND u.FKcredenziale = c.idCredenziale;
END //
DELIMITER ;