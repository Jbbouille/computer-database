package org.excilys.webservice.impl;

import java.util.ArrayList;

import javax.jws.WebService;

import org.excilys.model.Company;
import org.excilys.model.Computer;
import org.excilys.service.CompanyService;
import org.excilys.service.ComputerService;
import org.excilys.webservice.MyService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "org.excilys.webservice.MyService")
public class MyServiceImpl implements MyService {

	@Autowired
	private ComputerService computerServ;

	@Autowired
	private CompanyService companyServ;

	@Override
	public void insertComputer(Computer myComputer) {
		computerServ.insertComputer(myComputer);
	}

	@Override
	public void deleteComputer(Computer myComputer) {
		computerServ.deleteComputer(myComputer);
	}

	@Override
	public void updateComputer(Computer myComputer) {
		computerServ.updateComputer(myComputer);
	}

	@Override
	public Computer selectComputer(Integer id) {
		return computerServ.selectComputer(id);
	}

	@Override
	public ArrayList<Computer> selectComputers(String myLikeParam,
			Boolean bool, String orderBy, int currentPage,
			int NUMBER_OF_COMPUTER_BY_PAGE) {
		return computerServ.selectComputers(myLikeParam, bool, orderBy,
				currentPage, NUMBER_OF_COMPUTER_BY_PAGE);
	}

	@Override
	public int countNumberOfComputers(String myName, Boolean bool,
			String orderBy, int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE) {
		return computerServ.countNumberOfComputers(myName, bool, orderBy,
				currentPage, NUMBER_OF_COMPUTER_BY_PAGE);
	}

	@Override
	public ArrayList<Company> selectCompanies() {
		return companyServ.selectCompanies();
	}

}
