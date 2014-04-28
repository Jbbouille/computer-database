package org.excilys.service.impl;

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
	public int countNumberOfComputers(String myLikeParam, PageRequest page) {
		Long number = myComputerDao
				.findByNameContainingOrCompanyIdNameContaining(myLikeParam,
						myLikeParam, page).getTotalElements();
		return number.intValue();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Computer> selectComputers(String myLikeParam, PageRequest page) {
		List<Computer> myList = myComputerDao
				.findByNameContainingOrCompanyIdNameContaining(myLikeParam,
						myLikeParam, page).getContent();
		return myList;
	}
}
