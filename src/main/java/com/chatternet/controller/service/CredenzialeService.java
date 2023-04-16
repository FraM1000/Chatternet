package com.chatternet.controller.service;

import java.util.List;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;

public interface CredenzialeService {
	
	public void registraCredenziale(Credenziale credenziale);
	
	public void inserisciFK(Credenziale credenziale, Utente utente);
	
	public Object[] ricavaUtenteDaUsername(String username);
	
	public void modificaPass(Credenziale credenziale);
	
	public int ricavaIdCredenziale(int idUtente);
	
	public List<String[]> countRegisteredUsersInThePastYear();
	
	public List<String[]> countRegisteredUsersInThePastMonth();
	
	public List<String[]> countRegisteredUsersInThePastWeek();
}
