package org.excilys.service;

import java.util.ArrayList;

import org.excilys.model.Computer;
import org.springframework.data.domain.PageRequest;

public interface ComputerService {

	void insertComputer(Computer myComputer);

	void deleteComputer(Computer myComputer);

	void updateComputer(Computer myComputer);

	Computer selectComputer(Integer id);

	ArrayList<Computer> selectComputers(String myLikeParam, Boolean bool,
			String orderBy, int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE);

	int countNumberOfComputers(String myName, Boolean bool, String orderBy,
			int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE);

	PageRequest getPageRequest(Boolean bool, String orderBy, int currentPage,
			int NUMBER_OF_COMPUTER_BY_PAGE);
}
