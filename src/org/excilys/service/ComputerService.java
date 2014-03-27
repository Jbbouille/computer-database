package org.excilys.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.excilys.model.Computer;

public interface ComputerService {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public Computer selectComputer(int id);

	public int countNumberOfComputers(String myName);

	public double numberOfPage(int numberComputers, int numberOfRow);

	public int getStartLimit(int idPage, int numberOfRow);

	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow);

	public String getOrderBy(String myOrder, Boolean desc);

	public HttpServletRequest validateForm(String name, String introduced,
			String discontinued, int companyId, HttpServletRequest req);
	
	public boolean checkForm(String name, String introduced,
			String discontinued, int companyId);
}
