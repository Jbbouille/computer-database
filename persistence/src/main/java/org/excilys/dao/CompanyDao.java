package org.excilys.dao;

import java.util.List;

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
	 * selectCompanies is the method that returns all companies.
	 * 
	 * @return And HashMap object that contain all Companies
	 * @throws DaoException
	 *             Throw Dao Custom Exception in case of SqlException....
	 */
	public List<Company> selectCompanies() throws DaoException;
}
