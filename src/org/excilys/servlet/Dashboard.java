package org.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.service.impl.ServiceFactory;

public class Dashboard extends HttpServlet {

	private final static int NUMBER_OF_COMPUTER_BY_PAGE = 30;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int page;
		String search = req.getParameter("search");
		ComputerServiceImpl myService = ServiceFactory.getComputerServ();

		resp.setContentType("text/html");
		
		if (req.getParameter("search") == null) search = "";
			
		if ((req.getParameter("page") == null)||(Double.valueOf(req.getParameter("page")) > myService.numberPage(myService.countNumberComputers(search),NUMBER_OF_COMPUTER_BY_PAGE))) page = 1;
		else page = Integer.valueOf(req.getParameter("page"));

		ArrayList<Computer> myListComputers = myService.searchComputer(search, myService.getStartLimit(page, NUMBER_OF_COMPUTER_BY_PAGE), NUMBER_OF_COMPUTER_BY_PAGE);

		req.setAttribute("computers", myListComputers);

		req.setAttribute("search", search);

		req.setAttribute("numberOfComputers",
					myService.countNumberComputers(search));
		req.setAttribute("numberOfPages", myService.numberPage(
					myService.countNumberComputers(search),
					NUMBER_OF_COMPUTER_BY_PAGE));
			

		req.setAttribute("companies", ServiceFactory.getCompanyServ()
				.selectAllCompanies());

		req.setAttribute("currentPage", page);

		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(req, resp);
	}
}
