package org.excilys.dao;

import org.excilys.exception.DaoException;

public interface LogDao {

	public void insertLog(String text) throws DaoException;
}
