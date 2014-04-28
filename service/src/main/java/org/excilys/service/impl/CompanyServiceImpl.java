package org.excilys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.excilys.dao.CompanyDao;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

	public static final Logger LOG = LoggerFactory
			.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyDao myCompanyDao;

	@Override
	public List<Company> selectCompanies() {
		Iterable<Company> myList = myCompanyDao.findAll();

		List<Company> companies = new ArrayList<>();
		
		for (Company company : myList) {
			companies.add(company);
			myList.iterator().next();
		}
		
		return companies;
	}
}
