package org.excilys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.i18n.LocaleContextHolder;

public class Utilities {

	public static String dateSQLtoString(Date myDate) {
		SimpleDateFormat formatter = null;

		formatter = new SimpleDateFormat("yyyy-MM-dd");

		formatter.setLenient(false);
		return formatter.format(myDate);
	}

	public static Date stringToDate(String myString) throws ParseException {
		Date myDate = null;
		SimpleDateFormat formatter = null;

		formatter = new SimpleDateFormat("yyyy-MM-dd");

		formatter.setLenient(false);
		myDate = formatter.parse(myString);
		return myDate;
	}

	public static Date stringToDateRegional(String myString)
			throws ParseException {
		Date myDate = null;
		SimpleDateFormat formatter = null;

		String countryName = LocaleContextHolder.getLocale().getLanguage();

		if (countryName != null) {
			switch (countryName) {
			case "fr":
				formatter = new SimpleDateFormat("dd-MM-yyyy");
				break;

			case "en":
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				break;

			default:
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				break;
			}
		} else {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}
		formatter.setLenient(false);
		myDate = formatter.parse(myString);
		return myDate;
	}

	public static String dateSQLtoStringRegional(Date myDate) {
		SimpleDateFormat formatter = null;

		String countryName = LocaleContextHolder.getLocale().getLanguage();

		if (countryName != null) {
			switch (countryName) {
			case "fr":
				formatter = new SimpleDateFormat("dd-MM-yyyy");
				break;

			case "en":
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				break;

			default:
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				break;
			}
		} else {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}
		formatter.setLenient(false);
		return formatter.format(myDate);
	}
}
