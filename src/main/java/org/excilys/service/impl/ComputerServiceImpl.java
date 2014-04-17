package org.excilys.service.impl;

import java.util.ArrayList;

import org.excilys.dao.CompanyDao;
import org.excilys.dao.ComputerDao;
import org.excilys.dao.LogDao;
import org.excilys.dao.impl.ConnectionManager;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServiceImpl implements ComputerService {

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);

	@Autowired
	private ComputerDao myComputerDao;

	@Autowired
	private LogDao myLogDao;

	@Autowired
	private CompanyDao myCompanyDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertComputer(Computer myComputer) {
		int id = 0;
		id = myComputerDao.insertComputer(myComputer);
		myLogDao.insertLog("insert of a computer id :" + id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteComputer(Computer myComputer) {
		myComputerDao.deleteComputer(myComputer);
		myLogDao.insertLog("delete of a computer id :" + myComputer.getId());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateComputer(Computer myComputer) {
		myComputerDao.updateComputer(myComputer);
		myLogDao.insertLog("update of a computer id :" + myComputer.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public Computer selectComputer(int id) {
		return myComputerDao.selectComputer(id);
	}

	@Override
	@Transactional(readOnly = true)
	public int countNumberOfComputers(String myName) {
		int number = 0;
		number = myComputerDao.countNumberComputers(myName);
		return number;
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow) {
		return myComputerDao.selectComputers(myLikeParam, myOrder, startLimit,
				numberOfRow);
	}

	@Override
	public double numberOfPage(int numberComputers, int numberOfRow) {
		return Math.ceil(numberComputers / numberOfRow) + 1;
	}

	@Override
	public int getStartLimit(int idPage, int numberOfRow) {
		return ((idPage - 1) * numberOfRow);
	}

	@Override
	public String getOrderBy(String myOrder, Boolean desc) {
		StringBuilder myStringBuilder = new StringBuilder();

		switch (myOrder.toLowerCase()) {
		case "name":
			myStringBuilder.append("computer.name");

			break;
		case "introduced":
			myStringBuilder.append("computer.introduced");

			break;
		case "discontinued":
			myStringBuilder.append("computer.discontinued");

			break;
		case "company":
			myStringBuilder.append("company.name");

			break;
		default:
			myStringBuilder.append("computer.name");
			break;
		}

		if (desc)
			myStringBuilder.append(" DESC");

		return myStringBuilder.toString();
	}
}
