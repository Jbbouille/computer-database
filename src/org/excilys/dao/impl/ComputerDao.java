package org.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.excilys.model.Computer;
import org.excilys.util.Utilities;

public class ComputerDao {

	private ConnectionManager manager = ConnectionManager.getInstance();
	private static ComputerDao instance;

	public void insertComputer(Computer myComputer) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer-database-db.computer (id, name, introduced, discontinued, company_id) VALUES (NULL, ?, ?, ?, ?)";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, myComputer.getName());
			myPreStmt.setString(2,
					Utilities.dateSQLtoString(myComputer.getIntroduced()));
			myPreStmt.setString(3,
					Utilities.dateSQLtoString(myComputer.getDiscontinued()));
			myPreStmt.setInt(4, myComputer.getCompanyId());

			myPreStmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	public void deleteComputer(Computer myComputer) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "DELETE FROM computer-database-db.computer WHERE computer.id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myComputer.getId());

			myPreStmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	public void updateComputer(Computer myComputer) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myComputer.getId());
			myPreStmt.setString(2, myComputer.getName());
			myPreStmt.setString(3,
					Utilities.dateSQLtoString(myComputer.getIntroduced()));
			myPreStmt.setString(4,
					Utilities.dateSQLtoString(myComputer.getDiscontinued()));
			myPreStmt.setInt(5, myComputer.getId());

			myPreStmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}


	public Computer selectComputer(int id) {
		Computer myComputer = null;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		
		String sql = "SELECT * FROM computer WHERE id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			mySet = myPreStmt.executeQuery();
			mySet.next();

			myComputer = new Computer(mySet.getInt("id"), mySet.getString("name"),
					Utilities.stringToDateSQl(mySet.getString("introduced")),
					Utilities.stringToDateSQl(mySet.getString("discontinued")),
					mySet.getInt("company_id"));
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myComputer;
	}

	public ArrayList<Computer> selectAllComputers() {
		ArrayList<Computer> myList = new ArrayList<>();
		
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM computer";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			mySet = myPreStmt.executeQuery();
			while (mySet.next()) {

				Computer myComputer = new Computer(
						mySet.getInt("id"),
						mySet.getString("name"),
						mySet.getDate("introduced"),
						mySet.getDate("discontinued"),
						mySet.getInt("company_id"));

				myList.add(myComputer);
			}
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}
		
		return myList;
	}
	
	public static ComputerDao getInstance() {
		if (instance == null) instance = new ComputerDao();
		return instance;
	}

	protected ComputerDao() {
	}
	
}
