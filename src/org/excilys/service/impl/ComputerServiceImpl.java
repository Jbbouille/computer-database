package org.excilys.service.impl;

import java.util.ArrayList;

import org.excilys.dao.impl.ComputerDaoImpl;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;

public class ComputerServiceImpl implements ComputerService {

	@Override
	public void insertComputer(Computer myComputer) {
		ComputerDaoImpl.getInstance().insertComputer(myComputer);
	}

	@Override
	public void deleteComputer(Computer myComputer) {
		ComputerDaoImpl.getInstance().deleteComputer(myComputer);
	}

	@Override
	public void updateComputer(Computer myComputer) {
		ComputerDaoImpl.getInstance().updateComputer(myComputer);
	}

	@Override
	public Computer selectComputer(int id) {
		return ComputerDaoImpl.getInstance().selectComputer(id);
	}

	@Override
	public ArrayList<Computer> selectAllComputers() {
		return ComputerDaoImpl.getInstance().selectAllComputers();
	}

}
