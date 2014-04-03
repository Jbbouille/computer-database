package org.excilys.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
import org.excilys.validator.ComputerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class ModifyComputer extends HttpServlet {

	@Autowired
	private ComputerServiceImpl myComputerServ;

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Autowired
	private ModelMapper mM;

	@Autowired
	ComputerValidator compValid;

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HashMap<String, String> myMapErrors;

		ComputerDto myDto = new ComputerDto();
		myDto.setCompanyId(req.getParameter("company"));
		myDto.setDiscontinued(req.getParameter("discontinuedDate"));
		myDto.setIntroduced(req.getParameter("introducedDate"));
		myDto.setName(req.getParameter("name"));
		myDto.setId(req.getParameter("idComputer"));

		myMapErrors = compValid.validateForm(myDto, false);
		
		if (myMapErrors.get("error") == null) {
			myComputerServ.updateComputer(mM.ComputerDtoToComputer(myDto));
			resp.sendRedirect("dashboard");
		} else {
			/*
			 * If the value choose is not an integer, the JSP will throw
			 * NumberException that's why we must set a default value.
			 */
			if (myMapErrors.get("errorCompany") != null) {
				myDto.setCompanyId("-1");
			}
			req.setAttribute("errorMap", myMapErrors);
			req.setAttribute("computer", myDto);
			req.setAttribute("companies", myCompanyServ.selectCompanies());

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

			req.setAttribute("companies", myCompanyServ.selectCompanies());
			req.setAttribute("computer", myComputerServ.selectComputer(id));

			getServletContext().getRequestDispatcher(
					"/WEB-INF/modifyComputer.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("dashboard");
		}
	}
}
