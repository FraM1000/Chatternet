package com.chatternet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.dto.UtenteDTO;

@Controller
@RequestMapping("/")
public class CredenzialeController {
	
	@Autowired
	private CredenzialeService credenzialeService;
	
	@PostMapping("/modificaPassword")
	public String modificaPassword(RedirectAttributes redirectAttributes,
			@RequestParam("pass") String password1,
			@RequestParam("passFinale") String password2,
			Credenziale credenziale,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		UtenteDTO udto = (UtenteDTO) session.getAttribute("utente");
		int idUtente = udto.getId();
		if(password1.equals(password2)) {
			int idCredenziale = credenzialeService.ricavaIdCredenziale(idUtente);
			credenziale.setPassword(password2);
			credenziale.setIdCredenziale(idCredenziale);
			credenzialeService.modificaPass(credenziale);
			redirectAttributes.addFlashAttribute("passwordModificata", true);
		}
		return "redirect:/paginaProfilo";
	}

}
