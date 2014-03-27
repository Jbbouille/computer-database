package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.dao.CompanyDao;
import org.excilys.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;
	
	static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);
	private ConnectionManager manager = ConnectionManager.getInstance();

	@Override
	public void insertCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer-database-db.company (id, name) VALUES (NULL, ?)";
		LOG.debug("requete sql non-prepare : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, myCompany.getName());

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error in execution of resquest :"+e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	@Override
	public void deleteCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "DELETE FROM computer-database-db.company WHERE company.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myCompany.getId());

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error in execution of resquest :"+e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	@Override
	public void updateCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "UPDATE comany SET id = ?, name = ? WHERE company.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myCompany.getId());
			myPreStmt.setString(2, myCompany.getName());
			myPreStmt.setInt(3, myCompany.getId());

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error in execution of resquest :"+e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	@Override
	public Company selectCompany(int id) {
		Company myCompany = null;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company WHERE id = ?";
		LOG.debug("requete sql non-prepare : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();
			mySet.next();

			myCompany = new Company(mySet.getInt("id"), mySet.getString("name"));
		} catch (SQLException e) {
			LOG.error("Error in execution of resquest :"+e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() {
		HashMap<Integer, Company> myList = new HashMap<>();
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company";
		LOG.debug("requete sql non-prepare : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();
			while (mySet.next()) {

				myList.put(mySet.getInt("id"), new Company(mySet.getInt("id"),
						mySet.getString("name")));

			}
		} catch (SQLException e) {
			LOG.error("Error in execution of resquest :"+e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myList;
	}

	@Override
	public int countCompanies() {
		int number = 0;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT count(*) FROM computer";
		LOG.debug("requete sql non-prepare : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();
			
			mySet.next();
			number = mySet.getInt(1);
		} catch (SQLException e) {
			LOG.error("Error in execution of request :" + e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return number;
	}
}
