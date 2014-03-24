package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.dao.impl.CompanyDao;
import org.excilys.dao.impl.ComputerDao;

public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		
		ComputerDao mComputerDao = ComputerDao.getInstance();
		CompanyDao mCompanyDao = CompanyDao.getInstance();

		req.setAttribute("computers", mComputerDao.selectAllComputers());
		req.setAttribute("companies", mCompanyDao.selectAllCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req,
				resp);
	}
}
