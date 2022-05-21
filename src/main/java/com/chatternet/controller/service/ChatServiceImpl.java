package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.dao.ChatDAO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	
	@Override
	public int cercaChatTraUtenti(int idInviante, int idRicevente) {
		int idChat = chatDAO.cercaChatTraUtenti(idInviante, idRicevente);
		return idChat;
	}

}
