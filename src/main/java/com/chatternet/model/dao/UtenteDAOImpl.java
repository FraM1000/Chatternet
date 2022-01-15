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

	@Override
	@Transactional
	public void inserisciFoto(Utente utente) {
		
		Query upd = em.createNativeQuery("UPDATE utente SET fotoProfilo = ? WHERE idUtente = ?");
		upd.setParameter(1, utente.getFotoProfilo());
		upd.setParameter(2, utente.getIdUtente());
		int rs = upd.executeUpdate();
		if(rs == 1) {
			System.out.println("foto inserita");
		} else {
			System.out.println("foto non inserita");
		}
	}

	@Override
	public Object prendiFoto(Utente utente) {
		Query sel = em.createNativeQuery("SELECT fotoProfilo FROM utente WHERE idUtente = ?");
		sel.setParameter(1, utente.getIdUtente());
		Object foto = sel.getSingleResult();
		return foto;
	}

}
