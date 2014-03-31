package org.excilys.dao;

import java.util.ArrayList;

import org.excilys.model.Computer;

import exception.DaoException;

public interface ComputerDao {

	public int insertComputer(Computer myComputer)
			throws DaoException;

	public void deleteComputer(Computer myComputer)
			throws DaoException;

	public void updateComputer(Computer myComputer)
			throws DaoException;

	public Computer selectComputer(int id)
			throws DaoException;

	public int countNumberComputers(String myName)
			throws DaoException;

	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow)
			throws DaoException;

}
