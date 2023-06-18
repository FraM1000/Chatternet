package com.chatternet.model.dto;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class WeeklyChartDTO extends Chart {
	
	private String[] pastWeek;
	private String[] registeredUsers;
	private LocalDateTime currentDate;
	
	public WeeklyChartDTO() {
		this.currentDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		this.pastWeek = fillPastWeek();
	}
	
	public String[] getPastWeek() {
		return pastWeek;
	}

	public String[] getRegisteredUsers() {
		return registeredUsers;
	}

	private String[] fillPastWeek() {
		LocalDateTime startOfThePastWeek = currentDate.minusDays(7).minusHours(currentDate.getHour()).minusMinutes(currentDate.getMinute());
		String[] pastWeek = new String[8];
		pastWeek[0] = formatDateToDayMonthAndYear(startOfThePastWeek.format(getFormatter()), false);
		pastWeek[7] = formatDateToDayMonthAndYear(currentDate.format(getFormatter()), false);
		int i = 1;
		do {
			startOfThePastWeek = startOfThePastWeek.plusDays(1);
			pastWeek[i] = formatDateToDayMonthAndYear(startOfThePastWeek.format(getFormatter()), false);
			i++;
		} while (i < 7);
		return pastWeek;
	}
	
	@Override
	public String getStartDateOfTheChart() {
		LocalDateTime startOfThePastWeek = currentDate.minusDays(7).minusHours(currentDate.getHour()).minusMinutes(currentDate.getMinute());
		String startDateOfTheChart = startOfThePastWeek.format(getFormatter());
		return startDateOfTheChart.split(" ")[0];
	}

	@Override
	public String getEndDateOfTheChart() {
		String endDateOfTheChart = currentDate.format(getFormatter());
		return endDateOfTheChart;
	}

	@Override
	public void fillChartWithRegisteredUsersRetrievedFromDB(List<Object[]> registeredUsersInDB) {
		String[] users = new String[8];
		for(int i = 0; i < registeredUsersInDB.size(); i++) {
			Object[] usersInDbEachDay = registeredUsersInDB.get(i);
			Date registrationDay = (Date) usersInDbEachDay[0];
			String registrationDayString = formatDateToDayMonthAndYear(registrationDay.toString(), true);
			BigInteger numberOfUsers = (BigInteger) usersInDbEachDay[1];
			String numberOfUsersString = numberOfUsers.toString();
			for(int y = 0; y < pastWeek.length; y++) {
				String day = pastWeek[y];
				if(registrationDayString.equals(day)) {
					users[y] = numberOfUsersString;
				}
			}
		}
		fillArraysEmptyCells(users, pastWeek);
		registeredUsers = users;
	}

}