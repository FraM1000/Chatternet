package com.chatternet.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAOImpl implements ChatDAO {

	@PersistenceContext
	private EntityManager em;
	Logger logger = LoggerFactory.getLogger(ChatDAOImpl.class);
	
	@Override
	public int cercaChatTraUtenti(int idInviante, int idRicevente) {
		int idChat = 0;
		Query sel = em.createNativeQuery("SELECT idChat FROM chat \r\n"
	            + "WHERE FKutenteUno = ? AND FKutenteDue = ? \r\n" 
				+ "OR FKutenteUno = ? AND FKutenteDue = ?;");
		sel.setParameter(1, idInviante);
		sel.setParameter(2, idRicevente);
		sel.setParameter(3, idRicevente);
		sel.setParameter(4, idInviante);
		try {
			Object idChatTrovato = sel.getSingleResult();
			idChat = (Integer) idChatTrovato;
		} catch (NoResultException e) {
			logger.info("nessuna chat trovata tra utente con id {} e utente con id {}", idInviante, idRicevente);
			creaChat(idInviante, idRicevente);
		}
		return idChat;
	}
	
	@Transactional
	private void creaChat(int idInviante, int idRicevente) {
		Query ins = em.createNativeQuery("INSERT INTO chat(FKutenteUno,FKutenteDue) VALUES(?,?)");
		ins.setParameter(1, idInviante);
		ins.setParameter(2, idRicevente);
		int rs = ins.executeUpdate();
		if(rs == 1) logger.info("chat creata tra utente con id {} e utente con id {}", idInviante, idRicevente);
		cercaChatTraUtenti(idInviante, idRicevente);
	}

	@Override
	public int cercaChatTraUtentiSenzaCrearla(int idInviante, int idRicevente) {
		int idChat = 0;
		Query sel = em.createNativeQuery("SELECT idChat FROM chat \r\n"
	            + "WHERE FKutenteUno = ? AND FKutenteDue = ? \r\n" 
				+ "OR FKutenteUno = ? AND FKutenteDue = ?;");
		sel.setParameter(1, idInviante);
		sel.setParameter(2, idRicevente);
		sel.setParameter(3, idRicevente);
		sel.setParameter(4, idInviante);
		try {
			Object idChatTrovato = sel.getSingleResult();
			idChat = (Integer) idChatTrovato;
		} catch (NoResultException e) {
			
		}
		return idChat;
	}

}
