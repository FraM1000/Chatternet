package com.chatternet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.chatternet.controller.service.CredentialService;
import com.chatternet.model.bean.Credential;
import com.chatternet.model.dto.UserDTO;
import com.chatternet.security.RememberMeSingleton;

@Controller
@RequestMapping("/")
public class CredentialController {
	
	@Autowired
	private CredentialService credentialService;
	
	@PostMapping("/modificaPassword")
	public String updatePassword(RedirectAttributes redirectAttributes,
			@RequestParam("pass") String password1,
			@RequestParam("passFinale") String password2,
			Credential credential,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDTO udto;
		udto = (UserDTO) session.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		int userId = udto.getId();
		if(password1.equals(password2)) {
			int credentialId = credentialService.getIdCredential(userId);
			credential.setPassword(password2);
			credential.setIdCredential(credentialId);
			credentialService.updatePassword(credential);
			redirectAttributes.addFlashAttribute("passwordModificata", true);
		}
		return "redirect:/paginaProfilo";
	}

}
