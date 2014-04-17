package org.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Company;
import org.excilys.model.Computer;
import org.excilys.service.CompanyService;
import org.excilys.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {

	private final static int NUMBER_OF_COMPUTER_BY_PAGE = 30;

	@Autowired
	private ComputerService myComputerServ;

	@Autowired
	private CompanyService myCompanyServ;

	@Autowired
	private ServletContext srvContext;

	@Autowired
	private ModelMapper mM;

	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int page;
		String search;
		String orderBy;
		boolean desc;
		Double myNumberOfPage;
		int numberOfComputer;
		ArrayList<ComputerDto> myList = new ArrayList<>();

		HashMap<Integer, Company> myComp = myCompanyServ.selectCompanies();

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

		numberOfComputer = myComputerServ.countNumberOfComputers(search);

		myNumberOfPage = myComputerServ.numberOfPage(numberOfComputer,
				NUMBER_OF_COMPUTER_BY_PAGE);

		if ((req.getParameter("page") == null)
				|| Double.valueOf(req.getParameter("page")) > myNumberOfPage
				|| Double.valueOf(req.getParameter("page")) < 0)
			page = 1;
		else
			page = Integer.valueOf(req.getParameter("page"));

		ArrayList<Computer> myListComputers = myComputerServ.selectComputers(
				search, myComputerServ.getOrderBy(orderBy, desc),
				myComputerServ.getStartLimit(page, NUMBER_OF_COMPUTER_BY_PAGE),
				NUMBER_OF_COMPUTER_BY_PAGE);

		for (Computer computer : myListComputers) {
			myList.add(mM.computerToComputerDto(computer, myComp));
		}

		req.setAttribute("computers", myList);
		req.setAttribute("search", search);
		req.setAttribute("numberOfComputers", numberOfComputer);
		req.setAttribute("numberOfPages", myNumberOfPage);
		req.setAttribute("orderby", orderBy);
		req.setAttribute("bool", desc);
		req.setAttribute("companies", myComp);
		req.setAttribute("currentPage", page);

		srvContext.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req,
				resp);
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("exceptionError");
		return model;
	}
}
