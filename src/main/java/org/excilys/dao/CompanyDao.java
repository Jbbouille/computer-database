package org.excilys.dao;

import java.util.HashMap;

import org.excilys.exception.DaoException;
import org.excilys.model.Company;

/**
 * CompanyDao interface contains methods to select one or all company and a
 * method to count the number of companies.
 * 
 * @author excilys
 * 
 */
public interface CompanyDao {

	/**
	 * selectComany is the DAO method that take an idComany and return the
	 * Company object corresponding.
	 * 
	 * @param id
	 *            Take the companyId to return
	 * @return Return the Company object Corresponding to the id
	 * @throws DaoException
	 *             Throw Dao Custom Exception in case of SqlException....
	 */
	public Company selectCompany(int id) throws DaoException;

	/**
	 * selectCompanies is the method that returns all companies.
	 * 
	 * @return And HashMap object that contain all Companies
	 * @throws DaoException
	 *             Throw Dao Custom Exception in case of SqlException....
	 */
	public HashMap<Integer, Company> selectCompanies() throws DaoException;

	/**
	 * countCompanies count the number of companies and return it into and int.
	 * 
	 * @return Return the number of company in an int object
	 * @throws DaoException
	 *             Throw Dao Custom Exception in case of SqlException....
	 */
	public int countCompanies() throws DaoException;
}
