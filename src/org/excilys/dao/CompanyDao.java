package org.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.excilys.model.Company;

public class CompanyDao {

	private ConnectionManager manager = ConnectionManager.getInstance();
	private static CompanyDao instance;

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
			ConnectionManager.closeAll(myPreStmt, myCon, null);
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
			ConnectionManager.closeAll(myPreStmt, myCon, null);
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
			ConnectionManager.closeAll(myPreStmt, myCon, null);
		}
	}

	public Company selectCompany(int id) {
		Company myCompany = null;

		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company WHERE id = ?";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			mySet = myPreStmt.executeQuery();
			mySet.next();

			myCompany = new Company(mySet.getInt("id"), mySet.getString("name"));
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myCompany;
	}

	public HashMap<Integer, Company> selectAllCompanies() {
		HashMap<Integer, Company> myList = new HashMap<>();
		Connection myCon = null;
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT * FROM company";

		try {
			myCon = manager.createConnection();
			myPreStmt = myCon.prepareStatement(sql);

			mySet = myPreStmt.executeQuery();
			while (mySet.next()) {

				myList.put(mySet.getInt("id"), new Company(mySet.getInt("id"),
						mySet.getString("name")));

				myList.put(mySet.getInt("id"),
						new Company(mySet.getInt("id"), mySet.getString("name")));

			}
		} catch (SQLException e) {
		} finally {
			ConnectionManager.closeAll(myPreStmt, myCon, mySet);
		}

		return myList;
	}

	public static CompanyDao getInstance() {
		if (instance == null) instance = new CompanyDao();
		return instance;
	}

	protected CompanyDao() {
	}
}
