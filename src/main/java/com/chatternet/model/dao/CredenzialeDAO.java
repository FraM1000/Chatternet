package com.chatternet.model.dao;

import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.UsernameEsistenteException;

public interface CredenzialeDAO {
	
	public void registraCredenziale(Credenziale credenziale) throws UsernameEsistenteException;

}
