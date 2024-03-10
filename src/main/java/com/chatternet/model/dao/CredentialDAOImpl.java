package com.chatternet.model.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.Credential;

@Repository
public class CredentialDAOImpl implements CredentialDAO{

	@PersistenceContext
	private EntityManager em;
	Logger logger = LoggerFactory.getLogger(CredentialDAOImpl.class);
	
	@Override
	@Transactional
	public void saveCredentials(Credential credential) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A, 12);
		String encryptedPassword = encoder.encode(credential.getPassword());
		Query ins = em.createNativeQuery("INSERT INTO credential(username,password,signupDate) VALUES(?,?,?)");
		ins.setParameter(1, credential.getUsername());
		ins.setParameter(2, encryptedPassword);
		ins.setParameter(3, credential.getSignupDate());
		int rs = ins.executeUpdate();
		if (rs == 1) {
			logger.info("credenziali registrate");
		} else {
			logger.info("credenziali non registrate");
		}
	}

	@Override
	@Transactional
	public void insertForeignKey() {
		Query idC = em.createNativeQuery("SELECT MAX(idCredential) max FROM credential");
		Object rs = idC.getSingleResult();
		int FKcredential = (int) rs;
		logger.debug("" + FKcredential);
		Query id = em.createNativeQuery("SELECT MAX(idUser) max FROM user");
		Object rs2 = id.getSingleResult();
		int idU = (int) rs2;
		logger.debug("" + idU);
		Query upd = em.createNativeQuery("UPDATE user SET FKcredential=? WHERE idUser = ?");
		upd.setParameter(1, FKcredential);
		upd.setParameter(2, idU);
		int res = upd.executeUpdate();
		if(res==1) {
			logger.info("FKcredenziale: {} settata per utente con id: {}",FKcredential,idU);
		}
		
	}

	@Override
	public Object[] getUserByUsername(String username) {
		Query stored = em.createNativeQuery("call chatternet.getUser(?)");
		stored.setParameter(1, username);
		Object[] user = (Object[]) stored.getSingleResult();
		return user;
	}

	@Override
	@Transactional
	public void updatePassword(Credential credential) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A,12);
		String encryptedPassword = encoder.encode(credential.getPassword());
		Query upd = em.createNativeQuery("UPDATE credential SET password = ? WHERE idCredential = ?");
		upd.setParameter(1, encryptedPassword);
		upd.setParameter(2, credential.getIdCredential());
		int res = upd.executeUpdate();
		if(res == 1) logger.info("password modificata con successo");
	}

	@Override
	public int getIdCredential(int idUser) {
		Query sel = em.createNativeQuery("SELECT idCredential \r\n"
				+ "FROM credential c , user u \r\n"
				+ "WHERE u.idUser = ? \r\n"
				+ "AND u.FKcredential = c.idCredential");
		sel.setParameter(1, idUser);
		Object idCredential = sel.getSingleResult();
		return (int) idCredential;
	}

	@Override
	public List<Object[]> countRegisteredUsersFromStartDateToEndDate(String startDate, String endDate) {
		Query sel = em.createNativeQuery("SELECT str_to_date(c.signupDate, '%Y-%m-%d') as 'signupDate', COUNT(c.idCredential) as 'utentiIscritti' \r\n"
				+ "FROM credential c \r\n"
				+ "WHERE c.signupDate \r\n"
				+ "BETWEEN ? AND ? \r\n"
				+ "GROUP BY str_to_date(c.signupDate, '%Y-%m-%d')");
		sel.setParameter(1, startDate);
		sel.setParameter(2, endDate);
		List<Object[]> registeredUsers = sel.getResultList();
		return registeredUsers;
	}

	@Override
	public List<Object[]> countRegisteredUsersInThePastYear(String startDate, String endDate) {
		Query sel = em.createNativeQuery("SELECT (YEAR(c.signupDate) * 100) + MONTH(c.signupDate) as 'signupDate', COUNT(c.idCredential) as 'utentiIscritti' \r\n"
				+ "FROM credential c \r\n"
				+ "WHERE c.signupDate \r\n"
				+ "BETWEEN ? AND ? \r\n"
				+ "GROUP BY (YEAR(c.signupDate) * 100) + MONTH(c.signupDate)");
		sel.setParameter(1, startDate);
		sel.setParameter(2, endDate);
		List<Object[]> registeredUsers = sel.getResultList();
		return registeredUsers;
	}

	@Override
	@Transactional
	public void lockOrUnlockUserAccount(String username, String accountLockChoice) {
		Query upd = em.createNativeQuery("UPDATE credential SET blockedAccount = ? WHERE username = ?");
		upd.setParameter(1, accountLockChoice);
		upd.setParameter(2, username);
		int res = upd.executeUpdate();
		if(res == 1) logger.info("l'account dell'utente con username: {} è stato {}", username, 
				accountLockChoice.equals("Y") ? "bloccato" : "sbloccato");
	}

}