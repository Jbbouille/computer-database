package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.excilys.dao.ComputerDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
import org.excilys.util.Utilities;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

@Repository("computerDao")
public class ComputerDaoImpl implements ComputerDao {

	static final Logger LOG = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@Autowired
	private ConnectionManager myManager;

	@Override
	public int insertComputer(Computer myComputer) throws DaoException {

		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		int id = 0;
		Connection myCon = myManager.getConnection();
		String sql = "INSERT INTO computer VALUES (null, ?, ?, ?, ?)";
		LOG.debug("requete sql non-prepare : " + sql);

		try {

			myPreStmt = myCon.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

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

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			myPreStmt.execute();

			mySet = myPreStmt.getGeneratedKeys();

			if (mySet.next()) {
				id = mySet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DaoException("Error in insertComuter " + e.getMessage());
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return id;
	}

	@Override
	public void deleteComputer(Computer myComputer) throws DaoException {
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		Connection myCon = myManager.getConnection();
		String sql = "DELETE FROM computer WHERE computer.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		try {

			myPreStmt = myCon.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			myPreStmt.setInt(1, myComputer.getId());

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error in deleteComputer " + e.getMessage());
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
	}

	@Override
	public void updateComputer(Computer myComputer) throws DaoException {
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		Connection myCon = myManager.getConnection();
		String sql = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		try {

			myPreStmt = myCon.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

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

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			myPreStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error in updateComputer " + e.getMessage());
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
	}

	@Override
	public Computer selectComputer(int id) throws DaoException {
		Computer myComputer = null;
		Connection myCon = myManager.getConnection();
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;

		String sql = "SELECT * FROM computer WHERE id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		try {

			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setInt(1, id);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();
			if (mySet.next()) {

				myComputer = new Computer(mySet.getInt("id"),
						mySet.getString("name"), new DateTime(
								mySet.getDate("introduced")), new DateTime(
								mySet.getDate("discontinued")),
						mySet.getInt("company_id"));
			}
		} catch (SQLException e) {
			throw new DaoException("Error in -> selectComputer "
					+ e.getMessage());
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return myComputer;
	}

	@Override
	public int countNumberComputers(String myName) throws DaoException {
		int number = 0;
		Connection myCon = myManager.getConnection();
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		String sql = "SELECT count(*) FROM computer WHERE name like ?";
		LOG.debug("requete sql non-prepare : " + sql);

		try {

			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, "%" + myName + "%");

			LOG.debug("requete sql prepare : " + myPreStmt.toString());
			mySet = myPreStmt.executeQuery();

			if (mySet.next()) {
				number = mySet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DaoException("Error in -> countNumberComputers "
					+ e.getMessage());
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return number;
	}

	@Override
	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow)
			throws DaoException {
		ArrayList<Computer> myList = new ArrayList<>();
		Connection myCon = myManager.getConnection();
		PreparedStatement myPreStmt = null;
		ResultSet mySet = null;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name like ? OR company.name like ? ORDER BY ")
				.append(myOrder).append(" LIMIT ?,?");

		LOG.debug("requete sql non-prepare : " + sql);

		try {

			myPreStmt = myCon.prepareStatement(sql.toString());

			myPreStmt.setString(1, "%" + myLikeParam + "%");
			myPreStmt.setString(2, "%" + myLikeParam + "%");
			myPreStmt.setInt(3, startLimit);
			myPreStmt.setInt(4, numberOfRow);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());

			mySet = myPreStmt.executeQuery();

			while (mySet.next()) {

				Computer myComputer = new Computer(mySet.getInt("id"),
						mySet.getString("name"), new DateTime(
								mySet.getDate("introduced")), new DateTime(
								mySet.getDate("discontinued")),
						mySet.getInt("company_id"));

				myList.add(myComputer);
			}
		} catch (SQLException e) {
			throw new DaoException("Error in -> selectComputers "
					+ e.getMessage());
		} finally {
			myManager.closeAll(myPreStmt, mySet);
		}
		return myList;
	}
}
