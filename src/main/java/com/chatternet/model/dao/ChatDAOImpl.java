package com.chatternet.model.dao;

import java.util.List;
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
	public int cercaChatTraUtenti(int idInviante, int idRicevente) throws NoResultException {
		Query sel = em.createNativeQuery("SELECT idChat FROM chat \r\n"
				+ "WHERE FKfirstUser = ? AND FKsecondUser = ? \r\n" 
				+ "OR FKfirstUser = ? AND FKsecondUser = ?;");
		sel.setParameter(1, idInviante);
		sel.setParameter(2, idRicevente);
		sel.setParameter(3, idRicevente);
		sel.setParameter(4, idInviante);
		Object idChatTrovato = sel.getSingleResult();
		int idChat = (Integer) idChatTrovato;
		if(idChatTrovato == null) {
			throw new NoResultException();
		}
		return idChat;
	}
	
	@Override
	@Transactional
	public int creaChat(int idInviante, int idRicevente, String dataPrimoMessaggioInviato) {
		Query ins = em.createNativeQuery("INSERT INTO chat(FKfirstUser,FKsecondUser,lastTextDate) VALUES(?,?,?)");
		ins.setParameter(1, idInviante);
		ins.setParameter(2, idRicevente);
		ins.setParameter(3, dataPrimoMessaggioInviato);
		int rs = ins.executeUpdate();
		if(rs == 1) logger.info("chat creata tra utente con id {} e utente con id {}", idInviante, idRicevente);
		int idChat = cercaChatTraUtenti(idInviante, idRicevente);
		return idChat;
	}

	@Override
	public int cercaChatTraUtentiSenzaCrearla(int idInviante, int idRicevente) {
		int idChat = 0;
		Query sel = em.createNativeQuery("SELECT idChat FROM chat \r\n"
	            + "WHERE FKfirstUser = ? AND FKsecondUser = ? \r\n" 
				+ "OR FKfirstUser = ? AND FKsecondUser = ?;");
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

	@Override
	public List<Object[]> ricavaChatDaUsername(String username) {
		Query sel = em.createNativeQuery("SELECT c.FKsecondUser, c.lastTextDate, c.idChat \r\n"
				+ "FROM chat c, user u \r\n"
				+ "WHERE c.FKfirstUser = (SELECT u.idUser \r\n"
				+ "FROM credential c,user u \r\n"
				+ "WHERE c.username = ? \r\n"
				+ "AND u.FKcredential = c.idCredential) \r\n"
				+ "AND c.FKfirstUser = u.idUser \r\n"
				+ "UNION \r\n"
				+ "SELECT c.FKfirstUser, c.lastTextDate, c.idChat \r\n"
				+ "FROM chat c, user u \r\n"
				+ "WHERE c.FKsecondUser = (SELECT u.idUser \r\n"
				+ "FROM credential c,user u \r\n"
				+ "WHERE c.username = ? \r\n"
				+ "AND u.FKcredential = c.idCredential) \r\n"
				+ "AND c.FKfirstUser = u.idUser \r\n"
				+ "ORDER BY str_to_date(lastTextDate, '%Y-%m-%d %T') DESC");
		sel.setParameter(1, username);
		sel.setParameter(2, username);
		List<Object[]> chatResults = sel.getResultList();
		return chatResults;
	}

	@Override
	@Transactional
	public void eliminaChat(int idChat) {
		Query del = em.createNativeQuery("DELETE FROM chat WHERE idChat = ?");
		del.setParameter(1, idChat);
		int rs = del.executeUpdate();
		if(rs == 1) logger.info("chat con id {} eliminata", idChat);
	}

	@Override
	@Transactional
	public void aggiornaDataUltimoMessaggioDellaChat(int idChat, String dataUltimoMessaggio) {
		Query upd = em.createNativeQuery("UPDATE chat SET lastTextDate = ? WHERE idChat = ?");
		upd.setParameter(1, dataUltimoMessaggio);
		upd.setParameter(2, idChat);
		upd.executeUpdate();
	}

}
