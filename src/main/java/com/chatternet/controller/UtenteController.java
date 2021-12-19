package com.chatternet.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.UsernameEsistenteException;
import com.chatternet.model.bean.Utente;

@Controller
@RequestMapping("/")
public class UtenteController {

	@Autowired
	private CredenzialeService credenzialeService;
	
	@PostMapping("/registraUtente")
	public String registraUtente(HttpServletRequest request,
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("user") String username,
			@RequestParam("pass") String password,
			@RequestParam("dataNascita") Date dataNascita,
			@RequestParam("sex") String sesso,
			Utente utente, Credenziale credenziale) {
		credenziale.setUsername(username);
		credenziale.setPassword(password);
		try {
			credenzialeService.registraCredenziale(credenziale);
			request.setAttribute("registrazione", "success");
			return "index";
		}catch(UsernameEsistenteException uex) {
			request.setAttribute("registrazione", "failure");
			uex.printStackTrace();
			return "registrazione";
		}
		
		
	}
}
