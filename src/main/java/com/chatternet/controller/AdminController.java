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
import com.chatternet.controller.service.CredenzialeService;
import com.chatternet.controller.service.UtenteService;
import com.chatternet.model.bean.Utente;
import com.chatternet.model.dto.MonthlyChartDTO;
import com.chatternet.model.dto.UtenteDTO;
import com.chatternet.model.dto.WeeklyChartDTO;
import com.chatternet.model.dto.YearlyChartDTO;
import com.chatternet.security.RememberMeSingleton;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CredenzialeService credenzialeService;
	
	@Autowired
	private UtenteService utenteService;

	@GetMapping("/dashboard")
	public String paginaAdmin(HttpServletRequest request) {
		HttpSession mySession = request.getSession();
		UtenteDTO loggedUser;
		loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		if (loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UtenteDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		YearlyChartDTO yearlyChart = credenzialeService.getYearlyChartData();
		MonthlyChartDTO monthlyChart = credenzialeService.getMonthlyChartData();
		WeeklyChartDTO weeklyChart = credenzialeService.getWeeklyChartData();
		request.setAttribute("loggedUserName", loggedUser.getNome());
		request.setAttribute("yearlyChartDates", Arrays.toString(yearlyChart.getPastYear()));
		request.setAttribute("yearlyChartRegisteredUsers", Arrays.toString(yearlyChart.getRegisteredUsers()));
		request.setAttribute("monthlyChartDates", Arrays.toString(monthlyChart.getPastMonth()));
		request.setAttribute("monthlyChartRegisteredUsers", Arrays.toString(monthlyChart.getRegisteredUsers()));
		request.setAttribute("weeklyChartDates", Arrays.toString(weeklyChart.getPastWeek()));
		request.setAttribute("weeklyChartRegisteredUsers", Arrays.toString(weeklyChart.getRegisteredUsers()));
		return "admin";
	}
	
	@GetMapping("/cercaUtente")
	public String cercaUtenteERitornaDashboard(HttpServletRequest request, @RequestParam("nomeUtente") String username) {
		HttpSession mySession = request.getSession();
		UtenteDTO loggedUser;
		loggedUser = (UtenteDTO) mySession.getAttribute("utente");
		if(loggedUser == null) {
			RememberMeSingleton rememberMeToken = RememberMeSingleton.getToken();
			loggedUser = (UtenteDTO) rememberMeToken.getTokenDatas().get("utente");
		}
		List<Utente[]> users = utenteService.ricercaUtente(username, loggedUser.getUsername());
		YearlyChartDTO yearlyChart = credenzialeService.getYearlyChartData();
		MonthlyChartDTO monthlyChart = credenzialeService.getMonthlyChartData();
		WeeklyChartDTO weeklyChart = credenzialeService.getWeeklyChartData();
		if (users.isEmpty()) {
			request.setAttribute("listaUtenti", null);
		} else {
			ArrayList<UtenteDTO> list = new ArrayList<UtenteDTO>();
			for (Object[] user : users) {
				UtenteDTO userDto = new UtenteDTO();
				userDto.setUsername((String) user[0]);
				userDto.setNome((String) user[2]);
				userDto.setCognome((String) user[3]);
				userDto.setId((int) user[1]);
				userDto.setFoto((String) user[4]);
				String accountBloccato = (String) user[5];
				userDto.setAccountBloccato(accountBloccato.equals("Y"));
				list.add(userDto);
			}
			request.setAttribute("listaUtenti", list);
		}
		request.setAttribute("loggedUserName", loggedUser.getNome());
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
		credenzialeService.lockOrUnlockUserAccount(username, accountLockChoice);
		return "admin";
	}
}