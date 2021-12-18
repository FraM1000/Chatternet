package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.dao.CredenzialeDAO;

public class CredenzialeServiceImpl implements CredenzialeService {
	
	@Autowired
	private CredenzialeDAO credenzialeDAO;

	@Override
	public void registraCredenziale(Credenziale credenziale) {
		credenzialeDAO.registraCredenziale(credenziale);
		
	}

}
