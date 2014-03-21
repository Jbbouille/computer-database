package org.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {

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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mConnection;
	}
	
	protected static void closeAll(PreparedStatement myPreStmt, Connection myCon, ResultSet mySet) {
		try {
			if (myCon != null) myCon.close();
			if (mySet != null) mySet.close();
			if (myPreStmt != null) myPreStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
