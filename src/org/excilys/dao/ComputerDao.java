package org.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.excilys.model.Computer;

public interface ComputerDao {

	public int insertComputer(Computer myComputer, Connection myCon) throws SQLException;

	public void deleteComputer(Computer myComputer, Connection myCon) throws SQLException;

	public void updateComputer(Computer myComputer, Connection myCon) throws SQLException;

	public Computer selectComputer(int id);

	public int countNumberComputers(String myName);

	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow);

}
