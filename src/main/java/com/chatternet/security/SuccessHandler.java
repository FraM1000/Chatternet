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
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.UtenteDTO;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	private CredenzialeService credenzialeService;
	
	@Autowired
	private UtenteService utenteService;
	
	Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
	
	private static final String AUTHUSERSUCCESSURL = "/paginaChat";
	
	private static final String AUTHADMINSUCCESSURL = "/admin/dashboard";
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    		Authentication authentication) throws IOException, ServletException {
		HttpSession mySession = request.getSession();
		UtenteDTO utente = new UtenteDTO();
		Object[] user =  credenzialeService.ricavaUtenteDaUsername(authentication.getName());
		logger.info("l'utente: {} {} con ruolo {} ha effettuato l'accesso", user[1], user[2], user[7]);
		utenteService.aggiornaStato(UserStatus.ONLINE, (int) user[0]);
		if(user[5] != null) {
			utente.setFoto((String) user[5]);
		}
		utente.setUsername(authentication.getName());
		utente.setId((int) user[0]);
		utente.setNome((String) user[1]);
		utente.setCognome((String) user[2]);
		utente.setDataNascita((Date) user[4]);
		mySession.setAttribute("utente", utente);
		if(request.getParameter("remember-me") != null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			rememberMeToken.getTokenDatas().put("utente", utente);
		}
		String utenteRuolo = (String) user[7];
		if(utenteRuolo.equals("ADMIN")) {
			setDefaultTargetUrl(AUTHADMINSUCCESSURL);
			logger.info("l'utente è un ADMIN e sarà reindirizzato alla pagina admin");
		} else if(utenteRuolo.equals("USER")) {
			if(!getDefaultTargetUrl().equals(AUTHUSERSUCCESSURL)) {
				setDefaultTargetUrl(AUTHUSERSUCCESSURL);
			}
			logger.info("l'utente è un USER e sarà reindirizzato alla pagina homepage");
		}
		super.onAuthenticationSuccess(request, response, authentication);
    }
}
