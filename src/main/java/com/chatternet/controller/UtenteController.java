package com.chatternet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.images.ImmagineUploadUtil;
import com.chatternet.model.bean.Credenziale;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dto.UtenteDTO;
import com.chatternet.security.RememberMeSingleton;

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
		UtenteDTO udto;
		udto = (UtenteDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UtenteDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		String username = udto.getUsername();
		String nome = udto.getNome();
		String cognome = udto.getCognome();
		String foto = udto.getFotoPath();
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
		request.setAttribute("loggedUserId", udto.getId());
		return "profilo";
	}
	
	@PostMapping("/inserisciFoto")
	public String mettiFoto(HttpServletRequest request, @RequestParam("avatar") MultipartFile foto) throws IOException {
		HttpSession mySession = request.getSession();
		UtenteDTO udto;
		udto = (UtenteDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UtenteDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		Utente utente = new Utente();
		utente.setIdUtente(udto.getId());
		String nomeFoto = StringUtils.cleanPath(foto.getOriginalFilename());
		utente.setFotoProfilo(nomeFoto);
	    utenteService.inserisciFoto(utente);
	    String uploadDir = "user-photos/" + utente.getIdUtente();
	    ImmagineUploadUtil.saveFile(uploadDir, nomeFoto, foto);
	    udto.setFoto(nomeFoto);
		return "redirect:/paginaProfilo";
	}
	
	@DeleteMapping("/eliminaFoto")
	@ResponseBody
	public String eliminaFoto(HttpServletRequest request) throws IOException {
		HttpSession mySession = request.getSession();
		UtenteDTO udto;
		udto = (UtenteDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UtenteDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		udto.setFoto(null);
		Utente utente = new Utente();
		utente.setIdUtente(udto.getId());
		Object fotoProfilo = utenteService.prendiFoto(utente);
		String fotoProfiloString = fotoProfilo.toString();
		utenteService.eliminaFoto(utente);
		String imageDir = "user-photos/" + utente.getIdUtente();
		ImmagineUploadUtil.deleteFile(imageDir, fotoProfiloString);
		return "redirect:/paginaProfilo";
	}
	
	@GetMapping("/cercaUtente")
	public String cercaUtente(HttpServletRequest request, @RequestParam("nomeUtente") String nomeUtente) {
		HttpSession mySession = request.getSession();
		UtenteDTO udto;
		udto = (UtenteDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UtenteDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		String usernameResearcher = udto.getUsername();
		List<Utente[]> utenti = utenteService.ricercaUtente(nomeUtente, usernameResearcher);
		if (utenti.isEmpty()) {
			request.setAttribute("listaUtenti", null);
		} else {
			ArrayList<UtenteDTO> lista = new ArrayList<UtenteDTO>();
			for (Object[] utente : utenti) {
				UtenteDTO utenteView = new UtenteDTO();
				utenteView.setUsername((String) utente[0]);
				utenteView.setNome((String) utente[2]);
				utenteView.setCognome((String) utente[3]);
				utenteView.setId((int) utente[1]);
				utenteView.setFoto((String) utente[4]);
				lista.add(utenteView);
			}
			request.setAttribute("listaUtenti", lista);
		}
		request.setAttribute("loggedUserId", udto.getId());
		return "ricerca";
	}
	
	@GetMapping("/ricercaUtentePerId")
	@ResponseBody
	public UtenteDTO cercaDatiUtentePerNotifica(HttpServletRequest request, @RequestParam("idUtente") int idUtente) {
		Object[] utenteRicercato = utenteService.ricavaUtenteDaId(idUtente);
		UtenteDTO utente = new UtenteDTO();
		utente.setUsername((String) utenteRicercato[0]);
		utente.setFoto((String) utenteRicercato[1]);
		return utente;
	}
}
