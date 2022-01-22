package com.chatternet.security;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.model.dto.UtenteDTO;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	private CredenzialeService credenzialeService;
	Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
		HttpSession mySession = request.getSession();
		UtenteDTO utente = new UtenteDTO();
		Object[] user =  credenzialeService.ricavaUtenteDaUsername(authentication.getName());
		logger.info("l'utente: {} {} ha effettuato l'accesso",user[1],user[2]);
		if(user[5] != null) {
		utente.setFoto((String) user[5]);
		}
		utente.setUsername(authentication.getName());
		utente.setId((int) user[0]);
		utente.setNome((String) user[1]);
		utente.setCognome((String) user[2]);
		utente.setDataNascita((Date) user[4]);
		mySession.setAttribute("utente", utente);
	    super.onAuthenticationSuccess(request, response, authentication);
    }
}
