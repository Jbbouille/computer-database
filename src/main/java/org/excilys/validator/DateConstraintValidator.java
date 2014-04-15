package org.excilys.validator;

import java.text.ParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.excilys.util.Utilities;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateConstraintValidator implements
		ConstraintValidator<DateIntDisc, String> {

	@Override
	public void initialize(DateIntDisc arg0) {
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		String countryName = LocaleContextHolder.getLocale().getLanguage();
		
		String dateRegex;

		if (countryName != null) {
			switch (countryName) {
			case "fr":
				dateRegex = "^([0-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-[0-9]{4}$";
				System.out.println("Lang validate annotation" + countryName);
				break;

			case "en":
				dateRegex = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
				break;

			default:
				dateRegex = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
				break;
			}
		} else {
			dateRegex = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
		}
		
		if (!arg0.isEmpty()) {
			if (arg0.matches(dateRegex)) {
				try {
					Utilities.stringToDateRegional(arg0);
				} catch (ParseException e) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

}
