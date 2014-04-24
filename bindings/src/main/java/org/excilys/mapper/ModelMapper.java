package org.excilys.mapper;

import java.util.ArrayList;
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

	public Computer toComputer(ComputerDto myComputerDto) {
		Computer myComputer = new Computer();

		if (myComputerDto.getId() != null && myComputerDto.getCompanyId() != -1) {
			Company myComp = new Company();
			myComp.setId(myComputerDto.getCompanyId());
			myComp.setName(myComputerDto.getName());
			myComputer.setCompanyId(myComp);
		}

		if (myComputerDto.getId() != null)
			myComputer.setId(Integer.valueOf(myComputerDto.getId()));
		myComputer.setName(myComputerDto.getName());

		if (!myComputerDto.getDiscontinued().equals("")) {
			myComputer.setDiscontinued(myUtil
					.stringToDateRegional(myComputerDto.getDiscontinued()));
		}

		if (!myComputerDto.getIntroduced().equals("")) {
			myComputer.setIntroduced(myUtil.stringToDateRegional(myComputerDto
					.getIntroduced()));
		}

		return myComputer;
	}

	public ComputerDto toComputerDto(Computer myComputer, List<Company> myList) {
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

		if (myComputer.getCompanyId() != null) {
			myComputerDto.setCompanyName(myComputer.getCompanyId().getName());
			myComputerDto.setCompanyId(myComputer.getCompanyId().getId());
		} else {
			myComputerDto.setCompanyName("");
		}

		return myComputerDto;
	}

	public List<ComputerDto> toComputerDtoList(List<Computer> myComputers,
			List<Company> myCompanies) {
		List<ComputerDto> myList = new ArrayList<>();
		for (Computer item : myComputers) {
			myList.add(toComputerDto(item, myCompanies));
		}
		return myList;
	}

	public List<Computer> toComputerList(List<ComputerDto> myComputers,
			List<Company> myCompanies) {
		List<Computer> myList = new ArrayList<>();
		for (ComputerDto item : myComputers) {
			myList.add(toComputer(item));
		}
		return myList;
	}
}
