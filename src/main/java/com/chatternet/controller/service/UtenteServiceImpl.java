package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dao.UtenteDAO;


@Service
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteDAO utenteDAO;

	@Override
	public void registraUtente(Utente utente) {
		utenteDAO.registraUtente(utente);
	}

	@Override
	public void inserisciFoto(Utente utente) {	
		utenteDAO.inserisciFoto(utente);
	}

	@Override
	public Object prendiFoto(Utente utente) {
		return utenteDAO.prendiFoto(utente);
	}
	
	
	
	

}
