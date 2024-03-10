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
import com.chatternet.controller.service.UserService;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.UserDTO;

@Service
public class CustomLogoutHandler implements LogoutHandler  {
	
	public CustomLogoutHandler() {
		
	}
	
	@Autowired
	private UserService userService;
	Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		HttpSession mySession = request.getSession();
		UserDTO loggedUser;
		loggedUser = (UserDTO) mySession.getAttribute("utente");
		RememberMeSingleton rememberMeToken = null;
		if(loggedUser == null) {
			rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		userService.updateStatus(UserStatus.OFFLINE, loggedUser.getId());
		logger.info("l'utente: {} {} si è disconnesso",loggedUser.getName(),loggedUser.getSurname());
		if(rememberMeToken != null) rememberMeToken.getTokenDatas().clear();
	}

}
