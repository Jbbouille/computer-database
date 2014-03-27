package org.excilys.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.impl.ServiceFactory;
import org.excilys.util.Utilities;

public class ModifyComputer extends HttpServlet {

	private int id;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Computer myComputer = new Computer();
		
		myComputer.setId(id);
		myComputer.setName(req.getParameter("name"));
		myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));
		
		try {
			if(!(req.getParameter("introducedDate").equals("")))myComputer.setIntroduced(Utilities.stringToDate(req.getParameter("introducedDate")));
			if(!(req.getParameter("discontinuedDate").equals(""))) myComputer.setDiscontinued(Utilities.stringToDate(req.getParameter("discontinuedDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}	;

		ServiceFactory.getComputerServ().updateComputer(myComputer);
		
		resp.sendRedirect("dashboard");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		id = Integer.valueOf(req.getParameter("id"));

		req.setAttribute("companies", ServiceFactory.getCompanyServ().selectCompanies());
		req.setAttribute("computer", ServiceFactory.getComputerServ().selectComputer(id));

		getServletContext().getRequestDispatcher("/WEB-INF/modifyComputer.jsp")
				.forward(req, resp);
	}
}
