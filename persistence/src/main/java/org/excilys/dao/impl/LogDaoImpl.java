package org.excilys.dao.impl;

import org.excilys.dao.LogDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl implements LogDao {

	@Autowired
	SessionFactory sf;

	@Override
	public void insertLog(String text) throws DaoException {
		Session mSession = sf.getCurrentSession();
		mSession.persist(new Log(text, new DateTime()));
	}

}
