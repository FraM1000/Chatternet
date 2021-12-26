package com.chatternet.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.chatternet.model.bean.Utente;

@Repository
public class UtenteDAOImpl implements UtenteDAO {
	
	@PersistenceContext
	private EntityManager em;

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
			System.out.println("utente registrato");
		} else {
			System.out.println("utente non registrato");
		}
		
	}

}
