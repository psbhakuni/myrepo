package com.flightnetworks.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parsing date and time
 * 
 * To create a LocalDateTime object from a string you can use the static
 * LocalDateTime.parse() method. It takes a string and a DateTimeFormatter as
 * parameter. The DateTimeFormatter is used to specify the date/time pattern.
 * 
 * String str = "1986-04-08 12:30"; 
 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
 * LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
 * 
 * 
 * Formatting date and time
 * 
 * To create a formatted string out a LocalDateTime object you can use the
 * format() method.
 * 
 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
 * LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30); 
 * String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
 * 
 * @author Prakash
 *
 */
public class DateTimeUtility {
	private static final String MM_DD_YYYY_HH_MM_SS_1 = "MM-dd-yyyy HH:mm:ss";
	private static final String MM_DD_YYYY_HH_MM_SS_2 = "MM/dd/yyyy HH:mm:ss";

	private DateTimeUtility() {		
	}
	
	public static LocalDateTime getDateTime(String dateTimeString) {
		try {
			return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(MM_DD_YYYY_HH_MM_SS_1));
		} catch (DateTimeParseException e) {
			return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(MM_DD_YYYY_HH_MM_SS_2));
		}
		
	}

	public static String getDateTime(LocalDateTime dateTime) {
		try {
			return dateTime.format(DateTimeFormatter.ofPattern(MM_DD_YYYY_HH_MM_SS_1));
		} catch (IllegalArgumentException e) {
			return dateTime.format(DateTimeFormatter.ofPattern(MM_DD_YYYY_HH_MM_SS_2));
		}
		
	}

}
