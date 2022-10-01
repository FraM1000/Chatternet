package com.chatternet.controller.service;

import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.dao.ChatDAO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	
	@Override
	public int cercaChatTraUtenti(int idInviante, int idRicevente) throws NoResultException {
		int idChat = chatDAO.cercaChatTraUtenti(idInviante, idRicevente);
		return idChat;
	}

	@Override
	public int cercaChatTraUtentiSenzaCrearla(int idInviante, int idRicevente) {
		return chatDAO.cercaChatTraUtentiSenzaCrearla(idInviante, idRicevente);
		
	}
	
	@Override
	public int creaChat(int idInviante, int idRicevente, String dataPrimoMessaggioInviato) {
		return chatDAO.creaChat(idInviante, idRicevente, dataPrimoMessaggioInviato);
	}

	@Override
	public List<Object[]> ricavaChatDaUsername(String username) {
		return chatDAO.ricavaChatDaUsername(username);
	}

	@Override
	public void eliminaChat(int idChat) {
		chatDAO.eliminaChat(idChat);
	}

	@Override
	public void aggiornaDataUltimoMessaggioDellaChat(int idChat, String dataUltimoMessaggio) {
		chatDAO.aggiornaDataUltimoMessaggioDellaChat(idChat, dataUltimoMessaggio);
	}

}
