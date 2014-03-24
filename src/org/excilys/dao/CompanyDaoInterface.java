package org.excilys.dao;

import java.util.HashMap;

import org.excilys.model.Company;

public interface CompanyDaoInterface {

	public void insertCompany(Company myCompany);
	
	public void deleteCompany(Company myCompany);
	
	public void updateCompany(Company myCompany);
	
	public Company selectCompany(int id);
	
	public HashMap<Integer, Company> selectAllCompanies();
}
