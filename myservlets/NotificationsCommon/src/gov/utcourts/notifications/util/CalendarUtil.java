package gov.utcourts.notifications.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtil {
	
	public static final long ONE_DAY = 1000 * 60 * 60 * 24;
	public static final long ONE_MONTH = 1000 * 60 * 60 * 24 * 30;
	
	/*
	 * return the day ordinal string for the given day of the month
	 */
	public static String getDayOrdinal(int day) {
		String dayString = "th";
		switch (day) {
			case 1: 
			case 21:
			case 31:
					 dayString = "st";
					 break;
			case 2:
			case 22:
					 dayString = "nd";
			 		 break;
			case 3:
			case 23:  
					 dayString = "rd";
			 		 break;
		}
		return day + dayString;
	}
	
	/*
	 * return the month string for the given month
	 */
	public static String getMonthString(int month) {
		String monthString = "";
		switch (month) {
			case 0:  monthString = "January";		break;
			case 1:  monthString = "February";		break;
			case 2:  monthString = "March";			break;
			case 3:  monthString = "April";			break;
			case 4:  monthString = "May";			break;
			case 5:  monthString = "June";			break;
			case 6:  monthString = "July";			break;
			case 7:  monthString = "August";		break;
			case 8:  monthString = "September";		break;
			case 9:  monthString = "October";		break;
			case 10: monthString = "November";		break;
			case 11: monthString = "December";		break;
		}
		return monthString;
	}
	
	/*
	 * return the current calendar month or year
	 */
	public static int getCurrent(int whichOne) {
		Calendar current = GregorianCalendar.getInstance();
		current.setTime(new Date());
		return current.get(whichOne);
	}
	
	public static int getLastDayOfMonth(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.YEAR, year);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Date createDate(int month, int day, int year) {
		try {  
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		 	return (Date) formatter.parse("" + month + "/" + day + "/" + year);
		} catch (Exception e) {
			return new Date();
		}  
	}
	
	/*
	 * return if the month/year is prior to the specified month/year
	 */
	public static boolean dateBefore(Date date, int month, int year) {
		if (date == null) {
			return true;
		}
		boolean dateBefore = false;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (calendar.get(Calendar.MONTH) >= month && calendar.get(Calendar.YEAR) >= year) {
			dateBefore = true;
		} 
		calendar = null;
		return dateBefore;
	}
	
	public static long diffMonths(Calendar startDate, Calendar endDate) {
	    return diffDays(startDate, endDate) / 30;
	}
	
	public static long diffDays(Calendar startDate, Calendar endDate) {  
		Calendar date = (Calendar) startDate.clone();  
		long daysBetween = 0;  
		while (date.before(endDate)) {  
			date.add(Calendar.DAY_OF_MONTH, 1);  
			daysBetween++;  
		}  
		return daysBetween; 
	}
	
	public static long diffDays(Date startDate, Date endDate) {  
		Long datediff = startDate.getTime() - endDate.getTime();
		return datediff / CalendarUtil.ONE_DAY;
	}  
	
	public static String printDate() {
		return new java.text.SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss aaa").format(new java.util.Date()).toString();
	}
	
	public static Calendar convertDateToCalendar(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
