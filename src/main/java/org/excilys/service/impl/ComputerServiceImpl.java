package org.excilys.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.excilys.dao.impl.ConnectionManager;
import org.excilys.dao.impl.DaoFactory;
import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.excilys.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ComputerServiceImpl implements ComputerService {
	INSTANCE;

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);

	@Override
	public void insertComputer(Computer myComputer) {
		int id = 0;

		try {
			ConnectionManager.INSTANCE.startTransaction();
			id = DaoFactory.getInstanceComputerDao().insertComputer(myComputer);
			DaoFactory.getInstanceLogDao().insertLog(
					"insert of a computer id :" + id);
			ConnectionManager.INSTANCE.commit();
		} catch (DaoException e1) {
			LOG.error("Error on the insertComputer -computerId-" + id + " "
					+ e1);
				ConnectionManager.INSTANCE.rollback();
				closeThread();
				throw e1;
		}finally{
			closeThread();
		}

	}

	@Override
	public void deleteComputer(Computer myComputer) {

		try {
			ConnectionManager.INSTANCE.startTransaction();
			DaoFactory.getInstanceComputerDao().deleteComputer(myComputer);
			DaoFactory.getInstanceLogDao().insertLog(
					"delete of a computer id :" + myComputer.getId());
			ConnectionManager.INSTANCE.commit();
		} catch (DaoException e1) {
			LOG.error("Error on the deleteComputer " + e1);
			ConnectionManager.INSTANCE.rollback();
			throw e1;
		}
	}

	@Override
	public void updateComputer(Computer myComputer) {
		try {
			ConnectionManager.INSTANCE.startTransaction();
			DaoFactory.getInstanceComputerDao().updateComputer(myComputer);
			DaoFactory.getInstanceLogDao().insertLog(
					"update of a computer id :" + myComputer.getId());
			ConnectionManager.INSTANCE.commit();
		} catch (DaoException e1) {
			LOG.error("Error on the updateComputer -computerId-"
					+ myComputer.getId() + " " + e1);
			ConnectionManager.INSTANCE.rollback();
			throw e1;
		}
	}

	@Override
	public Computer selectComputer(int id) {
		ConnectionManager.INSTANCE.getConnection();
		Computer myComputer = null;
		try {
			myComputer = DaoFactory.getInstanceComputerDao().selectComputer(id);
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
		ConnectionManager.INSTANCE.getConnection();
		int number = 0;
		try {
			number = DaoFactory.getInstanceComputerDao().countNumberComputers(
					myName);
		} catch (DaoException e) {
			LOG.error("Error in -> countNumberOfComputers " + e);
			throw e;
		} finally {
			closeThread();
		}
		return number;
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
	public ArrayList<Computer> selectComputers(String myLikeParam,
			String myOrder, int startLimit, int numberOfRow) {
		ConnectionManager.INSTANCE.getConnection();
		ArrayList<Computer> myList = null;
		try {
			myList = DaoFactory.getInstanceComputerDao().selectComputers(
					myLikeParam, myOrder, startLimit, numberOfRow);
		} catch (DaoException e) {
			LOG.error("Error on the selectComputers " + e);
			throw e;
		} finally {
			closeThread();
		}
		return myList;
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

	@Override
	public HttpServletRequest validateForm(HttpServletRequest req) {

		Integer id = 0;

		if (req.getParameter("name").length() < 2) {
			req.setAttribute("errorName", "Please enter at least 2 characters.");
			req.setAttribute("checkForm", false);
		}

		if (req.getParameter("introducedDate").equals("") == false) {
			try {
				Utilities.stringToDate(req.getParameter("introducedDate"));
			} catch (ParseException e) {
				req.setAttribute("errorIntroduced",
						"Please enter a date in the format yyyy-mm-dd.");
				req.setAttribute("checkForm", false);
			}
		}

		if (req.getParameter("discontinuedDate").equals("") == false) {
			try {
				Utilities.stringToDate(req.getParameter("discontinuedDate"));
			} catch (ParseException e) {
				req.setAttribute("errorDiscontinued",
						"Please enter a date in the format yyyy-mm-dd.");
				req.setAttribute("checkForm", false);
			}
		}

		try {
			id = Integer.valueOf(req.getParameter("company"));
		} catch (NumberFormatException e) {
			req.setAttribute("errorCompany",
					"Please enter a company id in a range.");
			req.setAttribute("checkForm", false);
		}

		if (id != -1 && id != null) {
			if (ServiceFactory.INSTANCE.getCompanyServ().selectCompany(id) == null) {
				req.setAttribute("errorCompany",
						"Please enter a company id in a range.");
				req.setAttribute("checkForm", false);
			}
		}

		return req;
	}

	@Override
	public void closeThread() {
		try {
			ConnectionManager.INSTANCE.getConnection().close();
			ConnectionManager.INSTANCE.myThreadLocal.remove();
			LOG.debug("Close of Thread :" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error in -> Close of Thread "
					+ Thread.currentThread().toString());
			throw new DaoException("Error in -> Close of Thread "+e.getMessage());
		}
	}
}