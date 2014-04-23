package org.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.excilys.model.Computer;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ComputerExtractor implements ResultSetExtractor<Computer> {

	@Override
	public Computer extractData(ResultSet mySet) throws SQLException,
			DataAccessException {
		Computer myComputer = new Computer();

		myComputer.setName(mySet.getString("name"));
		myComputer.setId(mySet.getInt("id"));
		myComputer.setCompanyId(mySet.getInt("company_id"));
		if(mySet.getDate("introduced")!= null) myComputer.setIntroduced(new DateTime(mySet.getDate("introduced")));
		if(mySet.getDate("discontinued")!= null) myComputer.setDiscontinued(new DateTime(mySet.getDate("discontinued")));

		return myComputer;
	}

}
