package com.chatternet.model.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;

@Repository
public class CredenzialeDAOImpl implements CredenzialeDAO{

	@PersistenceContext
	private EntityManager em;
	Logger logger = LoggerFactory.getLogger(CredenzialeDAOImpl.class);
	
	@Override
	@Transactional
	public void registraCredenziale(Credenziale credenziale) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A, 12);
		String passCryptata = encoder.encode(credenziale.getPassword());
		Query ins = em.createNativeQuery("INSERT INTO credenziale(username,password,dataRegistrazione) VALUES(?,?,?)");
		ins.setParameter(1, credenziale.getUsername());
		ins.setParameter(2, passCryptata);
		ins.setParameter(3, credenziale.getDataRegistrazione());
		int rs = ins.executeUpdate();
		if (rs == 1) {
			logger.info("credenziali registrate");
		} else {
			logger.info("credenziali non registrate");
		}
	}

	@Override
	@Transactional
	public void inserisciFK(Credenziale credenziale, Utente utente) {
		Query idC = em.createNativeQuery("SELECT MAX(idCredenziale) max FROM credenziale");
		Object rs = idC.getSingleResult();
		int FKcredenziale = (int) rs;
		logger.debug("" + FKcredenziale);
		Query id = em.createNativeQuery("SELECT MAX(idUtente) max FROM utente");
		Object rs2 = id.getSingleResult();
		int idU = (int) rs2;
		logger.debug("" + idU);
		Query upd = em.createNativeQuery("UPDATE utente SET FKcredenziale=? WHERE idUtente = ?");
		upd.setParameter(1, FKcredenziale);
		upd.setParameter(2, idU);
		int res = upd.executeUpdate();
		if(res==1) {
			logger.info("FKcredenziale: {} settata per utente con id: {}",FKcredenziale,idU);
		}
		
	}

	@Override
	public Object[] ricavaUtenteDaUsername(String username) {
		Query stored = em.createNativeQuery("call chatternet.ricavaUtente(?)");
		stored.setParameter(1, username);
		Object[] user = (Object[]) stored.getSingleResult();
		return user;
	}

	@Override
	@Transactional
	public void modificaPass(Credenziale credenziale) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A,12);
		String passwordCryptata = encoder.encode(credenziale.getPassword());
		Query upd = em.createNativeQuery("UPDATE credenziale SET password = ? WHERE idCredenziale = ?");
		upd.setParameter(1, passwordCryptata);
		upd.setParameter(2, credenziale.getIdCredenziale());
		int res = upd.executeUpdate();
		if(res == 1) logger.info("password modificata con successo");
	}

	@Override
	public int ricavaIdCredenziale(int idUtente) {
		Query sel = em.createNativeQuery("SELECT idCredenziale \r\n"
				+ "FROM credenziale c , utente u \r\n"
				+ "WHERE u.idUtente = ? \r\n"
				+ "AND u.FKcredenziale = c.idCredenziale");
		sel.setParameter(1, idUtente);
		Object idCredenziale = sel.getSingleResult();
		return (int) idCredenziale;
	}

}
