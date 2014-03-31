package org.excilys.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.dao.impl.ConnectionManager;
import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);
	private ThreadLocal<Connection> myThreadLocal = ConnectionManager.INSTANCE.myThreadLocal;

	@Override
	public void insertCompany(Company myCompany) {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		try {
			DaoFactory.getInstanceCompanyDao().insertCompany(myCompany, myCon);
			closeThread();
		} catch (SQLException e) {
			LOG.error("Error in -> insertCompany"
					+ Thread.currentThread().toString());
		}
	}

	@Override
	public void deleteCompany(Company myCompany) {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		try {
			DaoFactory.getInstanceCompanyDao().deleteCompany(myCompany, myCon);
			closeThread();
		} catch (SQLException e) {
			LOG.error("Error in -> deleteCompany"
					+ Thread.currentThread().toString());
		}
	}

	@Override
	public void updateCompany(Company myCompany) {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		try {
			DaoFactory.getInstanceCompanyDao().updateCompany(myCompany, myCon);
			closeThread();
		} catch (SQLException e) {
			LOG.error("Error in -> updateCompany"
					+ Thread.currentThread().toString());
		}
	}

	@Override
	public Company selectCompany(int id) {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		Company myCompany = null;
		try {
			myCompany = DaoFactory.getInstanceCompanyDao().selectCompany(id,
					myCon);
			closeThread();
		} catch (SQLException e) {
			LOG.error("Error in -> selectCompany"
					+ Thread.currentThread().toString());
		}
		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		HashMap<Integer, Company> myMap = null;
		try {
			myMap = DaoFactory.getInstanceCompanyDao().selectCompanies(myCon);
			closeThread();
		} catch (SQLException e) {
			LOG.error("Error in -> selectCompanies"
					+ Thread.currentThread().toString());
		}
		return myMap;
	}

	@Override
	public int countCompanies() {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		int number = 0;
		try {
			number = DaoFactory.getInstanceCompanyDao().countCompanies(myCon);
			closeThread();
		} catch (SQLException e) {
			LOG.error("Error in -> countCompanies"
					+ Thread.currentThread().toString());
		}
		return number;
	}

	@Override
	public void closeThread() {
		try {
			myThreadLocal.get().close();
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error in -> Close of Thread"
					+ Thread.currentThread().toString());
		}
	}

}
