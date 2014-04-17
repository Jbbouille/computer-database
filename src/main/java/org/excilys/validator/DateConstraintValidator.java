package org.excilys.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.excilys.util.BindingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class DateConstraintValidator implements
		ConstraintValidator<DateIntDisc, String> {

	@Autowired
	private ResourceBundleMessageSource myMessage;

	@Autowired
	private BindingUtil myUtil;

	@Override
	public void initialize(DateIntDisc arg0) {
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

		String dateRegex = myMessage.getMessage("label.regex", null,
				LocaleContextHolder.getLocale());

		if (!arg0.isEmpty()) {
			if (arg0.matches(dateRegex)) {
				try {
					myUtil.stringToDateRegional(arg0);
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
}
