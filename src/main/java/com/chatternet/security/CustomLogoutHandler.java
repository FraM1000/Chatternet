package com.chatternet.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.UtenteDTO;

@Service
public class CustomLogoutHandler implements LogoutHandler  {
	
	public CustomLogoutHandler() {
		
	}
	
	@Autowired
	private UtenteService utenteService;
	Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		HttpSession mySession = request.getSession();
		UtenteDTO loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		utenteService.aggiornaStato(UserStatus.OFFLINE, loggedUser.getId());
		logger.info("l'utente: {} {} si è disconnesso",loggedUser.getNome(),loggedUser.getCognome());
	}

}
