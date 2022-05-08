package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.dao.ChatDAO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	
	@Override
	public boolean cercaChatTraUtenti(int idInviante, int idRicevente) {
		int chatTrovata = chatDAO.cercaChatTraUtenti(idInviante, idRicevente);
		boolean result = chatTrovata == 1 ? true : false;
		return result;
	}

}
