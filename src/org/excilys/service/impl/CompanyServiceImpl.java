package org.excilys.service.impl;

import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.dao.impl.ConnectionManager;
import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.DaoException;

public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);

	@Override
	public void insertCompany(Company myCompany) {
		ConnectionManager.INSTANCE.startTransaction();
		try {
			DaoFactory.getInstanceCompanyDao().insertCompany(myCompany);
			closeThread();
		} catch (DaoException e) {
			LOG.error("Error in -> insertCompany"
					+ Thread.currentThread().toString());
			throw e;
		}
	}

	@Override
	public void deleteCompany(Company myCompany) {
		ConnectionManager.INSTANCE.startTransaction();
		try {
			DaoFactory.getInstanceCompanyDao().deleteCompany(myCompany);
			closeThread();
		} catch (DaoException e) {
			LOG.error("Error in -> deleteCompany"
					+ Thread.currentThread().toString());
			throw e;
		}
	}

	@Override
	public void updateCompany(Company myCompany) {
		ConnectionManager.INSTANCE.startTransaction();
		try {
			DaoFactory.getInstanceCompanyDao().updateCompany(myCompany);
			closeThread();
		} catch (DaoException e) {
			LOG.error("Error in -> updateCompany"
					+ Thread.currentThread().toString());
			throw e;
		}
	}

	@Override
	public Company selectCompany(int id) {
		ConnectionManager.INSTANCE.getConnection();
		Company myCompany = null;
		try {
			myCompany = DaoFactory.getInstanceCompanyDao().selectCompany(id);
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
		ConnectionManager.INSTANCE.getConnection();
		HashMap<Integer, Company> myMap = null;
		try {
			myMap = DaoFactory.getInstanceCompanyDao().selectCompanies();
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
		ConnectionManager.INSTANCE.getConnection();
		int number = 0;
		try {
			number = DaoFactory.getInstanceCompanyDao().countCompanies();
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
			ConnectionManager.INSTANCE.myThreadLocal.get().close();
			ConnectionManager.INSTANCE.myThreadLocal.remove();
			LOG.debug("Close of Thread :" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error in -> Close of Thread :"
					+ Thread.currentThread().toString());
			throw new DaoException("Error in -> Close of Thread " +e);
		}
	}

}
