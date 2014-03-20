package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.dao.CompanyDao;
import org.excilys.dao.ComputerDao;

public class ServletDashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

		ComputerDao mComputerDao = new ComputerDao();
		CompanyDao mCompanyDao = new CompanyDao();

		req.setAttribute("Computers", mComputerDao.selectAllComputers());
		req.setAttribute("Companies", mCompanyDao.selectAllCompanies());

		getServletContext().getRequestDispatcher("/dashboard.jsp").forward(req,
				resp);
	}
}
