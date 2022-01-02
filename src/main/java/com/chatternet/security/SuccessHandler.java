package com.chatternet.security;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
		HttpSession mySession = request.getSession();
		UtenteDTO utente = new UtenteDTO();
		Object[] user =  credenzialeService.ricavaUtenteDaUsername(authentication.getName());
		if(user[5] != null) {
			Blob foto = (Blob) user[5];
			int lunghezzaFoto;
			try {
				lunghezzaFoto = (int) foto.length();
				byte[] fotoByte = foto.getBytes(1, lunghezzaFoto);
				String fotoUser = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fotoByte);
				utente.setFoto(fotoUser);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		utente.setUsername(authentication.getName());
		utente.setNome((String) user[1]);
		utente.setCognome((String) user[2]);
		mySession.setAttribute("utente", utente);
	    super.onAuthenticationSuccess(request, response, authentication);
    }
}
