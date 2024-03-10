package com.chatternet.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.bean.User;
import com.chatternet.model.dao.UserDAO;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	public void saveUserPhoto(User user) {	
		userDAO.saveUserPhoto(user);
	}
	
	@Override
	public void deleteUserPhoto(User user) {
		userDAO.deleteUserPhoto(user);
	}

	@Override
	public Object getUserPhoto(User user) {
		return userDAO.getUserPhoto(user);
	}

	@Override
	public List<User[]> findUser(String userName, String usernameResearcher) {
		return userDAO.findUser(userName,usernameResearcher);
	}

	@Override
	public Object[] getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public void updateStatus(UserStatus stato, int id) {
		userDAO.updateStatus(stato,id);
	}
	
}
