package org.excilys.servlet;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Company;
import org.excilys.webservice.MyService;
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

	private MyService myServ;

	@Autowired
	private ModelMapper mM;

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(
			@Valid @ModelAttribute("computerDto") ComputerDto myComputer,
			BindingResult result) throws MalformedURLException {

		setMyServ();
		
		ModelAndView mav = null;

		if (result.hasErrors()) {
			mav = new ModelAndView("modifyComputer");
			mav.addObject("companies", myServ.selectCompanies());
			mav.addObject("computerDto", myComputer);
			System.out.println(result.toString());
		} else {
			mav = new ModelAndView("redirect:dashboard");
			myServ.updateComputer(mM.toComputer(myComputer));
		}

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest req, ModelMap map) throws MalformedURLException {

		setMyServ();
		
		ModelAndView mav = null;

		Integer id = null;
		if (req.getParameter("id") != null && !(req.getParameter("id").equals(""))) {

			mav = new ModelAndView("modifyComputer");
			id = Integer.valueOf(req.getParameter("id"));
			List<Company> myList = myServ.selectCompanies();

			map.addAttribute("companies", myList);
			mav.addObject("computerDto", mM.toComputerDto(
					myServ.selectComputer(id), myList));

		} else {
			mav = new ModelAndView("redirect:dashboard");
		}

		return mav;
	}

	public void setMyServ() throws MalformedURLException {
		URL url = new URL("http://localhost:8080/webService/MyService?wsdl");
		QName mName = new QName("http://impl.webservice.excilys.org/", "MyServiceImplService");
		Service service = Service.create(url, mName);
		MyService myService = service.getPort(MyService.class);
		this.myServ = myService;
	}
	
}
