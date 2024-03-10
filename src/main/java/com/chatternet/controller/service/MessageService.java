package com.chatternet.controller.service;

import java.util.List;
import com.chatternet.model.bean.Message;

public interface MessageService {
	
	public void saveMessage(Message message);
	
	public List<Message[]> findMessages(int idChat);
	
	public void deleteChatMessages(int idChat);

	public void updateNotReadedReceivedMessages(int idChat, int idOfMessagedUser);
	
	public Object numberOfNotReadedReceivedMessages(int idChat, int idOfMessagedUser);
}
