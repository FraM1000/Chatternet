package com.chatternet.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.bean.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager em;
	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	@Transactional
	public void saveUser(User user) {
		Query ins = em.createNativeQuery("INSERT INTO user(name,surname,sex,birthDate) VALUES(?,?,?,?)");
		ins.setParameter(1, user.getName());
		ins.setParameter(2, user.getSurname());
		ins.setParameter(3, user.getSex());
		ins.setParameter(4, user.getBirthDate());
		int rs = ins.executeUpdate();
		if(rs == 1) {
			logger.info("utente registrato");
		} else {
			logger.info("utente non registrato");
		}
		
	}

	@Override
	@Transactional
	public void saveUserPhoto(User user) {
		Query upd = em.createNativeQuery("UPDATE user SET profilePhoto = ? WHERE idUser = ?");
		upd.setParameter(1, user.getProfilePhoto());
		upd.setParameter(2, user.getIdUser());
		int rs = upd.executeUpdate();
		if(rs == 1) {
			logger.info("foto inserita");
		} else {
			logger.info("foto non inserita");
		}
	}
	
	@Override
	@Transactional
	public void deleteUserPhoto(User user) {
		Query upd = em.createNativeQuery("UPDATE user SET profilePhoto = null WHERE idUser = ?");
		upd.setParameter(1, user.getIdUser());
		int rs = upd.executeUpdate();
		if(rs == 1) logger.info("rimossa immagine profilo dell'utente con id {} ", user.getIdUser());
	}

	@Override
	public Object getUserPhoto(User user) {
		Query sel = em.createNativeQuery("SELECT profilePhoto FROM user WHERE idUser = ?");
		sel.setParameter(1, user.getIdUser());
		Object photo = sel.getSingleResult();
		return photo;
	}

	@Override
	public List<User[]> findUser(String userName, String usernameResearcher) {
		Query sel = em.createNativeQuery("SELECT c.username , u.idUser , u.name , u.surname , u.profilePhoto, c.blockedAccount \r\n"
				+ "FROM credential c , user u \r\n"
				+ "WHERE c.username LIKE ? \r\n"
				+ "AND c.role = ? \r\n"
				+ "AND NOT(c.username = ?) \r\n"
				+ "AND u.FKcredential = c.idCredential");
		sel.setParameter(1, userName + "%");
		sel.setParameter(2, "user");
		sel.setParameter(3, usernameResearcher);
		List<User[]> users = sel.getResultList();
		if(users.isEmpty()) {
			logger.info("nessun utente è stato trovato");
		}
		return users;
	}

	@Override
	public Object[] getUserById(int id) {
		Query sel = em.createNativeQuery("SELECT c.username , u.profilePhoto, u.state FROM credential c , user u \r\n"
				+ "WHERE u.idUser = ? \r\n"
				+ "AND u.FKcredential = c.idCredential");
		sel.setParameter(1, id);
		Object[] user = (Object[]) sel.getSingleResult();
		if(user == null) {
			logger.error("utente con id {} non trovato", id);
		}
		return user;
	}

	@Override
	@Transactional
	public void updateStatus(UserStatus state, int id) {
		Query upd = em.createNativeQuery("UPDATE user SET state = ? WHERE idUser = ?");
		upd.setParameter(1, state.toString());
		upd.setParameter(2, id);
		upd.executeUpdate();
	}

}
