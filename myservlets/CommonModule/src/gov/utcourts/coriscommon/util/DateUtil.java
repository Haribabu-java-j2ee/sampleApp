package gov.utcourts.coriscommon.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	
	public static Date addMonths(Date date, int months) {
		if (date == null)
			return null;
		
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
	}
	
	public static Date addDays(Date date, int days) {
		if (date == null)
			return null;
		
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
	
	public static Date addHours(Date date, int hours) {
		if (date == null)
			return null;
		
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }
 
	public static Date addMinutes(Date date, int minutes) {
		if (date == null)
			return null;
		
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
	
	public static Date addTime(Date date, Date time) {
		if (date == null || time == null)
			return null;
		
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date);
        
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(time);
        
        calendar1.add(Calendar.HOUR, calendar2.get(Calendar.HOUR_OF_DAY));
        calendar1.add(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
        
        return calendar1.getTime();
    }
	
	public static String getMonthName(Calendar c) {
		if (c == null)
			return null;
		
		return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	}
	
	public static int getMonth(Date date) {
		if (date == null)
			return 0;
			
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}
	
	public static int getYear(Date date) {
		if (date == null)
			return 0;
			
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static String formatMonth(Date date) {
		if (date == null)
			return "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMonthName(calendar);
	}
	
	public static Date getFirstDayOfYear(Date date) {
		if (date == null)
			return null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, 1); 
		return c.getTime();
	}
	
	public static Date getFirstDayOfMonth(Date date, int addMonths) {
		if (date == null)
			return null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, addMonths);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	public static Date subtractDays(Date date, int numberOfDays) {
		if (date == null)
			return null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -numberOfDays);
		return c.getTime();
	}
	
	public static Date subtractMonths(Date date, int numberOfMonths) {
		if (date == null)
			return null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -numberOfMonths);
		return c.getTime();
	}
	
	public static int getDaysBetween(Date dateFrom, Date dateTo) {
		if (dateFrom == null || dateTo == null)
			return 0;
			
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(dateFrom);
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(dateTo);
		
	    if (fromCalendar.get(Calendar.YEAR) == toCalendar.get(Calendar.YEAR)) {
	        return Math.abs(fromCalendar.get(Calendar.DAY_OF_YEAR) - toCalendar.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (toCalendar.get(Calendar.YEAR) > fromCalendar.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = fromCalendar;
	            fromCalendar = toCalendar;
	            toCalendar = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = fromCalendar.get(Calendar.DAY_OF_YEAR);

	        while (fromCalendar.get(Calendar.YEAR) > toCalendar.get(Calendar.YEAR)) {
	        	fromCalendar.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += fromCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - toCalendar.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;//		long diff = Math.abs(fromCalendar.getTimeInMillis() - toCalendar.getTimeInMillis());
	    }
	}
	
	public static String formatDate(Date date) {
		String formatedDate = "";
		if (date != null) {
			SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
			formatedDate = fmt.format(date);
		}
		return formatedDate;
	}
	
	public static String formatDateTwoYear(Date date) {
		String formatedDate = "";
		if (date != null) {
			SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yy");
			formatedDate = fmt.format(date);
		}
		return formatedDate;
	}
	
	public static String getMonthYear(Date date) {
		if (date == null)
			return "";
		
		return formatMonth(date) + " " + getYear(date);
	}
	
	public static boolean isToday(Date date) {
		if (date == null)
			return false;
		
		Date today = new Date();
		
		return getDaysBetween(today, date) == 0;
	}
	
	public static String getMonthYearNumeric(Date date) {
		String formatedDate = "";
		if (date != null) {
			SimpleDateFormat fmt = new SimpleDateFormat("MM-yy");
			formatedDate = fmt.format(date);
		}
		return formatedDate;
	}
	
	public static Date removeTime(Date date) {
	    Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
}
