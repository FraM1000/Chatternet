package com.chatternet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.chatternet.controller.service.ChatService;
import com.chatternet.controller.service.MessaggioService;
import com.chatternet.model.bean.Messaggio;
import com.chatternet.model.dto.UtenteDTO;

@Controller
public class MessaggioController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat")
	public void processaMessaggio(@Payload Messaggio messaggio, HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UtenteDTO utenteRicevente = (UtenteDTO) mySession.getAttribute("utenteConCuiChattare");
		int idChat = chatService.cercaChatTraUtenti(messaggio.getFKutente(), utenteRicevente.getId());
		messaggio.setFKchat(idChat);
		messaggioService.salvaMessaggio(messaggio);
		messagingTemplate.convertAndSendToUser(String.valueOf(utenteRicevente.getId()), "/queue/messages", messaggio);
	}

}
