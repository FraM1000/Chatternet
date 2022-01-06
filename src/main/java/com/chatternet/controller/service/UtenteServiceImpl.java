package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
	public void inserisciFoto(MultipartFile foto, int idUtente) {
		utenteDAO.inserisciFoto(foto, idUtente);
	}

	@Override
	public Object prendiFoto(int idUtente) {
		return utenteDAO.prendiFoto(idUtente);
	}
	
	
	
	

}
