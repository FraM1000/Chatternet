package com.chatternet.controller;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dto.UtenteDTO;

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
	
	@GetMapping("/paginaProfilo")
	public String paginaProfilo(HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UtenteDTO udto = (UtenteDTO) mySession.getAttribute("utente");
		String username = udto.getUsername();
		String nome = udto.getNome();
		String cognome = udto.getCognome();
		String foto = udto.getFoto();
		Date dataNascita = udto.getDataNascita();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataNascita);
		int annoNascita = cal.get(Calendar.YEAR);
		int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
		int eta = annoCorrente - annoNascita;
		request.setAttribute("username", username);
		request.setAttribute("nome", nome);
		request.setAttribute("cognome", cognome);
		request.setAttribute("eta", eta);
		request.setAttribute("foto", foto);
		return "profilo";
	}
	
	@PostMapping("/inserisciFoto")
	public String mettiFoto(HttpServletRequest request, @RequestParam("avatar") MultipartFile foto) {
		HttpSession mySession = request.getSession();
		UtenteDTO udto = (UtenteDTO) mySession.getAttribute("utente");
	    int idUtente = udto.getId();
	    utenteService.inserisciFoto(foto, idUtente);
	    byte[] fotoPresa = (byte[]) utenteService.prendiFoto(idUtente);
	    String fotoUser = "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(fotoPresa);
	    udto.setFoto(fotoUser);
		return "redirect:/paginaProfilo";
	}
}
