package org.excilys.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.excilys.dao.impl.CompanyDaoImpl;
import org.excilys.dao.impl.ComputerDaoImpl;
import org.excilys.dao.impl.ConnectionManager;
import org.excilys.dao.impl.LogDaoImpl;
import org.excilys.dto.ComputerDto;
import org.excilys.exception.DaoException;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("computerService")
public class ComputerServiceImpl implements ComputerService {

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);

	@Autowired
	private ConnectionManager myManager;

	@Autowired
	private ComputerDaoImpl myComputerDao;

	@Autowired
	private LogDaoImpl myLogDao;

	@Autowired
	private CompanyDaoImpl myCompanyDao;

	@Autowired
	private ModelMapper mM;

	@Override
	public void insertComputer(Computer myComputer) {
		int id = 0;

		try {
			myManager.startTransaction();
			id = myComputerDao.insertComputer(myComputer);
			myLogDao.insertLog("insert of a computer id :" + id);
			myManager.commit();
		} catch (DaoException e1) {
			LOG.error("Error on the insertComputer -computerId-" + id + " "
					+ e1);
			myManager.rollback();
			closeThread();
			throw e1;
		} finally {
			closeThread();
		}

	}

	@Override
	public void deleteComputer(Computer myComputer) {

		try {
			myManager.startTransaction();
			myComputerDao.deleteComputer(myComputer);
			myLogDao.insertLog("delete of a computer id :" + myComputer.getId());
			myManager.commit();
		} catch (DaoException e1) {
			LOG.error("Error on the deleteComputer " + e1);
			myManager.rollback();
			throw e1;
		}
	}

	@Override
	public void updateComputer(Computer myComputer) {
		try {
			myManager.startTransaction();
			myComputerDao.updateComputer(myComputer);
			myLogDao.insertLog("update of a computer id :" + myComputer.getId());
			myManager.commit();
		} catch (DaoException e1) {
			LOG.error("Error on the updateComputer -computerId-"
					+ myComputer.getId() + " " + e1);
			myManager.rollback();
			throw e1;
		}
	}

	@Override
	public ComputerDto selectComputer(int id) {
		myManager.getConnection();
		ComputerDto myComputer = null;
		Computer myTest = null;
		try {
			myTest = myComputerDao.selectComputer(id);
			if (myTest != null) {
				myComputer = mM.computerToComputerDto(myTest);
			}
		} catch (DaoException e) {
			LOG.error("Error in -> selectComputer -computerId-" + id + " " + e);
			throw e;
		} finally {
			closeThread();
		}
		return myComputer;
	}

	@Override
	public int countNumberOfComputers(String myName) {
		myManager.getConnection();
		int number = 0;
		try {
			number = myComputerDao.countNumberComputers(myName);
		} catch (DaoException e) {
			LOG.error("Error in -> countNumberOfComputers " + e);
			throw e;
		} finally {
			closeThread();
		}
		return number;
	}

	@Override
	public ArrayList<ComputerDto> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow) {
		myManager.getConnection();
		ArrayList<ComputerDto> myList = null;
		try {
			myList = new ArrayList<>();
			for (Computer computer : myComputerDao.selectComputers(myLikeParam,
					myOrder, startLimit, numberOfRow)) {
				myList.add(mM.computerToComputerDto(computer));
			}
		} catch (DaoException e) {
			LOG.error("Error on the selectComputers " + e);
			throw e;
		} finally {
			closeThread();
		}
		return myList;
	}

	@Override
	public void closeThread() {
		try {
			myManager.getConnection().close();
			myManager.myThreadLocal.remove();
			LOG.debug("Close of Thread :" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error in -> Close of Thread "
					+ Thread.currentThread().toString());
			throw new DaoException("Error in -> Close of Thread "
					+ e.getMessage());
		}
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
