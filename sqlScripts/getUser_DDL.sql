DELIMITER //
CREATE PROCEDURE getUser(username varchar(30))
BEGIN
SELECT u.idUser, u.name, u.surname, u.sex, u.birthDate, u.profilePhoto, u.state, c.role
FROM credential c,user u
WHERE c.username = username
AND u.FKcredential = c.idCredential;
END //
DELIMITER ;