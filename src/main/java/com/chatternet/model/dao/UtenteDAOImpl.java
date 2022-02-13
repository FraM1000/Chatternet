package com.chatternet.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.Utente;

@Repository
public class UtenteDAOImpl implements UtenteDAO {
	
	@PersistenceContext
	private EntityManager em;
	Logger logger = LoggerFactory.getLogger(UtenteDAOImpl.class);

	@Override
	@Transactional
	public void registraUtente(Utente utente) {
		Query ins = em.createNativeQuery("INSERT INTO utente(nome,cognome,sesso,dataNascita) VALUES(?,?,?,?)");
		ins.setParameter(1, utente.getNome());
		ins.setParameter(2, utente.getCognome());
		ins.setParameter(3, utente.getSesso());
		ins.setParameter(4, utente.getDataNascita());
		int rs = ins.executeUpdate();
		if(rs == 1) {
			logger.info("utente registrato");
		} else {
			logger.info("utente non registrato");
		}
		
	}

	@Override
	@Transactional
	public void inserisciFoto(Utente utente) {
		Query upd = em.createNativeQuery("UPDATE utente SET fotoProfilo = ? WHERE idUtente = ?");
		upd.setParameter(1, utente.getFotoProfilo());
		upd.setParameter(2, utente.getIdUtente());
		int rs = upd.executeUpdate();
		if(rs == 1) {
			logger.info("foto inserita");
		} else {
			logger.info("foto non inserita");
		}
	}

	@Override
	public Object prendiFoto(Utente utente) {
		Query sel = em.createNativeQuery("SELECT fotoProfilo FROM utente WHERE idUtente = ?");
		sel.setParameter(1, utente.getIdUtente());
		Object foto = sel.getSingleResult();
		return foto;
	}

	@Override
	public List<Utente[]> ricercaUtente(String nomeUtente, String usernameResearcher) {
		Query sel = em.createNativeQuery("SELECT c.username , u.idUtente , u.nome , u.cognome , u.fotoProfilo \r\n"
				+ "FROM credenziale c , utente u \r\n"
				+ "WHERE c.username LIKE ? \r\n"
				+ "AND NOT(c.username = ?) \r\n"
				+ "AND u.FKcredenziale = c.idCredenziale");
		sel.setParameter(1, nomeUtente + "%");
		sel.setParameter(2, usernameResearcher);
		List<Utente[]> utenti = sel.getResultList();
		if(utenti.isEmpty()) {
			logger.info("nessun utente è stato trovato");
		}
		return utenti;
	}

}
