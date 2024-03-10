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
import com.chatternet.controller.service.CredentialService;
import com.chatternet.controller.service.UserService;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.UserDTO;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
	
	private static final String AUTHUSERSUCCESSURL = "/paginaChat";
	
	private static final String AUTHADMINSUCCESSURL = "/admin/dashboard";
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    		Authentication authentication) throws IOException, ServletException {
		HttpSession mySession = request.getSession();
		UserDTO userDto = new UserDTO();
		Object[] user =  credentialService.getUserByUsername(authentication.getName());
		logger.info("l'utente: {} {} con ruolo {} ha effettuato l'accesso", user[1], user[2], user[7]);
		userService.updateStatus(UserStatus.ONLINE, (int) user[0]);
		if(user[5] != null) {
			userDto.setPhoto((String) user[5]);
		}
		userDto.setUsername(authentication.getName());
		userDto.setId((int) user[0]);
		userDto.setName((String) user[1]);
		userDto.setSurname((String) user[2]);
		userDto.setBirthDate((Date) user[4]);
		mySession.setAttribute("utente", userDto);
		if(request.getParameter("remember-me") != null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			rememberMeToken.getTokenDatas().put("utente", userDto);
		}
		String userRole = (String) user[7];
		if(userRole.equals("ADMIN")) {
			setDefaultTargetUrl(AUTHADMINSUCCESSURL);
			logger.info("l'utente è un ADMIN e sarà reindirizzato alla pagina admin");
		} else if(userRole.equals("USER")) {
			if(!getDefaultTargetUrl().equals(AUTHUSERSUCCESSURL)) {
				setDefaultTargetUrl(AUTHUSERSUCCESSURL);
			}
			logger.info("l'utente è un USER e sarà reindirizzato alla pagina homepage");
		}
		super.onAuthenticationSuccess(request, response, authentication);
    }
}
