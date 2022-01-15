package com.chatternet.model.dao;

import com.chatternet.model.bean.Utente;

public interface UtenteDAO {
	
	public void registraUtente(Utente utente);
	
	public void inserisciFoto(Utente utente);
	
	public Object prendiFoto(Utente utente);

}
