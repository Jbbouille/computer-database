package org.excilys.service;

import java.util.HashMap;

import org.excilys.model.Company;

public interface CompanyService {

	public void insertCompany(Company myCompany);

	public void deleteCompany(Company myCompany);

	public void updateCompany(Company myCompany);

	public Company selectCompany(int id);

	public HashMap<Integer, Company> selectCompanies();
	
	public int countCompanies();
}
