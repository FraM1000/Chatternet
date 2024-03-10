package com.chatternet.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.bean.Credential;
import com.chatternet.model.dao.CredentialDAO;
import com.chatternet.model.dto.MonthlyChartDTO;
import com.chatternet.model.dto.WeeklyChartDTO;
import com.chatternet.model.dto.YearlyChartDTO;

@Service
public class CredentialServiceImpl implements CredentialService {
	
	@Autowired
	private CredentialDAO credentialDAO;

	@Override
	public void saveCredentials(Credential credential) {
		credentialDAO.saveCredentials(credential);
		
	}

	@Override
	public void insertForeignKey() {
		credentialDAO.insertForeignKey();
		
	}

	@Override
	public Object[] getUserByUsername(String username) {
		return credentialDAO.getUserByUsername(username);
	}

	@Override
	public void updatePassword(Credential credential) {
		credentialDAO.updatePassword(credential);
	}

	@Override
	public int getIdCredential(int idUser) {
		return credentialDAO.getIdCredential(idUser);
	}

	@Override
	public YearlyChartDTO getYearlyChartData() {
		YearlyChartDTO yearlyChart = new YearlyChartDTO();
		List<Object[]> registeredUsers = credentialDAO.countRegisteredUsersInThePastYear(yearlyChart.getStartDateOfTheChart(), yearlyChart.getEndDateOfTheChart());
		yearlyChart.fillChartWithRegisteredUsersRetrievedFromDB(registeredUsers);
		return yearlyChart;
	}

	@Override
	public MonthlyChartDTO getMonthlyChartData() {
		MonthlyChartDTO monthlyChart = new MonthlyChartDTO();
		List<Object[]> registeredUsers = credentialDAO.countRegisteredUsersFromStartDateToEndDate(monthlyChart.getStartDateOfTheChart(), monthlyChart.getEndDateOfTheChart());
		monthlyChart.fillChartWithRegisteredUsersRetrievedFromDB(registeredUsers);
		return monthlyChart;
	}

	@Override
	public WeeklyChartDTO getWeeklyChartData() {
		WeeklyChartDTO weeklyChart = new WeeklyChartDTO();
		List<Object[]> registeredUsers = credentialDAO.countRegisteredUsersFromStartDateToEndDate(weeklyChart.getStartDateOfTheChart(), weeklyChart.getEndDateOfTheChart());
		weeklyChart.fillChartWithRegisteredUsersRetrievedFromDB(registeredUsers);
		return weeklyChart;
	}

	@Override
	public void lockOrUnlockUserAccount(String username, String accountLockChoice) {
		credentialDAO.lockOrUnlockUserAccount(username, accountLockChoice);
	}
	
}