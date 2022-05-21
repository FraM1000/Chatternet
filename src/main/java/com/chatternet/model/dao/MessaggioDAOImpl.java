package com.chatternet.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.Messaggio;

@Repository
public class MessaggioDAOImpl implements MessaggioDAO {

	@PersistenceContext
	private EntityManager em;
	
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

}
