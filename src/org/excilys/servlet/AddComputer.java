package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.util.Utilities;

public class AddComputer extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ComputerServiceImpl mService = new ComputerServiceImpl();
		
		Computer myComputer = new Computer();
		
		myComputer.setIntroduced(Utilities.stringToDateSQl(req.getParameter("introducedDate")));
		myComputer.setDiscontinued(Utilities.stringToDateSQl(req.getParameter("discontinuedDate")));
		myComputer.setName(req.getParameter("name"));
		myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));
		
		mService.insertComputer(myComputer);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

		CompanyServiceImpl mService = new CompanyServiceImpl();

		req.setAttribute("companies", mService.selectAllCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(req, resp);
	}
}
