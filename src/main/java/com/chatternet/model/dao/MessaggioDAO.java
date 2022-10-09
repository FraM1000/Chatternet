package com.chatternet.model.dao;

import java.util.List;
import com.chatternet.model.bean.Messaggio;

public interface MessaggioDAO {
	
	public void salvaMessaggio(Messaggio messaggio);
	
	public List<Messaggio[]> cercaMessaggi(int idChat);
	
	public void eliminaMessaggiNellaChat(int idChat);

	public void aggiornaStatoMessaggiRicevutiNonLetti(int idChat, int idUtenteConCuiAbbiamoChattato);
	
	public Object numeroMessaggiRicevutiNonLetti(int idChat, int idUtenteConCuiAbbiamoChattato);
}
