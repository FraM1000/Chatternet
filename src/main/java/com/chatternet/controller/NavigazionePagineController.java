package com.chatternet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class NavigazionePagineController {
	
	@GetMapping("/login")
	public String paginaLogin() {
		return "login";	
	}
	
	@GetMapping("/registrazione")
	public String paginaRegistrazione() {
		return "registrazione";
	}
	
	@GetMapping("/index")
	public String paginaHome() {
		return "index";
	}
	 
	 @RequestMapping(value = "/Icona", method = RequestMethod.GET,
	            produces = MediaType.IMAGE_JPEG_VALUE)
	    public void getImageIcona(HttpServletResponse response) throws IOException {

	        ClassPathResource imgFile = new ClassPathResource("static/images/Icona.jpg");

	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	    }

}
