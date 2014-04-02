package org.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class Dashboard extends HttpServlet {

	private final static int NUMBER_OF_COMPUTER_BY_PAGE = 30;

	@Autowired
	private ComputerServiceImpl myComputerServ;

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ComputerServiceImpl myService = myComputerServ;

		int page;
		String search;
		String orderBy;
		boolean desc;
		Double myNumberOfPage;
		int numberOfComputer;

		if (req.getParameter("bool") == null)
			desc = false;
		else
			desc = Boolean.valueOf(req.getParameter("bool"));

		if (req.getParameter("orderby") == null)
			orderBy = "name";
		else
			orderBy = req.getParameter("orderby");

		if (req.getParameter("search") == null)
			search = "";
		else
			search = req.getParameter("search");

		numberOfComputer = myService.countNumberOfComputers(search);

		myNumberOfPage = myService.numberOfPage(numberOfComputer,
				NUMBER_OF_COMPUTER_BY_PAGE);

		if ((req.getParameter("page") == null)
				|| Double.valueOf(req.getParameter("page")) > myNumberOfPage
				|| Double.valueOf(req.getParameter("page")) < 0)
			page = 1;
		else
			page = Integer.valueOf(req.getParameter("page"));

		ArrayList<Computer> myListComputers = myService.selectComputers(search,
				myService.getOrderBy(orderBy, desc),
				myService.getStartLimit(page, NUMBER_OF_COMPUTER_BY_PAGE),
				NUMBER_OF_COMPUTER_BY_PAGE);

		req.setAttribute("computers", myListComputers);
		req.setAttribute("search", search);
		req.setAttribute("numberOfComputers", numberOfComputer);
		req.setAttribute("numberOfPages", myNumberOfPage);
		req.setAttribute("orderby", orderBy);
		req.setAttribute("bool", desc);
		req.setAttribute("companies", myCompanyServ.selectCompanies());
		req.setAttribute("currentPage", page);

		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(req, resp);
	}
}
