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
	public int findChatBetweenUserIfNotThrowNoResultException(int senderId, int receiverId) throws NoResultException {
		int idChat = chatDAO.findChatBetweenUserIfNotThrowNoResultException(senderId, receiverId);
		return idChat;
	}

	@Override
	public int findChatBetweenUser(int senderId, int receiverId) {
		return chatDAO.findChatBetweenUser(senderId, receiverId);
		
	}
	
	@Override
	public int createChat(int senderId, int receiverId, String dateOfTheFirstMessageSended) {
		return chatDAO.createChat(senderId, receiverId, dateOfTheFirstMessageSended);
	}

	@Override
	public List<Object[]> findChatByUsername(String username) {
		return chatDAO.findChatByUsername(username);
	}

	@Override
	public void deleteChat(int idChat) {
		chatDAO.deleteChat(idChat);
	}

	@Override
	public void updateChatLastSendedMessageDate(int idChat, String lastSendedMessageDate) {
		chatDAO.updateChatLastSendedMessageDate(idChat, lastSendedMessageDate);
	}

}
