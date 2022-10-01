package com.chatternet.controller.service;

import java.util.List;
import javax.persistence.NoResultException;

public interface ChatService {
	
	public int cercaChatTraUtenti(int idInviante, int idRicevente) throws NoResultException;
	
	public int cercaChatTraUtentiSenzaCrearla(int idInviante, int idRicevente);
	
	public int creaChat(int idInviante, int idRicevente, String dataPrimoMessaggioInviato);
	
	public List<Object[]> ricavaChatDaUsername(String username);
	
	public void eliminaChat(int idChat);
	
	public void aggiornaDataUltimoMessaggioDellaChat(int idChat, String dataUltimoMessaggio);

}
