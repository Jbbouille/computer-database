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

<<<<<<< HEAD:src/main/java/org/excilys/dao/impl/CompanyDaoImpl.java
=======
import org.excilys.exception.DaoException;

>>>>>>> 481708d1a6d302520afdaf97e03156e361fa2652:src/main/java/org/excilys/dao/impl/CompanyDaoImpl.java
public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;

	static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	@Override
	public void insertCompany(Company myCompany) throws DaoException {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer-database-db.company (id, name) VALUES (NULL, ?)";
		LOG.debug("requete sql non-prepare : " + sql);
		
		try {
		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setString(1, myCompany.getName());

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		myPreStmt.executeUpdate();
		
		}catch (Exception e) {
			throw new DaoException("Error in -> insertCompany :"+e);
		}finally{
			ConnectionManager.INSTANCE.closeAll(myPreStmt, null);
		}
	}

	@Override
	public void deleteCompany(Company myCompany) throws DaoException {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		PreparedStatement myPreStmt = null;
		String sql = "DELETE FROM computer-database-db.company WHERE company.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);
		try {
		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setInt(1, myCompany.getId());

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		myPreStmt.executeUpdate();
		}catch (Exception e) {
			throw new DaoException("Error in -> deleteCompany :"+e);
		}finally{
			ConnectionManager.INSTANCE.closeAll(myPreStmt, null);
		}
	}

	@Override
	public void updateCompany(Company myCompany) throws DaoException {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		PreparedStatement myPreStmt = null;
		String sql = "UPDATE comany SET id = ?, name = ? WHERE company.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);
		try {
		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setInt(1, myCompany.getId());
		myPreStmt.setString(2, myCompany.getName());
		myPreStmt.setInt(3, myCompany.getId());

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		myPreStmt.executeUpdate();
		}catch (Exception e) {
			throw new DaoException("Error in -> updateCompany :"+e);
		}finally{
			ConnectionManager.INSTANCE.closeAll(myPreStmt, null);
		}
	}

	@Override
	public Company selectCompany(int id) throws DaoException {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		Company myCompany = null;

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company WHERE id = ?";
		LOG.debug("requete sql non-prepare : " + sql);
		try {
		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setInt(1, id);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		mySet = myPreStmt.executeQuery();
		mySet.next();

		myCompany = new Company(mySet.getInt("id"), mySet.getString("name"));
		}catch (Exception e) {
			throw new DaoException("Error in -> selectCompany :"+e);
		}finally{
			ConnectionManager.INSTANCE.closeAll(myPreStmt, mySet);
		}
		return myCompany;
	}

	@Override
	public HashMap<Integer, Company> selectCompanies() throws DaoException {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		HashMap<Integer, Company> myList = new HashMap<>();
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company";
		LOG.debug("requete sql non-prepare : " + sql);
		try {
		myPreStmt = myCon.prepareStatement(sql);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		mySet = myPreStmt.executeQuery();
		while (mySet.next()) {

			myList.put(mySet.getInt("id"), new Company(mySet.getInt("id"),
					mySet.getString("name")));

		}
		}catch (Exception e) {
			throw new DaoException("Error in -> selectCompanies :"+e);
		}finally{
			ConnectionManager.INSTANCE.closeAll(myPreStmt, mySet);
		}
		return myList;
	}

	@Override
	public int countCompanies() throws DaoException {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		int number = 0;

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT count(*) FROM computer";
		LOG.debug("requete sql non-prepare : " + sql);
		try {
		myPreStmt = myCon.prepareStatement(sql);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());
		mySet = myPreStmt.executeQuery();

		mySet.next();
		number = mySet.getInt(1);
		
		}catch (Exception e) {
			throw new DaoException("Error in -> countCompanies :"+e);
		}finally{
			ConnectionManager.INSTANCE.closeAll(myPreStmt, mySet);
		}
		
		return number;
	}
}
