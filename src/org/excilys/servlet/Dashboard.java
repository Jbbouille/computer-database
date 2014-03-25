package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.service.impl.ServiceFactory;

public class Dashboard extends HttpServlet {

	private final static int NUMBER_OF_COMPUTER = 30;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int page;

		resp.setContentType("text/html");

		if (req.getParameter("page") == null) page = 1;
		else page = Integer.valueOf(req.getParameter("page"));

		req.setAttribute(
				"computers",
				ServiceFactory.getComputerServ().selectPartsComputers(
						ServiceFactory.getComputerServ().getStartLimit(page,
								NUMBER_OF_COMPUTER), NUMBER_OF_COMPUTER));
		req.setAttribute("companies", ServiceFactory.getCompanyServ()
				.selectAllCompanies());
		
		req.setAttribute("numberOfComputers", ServiceFactory.getComputerServ().countNumberComputers());

		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(req, resp);
	}
}
