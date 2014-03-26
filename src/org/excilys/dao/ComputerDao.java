package org.excilys.dao;

import java.util.ArrayList;

import org.excilys.model.Computer;

public interface ComputerDao {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public Computer selectComputer(int id);

	public ArrayList<Computer> selectAllComputers();

	public ArrayList<Computer> selectPartsComputers(int startLimit, int finLimit);

	public int countNumberComputers(String myName);

	public ArrayList<Computer> selectPartsSearchComputers(String myName,
			int startLimit, int finLimit);

}
