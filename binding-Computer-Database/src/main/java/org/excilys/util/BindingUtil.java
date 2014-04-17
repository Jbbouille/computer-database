package org.excilys.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Repository;

@Repository
public class BindingUtil {

	@Autowired
	private ResourceBundleMessageSource myMessage;

	public DateTime stringToDateRegional(String myString)
			throws UnsupportedOperationException, IllegalArgumentException {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(myMessage.getMessage(
				"label.javaPattern", null, LocaleContextHolder.getLocale()));

		return fmt.parseDateTime(myString);
	}

	public String dateSQLtoStringRegional(DateTime myDate) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(myMessage.getMessage(
				"label.javaPattern", null, LocaleContextHolder.getLocale()));

		return fmt.print(myDate);
	}
}
