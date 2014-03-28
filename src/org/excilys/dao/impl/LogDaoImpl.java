package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.excilys.dao.LogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum LogDaoImpl implements LogDao {
	INSTANCE;

	static final Logger LOG = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@Override
	public void insertLog(String text, Connection myCon) throws SQLException {
		PreparedStatement myPreStmt = null;
		String sql = "INSERT INTO log VALUES (null, ?, null)";
		LOG.debug("requete sql non-prepare : " + sql);

		myCon.setAutoCommit(false);
		myPreStmt = myCon.prepareStatement(sql);

		myPreStmt.setString(1, text);

		LOG.debug("requete sql prepare : " + myPreStmt.toString());

		myPreStmt.execute();
	}

}
