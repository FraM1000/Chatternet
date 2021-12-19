package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.UsernameEsistenteException;
import com.chatternet.model.dao.CredenzialeDAO;

@Service
public class CredenzialeServiceImpl implements CredenzialeService {
	
	@Autowired
	private CredenzialeDAO credenzialeDAO;

	@Override
	public void registraCredenziale(Credenziale credenziale) throws UsernameEsistenteException {
		credenzialeDAO.registraCredenziale(credenziale);
		
	}

}
