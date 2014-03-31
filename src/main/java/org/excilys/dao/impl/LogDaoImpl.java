package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.excilys.dao.LogDao;
import org.excilys.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

<<<<<<< HEAD:src/main/java/org/excilys/dao/impl/LogDaoImpl.java
=======
import org.excilys.exception.DaoException;

>>>>>>> 481708d1a6d302520afdaf97e03156e361fa2652:src/main/java/org/excilys/dao/impl/LogDaoImpl.java
public enum LogDaoImpl implements LogDao {
	INSTANCE;

	static final Logger LOG = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@Override
	public void insertLog(String text) throws DaoException {
		PreparedStatement myPreStmt = null;
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		String sql = "INSERT INTO log VALUES (null, ?, null)";
		LOG.debug("requete sql non-prepare : " + sql);

		try {
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, text);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());

			myPreStmt.execute();
		} catch (SQLException e) {
			ConnectionManager.INSTANCE.closeAll(myPreStmt, null);
			throw new DaoException("Error in -> insertLog "+e.getMessage());
		}
	}

}
