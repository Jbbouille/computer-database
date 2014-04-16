package org.excilys.validator;

import java.util.HashMap;

import org.excilys.dto.ComputerDto;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerValidator {

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Autowired
	private ComputerServiceImpl myComputerServ;

	private String intRegex = "-?\\d{0,9}";
	private String dateRegex = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";

	public HashMap<String, String> validateForm(ComputerDto myDto, boolean add) {

		HashMap<String, String> result = new HashMap<>();

		if (myDto.getName().length() < 2) {
			result.put("errorName", "Please enter at least 2 characters.");
			result.put("error", "true");
		}

		if (!myDto.getIntroduced().isEmpty()) {
			if (myDto.getIntroduced().matches(dateRegex)) {
				try {
					Utilities.stringToDate(myDto.getIntroduced());
				} catch (Exception e) {
					result.put("errorIntroduced",
							"Please enter a date in the format yyyy-mm-dd.");
					result.put("error", "true");
				}
			} else {
				result.put("errorIntroduced",
						"Please enter a date in the format yyyy-mm-dd.");
				result.put("error", "true");
			}
		}

		if (!myDto.getDiscontinued().isEmpty()) {
			if (myDto.getDiscontinued().matches(dateRegex)) {
				try {
					Utilities.stringToDate(myDto.getDiscontinued());
				} catch (Exception e) {
					result.put("errorDiscontinued",
							"Please enter a date in the format yyyy-mm-dd.");
					result.put("error", "true");
				}
			} else {
				result.put("errorDiscontinued",
						"Please enter a date in the format yyyy-mm-dd.");
				result.put("error", "true");
			}
		}

		if (add == false && idValidator(myDto.getId())!=null) {
			result.put("errorIdComp", idValidator(myDto.getId()));
			result.put("error", "true");
		}

		if (myDto.getCompanyName().matches(intRegex)) {
			int idCompany = Integer.valueOf(myDto.getCompanyName());
			if (idCompany != -1) {
				if (myCompanyServ.selectCompany(idCompany) == null) {
					result.put("errorCompany", "Please enter a valid Company.");
					result.put("error", "true");
				}
			}
		} else {
			result.put("errorCompany", "Please enter a valid Company.");
			result.put("error", "true");
		}
		return result;
	}

	public String idValidator(String idDto) {
		if (!idDto.equals("")) {
			if (idDto.matches(intRegex)) {
				int id = Integer.valueOf(idDto);
				if (myComputerServ.selectComputer(id) == null) {
					return "Please enter a valid ID of computer ! "
							+ idDto
							+ " is not valid. And please don't modify Source code (bastard).";
				} else {
					return null;
				}
			} else {
				return "Please enter a valid ID of computer ! "
						+ idDto
						+ " is not valid. And please don't modify Source code (bastard).";
			}
		} else {
			return "Please enter a valid ID of computer ! "
					+ idDto
					+ " is not valid. And please don't modify Source code (bastard).";
		}
	}
}
