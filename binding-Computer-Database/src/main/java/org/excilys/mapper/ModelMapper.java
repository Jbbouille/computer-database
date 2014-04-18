package org.excilys.mapper;

import java.util.List;

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

		myComputer.setCompanyId(Integer.valueOf(myComputerDto.getCompanyId()));
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
			List<Company> myList) {
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

		Company myComp = getCompany(myComputer.getCompanyId(), myList);

		if (myComp != null) {
			myComputerDto.setCompanyName(myComp.getName());
			myComputerDto.setCompanyId(myComp.getId());
		} else {
			myComputerDto.setCompanyName("");
		}

		return myComputerDto;
	}

	private Company getCompany(int id, List<Company> myList) {

		for (Company company : myList) {
			if (company.getId() == id) {
				return company;
			}
		}

		return null;
	}
}
