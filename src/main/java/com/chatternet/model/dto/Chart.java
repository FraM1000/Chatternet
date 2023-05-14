package com.chatternet.model.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Chart {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private final String[] MONTHSARRAY = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
	
	protected DateTimeFormatter getFormatter() {
		return formatter;
	}
	
	public abstract String getStartDateOfTheChart();
	
	public abstract String getEndDateOfTheChart();
	
	public abstract void fillChartWithRegisteredUsersRetrievedFromDB(List<Object[]> registeredUsersInDB);
	
	protected String formatDateToMonthAndYear(String date, boolean isFormatDifferentFromFormatter) {
		int monthInt = 0;
		String year = null;
		if(isFormatDifferentFromFormatter) {
			year = date.substring(0, 4);
			String month = date.substring(4, 6);
			monthInt = month.startsWith("0") ? Integer.parseInt(month.substring(1, 2)) -1 : Integer.parseInt(month) -1;
		} else {
			String dateWithoutTime = date.split(" ")[0];
			String[] dateWithoutTimeSplitted = dateWithoutTime.split("-");
			year = dateWithoutTimeSplitted[0];
			String month = dateWithoutTimeSplitted[1];
			monthInt = month.startsWith("0") ? Integer.parseInt(month.substring(1, 2)) -1 : Integer.parseInt(month) -1;
		}
		return MONTHSARRAY[monthInt] + " " + year;
	}
	
	protected String formatDateToDayMonthAndYear(String date, boolean isFormatDifferentFromFormatter) {
		int monthInt = 0;
		String year = null;
		String day = null;
		if(isFormatDifferentFromFormatter) {
			String[] dateSplitted = date.split("-");
			year = dateSplitted[0];
			String month = dateSplitted[1];
			day = dateSplitted[2];
			monthInt = month.startsWith("0") ? Integer.parseInt(month.substring(1, 2)) -1 : Integer.parseInt(month) -1;
		} else {
			String dateWithoutTime = date.split(" ")[0];
			String[] dateSplitted = dateWithoutTime.split("-");
			year = dateSplitted[0];
			String month = dateSplitted[1];
			day = dateSplitted[2];
			monthInt = month.startsWith("0") ? Integer.parseInt(month.substring(1, 2)) -1 : Integer.parseInt(month) -1;
		}
		return day + " " + MONTHSARRAY[monthInt] + " " + year;
	}
	
	protected void fillArraysEmptyCells(String[] users, String[] datesArr) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] == null) {
				users[i] = "0";
			}
		}
	}

}