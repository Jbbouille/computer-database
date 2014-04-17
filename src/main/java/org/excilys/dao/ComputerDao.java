package org.excilys.dao;

import java.util.ArrayList;

import org.excilys.exception.DaoException;
import org.excilys.model.Computer;

/**
 * ComputerDao interface contain all methods to make CRUD operations on Computer
 * database
 * 
 * @author excilys
 * 
 */
public interface ComputerDao {

	/**
	 * Take a Computer in parameter and insert it into the computer Database
	 * 
	 * @param myComputer
	 *            - the computer that must be insert into the database
	 * @return the id of the computer that have been insert in the computer
	 *         database
	 * @throws DaoException
	 *             in case of SqlException
	 */
	public int insertComputer(Computer myComputer) throws DaoException;

	/**
	 * Delete a computer of the database taken in parameter.
	 * 
	 * @param myComputer
	 *            - the computer that must be delete in the computer database
	 * @throws DaoException
	 *             in case of SqlException
	 */
	public void deleteComputer(Computer myComputer) throws DaoException;

	/**
	 * Update a computer of the database that have been taken in parameter
	 * 
	 * @param myComputer
	 *            -the computer that must be update in the computer database
	 * @throws DaoException
	 *             in case of SqlException
	 */
	public void updateComputer(Computer myComputer) throws DaoException;

	/**
	 * select a computer in the database from the id in parameter
	 * 
	 * @param id
	 *            - the id of the computer that we want ton get
	 * @return the computer that have select by an id or null if there is no
	 *         computer corresponding
	 * @throws DaoException
	 *             in case of SqlException
	 */
	public Computer selectComputer(int id) throws DaoException;

	/**
	 * count the number of computer in the database that match the string
	 * mySearch or count all computer if mySearch is null
	 * 
	 * @param mySearch
	 *            - the search parameter
	 * @return the number of computer
	 * @throws DaoException
	 *             in case of SqlException
	 */
	public int countNumberComputers(String mySearch) throws DaoException;

	/**
	 * 
	 * get computers in a range in an certain order and with a certain research
	 * into an ArrayList
	 * 
	 * @param myLikeParam
	 *            - the search parameter
	 * @param myOrder
	 *            - the orderBy parameter
	 * @param startLimit
	 *            - the start of the range
	 * @param numberOfRow
	 *            - the number of row in the range
	 * @return an ArrayList contain computers that match the parameters
	 *         previously present
	 * @throws DaoException
	 *             in case of SqlException
	 */
	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow)
			throws DaoException;
}
