package com.chatternet.model.dao;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;

public interface CredenzialeDAO {
	
	public void registraCredenziale(Credenziale credenziale);
	
	public void inserisciFK(Credenziale credenziale, Utente utente);

	public Object[] ricavaUtenteDaUsername(String username);
}
