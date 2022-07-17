package com.chatternet.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.chatternet.controller.service.ChatService;
import com.chatternet.controller.service.MessaggioService;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.Messaggio;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.MessaggioDTO;
import com.chatternet.model.dto.UtenteDTO;

@Controller
@RequestMapping("/")
public class ChatController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@GetMapping("/mostraChat")
	public String mostraChat(@RequestParam("id") int id, HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		Object[] utenteTrovato = utenteService.ricavaUtenteDaId(id);
		UtenteDTO utente = new UtenteDTO();
		utente.setId(id);
		utente.setUsername((String) utenteTrovato[0]);
		utente.setFoto((String) utenteTrovato[1]);
		if(utenteTrovato[2] == UserStatus.ONLINE.toString()) {
			request.setAttribute("statoOnline", true);
		}
		UtenteDTO loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		int idChat = chatService.cercaChatTraUtentiSenzaCrearla(loggedUser.getId(), id);
		if(idChat != 0) {
			List<Messaggio[]> messaggi = messaggioService.cercaMessaggi(idChat);
			ArrayList<MessaggioDTO> lista = new ArrayList<MessaggioDTO>();
			for(Object[] messaggio : messaggi) {
				MessaggioDTO messaggioView = new MessaggioDTO();
				messaggioView.setTesto((String) messaggio[0]);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dataOra = LocalDateTime.parse((String) messaggio[1], format);
				messaggioView.setOra(dataOra);
				messaggioView.setUtenteInviante((int) messaggio[2]);
				lista.add(messaggioView);
			}
			request.setAttribute("listaMessaggi", lista);
		}
		request.setAttribute("utenteConCuiChattare", utente);
		request.setAttribute("loggedUserId", loggedUser.getId());
		return "chat";
	}
	
}
