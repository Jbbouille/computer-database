package org.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.excilys.model.Company;

public class CompanyDao {

	ConnectionManager manager = ConnectionManager.getInstance();

	public void insertCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO computer-database-db.company (id, name) VALUES (NULL, ?)";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, myCompany.getName());

			myPreStmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			closeAll(myPreStmt, myCon);
		}
	}

	public void deleteCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "DELETE FROM computer-database-db.company WHERE company.id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myCompany.getId());

			myPreStmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			closeAll(myPreStmt, myCon);
		}
	}

	public void updateCompany(Company myCompany) {
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "UPDATE comany SET id = ?, name = ? WHERE company.id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, myCompany.getId());
			myPreStmt.setString(2, myCompany.getName());
			myPreStmt.setInt(3, myCompany.getId());

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

	public Company selectCompany(int id) {
		Company myCompany = null;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "SELECT * FROM company WHERE id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			ResultSet rs = myPreStmt.executeQuery();
			rs.next();

			myCompany = new Company(rs.getInt("id"), rs.getString("name"));
		} catch (SQLException e) {
		} finally {
			closeAll(myPreStmt, myCon);
		}

		return myCompany;
	}

	public HashMap<Integer, Company> selectAllCompanies() {
		HashMap<Integer, Company> myList = new HashMap<>();
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		String sql = "SELECT * FROM company";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			ResultSet rs = myPreStmt.executeQuery();
			while (rs.next()) {

				myList.put(rs.getInt("id"),
						new Company(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
		} finally {
			closeAll(myPreStmt, myCon);
		}
		return myList;

	}
}
