package org.excilys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.excilys.dao.CompanyDao;
import org.excilys.dao.CompanyRowMapper;
import org.excilys.exception.DaoException;
import org.excilys.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {

	static final Logger LOG = LoggerFactory.getLogger(CompanyDao.class);
	
	@Autowired
	BoneCPDataSource boneCP;

	@Override
	public Company selectCompany(int id) throws DaoException {
		List<Company> myList = new ArrayList<Company>();
		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);

		String sql = "SELECT * FROM computer WHERE id = ?";

		LOG.debug("requete sql non-prepare : " + sql);

		myList = myTemplate.query(sql, new Object[] { id },
				new CompanyRowMapper());

		return myList.get(0);
	}

	@Override
	public List<Company> selectCompanies() throws DaoException {
		List<Company> myList = new ArrayList<Company>();
		JdbcTemplate myTemplate = new JdbcTemplate(boneCP);

		String sql = "SELECT * FROM company";

		LOG.debug("requete sql non-prepare : " + sql);

		myList = myTemplate.query(sql,
				new CompanyRowMapper());

		return myList;
	}
}
