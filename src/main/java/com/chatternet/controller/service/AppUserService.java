package com.chatternet.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.dao.AppUserRepositoryInterface;

@Service
public class AppUserService implements UserDetailsService{

	
	@Autowired
	private AppUserRepositoryInterface appUserRepository;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credenziale credenziale = appUserRepository.findByUsername(username);
		if(username==null) {
			throw new UsernameNotFoundException(username);
		}
		return credenziale;
	}	

}
