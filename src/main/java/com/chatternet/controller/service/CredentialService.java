package com.chatternet.controller.service;

import com.chatternet.model.bean.Credential;
import com.chatternet.model.dto.MonthlyChartDTO;
import com.chatternet.model.dto.WeeklyChartDTO;
import com.chatternet.model.dto.YearlyChartDTO;

public interface CredentialService {
	
	public void saveCredentials(Credential credential);
	
	public void insertForeignKey();
	
	public Object[] getUserByUsername(String username);
	
	public void updatePassword(Credential credential);
	
	public int getIdCredential(int idUser);
	
	public YearlyChartDTO getYearlyChartData();
	
	public MonthlyChartDTO getMonthlyChartData();
	
	public WeeklyChartDTO getWeeklyChartData();
	
	public void lockOrUnlockUserAccount(String username, String accountLockChoice);
}