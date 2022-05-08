package com.chatternet.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query sel = em.createNativeQuery("SELECT idChat FROM chat \r\n"
	            + "WHERE FKutenteUno = ? AND FKutenteDue = ? \r\n" 
				+ "OR FKutenteUno = ? AND FKutenteDue = ?;");
		sel.setParameter(1, idInviante);
		sel.setParameter(2, idRicevente);
		sel.setParameter(3, idRicevente);
		sel.setParameter(4, idInviante);
		try {
			sel.getSingleResult();
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}
