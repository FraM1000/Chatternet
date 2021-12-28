package com.chatternet.controller.service;

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

}
