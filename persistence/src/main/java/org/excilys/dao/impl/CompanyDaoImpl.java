package org.excilys.dao.impl;

import java.util.List;

import org.excilys.dao.CompanyDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {

	static final Logger LOG = LoggerFactory.getLogger(CompanyDao.class);

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Company> selectCompanies() throws DaoException {
		Session mSession = sf.openSession();
		List<Company> myList = mSession.createQuery("from Company").list();
		mSession.close();
		return myList;
	}
}
