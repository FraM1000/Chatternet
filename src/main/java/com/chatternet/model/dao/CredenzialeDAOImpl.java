package com.chatternet.model.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Repository;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.UsernameEsistenteException;
import com.chatternet.model.bean.Utente;

@Repository
public class CredenzialeDAOImpl implements CredenzialeDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void registraCredenziale(Credenziale credenziale) throws UsernameEsistenteException {
		Query controlloUsern = em.createNativeQuery("SELECT username FROM credenziale WHERE username = ?");
		controlloUsern.setParameter(1, credenziale.getUsername());
		int controlloResult = controlloUsern.getFirstResult();
		if(controlloResult == 1) {
			throw new UsernameEsistenteException();
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A,12);
			String passCryptata = encoder.encode(credenziale.getPassword());
			Query ins = em.createNativeQuery("INSERT INTO credenziale(username,password) VALUES(?,?)");
			ins.setParameter(1, credenziale.getUsername());
			ins.setParameter(2, passCryptata);
			int rs = ins.executeUpdate();
			if(rs == 1) {
				System.out.println("credenziali registrate");
			}else {
				System.out.println("credenziali non registrate");
			}
		}
	
		
	}

	@Override
	@Transactional
	public void inserisciFK(Credenziale credenziale, Utente utente) {
		Query idC = em.createNativeQuery("SELECT MAX(idCredenziale) max FROM credenziale");
		Object rs = idC.getSingleResult();
		int FKcredenziale = (int) rs;
		System.out.println(FKcredenziale);
		Query id = em.createNativeQuery("SELECT MAX(idUtente) max FROM utente");
		Object rs2 = id.getSingleResult();
		 int idV = (int) rs2;
		System.out.println(idV);
		Query upd = em.createNativeQuery("UPDATE utente SET FKcredenziale=? WHERE idUtente = ?");
		upd.setParameter(1, FKcredenziale);
		upd.setParameter(2, idV);
		int res = upd.executeUpdate();
		if(res==1) {
			System.out.println("FKcredenziale settata");
		}
		
	}

}
