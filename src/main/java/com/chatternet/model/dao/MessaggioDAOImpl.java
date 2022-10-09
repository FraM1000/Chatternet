package com.chatternet.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chatternet.model.bean.MessageStatus;
import com.chatternet.model.bean.Messaggio;

@Repository
public class MessaggioDAOImpl implements MessaggioDAO {

	@PersistenceContext
	private EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(MessaggioDAOImpl.class);
	
	@Override
	@Transactional
	public void salvaMessaggio(Messaggio messaggio) {
		Query ins = em.createNativeQuery("INSERT INTO messaggio(testo,ora,FKutente,FKchat,stato) VALUES(?,?,?,?,?)");
		ins.setParameter(1, messaggio.getTesto());
		ins.setParameter(2, messaggio.getOra());
		ins.setParameter(3, messaggio.getFKutente());
		ins.setParameter(4, messaggio.getFKchat());
		ins.setParameter(5, messaggio.getStato().toString());
		ins.executeUpdate();
	}

	@Override
	public List<Messaggio[]> cercaMessaggi(int idChat) {
		Query sel = em.createNativeQuery("SELECT testo , ora , FKutente FROM messaggio WHERE FKchat = ? \r\n"
				+ "ORDER BY str_to_date(ora, '%Y-%m-%d %T')");
		sel.setParameter(1, idChat);
		List<Messaggio[]> messaggi = sel.getResultList();
		return messaggi;
	}

	@Override
	@Transactional
	public void eliminaMessaggiNellaChat(int idChat) {
		Query del = em.createNativeQuery("DELETE FROM messaggio WHERE FKchat = ?");
		del.setParameter(1, idChat);
		int rs = del.executeUpdate();
		if(rs >= 1) logger.info("messaggi eliminati nella chat con id {}", idChat);
	}

	@Override
	@Transactional
	public void aggiornaStatoMessaggiRicevutiNonLetti(int idChat, int idUtenteConCuiAbbiamoChattato) {
		Query upd = em.createNativeQuery("UPDATE messaggio SET stato = ? WHERE FKchat = ? \r\n"
				+ "AND FKutente = ? AND stato = ?");
		upd.setParameter(1, MessageStatus.LETTO.toString());
		upd.setParameter(2, idChat);
		upd.setParameter(3, idUtenteConCuiAbbiamoChattato);
		upd.setParameter(4, MessageStatus.NONLETTO.toString());
		upd.executeUpdate();
	}

	@Override
	public Object numeroMessaggiRicevutiNonLetti(int idChat, int idUtenteConCuiAbbiamoChattato) {
		Query sel = em.createNativeQuery("SELECT COUNT(idMessaggio) FROM messaggio WHERE FKchat = ? \r\n"
				+ "AND FKutente = ? AND stato = ?");
		sel.setParameter(1, idChat);
		sel.setParameter(2, idUtenteConCuiAbbiamoChattato);
		sel.setParameter(3, MessageStatus.NONLETTO.toString());
		return sel.getSingleResult();
	}

}
