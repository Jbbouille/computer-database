package org.excilys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

	public static String dateSQLtoString(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(myDate);
	}

	public static Date stringToDate(String myString) {
		Date myDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			myDate = formatter.parse(myString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return myDate;
	}
}
