package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.excilys.dao.CompanyDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {

	static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	@Autowired
	private ConnectionManager myManager;

	@Override
	public Company selectCompany(int id) throws DaoException {
		Connection myCon = myManager.getConnection();
		Company myCompany = null;

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company WHERE id = ?";
		LOG.debug("requete sql non-prepare selectCompany : " + sql);
		try {
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());

			mySet = myPreStmt.executeQuery();

			LOG.debug("id = " + id);

			if (mySet.next()) {
				myCompany = new Company(mySet.getInt("id"),
						mySet.getString("name"));
			}
		} catch (Exception e) {
			throw new DaoException("Error in -> selectCompany :" + e);
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() throws DaoException {
		Connection myCon = myManager.getConnection();
		HashMap<Integer, Company> myList = new HashMap<>();
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company";
		LOG.debug("requete sql non-prepare selectCompanies : " + sql);
		try {
			myPreStmt = myCon.prepareStatement(sql);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();
			while (mySet.next()) {
				myList.put(mySet.getInt("id"), new Company(mySet.getInt("id"),
						mySet.getString("name")));

			}
		} catch (Exception e) {
			throw new DaoException("Error in -> selectCompanies :" + e);
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return myList;
	}

	@Override
	public int countCompanies() throws DaoException {
		Connection myCon = myManager.getConnection();
		int number = 0;

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT count(*) FROM computer";
		LOG.debug("requete sql non-prepare : " + sql);
		try {
			myPreStmt = myCon.prepareStatement(sql);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();

			if (mySet.next()) {
				number = mySet.getInt(1);
			}
		} catch (Exception e) {
			throw new DaoException("Error in -> countCompanies :" + e);
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return number;
	}
}
