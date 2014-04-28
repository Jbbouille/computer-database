package org.excilys.service;

import java.util.List;

import org.excilys.model.Computer;
import org.springframework.data.domain.PageRequest;

public interface ComputerService {

	public void insertComputer(Computer myComputer);

	public void deleteComputer(Computer myComputer);

	public void updateComputer(Computer myComputer);

	public Computer selectComputer(Integer id);

	public List<Computer> selectComputers(String myLikeParam, PageRequest page);

	public int countNumberOfComputers(String myName, PageRequest page);
}
