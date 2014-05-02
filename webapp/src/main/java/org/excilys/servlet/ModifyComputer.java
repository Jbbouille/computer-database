package org.excilys.servlet;

import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.excilys.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modifycomputer")
public class ModifyComputer {

	@Autowired
	private ComputerService computerServ;
	
	@Autowired
	private CompanyService companyServ;

	@Autowired
	private ModelMapper mM;

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(
			@Valid @ModelAttribute("computerDto") ComputerDto myComputer,
			BindingResult result) throws MalformedURLException {

		ModelAndView mav = null;

		if (result.hasErrors()) {
			mav = new ModelAndView("modifyComputer");
			mav.addObject("companies", companyServ.selectCompanies());
			mav.addObject("computerDto", myComputer);
			System.out.println(result.toString());
		} else {
			mav = new ModelAndView("redirect:dashboard");
			computerServ.updateComputer(mM.toComputer(myComputer));
		}

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest req, ModelMap map) throws MalformedURLException {

		ModelAndView mav = null;

		Integer id = null;
		if (req.getParameter("id") != null && !(req.getParameter("id").equals(""))) {

			mav = new ModelAndView("modifyComputer");
			id = Integer.valueOf(req.getParameter("id"));
			List<Company> myList = companyServ.selectCompanies();

			map.addAttribute("companies", myList);
			mav.addObject("computerDto", mM.toComputerDto(
					computerServ.selectComputer(id), myList));

		} else {
			mav = new ModelAndView("redirect:dashboard");
		}

		return mav;
	}	
}
