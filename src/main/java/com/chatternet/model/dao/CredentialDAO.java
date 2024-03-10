package com.chatternet.model.dao;

import java.util.List;
import com.chatternet.model.bean.Credential;

public interface CredentialDAO {
	
	public void saveCredentials(Credential credential);
	
	public void insertForeignKey();
	
	public Object[] getUserByUsername(String username);
	
	public void updatePassword(Credential credential);
	
	public int getIdCredential(int idUser);
	
	public List<Object[]> countRegisteredUsersFromStartDateToEndDate(String startDate, String endDate);
	
	public List<Object[]> countRegisteredUsersInThePastYear(String startDate, String endDate);
	
	public void lockOrUnlockUserAccount(String username, String accountLockChoice);
}