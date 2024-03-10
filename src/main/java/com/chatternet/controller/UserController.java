package com.chatternet.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.chatternet.controller.service.CredentialService;
import com.chatternet.controller.service.UserService;
import com.chatternet.images.ImageUploadUtil;
import com.chatternet.model.bean.Credential;
import com.chatternet.model.bean.User;
import com.chatternet.model.dto.UserDTO;
import com.chatternet.security.RememberMeSingleton;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private CredentialService credentialService;
	
	@PostMapping("/registraUtente")
	public String saveUser(RedirectAttributes redirectAttributes,
			@RequestParam("nome") String name,
			@RequestParam("cognome") String surname,
			@RequestParam("user") String username,
			@RequestParam("pass") String password,
			@RequestParam("dataNascita") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
			@RequestParam("sex") String sex,
			User user, Credential credential) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		String formattedCurrentDate = currentDate.format(formatter);
		credential.setUsername(username);
		credential.setPassword(password);
		credential.setSignupDate(formattedCurrentDate);
		user.setName(name);
		user.setSurname(surname);
		user.setSex(sex);
		user.setBirthDate(birthDate);
		try {
			credentialService.saveCredentials(credential);
			userService.saveUser(user);
			credentialService.insertForeignKey();
			redirectAttributes.addFlashAttribute("registrazione", true);
			return "redirect:/login";
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("registrazione", false);
			e.printStackTrace();
			return "redirect:/registrazione";
		}
		
	}
	
	@GetMapping("/paginaProfilo")
	public String profilePage(HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UserDTO udto;
		udto = (UserDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		String username = udto.getUsername();
		String name = udto.getName();
		String surname = udto.getSurname();
		String photo = udto.getPhotoPath();
		int age = udto.getAgeFromBirthDate();
		request.setAttribute("username", username);
		request.setAttribute("nome", name);
		request.setAttribute("cognome", surname);
		request.setAttribute("eta", age);
		request.setAttribute("foto", photo);
		request.setAttribute("loggedUserId", udto.getId());
		return "profile";
	}
	
	@PutMapping("/inserisciFoto")
	@ResponseBody
	public String saveUserPhoto(HttpServletRequest request, @RequestParam("avatar") MultipartFile photo) throws IOException {
		HttpSession mySession = request.getSession();
		UserDTO udto;
		udto = (UserDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		User user = new User();
		user.setIdUser(udto.getId());
		String nameOfThePhoto = StringUtils.cleanPath(photo.getOriginalFilename());
		user.setProfilePhoto(nameOfThePhoto);
	    userService.saveUserPhoto(user);
	    String uploadDir = "user-photos/" + user.getIdUser();
	    ImageUploadUtil.saveFile(uploadDir, nameOfThePhoto, photo);
	    udto.setPhoto(nameOfThePhoto);
		return "redirect:/paginaProfilo";
	}
	
	@DeleteMapping("/eliminaFoto")
	@ResponseBody
	public String deleteUserPhoto(HttpServletRequest request) throws IOException {
		HttpSession mySession = request.getSession();
		UserDTO udto;
		udto = (UserDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		udto.setPhoto(null);
		User user = new User();
		user.setIdUser(udto.getId());
		Object profilePhoto = userService.getUserPhoto(user);
		String profilePhotoString = profilePhoto.toString();
		userService.deleteUserPhoto(user);
		String imageDir = "user-photos/" + user.getIdUser();
		ImageUploadUtil.deleteFile(imageDir, profilePhotoString);
		return "redirect:/paginaProfilo";
	}
	
	@GetMapping("/cercaUtente")
	public String findUser(HttpServletRequest request, @RequestParam("nomeUtente") String userName) {
		HttpSession mySession = request.getSession();
		UserDTO udto;
		udto = (UserDTO) mySession.getAttribute("utente");
		if(udto == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			udto = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		String usernameResearcher = udto.getUsername();
		List<User[]> users = userService.findUser(userName, usernameResearcher);
		if (users.isEmpty()) {
			request.setAttribute("listaUtenti", null);
		} else {
			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			for (Object[] user : users) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername((String) user[0]);
				userDTO.setName((String) user[2]);
				userDTO.setSurname((String) user[3]);
				userDTO.setId((int) user[1]);
				userDTO.setPhoto((String) user[4]);
				list.add(userDTO);
			}
			request.setAttribute("listaUtenti", list);
		}
		request.setAttribute("loggedUserId", udto.getId());
		return "search";
	}
	
	@GetMapping("/ricercaUtentePerId")
	@ResponseBody
	public UserDTO findUserDataForTheNotification(HttpServletRequest request, @RequestParam("idUtente") int userId) {
		Object[] retrievedUser = userService.getUserById(userId);
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername((String) retrievedUser[0]);
		userDTO.setPhoto((String) retrievedUser[1]);
		return userDTO;
	}
}
