package com.chatternet.model.dao;

import java.util.List;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.bean.User;

public interface UserDAO {
	
	public void saveUser(User user);
	
	public void saveUserPhoto(User user);
	
	public void deleteUserPhoto(User user);
	
	public Object getUserPhoto(User user);
	
	public List<User[]> findUser(String userName, String usernameResearcher);
		
    public Object[] getUserById(int id);
    
    public void updateStatus(UserStatus state, int id);
}
