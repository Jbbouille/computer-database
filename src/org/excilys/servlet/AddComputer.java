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

public class AddComputer extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Computer myComputer = new Computer();
		
		req = ServiceFactory.getComputerServ().validateForm(req);
		boolean myCheckForm = true;
		if (req.getAttribute("checkForm") != null) myCheckForm = (Boolean) req.getAttribute("checkForm");
		
		if (myCheckForm) {
			
			myComputer.setName(req.getParameter("name"));

			myComputer
					.setCompanyId(Integer.valueOf(req.getParameter("company")));

			try {
				if(!(req.getParameter("introducedDate").equals("")))myComputer.setIntroduced(Utilities.stringToDate(req.getParameter("introducedDate")));
				if(!(req.getParameter("discontinuedDate").equals(""))) myComputer.setDiscontinued(Utilities.stringToDate(req.getParameter("discontinuedDate")));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ServiceFactory.getComputerServ().insertComputer(myComputer);

			resp.sendRedirect("dashboard");
		} else {
			
			req.setAttribute("name", req.getParameter("name"));
			req.setAttribute("introducedDate", req.getParameter("introducedDate"));
			req.setAttribute("discontinuedDate", req.getParameter("discontinuedDate"));
			req.setAttribute("companyParam", req.getParameter("company"));
			req.setAttribute("companies", ServiceFactory.getCompanyServ()
					.selectCompanies());
			
			getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("companies", ServiceFactory.getCompanyServ()
				.selectCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(req, resp);
	}
}
