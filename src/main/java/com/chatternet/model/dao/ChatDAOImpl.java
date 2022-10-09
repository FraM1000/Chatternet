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
				+ "WHERE FKutenteUno = ? AND FKutenteDue = ? \r\n" 
				+ "OR FKutenteUno = ? AND FKutenteDue = ?;");
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
		Query ins = em.createNativeQuery("INSERT INTO chat(FKutenteUno,FKutenteDue,dataUltimoMessaggio) VALUES(?,?,?)");
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

	@Override
	public List<Object[]> ricavaChatDaUsername(String username) {
		Query sel = em.createNativeQuery("SELECT c.FKutenteDue, c.dataUltimoMessaggio, c.idChat \r\n"
				+ "FROM chat c, utente u \r\n"
				+ "WHERE c.FKutenteUno = (SELECT u.idUtente \r\n"
				+ "FROM credenziale c,utente u \r\n"
				+ "WHERE c.username = ? \r\n"
				+ "AND u.FKcredenziale = c.idCredenziale) \r\n"
				+ "AND c.FKutenteUno = u.idUtente \r\n"
				+ "UNION \r\n"
				+ "SELECT c.FKutenteUno, c.dataUltimoMessaggio, c.idChat \r\n"
				+ "FROM chat c, utente u \r\n"
				+ "WHERE c.FKutenteDue = (SELECT u.idUtente \r\n"
				+ "FROM credenziale c,utente u \r\n"
				+ "WHERE c.username = ? \r\n"
				+ "AND u.FKcredenziale = c.idCredenziale) \r\n"
				+ "AND c.FKutenteUno = u.idUtente \r\n"
				+ "ORDER BY str_to_date(dataUltimoMessaggio, '%Y-%m-%d %T') DESC");
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
		Query upd = em.createNativeQuery("UPDATE chat SET dataUltimoMessaggio = ? WHERE idChat = ?");
		upd.setParameter(1, dataUltimoMessaggio);
		upd.setParameter(2, idChat);
		upd.executeUpdate();
	}

}
