package com.chatternet.controller.service;

public interface ChatService {
	
	public int cercaChatTraUtenti(int idInviante, int idRicevente);
	
	public int cercaChatTraUtentiSenzaCrearla(int idInviante, int idRicevente);

}
