package org.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.excilys.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCPDataSource;

@Component("connectionManager")
public class ConnectionManager {
	
	@Autowired
	private BoneCPDataSource boneCP;

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);
	
	public ThreadLocal<Connection> myThreadLocal = new ThreadLocal<Connection>();
	
	public Connection getConnection() {
		try {
			if (myThreadLocal.get() == null) {
				myThreadLocal.set(boneCP.getConnection());
				LOG.debug("getThread NEW :" + Thread.currentThread().toString());
			}
			LOG.debug("getThread NOT NEW :" + Thread.currentThread().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myThreadLocal.get();
	}

	protected void closeAll(PreparedStatement myPreStmt, ResultSet mySet) {
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

	public void startTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
		}
	}

	public void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			LOG.error("Error on the commit " + e);
			throw new DaoException("Error on the commit " + e.getMessage());
		}
	}

	public void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			LOG.error("Error on the rollback " + e);
			throw new DaoException("Error on the rollback " + e.getMessage());
		}
	}
}
