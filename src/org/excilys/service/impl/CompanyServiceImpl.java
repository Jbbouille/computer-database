package org.excilys.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.dao.impl.ConnectionManager;
import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;

public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;

	@Override
	public void insertCompany(Company myCompany) {
		Connection myCon = ConnectionManager.INSTANCE.createConnection();
		try {
			DaoFactory.getInstanceCompanyDao().insertCompany(myCompany, myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCompany(Company myCompany) {
		Connection myCon = ConnectionManager.INSTANCE.createConnection();
		try {
			DaoFactory.getInstanceCompanyDao().deleteCompany(myCompany, myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCompany(Company myCompany) {
		Connection myCon = ConnectionManager.INSTANCE.createConnection();
		try {
			DaoFactory.getInstanceCompanyDao().updateCompany(myCompany, myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Company selectCompany(int id) {
		Connection myCon = ConnectionManager.INSTANCE.createConnection();
		Company myCompany = null;
		try {
			myCompany = DaoFactory.getInstanceCompanyDao().selectCompany(id,
					myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() {
		Connection myCon = ConnectionManager.INSTANCE.createConnection();
		HashMap<Integer, Company> myMap = null;
		try {
			myMap = DaoFactory.getInstanceCompanyDao().selectCompanies(myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myMap;
	}

	@Override
	public int countCompanies() {
		Connection myCon = ConnectionManager.INSTANCE.createConnection();
		int number = 0;
		try {
			number = DaoFactory.getInstanceCompanyDao().countCompanies(myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
}
