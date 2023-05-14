package com.chatternet.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chatternet.controller.service.CredenzialeService;
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

}