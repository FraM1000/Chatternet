package com.chatternet.controller.service;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.UsernameEsistenteException;

public interface CredenzialeService {
	
	public void registraCredenziale(Credenziale credenziale) throws UsernameEsistenteException;

}
