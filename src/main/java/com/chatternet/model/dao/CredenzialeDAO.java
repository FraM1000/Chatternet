package com.chatternet.model.dao;

import java.util.List;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;

public interface CredenzialeDAO {
	
	public void registraCredenziale(Credenziale credenziale);
	
	public void inserisciFK(Credenziale credenziale, Utente utente);

	public Object[] ricavaUtenteDaUsername(String username);
	
	public void modificaPass(Credenziale credenziale);
	
	public int ricavaIdCredenziale(int idUtente);
	
	public List<Object[]> countRegisteredUsersFromStartDateToEndDate(String startDate, String endDate);
	
	public List<Object[]> countRegisteredUsersInThePastYear(String startDate, String endDate);
	
	public void lockOrUnlockUserAccount(String username, String accountLockChoice);
}