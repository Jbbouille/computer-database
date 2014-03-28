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

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);
	
	private BoneCPDataSource boneCP = new BoneCPDataSource();
	
	public ThreadLocal<Connection> myThreadLocal = new ThreadLocal<Connection>();

	{
		Context ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/computerdatabase");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		boneCP.setDatasourceBean(ds);
	}

	public ConnectionManager getInstance() {
		return ConnectionManager.INSTANCE;
	}

	public Connection getConnection() {
		try {
			if(myThreadLocal.get()==null){
				myThreadLocal.set(boneCP.getConnection());
				LOG.debug("getThread NEW :"+Thread.currentThread().toString());
			}
			LOG.debug("getThread NOT NEW :"+Thread.currentThread().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myThreadLocal.get();
	}

	protected void closeAll(PreparedStatement myPreStmt, Connection myCon,
			ResultSet mySet) {
		try {
			LOG.debug("Close of all connections " + myPreStmt.toString());
			if (mySet != null) mySet.close();
			if (myPreStmt != null) myPreStmt.close();
		} catch (SQLException e) {
			LOG.error("CANNOT close connections : " + e);
		}
	}
}
