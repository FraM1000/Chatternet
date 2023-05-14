package com.chatternet.model.dto;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class MonthlyChartDTO extends Chart {
	
	private String[] pastMonth;
	private String[] registeredUsers;
	private LocalDateTime currentDate;
	
	public MonthlyChartDTO() {
		this.currentDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		this.pastMonth = fillPastMonth();
	}
	
	public String[] getPastMonth() {
		return pastMonth;
	}

	public String[] getRegisteredUsers() {
		return registeredUsers;
	}

	private String[] fillPastMonth() {
		LocalDateTime startOfThePastMonth = currentDate.minusDays(31).minusHours(currentDate.getHour()).minusMinutes(currentDate.getMinute());
		String[] pastMonth = new String[32];
		pastMonth[0] = formatDateToDayMonthAndYear(startOfThePastMonth.format(getFormatter()), false);
		pastMonth[31] = formatDateToDayMonthAndYear(currentDate.format(getFormatter()), false);
		int i = 1;
		do {
			startOfThePastMonth = startOfThePastMonth.plusDays(1);
			pastMonth[i] = formatDateToDayMonthAndYear(startOfThePastMonth.format(getFormatter()), false);
			i++;
		} while (i < 31);
		return pastMonth;
	}
	
	@Override
	public String getStartDateOfTheChart() {
		LocalDateTime startOfThePastMonth = currentDate.minusDays(31).minusHours(currentDate.getHour()).minusMinutes(currentDate.getMinute());
		String startDateOfTheChart = startOfThePastMonth.format(getFormatter());
		return startDateOfTheChart.split(" ")[0];
	}

	@Override
	public String getEndDateOfTheChart() {
		String endDateOfTheChart = currentDate.format(getFormatter());
		return endDateOfTheChart;
	}

	@Override
	public void fillChartWithRegisteredUsersRetrievedFromDB(List<Object[]> registeredUsersInDB) {
		String[] users = new String[32];
		for(int i = 0; i < registeredUsersInDB.size(); i++) {
			Object[] usersInDbEachDay = registeredUsersInDB.get(i);
			Date registrationDay = (Date) usersInDbEachDay[0];
			String registrationDayString = formatDateToDayMonthAndYear(registrationDay.toString(), true);
			BigInteger numberOfUsers = (BigInteger) usersInDbEachDay[1];
			String numberOfUsersString = numberOfUsers.toString();
			for(int y = 0; y < pastMonth.length; y++) {
				String day = pastMonth[y];
				if(registrationDayString.equals(day)) {
					users[y] = numberOfUsersString;
				}
			}
		}
		fillArraysEmptyCells(users, pastMonth);
		registeredUsers = users;
	}

}