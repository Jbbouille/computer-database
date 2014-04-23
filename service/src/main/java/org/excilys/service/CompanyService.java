package org.excilys.service;

import java.util.List;

import org.excilys.model.Company;

public interface CompanyService {

	public Company selectCompany(int id);

	public List<Company> selectCompanies();
}
