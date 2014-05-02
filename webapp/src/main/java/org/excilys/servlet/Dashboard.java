package org.excilys.servlet;

import java.net.MalformedURLException;

import org.excilys.wrapper.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	@Autowired
	private PageWrapper mPageWrapper;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(
			ModelMap myMap,
			@RequestParam(value = "bool", required = false) String desc,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "orderby", required = false) String orderBy,
			@RequestParam(value = "page", required = false) String page) throws MalformedURLException {

		mPageWrapper.setBool(desc);
		mPageWrapper.setSearch(search);
		mPageWrapper.setOrderBy(orderBy);
		mPageWrapper.setCurrentPage(page);
		mPageWrapper.setNumberOfComputer();
		mPageWrapper.setNumberOfPage();
		mPageWrapper.setCompanies();
		mPageWrapper.setComputerDTOs();
		
		myMap.addAttribute("wrap", mPageWrapper);

		return new ModelAndView("dashboard");
	}

}
