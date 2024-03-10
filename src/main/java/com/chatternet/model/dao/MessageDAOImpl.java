package com.chatternet.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chatternet.model.bean.MessageStatus;
import com.chatternet.model.bean.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@PersistenceContext
	private EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);
	
	@Override
	@Transactional
	public void saveMessage(Message message) {
		Query ins = em.createNativeQuery("INSERT INTO message(text,time,FKuser,FKchat,state) VALUES(?,?,?,?,?)");
		ins.setParameter(1, message.getText());
		ins.setParameter(2, message.getTime());
		ins.setParameter(3, message.getFKuser());
		ins.setParameter(4, message.getFKchat());
		ins.setParameter(5, message.getState().toString());
		ins.executeUpdate();
	}

	@Override
	public List<Message[]> findMessages(int idChat) {
		Query sel = em.createNativeQuery("SELECT text , time , FKuser FROM message WHERE FKchat = ? \r\n"
				+ "ORDER BY str_to_date(time, '%Y-%m-%d %T')");
		sel.setParameter(1, idChat);
		List<Message[]> messaggi = sel.getResultList();
		return messaggi;
	}

	@Override
	@Transactional
	public void deleteChatMessages(int idChat) {
		Query del = em.createNativeQuery("DELETE FROM message WHERE FKchat = ?");
		del.setParameter(1, idChat);
		int rs = del.executeUpdate();
		if(rs >= 1) logger.info("messaggi eliminati nella chat con id {}", idChat);
	}

	@Override
	@Transactional
	public void updateNotReadedReceivedMessages(int idChat, int idOfMessagedUser) {
		Query upd = em.createNativeQuery("UPDATE message SET state = ? WHERE FKchat = ? \r\n"
				+ "AND FKuser = ? AND state = ?");
		upd.setParameter(1, MessageStatus.READ.toString());
		upd.setParameter(2, idChat);
		upd.setParameter(3, idOfMessagedUser);
		upd.setParameter(4, MessageStatus.NOT_READ.toString());
		upd.executeUpdate();
	}

	@Override
	public Object numberOfNotReadedReceivedMessages(int idChat, int idOfMessagedUser) {
		Query sel = em.createNativeQuery("SELECT COUNT(idMessage) FROM message WHERE FKchat = ? \r\n"
				+ "AND FKuser = ? AND state = ?");
		sel.setParameter(1, idChat);
		sel.setParameter(2, idOfMessagedUser);
		sel.setParameter(3, MessageStatus.NOT_READ.toString());
		return sel.getSingleResult();
	}

}
