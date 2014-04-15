package org.excilys.servlet;

import java.io.IOException;

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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/deletecomputer")
public class DeleteComputer  {

	@Autowired
	private ComputerServiceImpl myComputerServ;

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private ComputerValidator compValid;

	@Autowired
	private ModelMapper mM;

	@Autowired
	private ServletContext srvContext;

	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String validation = compValid.idValidator(req.getParameter("id"));
		if (validation == null) {
			int id = Integer.valueOf(req.getParameter("id"));
			ComputerDto myComputer = myComputerServ.selectComputer(id);
			myComputerServ.deleteComputer(mM.ComputerDtoToComputer(myComputer));
			resp.sendRedirect("dashboard");
		} else {
			req.setAttribute("deleteError", validation);

			srvContext.getRequestDispatcher("/dashboard").forward(req,
					resp);
		}
	}
}
