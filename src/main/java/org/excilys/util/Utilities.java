package org.excilys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

	public static String dateSQLtoString(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		return formatter.format(myDate);
	}

	public static Date stringToDate(String myString) throws ParseException {
		Date myDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		myDate = formatter.parse(myString);
		return myDate;
	}
}
