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
		
		int form = ServiceFactory.getComputerServ().validateForm(req.getParameter("name"), req.getParameter("introducedDate"), req.getParameter("discontinuedDate"), Integer.valueOf(req.getParameter("company")));

		if(form == 0){
		myComputer.setName(req.getParameter("name"));
	
		myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));

		
			try {
				if(!(req.getParameter("introducedDate").equals("")))myComputer.setIntroduced(Utilities.stringToDate(req.getParameter("introducedDate")));
				if(!(req.getParameter("discontinuedDate").equals(""))) myComputer.setDiscontinued(Utilities.stringToDate(req.getParameter("discontinuedDate")));
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		
		ServiceFactory.getComputerServ().insertComputer(myComputer);
		
		resp.sendRedirect("dashboard");
		}else{
			
			req.setAttribute("name", req.getParameter("name"));
			req.setAttribute("introducedDate", req.getParameter("introducedDate"));
			req.setAttribute("discontinuedDate", req.getParameter("discontinuedDate"));
			req.setAttribute("company", req.getParameter("company"));
			
			switch (form) {
			case 1:
				req.setAttribute("errorName", "name must be superior to 2");
				break;
			case 2:
				req.setAttribute("errorIntroduced", "Date must be in the form yyyy-mm-dd");
				break;
			case 3:
				req.setAttribute("errorDiscontinued", "Date must be in the form yyyy-mm-dd");
				break;
			case 4:
				req.setAttribute("errorCompany", "Company Number must be in the range");
				break;
			}
			
			getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp")
			.forward(req, resp);
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("companies", ServiceFactory.getCompanyServ().selectCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(req, resp);
	}
}
