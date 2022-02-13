package com.chatternet.controller.service;

import java.util.List;
import com.chatternet.model.bean.Utente;

public interface UtenteService {
	
	public void registraUtente(Utente utente);
	
	public void inserisciFoto(Utente utente);
	
	public Object prendiFoto(Utente utente);
	
	public List<Utente[]> ricercaUtente(String nomeUtente, String usernameResearcher);
		

}
