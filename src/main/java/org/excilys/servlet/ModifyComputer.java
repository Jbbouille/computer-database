package org.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.service.impl.CompanyServiceImpl;
import org.excilys.service.impl.ComputerServiceImpl;
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
	private ComputerServiceImpl myComputerServ;

	@Autowired
	private CompanyServiceImpl myCompanyServ;

	@Autowired
	private ModelMapper mM;

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(
			@Valid @ModelAttribute("computerDto") ComputerDto myComputer,
			BindingResult result) {
		
		ModelAndView mav = null;
		
		if (result.hasErrors()) {
			mav = new ModelAndView("modifyComputer");
			mav.addObject("companies", myCompanyServ.selectCompanies());
			mav.addObject("computerDto", myComputer);
			System.out.println(result.toString());
		} else {
			mav = new ModelAndView("redirect:dashboard");
			myComputerServ.updateComputer(mM.ComputerDtoToComputer(myComputer));
		}
 
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest req, ModelMap map)
			throws ServletException, IOException {

		ModelAndView mav = null;
		
		Integer id = null;
		if (req.getParameter("id") != null) {
			
			mav = new ModelAndView("modifyComputer");
			
			id = Integer.valueOf(req.getParameter("id"));
				
			map.addAttribute("companies", myCompanyServ.selectCompanies());
			mav.addObject("computerDto", myComputerServ.selectComputer(id));

		} else {
			mav = new ModelAndView("redirect:dashboard");
		}
		
		return mav;
	}
	
}
