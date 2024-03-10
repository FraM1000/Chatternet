package com.chatternet.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatternet.model.bean.Message;
import com.chatternet.model.dao.MessageDAO;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDAO messageDAO;

	@Override
	public void saveMessage(Message message) {
		messageDAO.saveMessage(message);
	}

	@Override
	public List<Message[]> findMessages(int idChat) {
		return messageDAO.findMessages(idChat);
	}

	@Override
	public void deleteChatMessages(int idChat) {
		messageDAO.deleteChatMessages(idChat);
	}

	@Override
	public void updateNotReadedReceivedMessages(int idChat, int idOfMessagedUser) {
		messageDAO.updateNotReadedReceivedMessages(idChat, idOfMessagedUser);
	}

	@Override
	public Object numberOfNotReadedReceivedMessages(int idChat, int idOfMessagedUser) {
		return messageDAO.numberOfNotReadedReceivedMessages(idChat, idOfMessagedUser);
	}

}
