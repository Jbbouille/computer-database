package org.excilys.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

public class Utilities {

	public static String dateSQLtoString(DateTime myDate) {

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

		return fmt.print(myDate);
	}

	public static DateTime stringToDate(String myString)
			throws UnsupportedOperationException, IllegalArgumentException {

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

		return fmt.parseDateTime(myString);
	}

	public static DateTime stringToDateRegional(String myString)
			throws UnsupportedOperationException, IllegalArgumentException {
		DateTimeFormatter fmt = null;

		String countryName = LocaleContextHolder.getLocale().getLanguage();

		if (countryName != null) {
			switch (countryName) {
			case "fr":
				fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
				break;

			case "en":
				fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
				break;

			default:
				fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
				break;
			}
		} else {
			fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		}
		return fmt.parseDateTime(myString);
	}

	public static String dateSQLtoStringRegional(DateTime myDate) {
		DateTimeFormatter fmt = null;

		String countryName = LocaleContextHolder.getLocale().getLanguage();

		if (countryName != null) {
			switch (countryName) {
			case "fr":
				fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
				break;

			case "en":
				fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
				break;

			default:
				fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
				break;
			}
		} else {
			fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		}
		return fmt.print(myDate);
	}
}
