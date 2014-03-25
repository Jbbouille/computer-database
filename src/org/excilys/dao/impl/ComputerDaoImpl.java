package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.excilys.dao.ComputerDao;
import org.excilys.model.Computer;
import org.excilys.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerDaoImpl implements ComputerDao {

	static final Logger LOG = LoggerFactory.getLogger(ComputerDaoImpl.class);
	private ConnectionManager manager = ConnectionManager.getInstance();

	@Override
	public void insertComputer(Computer myComputer) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer VALUES (null, ?, ?, ?, ?)";
		LOG.debug("prepare request : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, myComputer.getName());
			
			if(myComputer.getDiscontinued() == null) myPreStmt.setNull(3, Types.NULL);
			else myPreStmt.setString(3,Utilities.dateSQLtoString(myComputer.getDiscontinued()));
			
			if(myComputer.getIntroduced() == null) myPreStmt.setNull(2, Types.NULL);
			else myPreStmt.setString(2,Utilities.dateSQLtoString(myComputer.getIntroduced()));
						
			if(myComputer.getCompanyId() == -1) myPreStmt.setNull(4, Types.NULL);
			else myPreStmt.setInt(4, myComputer.getCompanyId());
			
			LOG.debug("request : " + myPreStmt.toString());
			myPreStmt.execute();
		} catch (SQLException e) {
			LOG.error("Error in execution of request :" + e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	@Override
	public void deleteComputer(Computer myComputer) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "DELETE FROM computer WHERE computer.id = ?";
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myComputer.getId());

			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error in execution of request :" + e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	@Override
	public void updateComputer(Computer myComputer) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id = ?";
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myComputer.getId());
			myPreStmt.setString(2, myComputer.getName());
			
			if(myComputer.getDiscontinued() == null) myPreStmt.setNull(4, Types.NULL);
			else myPreStmt.setString(4,Utilities.dateSQLtoString(myComputer.getDiscontinued()));
			
			if(myComputer.getIntroduced() == null) myPreStmt.setNull(3, Types.NULL);
			else myPreStmt.setString(3,Utilities.dateSQLtoString(myComputer.getIntroduced()));
						
			if(myComputer.getCompanyId() == -1) myPreStmt.setNull(5, Types.NULL);
			else myPreStmt.setInt(5, myComputer.getCompanyId());

			myPreStmt.setInt(6, myComputer.getId());

			LOG.debug(myPreStmt.toString());
			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error in execution of request :" + e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	@Override
	public Computer selectComputer(int id) {
		Computer myComputer = null;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;

		String sql = "SELECT * FROM computer WHERE id = ?";
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			mySet = myPreStmt.executeQuery();
			mySet.next();

			myComputer = new Computer(mySet.getInt("id"),
					mySet.getString("name"), mySet.getDate("introduced"),
					mySet.getDate("discontinued"), mySet.getInt("company_id"));
		} catch (SQLException e) {
			LOG.error("Error in execution of request :" + e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myComputer;
	}

	@Override
	public ArrayList<Computer> selectAllComputers() {
		ArrayList<Computer> myList = new ArrayList<>();

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM computer";
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			mySet = myPreStmt.executeQuery();
			while (mySet.next()) {

				Computer myComputer = new Computer(mySet.getInt("id"),
						mySet.getString("name"), mySet.getDate("introduced"),
						mySet.getDate("discontinued"),
						mySet.getInt("company_id"));

				myList.add(myComputer);
			}
		} catch (SQLException e) {
			LOG.error("Error in execution of request :" + e);
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myList;
	}

	protected ComputerDaoImpl() {
	}

}
