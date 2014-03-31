package org.excilys.dao;

import java.util.HashMap;

import org.excilys.exception.DaoException;
import org.excilys.model.Company;

public interface CompanyDao {

	public void insertCompany(Company myCompany) throws DaoException;

	public void deleteCompany(Company myCompany) throws DaoException;

	public void updateCompany(Company myCompany) throws DaoException;

	public Company selectCompany(int id) throws DaoException;

	public HashMap<Integer, Company> selectCompanies() throws DaoException;

	public int countCompanies() throws DaoException;
}
