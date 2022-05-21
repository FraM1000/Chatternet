package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.bean.Messaggio;
import com.chatternet.model.dao.MessaggioDAO;

@Service
public class MessaggioServiceImpl implements MessaggioService {
	
	@Autowired
	private MessaggioDAO messaggioDAO;

	@Override
	public void salvaMessaggio(Messaggio messaggio) {
		messaggioDAO.salvaMessaggio(messaggio);
	}

}
