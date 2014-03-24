package org.excilys.service.impl;

import java.util.HashMap;

import org.excilys.dao.impl.CompanyDaoImpl;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

	@Override
	public void insertCompany(Company myCompany) {
		CompanyDaoImpl.getInstance().insertCompany(myCompany);
	}

	@Override
	public void deleteCompany(Company myCompany) {
		CompanyDaoImpl.getInstance().deleteCompany(myCompany);
	}

	@Override
	public void updateCompany(Company myCompany) {
		CompanyDaoImpl.getInstance().updateCompany(myCompany);
	}

	@Override
	public Company selectCompany(int id) {
		return CompanyDaoImpl.getInstance().selectCompany(id);
	}

	@Override
	public HashMap<Integer, Company> selectAllCompanies() {
		return CompanyDaoImpl.getInstance().selectAllCompanies();
	}

}
