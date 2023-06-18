package com.chatternet.controller.service;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dto.MonthlyChartDTO;
import com.chatternet.model.dto.WeeklyChartDTO;
import com.chatternet.model.dto.YearlyChartDTO;

public interface CredenzialeService {
	
	public void registraCredenziale(Credenziale credenziale);
	
	public void inserisciFK(Credenziale credenziale, Utente utente);
	
	public Object[] ricavaUtenteDaUsername(String username);
	
	public void modificaPass(Credenziale credenziale);
	
	public int ricavaIdCredenziale(int idUtente);
	
	public YearlyChartDTO getYearlyChartData();
	
	public MonthlyChartDTO getMonthlyChartData();
	
	public WeeklyChartDTO getWeeklyChartData();
	
	public void lockOrUnlockUserAccount(String username, String accountLockChoice);
}