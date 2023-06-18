package com.chatternet.model.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class YearlyChartDTO extends Chart {
	
	private String[] pastYear;
	private String[] registeredUsers;
	private LocalDateTime currentDate;
	
	public YearlyChartDTO() {
		this.currentDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		this.pastYear = fillPastYear();
	}
	
	public String[] getPastYear() {
		return pastYear;
	}

	public String[] getRegisteredUsers() {
		return registeredUsers;
	}

	private String[] fillPastYear() {
		LocalDateTime startOfThePastYear = currentDate.minusMonths(12).minusHours(currentDate.getHour()).minusMinutes(currentDate.getMinute());
		String[] pastYear = new String[13];
		pastYear[0] = formatDateToMonthAndYear(startOfThePastYear.format(getFormatter()), false);
		pastYear[12] = formatDateToMonthAndYear(currentDate.format(getFormatter()), false);
		int i = 1;
		do {
			startOfThePastYear = startOfThePastYear.plusMonths(1);
			pastYear[i] = formatDateToMonthAndYear(startOfThePastYear.format(getFormatter()), false);
			i++;
		} while (i < 12);
		return pastYear;
	}
	
	@Override
	public String getStartDateOfTheChart() {
		LocalDateTime startOfThePastYear = currentDate.minusMonths(12).minusHours(currentDate.getHour()).minusMinutes(currentDate.getMinute());
		String startDateOfTheChart = startOfThePastYear.format(getFormatter());
		return startDateOfTheChart.split(" ")[0];
	}

	@Override
	public String getEndDateOfTheChart() {
		String endDateOfTheChart = currentDate.format(getFormatter());
		return endDateOfTheChart;
	}

	@Override
	public void fillChartWithRegisteredUsersRetrievedFromDB(List<Object[]> registeredUsersInDB) {
		String[] users = new String[13];
		for(int i = 0; i < registeredUsersInDB.size(); i++) {
			Object[] usersInDbEachMonth = registeredUsersInDB.get(i);
			Integer registrationMonth = (Integer) usersInDbEachMonth[0];
			String registrationMonthString = formatDateToMonthAndYear(registrationMonth.toString(), true);
			BigInteger numberOfUsers = (BigInteger) usersInDbEachMonth[1];
			String numberOfUsersString = numberOfUsers.toString();
			for(int y = 0; y < pastYear.length; y++) {
				String month = pastYear[y];
				if(registrationMonthString.equals(month)) {
					users[y] = numberOfUsersString;
				}
			}
		}
		fillArraysEmptyCells(users, pastYear);
		registeredUsers = users;
	}

}