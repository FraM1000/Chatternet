package com.chatternet.model.dao;

import java.util.List;
import com.chatternet.model.bean.Utente;

public interface UtenteDAO {
	
	public void registraUtente(Utente utente);
	
	public void inserisciFoto(Utente utente);
	
	public Object prendiFoto(Utente utente);
	
	public List<?> ricercaUtente(String nomeUtente, String usernameResearcher);

}
