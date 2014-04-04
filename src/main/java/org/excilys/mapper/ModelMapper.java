package org.excilys.mapper;

import java.text.ParseException;

import org.excilys.dto.ComputerDto;
import org.excilys.model.Computer;
import org.excilys.util.Utilities;
import org.springframework.stereotype.Component;

@Component("modelMapper")
public class ModelMapper {

	public Computer ComputerDtoToComputer(ComputerDto myComputerDto) {
		Computer myComputer = new Computer();

		myComputer.setCompanyId(Integer.valueOf(myComputerDto.getCompanyId()));
		if (myComputerDto.getId() != null)
			myComputer.setId(Integer.valueOf(myComputerDto.getId()));
		myComputer.setName(myComputerDto.getName());

		try {
			if (!myComputerDto.getDiscontinued().equals("")) {
				myComputer.setDiscontinued(Utilities.stringToDate(myComputerDto
						.getDiscontinued()));
			}

			if (!myComputerDto.getIntroduced().equals("")) {
				myComputer.setIntroduced(Utilities.stringToDate(myComputerDto
						.getIntroduced()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return myComputer;
	}

	public ComputerDto computerToComputerDto(Computer myComputer) {
		ComputerDto myComputerDto = new ComputerDto();

		myComputerDto.setCompanyId(String.valueOf(myComputer.getCompanyId()));

		myComputerDto.setId(String.valueOf(myComputer.getId()));
		myComputerDto.setName(myComputer.getName());

		if (myComputer.getDiscontinued() != null) {
			myComputerDto.setDiscontinued(Utilities.dateSQLtoString(myComputer
					.getDiscontinued()));
		} else {
			myComputerDto.setDiscontinued("");
		}

		if (myComputer.getIntroduced() != null) {
			myComputerDto.setIntroduced(Utilities.dateSQLtoString(myComputer
					.getIntroduced()));
		} else {
			myComputerDto.setIntroduced("");
		}

		return myComputerDto;
	}
}
