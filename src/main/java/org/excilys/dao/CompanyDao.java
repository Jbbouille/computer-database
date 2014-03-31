package org.excilys.dao;

import java.util.HashMap;

import org.excilys.exception.DaoException;
import org.excilys.model.Company;
<<<<<<< HEAD:src/main/java/org/excilys/dao/CompanyDao.java
=======
import org.excilys.exception.DaoException;
>>>>>>> 481708d1a6d302520afdaf97e03156e361fa2652:src/main/java/org/excilys/dao/CompanyDao.java

public interface CompanyDao {

	public void insertCompany(Company myCompany) throws DaoException;

	public void deleteCompany(Company myCompany) throws DaoException;

	public void updateCompany(Company myCompany) throws DaoException;

	public Company selectCompany(int id) throws DaoException;

	public HashMap<Integer, Company> selectCompanies() throws DaoException;

	public int countCompanies() throws DaoException;
}
