package org.excilys.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class ModifyComputer extends HttpServlet {
	
	@Autowired
	private ComputerServiceImpl myComputerServ;

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Integer id = Integer.valueOf(req.getParameter("idComputer"));
		
		req = myComputerServ.validateForm(req);
		
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

			myComputerServ.updateComputer(myComputer);

			resp.sendRedirect("dashboard");
		} else {

			Computer myComputer = myComputerServ
					.selectComputer(id);

			myComputer.setId(id);
			myComputer.setName(req.getParameter("name"));
			myComputer.setCompanyId(Integer.valueOf(req.getParameter("company")));

			req.setAttribute("computer", myComputer);
			req.setAttribute("companies", myCompanyServ
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
			
			req.setAttribute("companies", myCompanyServ
					.selectCompanies());
			req.setAttribute("computer", myComputerServ
					.selectComputer(id));

			getServletContext().getRequestDispatcher("/WEB-INF/modifyComputer.jsp")
					.forward(req, resp);
		} else {
			resp.sendRedirect("dashboard");
		}
	}
}
