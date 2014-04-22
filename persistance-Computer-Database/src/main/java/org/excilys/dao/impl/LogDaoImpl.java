package org.excilys.dao.impl;

import org.excilys.dao.ComputerDao;
import org.excilys.dao.LogDao;
import org.excilys.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

@Repository("logDao")
public class LogDaoImpl implements LogDao {

	static final Logger LOG = LoggerFactory.getLogger(ComputerDao.class);

	@Autowired
	BoneCPDataSource boneCP;

	@Autowired
	JdbcTemplate myTemplate;

	@Override
	public void insertLog(String text) throws DaoException {
		String sql = "INSERT INTO log VALUES (null, ?, null)";
		LOG.debug("requete sql non-prepare : " + sql);

		myTemplate.update(sql, new Object[] { text });

		LOG.debug("requete sql prepare : " + sql.toString());
	}

}
