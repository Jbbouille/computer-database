package org.excilys.service.impl;

import java.util.List;

import org.excilys.dao.CompanyDao;
import org.excilys.dao.ComputerDao;
import org.excilys.dao.LogDao;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	private ComputerDao myComputerDao;

	@Autowired
	private LogDao myLogDao;

	@Autowired
	private CompanyDao myCompanyDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertComputer(Computer myComputer) {
		myComputerDao.insertComputer(myComputer);
		myLogDao.insertLog("insert of a computer id :" + myComputer.getId());
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
		Computer myComputer = myComputerDao.selectComputer(id);
		return myComputer;
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
	public List<Computer> selectComputers(String myLikeParam, String myOrder,
			int startLimit, int numberOfRow) {
		List<Computer> myList = myComputerDao.selectComputers(myLikeParam,
				myOrder, startLimit, numberOfRow);
		return myList;
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
			myStringBuilder.append("name");

			break;
		case "introduced":
			myStringBuilder.append("introduced");

			break;
		case "discontinued":
			myStringBuilder.append("discontinued");

			break;
		case "company":
			myStringBuilder.append("name");

			break;
		default:
			myStringBuilder.append("name");
			break;
		}

		if (desc)
			myStringBuilder.append(" desc");

		return myStringBuilder.toString();
	}
}
