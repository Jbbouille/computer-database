package org.excilys.servlet;

import java.util.List;

import javax.servlet.ServletContext;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.excilys.service.ComputerService;
import org.excilys.util.BindingUtil;
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

	private final static int NUMBER_OF_COMPUTER_BY_PAGE = 30;

	@Autowired
	private ComputerService myComputerServ;

	@Autowired
	private CompanyService myCompanyServ;

	@Autowired
	private ServletContext srvContext;

	@Autowired
	private ModelMapper mM;

	@Autowired
	private BindingUtil mBU;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(
			ModelMap myMap,
			@RequestParam(value = "bool", required = false) String desc,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "orderby", required = false) String orderBy,
			@RequestParam(value = "page", required = false) String page) {

		Double myNumberOfPage;
		int numberOfComputer;
		List<ComputerDto> myListDto;

		Object[] myObjs = mBU.validateParameter(desc, orderBy, page);

		List<Company> myComp = myCompanyServ.selectCompanies();

		numberOfComputer = myComputerServ.countNumberOfComputers(search);

		myNumberOfPage = myComputerServ.numberOfPage(
				myComputerServ.countNumberOfComputers(search),
				NUMBER_OF_COMPUTER_BY_PAGE);

		myListDto = mM.toComputerDtoList(myComputerServ.selectComputers(search,
				myObjs[1] + " " + myObjs[0], myComputerServ.getStartLimit(
						(Integer) myObjs[2], NUMBER_OF_COMPUTER_BY_PAGE),
				NUMBER_OF_COMPUTER_BY_PAGE), myComp);

		myMap.addAttribute("computers", myListDto);
		myMap.addAttribute("search", search);
		myMap.addAttribute("numberOfComputers", numberOfComputer);
		myMap.addAttribute("numberOfPages", myNumberOfPage);
		myMap.addAttribute("orderby", myObjs[1]);
		myMap.addAttribute("bool", myObjs[0]);
		myMap.addAttribute("companies", myComp);
		myMap.addAttribute("currentPage", myObjs[2]);

		return new ModelAndView("dashboard");
	}

}
