package com.chatternet.model.dao;

public interface ChatDAO {
	
	public int cercaChatTraUtenti(int idInviante, int idRicevente);
	
	public int cercaChatTraUtentiSenzaCrearla(int idInviante, int idRicevente);

}
