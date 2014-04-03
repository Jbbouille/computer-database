package org.excilys.service.impl;

import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.dao.impl.CompanyDaoImpl;
import org.excilys.dao.impl.ConnectionManager;
import org.excilys.exception.DaoException;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);
	
	@Autowired
	private ConnectionManager myManager;
	
	@Autowired
	private CompanyDaoImpl myCompanyDao;

	@Override
	public Company selectCompany(int id) {
		myManager.getConnection();
		Company myCompany = null;
		try {
			myCompany = myCompanyDao.selectCompany(id);
			closeThread();
		} catch (DaoException e) {
			LOG.error("Error in -> selectCompany"
					+ Thread.currentThread().toString());
			throw e;
		}
		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() {
		myManager.getConnection();
		HashMap<Integer, Company> myMap = null;
		try {
			myMap = myCompanyDao.selectCompanies();
			closeThread();
		} catch (DaoException e) {
			LOG.error("Error in -> selectCompanies"
					+ Thread.currentThread().toString());
			throw e;
		}
		return myMap;
	}

	@Override
	public int countCompanies() {
		myManager.getConnection();
		int number = 0;
		try {
			number = myCompanyDao.countCompanies();
			closeThread();
		} catch (DaoException e) {
			LOG.error("Error in -> countCompanies"
					+ Thread.currentThread().toString());
			throw e;
		}
		return number;
	}

	@Override
	public void closeThread() {
		try {
			myManager.myThreadLocal.get().close();
			myManager.myThreadLocal.remove();
			LOG.debug("Close of Thread :" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error in -> Close of Thread :"
					+ Thread.currentThread().toString());
			throw new DaoException("Error in -> Close of Thread " +e);
		}
	}

}
