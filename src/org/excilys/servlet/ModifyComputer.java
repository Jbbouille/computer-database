package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;

public class ModifyComputer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		ComputerServiceImpl mServiceComputer = new ComputerServiceImpl();
		CompanyServiceImpl mServiceCompany = new CompanyServiceImpl();

		req.setAttribute("companies", mServiceCompany.selectAllCompanies());
		req.setAttribute("computer", mServiceComputer.selectComputer(Integer.valueOf( req.getParameter("id"))));
		
		getServletContext().getRequestDispatcher("/WEB-INF/modifyComputer.jsp")
		.forward(req, resp);
	}
}
