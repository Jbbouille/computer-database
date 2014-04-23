package org.excilys.service.impl;

import java.util.List;

import org.excilys.dao.CompanyDao;
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
			.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyDao myCompanyDao;

	@Override
	@Transactional(readOnly = true)
	public Company selectCompany(int id) {
		return myCompanyDao.selectCompany(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Company> selectCompanies() {
		return myCompanyDao.selectCompanies();
	}
}
