package com.chatternet.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chatternet.controller.service.CredenzialeService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CredenzialeService credenzialeService;
	
	@GetMapping("/dashboard")
	public String paginaAdmin(HttpServletRequest request) {
		/* TODO: chiamare metodi che ritornino dati per popolare il grafico a front end
		 * i metodi devono ritornare una lista di array, questa lista conterrà 2 array di stringhe
		 * il primo array conterrà le date, es: ["Gennaio", "Febbraio"]
		 * il secondo array conterrà gli utenti iscritti, es: ["60", "80"]
		*/ 
		
		List<String[]> registeredUsersInThePastYear = credenzialeService.countRegisteredUsersInThePastYear();
		List<String[]> registeredUsersInThePastMonth = credenzialeService.countRegisteredUsersInThePastMonth();
		List<String[]> registeredUsersInThePastWeek = credenzialeService.countRegisteredUsersInThePastWeek();
		return "admin";
	}

}