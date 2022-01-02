package com.chatternet.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;

@Controller
@RequestMapping("/")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private CredenzialeService credenzialeService;
	
	@PostMapping("/registraUtente")
	public String registraUtente(RedirectAttributes redirectAttributes,
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("user") String username,
			@RequestParam("pass") String password,
			@RequestParam("dataNascita") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataNascita,
			@RequestParam("sex") String sesso,
			Utente utente, Credenziale credenziale) {
		credenziale.setUsername(username);
		credenziale.setPassword(password);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setSesso(sesso);
		utente.setDataNascita(dataNascita);
		try {
			credenzialeService.registraCredenziale(credenziale);
			utenteService.registraUtente(utente);
			credenzialeService.inserisciFK(credenziale, utente);
			redirectAttributes.addFlashAttribute("registrazione", true);
			return "redirect:/login";
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("registrazione", false);
			e.printStackTrace();
			return "redirect:/registrazione";
		}
		
	}
}
