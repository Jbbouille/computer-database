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

public enum ComputerServiceImpl implements ComputerService {
	INSTANCE;

	@Override
	public void insertComputer(Computer myComputer) {
		Connection myCon = null;
		int id = 0;

		try {
			myCon = ConnectionManager.INSTANCE.createConnection();
			id = DaoFactory.getInstanceComputerDao().insertComputer(myComputer,
					myCon);
			DaoFactory.getInstanceLogDao().insertLog(
					"insert of a computer id :" + id, myCon);
		} catch (SQLException e1) {
			try {
				myCon.rollback();
				myCon.close();
			} catch (SQLException e) {
			}
		}

		try {
			myCon.commit();
		} catch (SQLException e) {
		}
	}

	@Override
	public void deleteComputer(Computer myComputer) {
		Connection myCon = null;

		try {
			myCon = ConnectionManager.INSTANCE.createConnection();
			DaoFactory.getInstanceComputerDao().deleteComputer(myComputer,
					myCon);
			DaoFactory.getInstanceLogDao().insertLog(
					"delete of a computer id :" + myComputer.getId(), myCon);
		} catch (SQLException e1) {
			try {
				myCon.rollback();
				myCon.close();
			} catch (SQLException e) {
			}
		}

		try {
			myCon.commit();
		} catch (SQLException e) {
		}
	}

	@Override
	public void updateComputer(Computer myComputer) {
		Connection myCon = null;

		try {
			myCon = ConnectionManager.INSTANCE.createConnection();
			DaoFactory.getInstanceComputerDao().updateComputer(myComputer,
					myCon);
			DaoFactory.getInstanceLogDao().insertLog(
					"update of a computer id :" +myComputer.getId(), myCon);
		} catch (SQLException e1) {
			try {
				myCon.rollback();
				myCon.close();
			} catch (SQLException e) {
			}
		}

		try {
			myCon.commit();
		} catch (SQLException e) {
		}
	}

	@Override
	public Computer selectComputer(int id) {
		return DaoFactory.getInstanceComputerDao().selectComputer(id);
	}

	@Override
	public int countNumberOfComputers(String myName) {
		return DaoFactory.getInstanceComputerDao().countNumberComputers(myName);
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
		return DaoFactory.getInstanceComputerDao().selectComputers(myLikeParam,
				myOrder, startLimit, numberOfRow);
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

		int numberCompanies = ServiceFactory.getCompanyServ().countCompanies();
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
