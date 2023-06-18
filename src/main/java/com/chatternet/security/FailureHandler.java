package com.chatternet.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    		AuthenticationException exception) throws IOException, ServletException {
		String loginErrorMessage = null;
		setDefaultFailureUrl("/login?error");
		if(exception instanceof BadCredentialsException) {
			loginErrorMessage = "Errore: credenziali inserite errate.";
		} else if(exception instanceof LockedException) {
			loginErrorMessage = "Errore: utenza bloccata.";
		}
		request.getSession().setAttribute("login", false);
		request.getSession().setAttribute("loginErrorMessage", loginErrorMessage);
		super.onAuthenticationFailure(request, response, exception);
	}

}