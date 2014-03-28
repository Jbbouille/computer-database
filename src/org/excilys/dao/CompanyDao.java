package org.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.model.Company;

public interface CompanyDao {

	public void insertCompany(Company myCompany, Connection myCon)
			throws SQLException;

	public void deleteCompany(Company myCompany, Connection myCon)
			throws SQLException;

	public void updateCompany(Company myCompany, Connection myCon)
			throws SQLException;

	public Company selectCompany(int id, Connection myCon) throws SQLException;

	public HashMap<Integer, Company> selectCompanies(Connection myCon)
			throws SQLException;

	public int countCompanies(Connection myCon) throws SQLException;
}
