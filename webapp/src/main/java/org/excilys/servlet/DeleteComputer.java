package org.excilys.servlet;

import java.net.MalformedURLException;

import org.excilys.model.Computer;
import org.excilys.service.CompanyService;
import org.excilys.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deletecomputer")
public class DeleteComputer {

	@Autowired
	private ComputerService computerServ;
	
	@Autowired
	private CompanyService companyServ;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(ModelMap myMap,
			@RequestParam(value = "id", required = false) Integer id)
			throws MalformedURLException {

		Computer myComputer = computerServ.selectComputer(id);

		if (myComputer == null) {
			myMap.addAttribute("deleteError",
					"Could Not delete Computer that not exist");
		} else {
			computerServ.deleteComputer(myComputer);
		}

		return new ModelAndView("redirect:dashboard");
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("exceptionError");
		return model;
	}
}
