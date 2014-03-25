package org.excilys.service.impl;

import java.util.ArrayList;

import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;

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
	public ArrayList<Computer> selectAllComputers() {
		return DaoFactory.getInstanceComputerDao().selectAllComputers();
	}
	
	@Override
	public ArrayList<Computer> selectPartsComputers(int startLimit, int numberOfRow) {
		return DaoFactory.getInstanceComputerDao().selectPartsComputers(startLimit, numberOfRow);
	}

	@Override
	public int countNumberComputers() {
		return DaoFactory.getInstanceComputerDao().countNumberComputers();
	}

	@Override
	public double numberPage(int numberComputers, int numberOfRow) {
		return Math.ceil(numberComputers / numberOfRow);
	}

	@Override
	public int getStartLimit(int idPage, int numberOfRow) {
		return (idPage-1)*numberOfRow;
	}

	protected ComputerServiceImpl() {
		
	}
}
