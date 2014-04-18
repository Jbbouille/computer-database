package org.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.excilys.model.Company;
import org.springframework.jdbc.core.RowMapper;

public class CompanyRowMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet mySet, int line) throws SQLException {
		CompanyExtractor myExtractor = new CompanyExtractor();
		return myExtractor.extractData(mySet);
	}

}
