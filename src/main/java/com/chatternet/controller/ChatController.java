package com.chatternet.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		if(utenteTrovato[2] != null && utenteTrovato[2].equals(UserStatus.ONLINE.toString())) {
			request.setAttribute("statoOnline", true);
		}
		UtenteDTO loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		int idChat = chatService.cercaChatTraUtentiSenzaCrearla(loggedUser.getId(), id);
		if(idChat != 0) {
			messaggioService.aggiornaStatoMessaggiRicevutiNonLetti(idChat, id);
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
	
	@GetMapping("/paginaChat")
	public String paginaChat(HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UtenteDTO loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		List<Object[]> chatRicavate = chatService.ricavaChatDaUsername(loggedUser.getUsername());
		ArrayList<UtenteDTO> listaChat = new ArrayList<UtenteDTO>();
		if(!chatRicavate.isEmpty()) {
			/* chat è un Object[] di lunghezza 3,
			   Object[0] = idUtenteConCuiAbbiamoChattato,
			   Object[1] = dataUltimoMessaggioInviato,
			   Object[2] = idChat
			 */
			chatRicavate.forEach(chat -> {
				Object numMessRicNonLet = messaggioService.numeroMessaggiRicevutiNonLetti((int) chat[2], (int) chat[0]);
				Object[] utenteConCuiAbbiamoChattato = utenteService.ricavaUtenteDaId((int) chat[0]);
				UtenteDTO utenteView = new UtenteDTO();
				utenteView.setId((int) chat[0]);
				utenteView.setUsername((String) utenteConCuiAbbiamoChattato[0]);
				utenteView.setFoto((String) utenteConCuiAbbiamoChattato[1]);
				utenteView.setMessaggiRicevutiNonLetti(numMessRicNonLet);
				listaChat.add(utenteView);
			});
		}
		request.setAttribute("listaChat", listaChat);
		return "index";
	}
	
	@DeleteMapping("/eliminaChat")
	@ResponseBody
	public String eliminaChat(HttpServletRequest request, @RequestParam("idUtenteConCuiAbbiamoChattato") int idUtente) {
		HttpSession mySession = request.getSession();
		UtenteDTO loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		int idChat = chatService.cercaChatTraUtentiSenzaCrearla(loggedUser.getId(), idUtente);
		messaggioService.eliminaMessaggiNellaChat(idChat);
		chatService.eliminaChat(idChat);
		return "redirect:/paginaChat";
	}
	
}
