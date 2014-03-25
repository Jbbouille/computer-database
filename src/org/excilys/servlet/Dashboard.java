package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.service.impl.ServiceFactory;

public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		
		req.setAttribute("computers", ServiceFactory.getComputerServ().selectAllComputers());
		req.setAttribute("companies", ServiceFactory.getCompanyServ().selectAllCompanies());

		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req,
				resp);
	}
}
