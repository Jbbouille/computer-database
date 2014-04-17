package org.excilys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("connectionManager")
public class ConnectionManager {
	

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);
	

	protected static void closeAll(PreparedStatement myPreStmt, ResultSet mySet) {
		try {
			LOG.debug("Close of Statment and ResultSet if not null");
			if (mySet != null) {
				mySet.close();
				LOG.debug("Close of ResultSet -> done");
			}
			if (myPreStmt != null) {
				myPreStmt.close();
				LOG.debug("Close of Statment -> done");
			}
		} catch (SQLException e) {
			LOG.error("CANNOT close connections : " + e);
		}
	}

}
