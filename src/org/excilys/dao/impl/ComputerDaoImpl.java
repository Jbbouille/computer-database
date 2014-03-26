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

			if (myComputer.getDiscontinued() == null)
				myPreStmt.setNull(3, Types.NULL);
			else
				myPreStmt
						.setString(3, Utilities.dateSQLtoString(myComputer
								.getDiscontinued()));

			if (myComputer.getIntroduced() == null)
				myPreStmt.setNull(2, Types.NULL);
			else
				myPreStmt.setString(2,
						Utilities.dateSQLtoString(myComputer.getIntroduced()));

			if (myComputer.getCompanyId() == -1)
				myPreStmt.setNull(4, Types.NULL);
			else
				myPreStmt.setInt(4, myComputer.getCompanyId());

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

			if (myComputer.getDiscontinued() == null)
				myPreStmt.setNull(4, Types.NULL);
			else
				myPreStmt
						.setString(4, Utilities.dateSQLtoString(myComputer
								.getDiscontinued()));

			if (myComputer.getIntroduced() == null)
				myPreStmt.setNull(3, Types.NULL);
			else
				myPreStmt.setString(3,
						Utilities.dateSQLtoString(myComputer.getIntroduced()));

			if (myComputer.getCompanyId() == -1)
				myPreStmt.setNull(5, Types.NULL);
			else
				myPreStmt.setInt(5, myComputer.getCompanyId());

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

	protected ComputerDaoImpl() {
	}

	@Override
	public int countNumberComputers(String myName) {
		int number = 0;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT count(*) FROM computer WHERE name like ?";
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, "%" + myName + "%");

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

	@Override
	public ArrayList<Computer> selectPartsSearchComputers(String myName,
			String myOrder, int startLimit, int numberOfRow) {
		ArrayList<Computer> myList = new ArrayList<>();

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name like ? OR company.name like ? ORDER BY ")
				.append(myOrder).append(" LIMIT ?,?");
		LOG.debug("requete : " + sql);

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql.toString());

			myPreStmt.setString(1, "%" + myName + "%");
			myPreStmt.setString(2, "%" + myName + "%");
			myPreStmt.setInt(3, startLimit);
			myPreStmt.setInt(4, numberOfRow);

			LOG.debug(myPreStmt.toString());
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
}
