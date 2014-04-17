package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.excilys.model.Computer;
import org.excilys.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deletecomputer")
public class DeleteComputer {

	@Autowired
	private ComputerService myComputerServ;

	@Autowired
	private ServletContext srvContext;

	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (!req.getParameter("id").equals("")) {
			int id = Integer.valueOf(req.getParameter("id"));
			Computer myComputer = myComputerServ.selectComputer(id);
			myComputerServ.deleteComputer(myComputer);
			resp.sendRedirect("dashboard");
		} else {
			req.setAttribute("deleteError",
					"Could Not delete Computer that not exist");

			srvContext.getRequestDispatcher("/dashboard").forward(req, resp);
		}
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("exceptionError");
		return model;
	}
}
