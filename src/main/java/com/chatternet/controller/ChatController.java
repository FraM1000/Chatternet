package com.chatternet.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.dto.UtenteDTO;

@Controller
@RequestMapping("/")
public class ChatController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/mostraChat")
	public String mostraChat(@RequestParam("id") int id, HttpServletRequest request) {
		Object[] utenteTrovato = utenteService.ricavaUtenteDaId(id);
		UtenteDTO utente = new UtenteDTO();
		utente.setId(id);
		utente.setUsername((String) utenteTrovato[0]);
		utente.setFoto((String) utenteTrovato[1]);
		request.setAttribute("utente", utente);
		return "chat";
	}

}
