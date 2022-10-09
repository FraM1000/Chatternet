package com.chatternet.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.chatternet.controller.service.ChatService;
import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.controller.service.MessaggioService;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.UtenteDTO;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	private CredenzialeService credenzialeService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessaggioService messaggioService;
	
	Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    		Authentication authentication) throws IOException, ServletException {
		HttpSession mySession = request.getSession();
		UtenteDTO utente = new UtenteDTO();
		Object[] user =  credenzialeService.ricavaUtenteDaUsername(authentication.getName());
		List<Object[]> chatRicavate = chatService.ricavaChatDaUsername(authentication.getName());
		logger.info("l'utente: {} {} ha effettuato l'accesso",user[1],user[2]);
		utenteService.aggiornaStato(UserStatus.ONLINE, (int) user[0]);
		if(user[5] != null) {
			utente.setFoto((String) user[5]);
		}
		utente.setUsername(authentication.getName());
		utente.setId((int) user[0]);
		utente.setNome((String) user[1]);
		utente.setCognome((String) user[2]);
		utente.setDataNascita((Date) user[4]);
		mySession.setAttribute("utente", utente);
		ArrayList<UtenteDTO> listaChat = new ArrayList<UtenteDTO>();
		if(!chatRicavate.isEmpty()) {
			/* chat è un Object[] di lunghezza 3,
			   Object[0] = idUtenteConCuiAbbiamoChattato,
			   Object[1] = dataUltimoMessaggioInviato,
			   Object[2] = idChat
			 */
			chatRicavate.forEach(chat -> {
				Object numMessRicNonLet = messaggioService.numeroMessaggiRicevutiNonLetti((int) chat[2], (int) chat[0]);
				Object[] utenteConCuiAbbiamoChattato = utenteService.ricavaUtenteDaId((int) chat[0]);
				UtenteDTO utenteView = new UtenteDTO();
				utenteView.setId((int) chat[0]);
				utenteView.setUsername((String) utenteConCuiAbbiamoChattato[0]);
				utenteView.setFoto((String) utenteConCuiAbbiamoChattato[1]);
				utenteView.setMessaggiRicevutiNonLetti(numMessRicNonLet);
				listaChat.add(utenteView);
			});
		}
		mySession.setAttribute("listaChat", listaChat);
		super.onAuthenticationSuccess(request, response, authentication);
    }
}
