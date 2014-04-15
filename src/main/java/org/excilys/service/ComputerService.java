package org.excilys.service;

import java.util.ArrayList;

import org.excilys.dto.ComputerDto;
import org.excilys.model.Computer;

public interface ComputerService {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public void closeThread();

	public ComputerDto selectComputer(int id);

	public ArrayList<ComputerDto> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow);

	public int countNumberOfComputers(String myName);

	public int getStartLimit(int idPage, int numberOfRow);

	public double numberOfPage(int numberComputers, int numberOfRow);

	public String getOrderBy(String myOrder, Boolean desc);
}
