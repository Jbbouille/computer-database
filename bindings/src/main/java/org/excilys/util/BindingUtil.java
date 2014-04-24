package org.excilys.util;

import java.util.List;

import org.excilys.model.Company;
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

	public Company getCompanyWithId(int id, List<Company> myList) {
		for (Company company : myList) {
			if (company.getId() == id)
				return company;

		}
		return null;
	}

	public Object[] validateParameter(String desc, String orderBy, String page) {
		Object[] myListObject = new Object[3];
		
		if (desc != null) {
			if (!desc.equals("false") && !desc.equals("true")) {
				myListObject[0] = false;
			} else {
				myListObject[0] = Boolean.valueOf(desc);
			}
		} else {
			myListObject[0] = false;
		}
		
		if (orderBy != null) {
			switch (orderBy) {
			case "name":
				myListObject[1] = "name";
				break;
			case "introduced":
				myListObject[1] = "introduced";
				break;
			case "discontinued":
				myListObject[1] = "discontinued";
				break;
			case "company":
				myListObject[1] = "company";
				break;
			default:
				myListObject[1] = "name";
				break;
			}
		} else {
			myListObject[1] = "name";
		}

		if (page != null) {
			if (page.matches("\\d+")) {
				myListObject[2] = Integer.valueOf(page);
			} else {
				myListObject[2] = 1;
			}
		} else {
			myListObject[2] = 1;
		}

		return myListObject;
	}
}
