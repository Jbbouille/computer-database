package org.excilys.dao;

import exception.DaoException;

public interface LogDao {

	public void insertLog(String text) throws DaoException;
}
