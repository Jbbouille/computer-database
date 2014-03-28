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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Integer id =null;
		if(req.getParameter("idComputer")!=null) id = Integer.valueOf(req.getParameter("idComputer"));
		else resp.sendRedirect("dashboard");
		
		req = ServiceFactory.getComputerServ().validateForm(req);
		
		boolean myCheckForm = true;
		if (req.getAttribute("checkForm") != null) myCheckForm = (Boolean) req.getAttribute("checkForm");
		
		if (myCheckForm) {

			Computer myComputer = new Computer();

			myComputer.setId(id);
			myComputer.setName(req.getParameter("name"));
			myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));

			try {
				if (!(req.getParameter("introducedDate").equals("")))
					myComputer.setIntroduced(Utilities.stringToDate(req
							.getParameter("introducedDate")));
				if (!(req.getParameter("discontinuedDate").equals("")))
					myComputer.setDiscontinued(Utilities.stringToDate(req
							.getParameter("discontinuedDate")));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ServiceFactory.getComputerServ().updateComputer(myComputer);

			resp.sendRedirect("dashboard");
		} else {

			Computer myComputer = ServiceFactory.getComputerServ()
					.selectComputer(id);

			myComputer.setId(id);
			myComputer.setName(req.getParameter("name"));
			myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));

			req.setAttribute("computer", myComputer);
			req.setAttribute("companies", ServiceFactory.getCompanyServ()
					.selectCompanies());

			getServletContext().getRequestDispatcher(
					"/WEB-INF/modifyComputer.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Integer id = null;
		if (req.getParameter("id") != null) {
			
			id = Integer.valueOf(req.getParameter("id"));
			
			req.setAttribute("companies", ServiceFactory.getCompanyServ()
					.selectCompanies());
			req.setAttribute("computer", ServiceFactory.getComputerServ()
					.selectComputer(id));

			getServletContext().getRequestDispatcher("/WEB-INF/modifyComputer.jsp")
					.forward(req, resp);
		} else {
			resp.sendRedirect("dashboard");
		}
	}
}
