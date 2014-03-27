package org.excilys.service.impl;

import java.util.HashMap;

import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;

public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;
	
	@Override
	public void insertCompany(Company myCompany) {
		DaoFactory.getInstanceCompanyDao().insertCompany(myCompany);
	}

	@Override
	public void deleteCompany(Company myCompany) {
		DaoFactory.getInstanceCompanyDao().deleteCompany(myCompany);
	}

	@Override
	public void updateCompany(Company myCompany) {
		DaoFactory.getInstanceCompanyDao().updateCompany(myCompany);
	}

	@Override
	public Company selectCompany(int id) {
		return DaoFactory.getInstanceCompanyDao().selectCompany(id);
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() {
		return DaoFactory.getInstanceCompanyDao().selectCompanies();
	}

	@Override
	public int countCompanies() {
		return DaoFactory.getInstanceCompanyDao().countCompanies();
	}
}
