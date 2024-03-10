package com.chatternet.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chatternet.controller.service.ChatService;
import com.chatternet.controller.service.MessageService;
import com.chatternet.controller.service.UserService;
import com.chatternet.model.bean.Message;
import com.chatternet.model.bean.UserStatus;
import com.chatternet.model.dto.MessageDTO;
import com.chatternet.model.dto.UserDTO;
import com.chatternet.security.RememberMeSingleton;

@Controller
@RequestMapping("/")
public class ChatController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/mostraChat")
	public String showChat(@RequestParam("id") int id, HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		Object[] retrievedUser = userService.getUserById(id);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setUsername((String) retrievedUser[0]);
		userDTO.setPhoto((String) retrievedUser[1]);
		if(retrievedUser[2] != null && retrievedUser[2].equals(UserStatus.ONLINE.toString())) {
			request.setAttribute("statoOnline", true);
		}
		UserDTO loggedUser;
		loggedUser = (UserDTO) mySession.getAttribute("utente");
		if(loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		int idChat = chatService.findChatBetweenUser(loggedUser.getId(), id);
		if(idChat != 0) {
			messageService.updateNotReadedReceivedMessages(idChat, id);
			List<Message[]> messages = messageService.findMessages(idChat);
			ArrayList<MessageDTO> list = new ArrayList<MessageDTO>();
			for(Object[] message : messages) {
				MessageDTO messageDTO = new MessageDTO();
				messageDTO.setText((String) message[0]);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dataOra = LocalDateTime.parse((String) message[1], format);
				messageDTO.setTime(dataOra);
				messageDTO.setSender((int) message[2]);
				list.add(messageDTO);
			}
			request.setAttribute("listaMessaggi", list);
		}
		request.setAttribute("utenteConCuiChattare", userDTO);
		request.setAttribute("loggedUserId", loggedUser.getId());
		return "chat";
	}
	
	@GetMapping("/paginaChat")
	public String chatPage(HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UserDTO loggedUser;
		loggedUser = (UserDTO) mySession.getAttribute("utente");
		if(loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		List<Object[]> retrievedChat = chatService.findChatByUsername(loggedUser.getUsername());
		ArrayList<UserDTO> chatList = new ArrayList<UserDTO>();
		if(!retrievedChat.isEmpty()) {
			/* chat è un Object[] di lunghezza 3,
			   Object[0] = idUtenteConCuiAbbiamoChattato,
			   Object[1] = dataUltimoMessaggioInviato,
			   Object[2] = idChat
			 */
			retrievedChat.forEach(chat -> {
				Object numberOfNotReadedReceivedMessages = messageService.numberOfNotReadedReceivedMessages((int) chat[2], (int) chat[0]);
				Object[] userWeChattedWith = userService.getUserById((int) chat[0]);
				UserDTO userDTO = new UserDTO();
				userDTO.setId((int) chat[0]);
				userDTO.setUsername((String) userWeChattedWith[0]);
				userDTO.setPhoto((String) userWeChattedWith[1]);
				userDTO.setUnreadReceivedMessages(numberOfNotReadedReceivedMessages);
				chatList.add(userDTO);
			});
		}
		request.setAttribute("listaChat", chatList);
		request.setAttribute("loggedUserId", loggedUser.getId());
		return "homepage";
	}
	
	@DeleteMapping("/eliminaChat")
	@ResponseBody
	public String deleteChat(HttpServletRequest request, @RequestParam("idUtenteConCuiAbbiamoChattato") int userId) {
		HttpSession mySession = request.getSession();
		UserDTO loggedUser;
		loggedUser = (UserDTO) mySession.getAttribute("utente");
		if(loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		int idChat = chatService.findChatBetweenUser(loggedUser.getId(), userId);
		messageService.deleteChatMessages(idChat);
		chatService.deleteChat(idChat);
		return "redirect:/paginaChat";
	}
	
}
