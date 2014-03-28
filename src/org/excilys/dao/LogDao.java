package org.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface LogDao {

	public void insertLog(String text, Connection myCon) throws SQLException;
}
