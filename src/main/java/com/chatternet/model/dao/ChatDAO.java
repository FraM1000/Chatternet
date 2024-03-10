package com.chatternet.model.dao;

import java.util.List;
import javax.persistence.NoResultException;

public interface ChatDAO {
	
	public int findChatBetweenUserIfNotThrowNoResultException(int senderId, int receiverId) throws NoResultException;
	
	public int findChatBetweenUser(int senderId, int receiverId);
	
	public int createChat(int senderId, int receiverId, String dateOfTheFirstMessageSended);
	
	public List<Object[]> findChatByUsername(String username);
	
	public void deleteChat(int idChat);
	
	public void updateChatLastSendedMessageDate(int idChat, String lastSendedMessageDate);
}
