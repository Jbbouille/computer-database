package org.excilys.dao;

import java.util.HashMap;

import org.excilys.exception.DaoException;
import org.excilys.model.Company;

public interface CompanyDao {

	public Company selectCompany(int id) throws DaoException;

	public HashMap<Integer, Company> selectCompanies() throws DaoException;

	public int countCompanies() throws DaoException;
}
