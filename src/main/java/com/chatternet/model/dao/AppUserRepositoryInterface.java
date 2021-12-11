package com.chatternet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatternet.model.bean.Credenziale;



@Repository
public interface AppUserRepositoryInterface extends JpaRepository<Credenziale, Integer>{
	
	Credenziale findByUsername(String username);

}
