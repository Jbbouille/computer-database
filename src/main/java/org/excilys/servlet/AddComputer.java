package org.excilys.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.validator.ComputerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addcomputer")
public class AddComputer{

	@Autowired
	private ComputerServiceImpl myComputerServ;

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Autowired
	private ModelMapper mM;

	@Autowired
	ComputerValidator compValid;
	
	@Autowired
	ServletContext srvContext;

	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HashMap<String, String> myMapErrors;

		ComputerDto myDto = new ComputerDto();
		myDto.setCompanyId(req.getParameter("company"));
		myDto.setDiscontinued(req.getParameter("discontinuedDate"));
		myDto.setIntroduced(req.getParameter("introducedDate"));
		myDto.setName(req.getParameter("name"));

		myMapErrors = compValid.validateForm(myDto, true);

		if (myMapErrors.get("error") == null) {
			myComputerServ.insertComputer(mM.ComputerDtoToComputer(myDto));
			resp.sendRedirect("dashboard");
		} else {
			req.setAttribute("name", req.getParameter("name"));
			req.setAttribute("introducedDate",
					req.getParameter("introducedDate"));
			req.setAttribute("discontinuedDate",
					req.getParameter("discontinuedDate"));
			if (myMapErrors.get("errorCompany") != null) {
				req.setAttribute("companyParam", "-1");
			}
			req.setAttribute("companies", myCompanyServ.selectCompanies());
			req.setAttribute("errorMap", myMapErrors);

			srvContext.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(
							req, resp);
		}
	}
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("companies", myCompanyServ.selectCompanies());

		srvContext.getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(req, resp);
	}
}
