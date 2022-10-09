package com.chatternet.controller;

import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.chatternet.controller.service.ChatService;
import com.chatternet.controller.service.MessaggioService;
import com.chatternet.model.bean.MessageStatus;
import com.chatternet.model.bean.Messaggio;
import com.chatternet.model.dto.MessaggioDTO;

@Controller
public class MessaggioController {
	
	Logger logger = LoggerFactory.getLogger(MessaggioController.class);
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat")
	public void processaMessaggio(@Payload MessaggioDTO messaggioDto) {
		logger.info("l'utente con id {} ha inviato un messaggio all'utente con id {}", messaggioDto.getUtenteInviante(), messaggioDto.getUtenteRicevente());
		int idChat = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			idChat = chatService.cercaChatTraUtenti(messaggioDto.getUtenteInviante(), messaggioDto.getUtenteRicevente());
		} catch (NoResultException e) {
			logger.info("nessuna chat trovata tra utente con id {} e utente con id {}", messaggioDto.getUtenteInviante(), messaggioDto.getUtenteRicevente());
			LocalDateTime dataPrimoMessaggioInviato = LocalDateTime.now(ZoneId.of("Europe/Paris"));
			String dataPrimoMessaggioInviatoStringa = dataPrimoMessaggioInviato.format(formatter);
			idChat = chatService.creaChat(messaggioDto.getUtenteInviante(), messaggioDto.getUtenteRicevente(), dataPrimoMessaggioInviatoStringa);
		}
		LocalDateTime dataOra = messaggioDto.getOra();
		String oraStringa = dataOra.format(formatter);
		Messaggio messaggio = new Messaggio(messaggioDto.getTesto(), oraStringa, messaggioDto.getUtenteInviante(), idChat, MessageStatus.NONLETTO);
		messaggioService.salvaMessaggio(messaggio);
		LocalDateTime dataPrimoMessaggioInviato = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		String dataPrimoMessaggioInviatoStringa = dataPrimoMessaggioInviato.format(formatter);
		chatService.aggiornaDataUltimoMessaggioDellaChat(idChat, dataPrimoMessaggioInviatoStringa);
		messagingTemplate.convertAndSendToUser(String.valueOf(messaggioDto.getUtenteRicevente()), "/queue/messages", messaggioDto);
	}

}
