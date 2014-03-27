package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.service.impl.ServiceFactory;

public class DeleteComputer extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int id = Integer.valueOf(req.getParameter("idComputer"));
		
		ComputerServiceImpl myService = ServiceFactory.getComputerServ();

		myService.deleteComputer(myService.selectComputer(id));

		resp.sendRedirect("dashboard");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int id = Integer.valueOf(req.getParameter("id"));

		req.setAttribute("computer", ServiceFactory.getComputerServ()
				.selectComputer(id));

		getServletContext().getRequestDispatcher("/WEB-INF/deleteComputer.jsp")
				.forward(req, resp);
	}
}
