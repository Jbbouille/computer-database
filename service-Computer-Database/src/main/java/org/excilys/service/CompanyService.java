package org.excilys.service;

import java.util.HashMap;

import org.excilys.model.Company;

public interface CompanyService {

	public Company selectCompany(int id);

	public HashMap<Integer, Company> selectCompanies();
	
	public int countCompanies();
}
