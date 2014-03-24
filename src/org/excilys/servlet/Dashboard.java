package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;

public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		
		ComputerServiceImpl mComputerService = new ComputerServiceImpl();
		CompanyServiceImpl mCompanyService = new CompanyServiceImpl();
		
		req.setAttribute("computers", mComputerService.selectAllComputers());
		req.setAttribute("companies", mCompanyService.selectAllCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req,
				resp);
	}
}
