package org.excilys.service.impl;

import java.util.ArrayList;

import org.excilys.dao.CompanyDao;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao myCompanyDao;

	@Override
	public ArrayList<Company> selectCompanies() {
		Iterable<Company> myList = myCompanyDao.findAll();

		ArrayList<Company> companies = new ArrayList<>();
		
		for (Company company : myList) {
			companies.add(company);
			myList.iterator().next();
		}
		
		return companies;
	}
}
