package com.chatternet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class NavigazionePagineController {
	
	@GetMapping("/login")
	public String paginaLogin() {
		return "login";	
	}
	
	@PostMapping("/loginFailed")
	public String paginaLoginFailed(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("login", false);
		return "redirect:/login?error";
		
	}
	
	@GetMapping("/registrazione")
	public String paginaRegistrazione() {
		return "registrazione";
	}
	
	@GetMapping("/index")
	public String paginaHome() {
		return "index";
	}
	
	@GetMapping("/profilo")
	public String paginaProfilo() {
		return "profilo";
	}
	
	@GetMapping("/ricerca")
	public String paginaRicerca() {
		return "ricerca";
	}
	
	@GetMapping("/chat")
	public String paginaChat() {
		return "chat";
	}
	 
	 @RequestMapping(value = "/Icona", method = RequestMethod.GET,
	            produces = MediaType.IMAGE_JPEG_VALUE)
	    public void getImageIcona(HttpServletResponse response) throws IOException {

	        ClassPathResource imgFile = new ClassPathResource("static/images/Icona.jpg");

	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	    }
	 
	 @RequestMapping(value = "/User", method = RequestMethod.GET,
	            produces = MediaType.IMAGE_PNG_VALUE)
	    public void getImageUser(HttpServletResponse response) throws IOException {

	        ClassPathResource imgFile = new ClassPathResource("static/images/userProfileDefaultImage.png");

	        response.setContentType(MediaType.IMAGE_PNG_VALUE);
	        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	    }

}
