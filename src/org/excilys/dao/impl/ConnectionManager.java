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

import com.jolbox.bonecp.BoneCPDataSource;

public enum ConnectionManager {
	INSTANCE;
	
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
	private BoneCPDataSource boneCP = new BoneCPDataSource();
	
	public static ConnectionManager getInstance() {
		return ConnectionManager.INSTANCE;
	}

	public Connection createConnection() {
		Connection mConnection = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/computerdatabase");
			boneCP.setDatasourceBean(ds);
			mConnection = boneCP.getConnection();
		} catch (NamingException e) {
			LOG.error("Cannot get Name of DataSource :" + e);
		} catch (SQLException e) {
			LOG.error("Cannot create DataSource :" + e);
		}
		return mConnection;
	}
	
	protected static void closeAll(PreparedStatement myPreStmt, Connection myCon, ResultSet mySet) {
		try {
			LOG.debug("Close of all connections " + myPreStmt.toString());
			if (myCon != null) myCon.close();
			if (mySet != null) mySet.close();
			if (myPreStmt != null) myPreStmt.close();
		} catch (SQLException e) {
			LOG.error("CANNOT close connections : " + e);
		}
	}
}
