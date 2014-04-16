package org.excilys.service.impl;

import java.util.HashMap;

import org.excilys.dao.CompanyDao;
import org.excilys.dao.impl.ConnectionManager;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);

	@Autowired
	private CompanyDao myCompanyDao;

	@Override
	@Transactional(readOnly = true)
	public Company selectCompany(int id) {

		Company myCompany = null;
		myCompany = myCompanyDao.selectCompany(id);
		return myCompany;
	}

	@Override
	@Transactional(readOnly = true)
	public HashMap<Integer, Company> selectCompanies() {
		HashMap<Integer, Company> myMap = null;
		myMap = myCompanyDao.selectCompanies();
		return myMap;
	}

	@Override
	@Transactional(readOnly = true)
	public int countCompanies() {
		int number = 0;
		number = myCompanyDao.countCompanies();
		return number;
	}
}
