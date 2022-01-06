package com.chatternet.controller.service;

import org.springframework.web.multipart.MultipartFile;
import com.chatternet.model.bean.Utente;

public interface UtenteService {
	
	public void registraUtente(Utente utente);
	
	public void inserisciFoto(MultipartFile foto, int idUtente);
	
	public Object prendiFoto(int idUtente);
		

}
