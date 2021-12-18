package com.chatternet.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.chatternet.model.bean.Credenziale;

public class CredenzialeDAOImpl implements CredenzialeDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void registraCredenziale(Credenziale credenziale) {
		Query ins = em.createNativeQuery("INSERT INTO credenziale(username,password) VALUES(?,?)");
		ins.setParameter(1, credenziale.getUsername());
		ins.setParameter(2, credenziale.getPassword());
		int rs = ins.executeUpdate();
		if(rs == 1) {
			System.out.println("credenziali registrate");
		}else {
			System.out.println("credenziali non registrate");
		}
		
	}

}
