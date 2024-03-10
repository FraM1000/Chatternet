package com.chatternet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatternet.model.bean.Credential;



@Repository
public interface AppUserRepositoryInterface extends JpaRepository<Credential, Integer>{
	
	Credential findByUsername(String username);

}
