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

	@Override
	public void insertCompany(Company myCompany, Connection myCon)
			throws SQLException {
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer-database-db.company (id, name) VALUES (NULL, ?)";
		LOG.debug("requete sql non-prepare : " + sql);

		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setString(1, myCompany.getName());

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		myPreStmt.executeUpdate();
		ConnectionManager.INSTANCE.closeAll(myPreStmt, myCon, null);
	}

	@Override
	public void deleteCompany(Company myCompany, Connection myCon)
			throws SQLException {
		PreparedStatement myPreStmt = null;
		String sql = "DELETE FROM computer-database-db.company WHERE company.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setInt(1, myCompany.getId());

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		myPreStmt.executeUpdate();
		ConnectionManager.INSTANCE.closeAll(myPreStmt, myCon, null);
	}

	@Override
	public void updateCompany(Company myCompany, Connection myCon)
			throws SQLException {
		PreparedStatement myPreStmt = null;
		String sql = "UPDATE comany SET id = ?, name = ? WHERE company.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setInt(1, myCompany.getId());
		myPreStmt.setString(2, myCompany.getName());
		myPreStmt.setInt(3, myCompany.getId());

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		myPreStmt.executeUpdate();
		ConnectionManager.INSTANCE.closeAll(myPreStmt, myCon, null);
	}

	@Override
	public Company selectCompany(int id, Connection myCon) throws SQLException {
		Company myCompany = null;

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company WHERE id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setInt(1, id);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		mySet = myPreStmt.executeQuery();
		mySet.next();

		myCompany = new Company(mySet.getInt("id"), mySet.getString("name"));
		ConnectionManager.INSTANCE.closeAll(myPreStmt, myCon, null);
		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies(Connection myCon)
			throws SQLException {
		HashMap<Integer, Company> myList = new HashMap<>();
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company";
		LOG.debug("requete sql non-prepare : " + sql);

		myPreStmt = myCon.prepareStatement(sql);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		mySet = myPreStmt.executeQuery();
		while (mySet.next()) {

			myList.put(mySet.getInt("id"), new Company(mySet.getInt("id"),
					mySet.getString("name")));

		}

		ConnectionManager.INSTANCE.closeAll(myPreStmt, myCon, null);
		return myList;
	}

	@Override
	public int countCompanies(Connection myCon) throws SQLException {
		int number = 0;

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT count(*) FROM computer";
		LOG.debug("requete sql non-prepare : " + sql);

		myPreStmt = myCon.prepareStatement(sql);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		mySet = myPreStmt.executeQuery();

		mySet.next();
		number = mySet.getInt(1);

		ConnectionManager.INSTANCE.closeAll(myPreStmt, myCon, null);
		return number;
	}
}
