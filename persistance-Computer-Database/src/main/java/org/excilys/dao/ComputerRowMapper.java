package org.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.excilys.model.Computer;
import org.springframework.jdbc.core.RowMapper;

public class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet mySet, int line) throws SQLException {
		ComputerExtractor myExtractor = new ComputerExtractor();
		return myExtractor.extractData(mySet);
	}

}
