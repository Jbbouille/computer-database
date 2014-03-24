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

public class CompanyDaoImpl implements CompanyDao {

	static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);
	private ConnectionManager manager = ConnectionManager.getInstance();
	private static CompanyDaoImpl instance;

	@Override
	public void insertCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer-database-db.company (id, name) VALUES (NULL, ?)";
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, myCompany.getName());

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
		LOG.debug("requete : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myCompany.getId());

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
		LOG.debug("requete : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myCompany.getId());
			myPreStmt.setString(2, myCompany.getName());
			myPreStmt.setInt(3, myCompany.getId());

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
		LOG.debug("requete : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

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
	public HashMap<Integer, Company> selectAllCompanies() {
		HashMap<Integer, Company> myList = new HashMap<>();
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company";
		LOG.debug("requete : " + sql);
		
		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

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

	public static CompanyDaoImpl getInstance() {
		if (instance == null)
			instance = new CompanyDaoImpl();
		return instance;
	}

	protected CompanyDaoImpl() {
	}
}
