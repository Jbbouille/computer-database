package org.excilys.service.impl;

import java.text.ParseException;
import java.util.ArrayList;

import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.excilys.util.Utilities;

public class ComputerServiceImpl implements ComputerService {

	@Override
	public void insertComputer(Computer myComputer) {
		DaoFactory.getInstanceComputerDao().insertComputer(myComputer);
	}

	@Override
	public void deleteComputer(Computer myComputer) {
		DaoFactory.getInstanceComputerDao().deleteComputer(myComputer);
	}

	@Override
	public void updateComputer(Computer myComputer) {
		DaoFactory.getInstanceComputerDao().updateComputer(myComputer);
	}

	@Override
	public Computer selectComputer(int id) {
		return DaoFactory.getInstanceComputerDao().selectComputer(id);
	}

	@Override
	public int countNumberOfComputers(String myName) {
		return DaoFactory.getInstanceComputerDao().countNumberComputers(myName);
	}

	@Override
	public double numberOfPage(int numberComputers, int numberOfRow) {
		return Math.ceil(numberComputers / numberOfRow) + 1;
	}

	@Override
	public int getStartLimit(int idPage, int numberOfRow) {
		return ((idPage - 1) * numberOfRow);
	}

	@Override
	public ArrayList<Computer> selectComputers(String myLikeParam, String myOrder,
			int startLimit, int numberOfRow) {
		return DaoFactory.getInstanceComputerDao().selectComputers(
				myLikeParam, myOrder, startLimit, numberOfRow);
	}

	@Override
	public String getOrderBy(String myOrder, Boolean desc) {
		StringBuilder myStringBuilder = new StringBuilder();

		switch (myOrder.toLowerCase()) {
		case "name":
			myStringBuilder.append("computer.name");

			break;
		case "introduced":
			myStringBuilder.append("computer.introduced");

			break;
		case "discontinued":
			myStringBuilder.append("computer.discontinued");

			break;
		case "company":
			myStringBuilder.append("company.name");

			break;
		default:
			myStringBuilder.append("computer.name");
			break;
		}

		if (desc) myStringBuilder.append(" DESC");

		return myStringBuilder.toString();
	}

	protected ComputerServiceImpl() {

	}

	@Override
	public int validateForm(String name, String introduced, String discontinued, int companyId) {
		int numberCompanies =  ServiceFactory.getCompanyServ().countCompanies();
		
		if(name.length() < 2) return 1;
		
		try {
			Utilities.stringToDate(introduced);
		} catch (ParseException e) {
			return 2;
		}
		
		try {
			Utilities.stringToDate(discontinued);
		} catch (ParseException e) {
			return 3;
		}
		
		if(companyId > numberCompanies || companyId < -1) return 4;
		
		return 0;
	}
}
