package com.chatternet.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.Messaggio;

@Repository
public class MessaggioDAOImpl implements MessaggioDAO {

	@PersistenceContext
	private EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(MessaggioDAOImpl.class);
	
	@Override
	@Transactional
	public void salvaMessaggio(Messaggio messaggio) {
		Query ins = em.createNativeQuery("INSERT INTO messaggio(testo,ora,FKutente,FKchat) VALUES(?,?,?,?)");
		ins.setParameter(1, messaggio.getTesto());
		ins.setParameter(2, messaggio.getOra());
		ins.setParameter(3, messaggio.getFKutente());
		ins.setParameter(4, messaggio.getFKchat());
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

}
