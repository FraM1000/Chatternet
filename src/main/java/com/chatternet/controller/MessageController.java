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
import com.chatternet.controller.service.MessageService;
import com.chatternet.model.bean.MessageStatus;
import com.chatternet.model.bean.Message;
import com.chatternet.model.dto.MessageDTO;

@Controller
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat")
	public void processMessage(@Payload MessageDTO messageDto) {
		logger.info("l'utente con id {} ha inviato un messaggio all'utente con id {}", messageDto.getSender(), messageDto.getReceiver());
		int idChat = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			idChat = chatService.findChatBetweenUserIfNotThrowNoResultException(messageDto.getSender(), messageDto.getReceiver());
		} catch (NoResultException e) {
			logger.info("nessuna chat trovata tra utente con id {} e utente con id {}", messageDto.getSender(), messageDto.getReceiver());
			LocalDateTime dateOfTheFirstMessageSended = LocalDateTime.now(ZoneId.of("Europe/Paris"));
			String dateOfTheFirstMessageSendedString = dateOfTheFirstMessageSended.format(formatter);
			idChat = chatService.createChat(messageDto.getSender(), messageDto.getReceiver(), dateOfTheFirstMessageSendedString);
		}
		LocalDateTime time = messageDto.getTime();
		String timeString = time.format(formatter);
		Message message = new Message(messageDto.getText(), timeString, messageDto.getSender(), idChat, MessageStatus.NOT_READ);
		messageService.saveMessage(message);
		LocalDateTime dateOfTheFirstMessageSended = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		String dateOfTheFirstMessageSendedString = dateOfTheFirstMessageSended.format(formatter);
		chatService.updateChatLastSendedMessageDate(idChat, dateOfTheFirstMessageSendedString);
		messagingTemplate.convertAndSendToUser(String.valueOf(messageDto.getReceiver()), "/queue/messages", messageDto);
	}

}
