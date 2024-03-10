package com.chatternet.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chatternet.controller.service.CredentialService;
import com.chatternet.controller.service.UserService;
import com.chatternet.model.bean.User;
import com.chatternet.model.dto.MonthlyChartDTO;
import com.chatternet.model.dto.UserDTO;
import com.chatternet.model.dto.WeeklyChartDTO;
import com.chatternet.model.dto.YearlyChartDTO;
import com.chatternet.security.RememberMeSingleton;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/dashboard")
	public String adminPage(HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UserDTO loggedUser;
		loggedUser = (UserDTO) mySession.getAttribute("utente");
		if (loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		YearlyChartDTO yearlyChart = credentialService.getYearlyChartData();
		MonthlyChartDTO monthlyChart = credentialService.getMonthlyChartData();
		WeeklyChartDTO weeklyChart = credentialService.getWeeklyChartData();
		request.setAttribute("loggedUserName", loggedUser.getName());
		request.setAttribute("yearlyChartDates", Arrays.toString(yearlyChart.getPastYear()));
		request.setAttribute("yearlyChartRegisteredUsers", Arrays.toString(yearlyChart.getRegisteredUsers()));
		request.setAttribute("monthlyChartDates", Arrays.toString(monthlyChart.getPastMonth()));
		request.setAttribute("monthlyChartRegisteredUsers", Arrays.toString(monthlyChart.getRegisteredUsers()));
		request.setAttribute("weeklyChartDates", Arrays.toString(weeklyChart.getPastWeek()));
		request.setAttribute("weeklyChartRegisteredUsers", Arrays.toString(weeklyChart.getRegisteredUsers()));
		return "admin";
	}
	
	@GetMapping("/cercaUtente")
	public String searchUserAndReturnDashboard(HttpServletRequest request, @RequestParam("nomeUtente") String username) {
		HttpSession mySession = request.getSession();
		UserDTO loggedUser;
		loggedUser = (UserDTO) mySession.getAttribute("utente");
		if(loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UserDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		List<User[]> users = userService.findUser(username, loggedUser.getUsername());
		YearlyChartDTO yearlyChart = credentialService.getYearlyChartData();
		MonthlyChartDTO monthlyChart = credentialService.getMonthlyChartData();
		WeeklyChartDTO weeklyChart = credentialService.getWeeklyChartData();
		if (users.isEmpty()) {
			request.setAttribute("listaUtenti", null);
		} else {
			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			for (Object[] user : users) {
				UserDTO userDto = new UserDTO();
				userDto.setUsername((String) user[0]);
				userDto.setName((String) user[2]);
				userDto.setSurname((String) user[3]);
				userDto.setId((int) user[1]);
				userDto.setPhoto((String) user[4]);
				String blockedAccount = (String) user[5];
				userDto.setBlockedAccount(blockedAccount.equals("Y"));
				list.add(userDto);
			}
			request.setAttribute("listaUtenti", list);
		}
		request.setAttribute("loggedUserName", loggedUser.getName());
		request.setAttribute("yearlyChartDates", Arrays.toString(yearlyChart.getPastYear()));
		request.setAttribute("yearlyChartRegisteredUsers", Arrays.toString(yearlyChart.getRegisteredUsers()));
		request.setAttribute("monthlyChartDates", Arrays.toString(monthlyChart.getPastMonth()));
		request.setAttribute("monthlyChartRegisteredUsers", Arrays.toString(monthlyChart.getRegisteredUsers()));
		request.setAttribute("weeklyChartDates", Arrays.toString(weeklyChart.getPastWeek()));
		request.setAttribute("weeklyChartRegisteredUsers", Arrays.toString(weeklyChart.getRegisteredUsers()));
		return "admin";
	}
	
	@PutMapping("/lockOrUnlockAccount")
	@ResponseBody
	public String lockOrUnlockUserAccount(HttpServletRequest request, @RequestParam("username") String username, 
			@RequestParam("accountLockChoice") String accountLockChoice) {
		credentialService.lockOrUnlockUserAccount(username, accountLockChoice);
		return "admin";
	}
}