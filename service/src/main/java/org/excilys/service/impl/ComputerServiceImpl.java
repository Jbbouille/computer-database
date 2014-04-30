package org.excilys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.excilys.dao.CompanyDao;
import org.excilys.dao.ComputerDao;
import org.excilys.dao.LogDao;
import org.excilys.model.Computer;
import org.excilys.model.Log;
import org.excilys.service.ComputerService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		myComputerDao.save(myComputer);
		Log myLog = new Log("insert of a computer id :" + myComputer.getId(),
				new DateTime());
		myLogDao.save(myLog);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteComputer(Computer myComputer) {
		myComputerDao.delete(myComputer);
		Log myLog = new Log("delete of a computer id :" + myComputer.getId(),
				new DateTime());
		myLogDao.save(myLog);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateComputer(Computer myComputer) {
		myComputerDao.save(myComputer);
		Log myLog = new Log("update of a computer id :" + myComputer.getId(),
				new DateTime());
		myLogDao.save(myLog);
	}

	@Override
	@Transactional(readOnly = true)
	public Computer selectComputer(Integer id) {
		Computer myComputer = myComputerDao.findOne(id);
		return myComputer;
	}

	@Override
	@Transactional(readOnly = true)
	public int countNumberOfComputers(String myLikeParam, Boolean bool,
			String orderBy, int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE) {
		Long number = myComputerDao
				.findByNameContainingOrCompanyIdNameContaining(
						myLikeParam,
						myLikeParam,
						getPageRequest(bool, orderBy, currentPage,
								NUMBER_OF_COMPUTER_BY_PAGE)).getTotalElements();
		return number.intValue();
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Computer> selectComputers(String myLikeParam,
			Boolean bool, String orderBy, int currentPage,
			int NUMBER_OF_COMPUTER_BY_PAGE) {
		List<Computer> myList = myComputerDao
				.findByNameContainingOrCompanyIdNameContaining(
						myLikeParam,
						myLikeParam,
						getPageRequest(bool, orderBy, currentPage,
								NUMBER_OF_COMPUTER_BY_PAGE)).getContent();
		
		ArrayList<Computer> myReturnList = new ArrayList<>();
		for (Computer computer : myList) {
			myReturnList.add(computer);
		}
		
		return myReturnList;
	}

	@Override
	public PageRequest getPageRequest(Boolean bool, String orderBy,
			int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE) {

		Sort mSort;
		if (bool) {
			mSort = new Sort(Direction.DESC, orderBy);
		} else {
			mSort = new Sort(Direction.ASC, orderBy);
		}

		return new PageRequest((currentPage - 1), NUMBER_OF_COMPUTER_BY_PAGE,
				mSort);
	}
}
