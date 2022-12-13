package utilities.generic.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Timestamp {

	// adjust this as needed (switch to getTimestampMinus1Hour for daylight savings)
	public static String getTimestamp(String pattern) {
		return getTimestampMinus1Hour(pattern);
	}
	
	// return the current timestamp in the specified String format
	public static String getCurrentTimestamp(String pattern) {
		return getDateString(new Date(),pattern);
	}
	
	// return the current timestamp minus 1 hour in the specified String format
	public static String getTimestampMinus1Hour(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -1);		
		return getDateString(cal.getTime(),pattern);
	}
	
	// return a unique timestamp string
	public static String getUniqueTimestamp() {
		WaitUtil.waitForMilliSeconds(1);
		return getCurrentTimestamp("yyyyMMddHHmmssSS");
	}
	
	// return the string value for a date
	private static String getDateString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}
	
	/**
	 * Return tomorrow's date in a particular
	 * @param pattern
	 * @return
	 */
	public static String getFutureDate(int daysIntoFuture,String pattern) {
		Date today = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(today); 
		c.add(Calendar.DATE, daysIntoFuture);
		return getDateString(c.getTime(),pattern);
	}
}
