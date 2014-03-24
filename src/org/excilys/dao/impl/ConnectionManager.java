package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.log.Log;

public class ConnectionManager {

	static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
	private static ConnectionManager instance;


	public static ConnectionManager getInstance() {
		if (instance == null) instance = new ConnectionManager();
		return instance;
	}

	private ConnectionManager() {
	}

	public Connection createConnection() {
		Connection mConnection = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/computerdatabase");
			mConnection = ds.getConnection();
		} catch (NamingException e) {
			LOG.error("Cannot get Name of DataSource :" + e);
		} catch (SQLException e) {
			LOG.error("Cannot create DataSource :" + e);
		}
		return mConnection;
	}
	
	protected static void closeAll(PreparedStatement myPreStmt, Connection myCon, ResultSet mySet) {
		try {
			LOG.debug("Close of all connections");
			if (myCon != null) myCon.close();
			if (mySet != null) mySet.close();
			if (myPreStmt != null) myPreStmt.close();
		} catch (SQLException e) {
			LOG.error("Cannot close connections :" + e);
		}
	}
}
