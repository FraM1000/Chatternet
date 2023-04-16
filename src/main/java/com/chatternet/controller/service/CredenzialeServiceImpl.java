package com.chatternet.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dao.CredenzialeDAO;

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
	public List<String[]> countRegisteredUsersInThePastYear() {
		// get startDate and endDate from the calendar, take MessaggioController class as an example
		String startDate = null, endDate = null;
		credenzialeDAO.countRegisteredUsersFromStartDateToEndDate(startDate, endDate);
		return null;
	}

	@Override
	public List<String[]> countRegisteredUsersInThePastMonth() {
		String startDate = null, endDate = null;
		credenzialeDAO.countRegisteredUsersFromStartDateToEndDate(startDate, endDate);
		return null;
	}

	@Override
	public List<String[]> countRegisteredUsersInThePastWeek() {
		String startDate = null, endDate = null;
		credenzialeDAO.countRegisteredUsersFromStartDateToEndDate(startDate, endDate);
		return null;
	}

}