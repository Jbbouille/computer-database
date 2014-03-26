package org.excilys.dao;

import java.util.ArrayList;

import org.excilys.model.Computer;

public interface ComputerDao {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public Computer selectComputer(int id);

	public int countNumberComputers(String myName);

	public ArrayList<Computer> selectPartsSearchComputers(String myName,
			String myOrder, int startLimit, int numberOfRow);

}
