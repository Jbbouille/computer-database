package org.excilys.service;

import java.util.ArrayList;

import org.excilys.model.Computer;

public interface ComputerService {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public Computer selectComputer(int id);

	public ArrayList<Computer> selectAllComputers();
}
