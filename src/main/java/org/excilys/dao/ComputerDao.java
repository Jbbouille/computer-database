package org.excilys.dao;

import java.util.ArrayList;

import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
<<<<<<< HEAD:src/main/java/org/excilys/dao/ComputerDao.java
=======
import org.excilys.exception.DaoException;
>>>>>>> 481708d1a6d302520afdaf97e03156e361fa2652:src/main/java/org/excilys/dao/ComputerDao.java

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
