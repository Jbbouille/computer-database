package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.excilys.dao.ComputerDao;
import org.excilys.dao.LogDao;
import org.excilys.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

@Repository("logDao")
public class LogDaoImpl implements LogDao {

	static final Logger LOG = LoggerFactory.getLogger(ComputerDao.class);
	
	@Autowired
	BoneCPDataSource boneCP;
	
	@Override
	public void insertLog(String text) throws DaoException {
		PreparedStatement myPreStmt = null;
		Connection myCon = DataSourceUtils.getConnection(boneCP);
		String sql = "INSERT INTO log VALUES (null, ?, null)";
		LOG.debug("requete sql non-prepare : " + sql);

		try {
			myPreStmt = myCon.prepareStatement(sql);

			myPreStmt.setString(1, text);

			LOG.debug("requete sql prepare : " + myPreStmt.toString());

			myPreStmt.execute();
		} catch (SQLException e) {
			ConnectionManager.closeAll(myPreStmt, null);
			throw new DaoException("Error in -> insertLog "+e.getMessage());
		}
	}

}
