package com.file.transfer.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ChronoUtils {
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
	public static final String DATE_TIME = "DATE_TIME";
	public static final String DATE = "DATE";
	public static final String TIME = "TIME";
	
	public Map<String, String> getNowDateTimeMap(){
		Map<String, String> result = new HashMap<String, String>();
		String dateTime = LocalDateTime.now().format(dateTimeFormatter);
		String date = dateTime.substring(0, 8);
		String time = dateTime.substring(8);
		result.put(DATE_TIME, dateTime);
		result.put(DATE, date);
		result.put(TIME, time);
		return result;
	}
	
	public String getNowDateTime() {
		return LocalDateTime.now().format(dateTimeFormatter);
	}
	
	public String getNowDate() {
		return LocalDateTime.now().format(dateFormatter);
	}
	
	public String getNowTime() {
		return LocalDateTime.now().format(timeFormatter);
	}
}
