package org.excilys.mapper;

import java.util.HashMap;

import org.excilys.dto.ComputerDto;
import org.excilys.model.Company;
import org.excilys.model.Computer;
import org.excilys.util.BindingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("modelMapper")
public class ModelMapper {
	
	@Autowired
	private BindingUtil myUtil;

	public Computer ComputerDtoToComputer(ComputerDto myComputerDto) {
		Computer myComputer = new Computer();

		myComputer
				.setCompanyId(Integer.valueOf(myComputerDto.getCompanyId()));
		if (myComputerDto.getId() != null)
			myComputer.setId(Integer.valueOf(myComputerDto.getId()));
		myComputer.setName(myComputerDto.getName());

		try {
			if (!myComputerDto.getDiscontinued().equals("")) {
				myComputer.setDiscontinued(myUtil
						.stringToDateRegional(myComputerDto.getDiscontinued()));
			}

			if (!myComputerDto.getIntroduced().equals("")) {
				myComputer.setIntroduced(myUtil
						.stringToDateRegional(myComputerDto.getIntroduced()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return myComputer;
	}

	public ComputerDto computerToComputerDto(Computer myComputer,
			HashMap<Integer, Company> myMap) {
		ComputerDto myComputerDto = new ComputerDto();

		myComputerDto.setId(String.valueOf(myComputer.getId()));
		myComputerDto.setName(myComputer.getName());

		if (myComputer.getDiscontinued() != null) {
			myComputerDto.setDiscontinued(myUtil
					.dateSQLtoStringRegional(myComputer.getDiscontinued()));
		} else {
			myComputerDto.setDiscontinued("");
		}

		if (myComputer.getIntroduced() != null) {
			myComputerDto.setIntroduced(myUtil
					.dateSQLtoStringRegional(myComputer.getIntroduced()));
		} else {
			myComputerDto.setIntroduced("");
		}

		if (myMap.get(myComputer.getCompanyId()) != null) {
			myComputerDto.setCompanyName(myMap.get(myComputer.getCompanyId()).toString());
			myComputerDto.setCompanyId(myComputer.getCompanyId());
		}else {
			myComputerDto.setCompanyName("");
		}

		return myComputerDto;
	}
}
