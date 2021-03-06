package org.excilys.servlet;

import java.net.MalformedURLException;

import javax.validation.Valid;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
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
@RequestMapping("/addcomputer")
public class AddComputer {

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
			mav = new ModelAndView("addComputer");
			mav.addObject("companies", companyServ.selectCompanies());
			mav.addObject("computerDto", myComputer);
		} else {
			mav = new ModelAndView("redirect:dashboard");
			computerServ.insertComputer(mM.toComputer(myComputer));
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(ModelMap map) throws MalformedURLException {
		ModelAndView mav = new ModelAndView("addComputer");
		map.addAttribute("companies", companyServ.selectCompanies());
		mav.addObject("computerDto", new ComputerDto());
		return mav;
	}

}
