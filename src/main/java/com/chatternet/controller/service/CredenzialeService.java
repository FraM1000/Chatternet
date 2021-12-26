package com.chatternet.controller.service;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.UsernameEsistenteException;
import com.chatternet.model.bean.Utente;

public interface CredenzialeService {
	
	public void registraCredenziale(Credenziale credenziale) throws UsernameEsistenteException;
	
	public void inserisciFK(Credenziale credenziale, Utente utente);

}
