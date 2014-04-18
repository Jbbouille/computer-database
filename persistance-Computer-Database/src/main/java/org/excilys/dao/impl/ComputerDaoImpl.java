package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.excilys.dao.ComputerDao;
import org.excilys.dao.ComputerRowMapper;
import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

@Repository("computerDao")
public class ComputerDaoImpl implements ComputerDao {

	static final Logger LOG = LoggerFactory.getLogger(ComputerDao.class);

	@Autowired
	BoneCPDataSource boneCP;

	@Override
	public int insertComputer(final Computer myComputer) throws DaoException {

		final String sql = "INSERT INTO computer VALUES (null, ?, ?, ?, ?)";

		LOG.debug("requete sql non-prepare : " + sql);

		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		myTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement myPreStmt = connection.prepareStatement(sql,
						new String[] { "id" });
				myPreStmt.setString(1, myComputer.getName());

				if (myComputer.getDiscontinued() == null)
					myPreStmt.setString(3, "0000-00-00");
				else
					myPreStmt.setString(3,
							dateSQLtoString(myComputer.getDiscontinued()));

				if (myComputer.getIntroduced() == null)
					myPreStmt.setString(2, "0000-00-00");
				else
					myPreStmt.setString(2,
							dateSQLtoString(myComputer.getIntroduced()));

				if (myComputer.getCompanyId() == -1)
					myPreStmt.setNull(4, Types.NULL);
				else
					myPreStmt.setInt(4, myComputer.getCompanyId());
				return myPreStmt;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public void deleteComputer(Computer myComputer) throws DaoException {
		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);
		String sql = "DELETE FROM computer WHERE computer.id = ?";
		myTemplate.update(sql, new Object[] { myComputer.getId() });
	}

	@Override
	public void updateComputer(Computer myComputer) throws DaoException {
		String sql = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id = ?";
		LOG.debug("requete sql non-prepare : " + sql);

		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);
		Object[] myObjectTable = new Object[6];

		myObjectTable[0] = myComputer.getId();

		myObjectTable[1] = myComputer.getName();

		if (myComputer.getIntroduced() == null)
			myObjectTable[2] = null;
		else
			myObjectTable[2] = dateSQLtoString(myComputer.getIntroduced());

		if (myComputer.getDiscontinued() == null)
			myObjectTable[3] = null;
		else
			myObjectTable[3] = dateSQLtoString(myComputer.getDiscontinued());

		if (myComputer.getCompanyId() == -1)
			myObjectTable[4] = null;
		else
			myObjectTable[4] = myComputer.getCompanyId();

		myObjectTable[5] = myComputer.getId();

		myTemplate.update(sql, myObjectTable);

	}

	@Override
	public Computer selectComputer(int id) throws DaoException {
		List<Computer> myList = new ArrayList<Computer>();
		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);

		String sql = "SELECT * FROM computer WHERE id = ?";

		LOG.debug("requete sql non-prepare : " + sql);

		myList = myTemplate.query(sql, new Object[] { id },
				new ComputerRowMapper());

		return myList.get(0);
	}

	@Override
	public int countNumberComputers(String myName) throws DaoException {
		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);
		String sql = "SELECT count(*) FROM computer WHERE name like ?";
		LOG.debug("requete sql non-prepare : " + sql);
		return myTemplate.queryForObject(sql,
				new Object[] { "%" + myName + "%" }, Integer.class);
	}

	@Override
	public List<Computer> selectComputers(String myLikeParam, String myOrder,
			int startLimit, int numberOfRow) throws DaoException {
		List<Computer> myList = new ArrayList<Computer>();
		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);
		Object[] myObjectTable = new Object[4];
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name like ? OR company.name like ? ORDER BY ")
				.append(myOrder).append(" LIMIT ?,?");

		LOG.debug("requete sql non-prepare : " + sql);

		myObjectTable[0] = "%" + myLikeParam + "%";

		myObjectTable[1] = "%" + myLikeParam + "%";

		myObjectTable[2] = startLimit;

		myObjectTable[3] = numberOfRow;

		myList = myTemplate.query(sql.toString(), myObjectTable,
				new ComputerRowMapper());

		return myList;
	}

	private String dateSQLtoString(DateTime myDate) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		return fmt.print(myDate);
	}
}
