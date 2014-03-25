package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.service.impl.ServiceFactory;

public class DeleteComputer extends HttpServlet {

	private int id;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");

		ServiceFactory.getComputerServ().deleteComputer(ServiceFactory.getComputerServ().selectComputer(id));
		
		resp.sendRedirect("dashboard");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

		id = Integer.valueOf(req.getParameter("id"));
		
		req.setAttribute("computer", ServiceFactory.getComputerServ().selectComputer(id));

		getServletContext().getRequestDispatcher("/WEB-INF/deleteComputer.jsp")
				.forward(req, resp);
	}
}
