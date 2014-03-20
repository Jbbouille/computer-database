package org.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.excilys.model.Computer;
import org.excilys.util.Utilities;

public class ComputerDao {

	ConnectionManager manager = ConnectionManager.getInstance();

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
			closeAll(myPreStmt, myCon);
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
			closeAll(myPreStmt, myCon);
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
			closeAll(myPreStmt, myCon);
		}
	}

	private void closeAll(PreparedStatement myPreStmt, Connection myCon) {

		try {
			if (myPreStmt != null)
				myPreStmt.close();
			if (myCon != null)
				myPreStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Computer selectComputer(int id) {
		Computer myComputer = null;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "SELECT * FROM computer WHERE id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			ResultSet rs = myPreStmt.executeQuery();
			rs.next();

			myComputer = new Computer(rs.getInt("id"), rs.getString("name"),
					Utilities.stringToDateSQl(rs.getString("introduced")),
					Utilities.stringToDateSQl(rs.getString("discontinued")),
					rs.getInt("company_id"));
		} catch (SQLException e) {
		} finally {
			closeAll(myPreStmt, myCon);
		}

		return myComputer;
	}

	public ArrayList<Computer> selectAllComputers() {
		ArrayList<Computer> myList = new ArrayList<>();
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "SELECT * FROM computer";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			ResultSet rs = myPreStmt.executeQuery();
			while (rs.next()) {

				Computer myComputer = new Computer(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDate("introduced"),
						rs.getDate("discontinued"),
						rs.getInt("company_id"));

				myList.add(myComputer);
			}
		} catch (SQLException e) {
		} finally {
			closeAll(myPreStmt, myCon);
		}
		return myList;

	}
}
