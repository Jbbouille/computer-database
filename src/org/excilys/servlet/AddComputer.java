package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.impl.ServiceFactory;
import org.excilys.util.Utilities;

public class AddComputer extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		Computer myComputer = new Computer();

		myComputer.setName(req.getParameter("name"));
	
		myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));

		if(!(req.getParameter("introducedDate").equals(""))) myComputer.setIntroduced(Utilities.stringToDate(req.getParameter("introducedDate")));
		if(!(req.getParameter("discontinuedDate").equals(""))) myComputer.setDiscontinued(Utilities.stringToDate(req.getParameter("discontinuedDate")));
		
		ServiceFactory.getComputerServ().insertComputer(myComputer);
		
		resp.sendRedirect("dashboard");
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("companies", ServiceFactory.getCompanyServ().selectAllCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(req, resp);
	}
}
