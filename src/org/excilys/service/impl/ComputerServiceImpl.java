package org.excilys.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.excilys.dao.impl.ConnectionManager;
import org.excilys.dao.impl.DaoFactory;
import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.excilys.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ComputerServiceImpl implements ComputerService {
	INSTANCE;

	public static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);
	private ThreadLocal<Connection> myThreadLocal = ConnectionManager.INSTANCE.myThreadLocal;

	@Override
	public void insertComputer(Computer myComputer) {
		Connection myCon = null;
		int id = 0;

		try {
			myCon = ConnectionManager.INSTANCE.getConnection();
			id = DaoFactory.getInstanceComputerDao().insertComputer(myComputer,
					myCon);
			DaoFactory.getInstanceLogDao().insertLog(
					"insert of a computer id :" + id, myCon);
		} catch (SQLException e1) {
			LOG.error("Error on the insertComputer -computerId-"+id+" "+e1);
			try {
				myCon.rollback();
				myThreadLocal.get().close();
				myThreadLocal.remove();
				LOG.debug("Close of Thread" + Thread.currentThread().toString());
			} catch (SQLException e) {
				LOG.error("Error on the rollback insertComputer -computerId-"+id+" "+e);
			}
		}

		try {
			myCon.commit();
			myThreadLocal.get().close();
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error on the commit insertComputer -computerId-"+id+" "+e);
		}
	}

	@Override
	public void deleteComputer(Computer myComputer) {
		Connection myCon = null;

		try {
			myCon = ConnectionManager.INSTANCE.getConnection();
			DaoFactory.getInstanceComputerDao().deleteComputer(myComputer,
					myCon);
			DaoFactory.getInstanceLogDao().insertLog(
					"delete of a computer id :" + myComputer.getId(), myCon);
		} catch (SQLException e1) {
			LOG.error("Error on the deleteComputer "+e1);
			try {
				myCon.rollback();
				myThreadLocal.get().close();
				myThreadLocal.remove();
				LOG.debug("Close of Thread" + Thread.currentThread().toString());
			} catch (SQLException e) {
				LOG.error("Error on the rollback deleteComputer "+e);
			}
		}

		try {
			myCon.commit();
			myThreadLocal.get().close();
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error on the commit deleteComputer "+e);
		}
	}

	@Override
	public void updateComputer(Computer myComputer) {
		Connection myCon = null;

		try {
			myCon = ConnectionManager.INSTANCE.getConnection();
			DaoFactory.getInstanceComputerDao().updateComputer(myComputer,
					myCon);
			DaoFactory.getInstanceLogDao().insertLog(
					"update of a computer id :" + myComputer.getId(), myCon);
		} catch (SQLException e1) {
			LOG.error("Error on the updateComputer -computerId-"+myComputer.getId()+" "+e1);
			try {
				myCon.rollback();
				myThreadLocal.get().close();
				myThreadLocal.remove();
				LOG.debug("Close of Thread" + Thread.currentThread().toString());
			} catch (SQLException e) {
				LOG.error("Error on the rollback updateComputer -computerId-"+myComputer.getId()+" "+e1);
			}
		}

		try {
			myCon.commit();
			myThreadLocal.get().close();
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
		} catch (SQLException e) {
			LOG.error("Error on the commit updateComputer -computerId-"+myComputer.getId()+" "+e);
		}
	}

	@Override
	public Computer selectComputer(int id) {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		Computer myComputer = null;
		try {
			myComputer = DaoFactory.getInstanceComputerDao().selectComputer(id,
					myCon);

		} catch (SQLException e) {
			LOG.error("Error on the selectComputer -computerId-"+id+" "+e);
		} finally {
			try {
				myThreadLocal.get().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
		}
		return myComputer;
	}

	@Override
	public int countNumberOfComputers(String myName) {
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		int number = 0;
		try {
			number = DaoFactory.getInstanceComputerDao().countNumberComputers(
					myName, myCon);

		} catch (SQLException e) {
			LOG.error("Error on the countNumberOfComputers "+e);
		} finally {
			try {
				ConnectionManager.INSTANCE.myThreadLocal.get().close();
			} catch (SQLException e) {
				LOG.error("Error on the close connection of countNumberOfComputers "+e);
			}
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
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
		Connection myCon = ConnectionManager.INSTANCE.getConnection();
		ArrayList<Computer> myList = null;
		try {
			myList = DaoFactory.getInstanceComputerDao().selectComputers(
					myLikeParam, myOrder, startLimit, numberOfRow, myCon);

		} catch (SQLException e) {
			LOG.error("Error on the selectComputers "+e);
		} finally {
			try {
				myThreadLocal.get().close();
			} catch (SQLException e) {
				LOG.error("Error on the close of selectComputers "+e);
			}
			myThreadLocal.remove();
			LOG.debug("Close of Thread" + Thread.currentThread().toString());
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
}
