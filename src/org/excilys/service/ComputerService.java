package org.excilys.service;

import java.util.ArrayList;

import org.excilys.model.Computer;

public interface ComputerService {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public Computer selectComputer(int id);

	public int countNumberComputers(String myName);

	public double numberPage(int numberComputers, int numberOfRow);

	public int getStartLimit(int idPage, int numberOfRow);

	public ArrayList<Computer> searchComputer(String myName, String myOrder, int startLimit,
			int numberOfRow);
	
	public String getOrderBy(String myOrder, Boolean desc);
}
