package com.chatternet.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dao.CredenzialeDAO;
import com.chatternet.model.dto.MonthlyChartDTO;
import com.chatternet.model.dto.WeeklyChartDTO;
import com.chatternet.model.dto.YearlyChartDTO;

@Service
public class CredenzialeServiceImpl implements CredenzialeService {
	
	@Autowired
	private CredenzialeDAO credenzialeDAO;

	@Override
	public void registraCredenziale(Credenziale credenziale) {
		credenzialeDAO.registraCredenziale(credenziale);
		
	}

	@Override
	public void inserisciFK(Credenziale credenziale, Utente utente) {
		credenzialeDAO.inserisciFK(credenziale,utente);
		
	}

	@Override
	public Object[] ricavaUtenteDaUsername(String username) {
		return credenzialeDAO.ricavaUtenteDaUsername(username);
	}

	@Override
	public void modificaPass(Credenziale credenziale) {
		credenzialeDAO.modificaPass(credenziale);
	}

	@Override
	public int ricavaIdCredenziale(int idUtente) {
		return credenzialeDAO.ricavaIdCredenziale(idUtente);
	}

	@Override
	public YearlyChartDTO getYearlyChartData() {
		YearlyChartDTO yearlyChart = new YearlyChartDTO();
		List<Object[]> registeredUsers = credenzialeDAO.countRegisteredUsersInThePastYear(yearlyChart.getStartDateOfTheChart(), yearlyChart.getEndDateOfTheChart());
		yearlyChart.fillChartWithRegisteredUsersRetrievedFromDB(registeredUsers);
		return yearlyChart;
	}

	@Override
	public MonthlyChartDTO getMonthlyChartData() {
		MonthlyChartDTO monthlyChart = new MonthlyChartDTO();
		List<Object[]> registeredUsers = credenzialeDAO.countRegisteredUsersFromStartDateToEndDate(monthlyChart.getStartDateOfTheChart(), monthlyChart.getEndDateOfTheChart());
		monthlyChart.fillChartWithRegisteredUsersRetrievedFromDB(registeredUsers);
		return monthlyChart;
	}

	@Override
	public WeeklyChartDTO getWeeklyChartData() {
		WeeklyChartDTO weeklyChart = new WeeklyChartDTO();
		List<Object[]> registeredUsers = credenzialeDAO.countRegisteredUsersFromStartDateToEndDate(weeklyChart.getStartDateOfTheChart(), weeklyChart.getEndDateOfTheChart());
		weeklyChart.fillChartWithRegisteredUsersRetrievedFromDB(registeredUsers);
		return weeklyChart;
	}
	
}