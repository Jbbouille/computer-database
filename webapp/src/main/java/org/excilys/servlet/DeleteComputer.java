package org.excilys.servlet;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.excilys.model.Computer;
import org.excilys.webservice.MyService;
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

	private MyService myServ;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(ModelMap myMap,
			@RequestParam(value = "id", required = false) Integer id)
			throws MalformedURLException {

		setMyServ();

		Computer myComputer = myServ.selectComputer(id);

		if (myComputer == null) {
			myMap.addAttribute("deleteError",
					"Could Not delete Computer that not exist");
		} else {
			myServ.deleteComputer(myComputer);
		}

		return new ModelAndView("redirect:dashboard");
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("exceptionError");
		return model;
	}

	public void setMyServ() throws MalformedURLException {
		URL url = new URL("http://localhost:8080/webService/MyService?wsdl");
		QName mName = new QName("http://impl.webservice.excilys.org/",
				"MyServiceImplService");
		Service service = Service.create(url, mName);
		MyService myService = service.getPort(MyService.class);
		this.myServ = myService;
	}
}
