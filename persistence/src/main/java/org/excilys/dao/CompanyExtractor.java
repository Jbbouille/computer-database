package org.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.excilys.model.Company;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CompanyExtractor implements ResultSetExtractor<Company> {

	@Override
	public Company extractData(ResultSet mySet) throws SQLException,
			DataAccessException {

		Company myCompany = new Company();

		myCompany.setId(mySet.getInt("id"));
		myCompany.setName(mySet.getString("name"));
		return myCompany;
	}

}
