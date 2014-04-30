package org.excilys.wrapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Company;
import org.excilys.webservice.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

@Controller
public class PageWrapper {

	private MyService myServ;

	@Autowired
	private ModelMapper mM;

	private final static int NUMBER_OF_COMPUTER_BY_PAGE = 30;

	private boolean bool;
	private String search;
	private String orderBy;
	private int currentPage;
	private PageRequest pageRequest;
	private int numberOfComputer;
	private int numberOfPage;
	private ArrayList<ComputerDto> computerDTOs;
	private ArrayList<Company> companies;

	public PageWrapper() {
	}
	
	public void setMyServ() throws MalformedURLException {
		URL url = new URL("http://localhost:8080/webService/MyService?wsdl");
		QName mName = new QName("http://impl.webservice.excilys.org/", "MyServiceImplService");
		Service service = Service.create(url, mName);
		MyService myService = service.getPort(MyService.class);
		this.myServ = myService;
	}
	
	public boolean isBool() {
		return bool;
	}

	public void setBool(String bool) {
		if (bool != null) {
			if (!bool.equals("false") && !bool.equals("true")) {
				this.bool = false;
			} else {
				this.bool = Boolean.valueOf(bool);
			}
		} else {
			this.bool = false;
		}
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		if (search == null) {
			this.search = "";
		} else {
			this.search = search;
		}
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		if (orderBy != null) {
			switch (orderBy) {
			case "name":
				this.orderBy = "name";
				break;
			case "introduced":
				this.orderBy = "introduced";
				break;
			case "discontinued":
				this.orderBy = "discontinued";
				break;
			case "companyId":
				this.orderBy = "companyId";
				break;
			default:
				this.orderBy = "id";
				break;
			}
		} else {
			this.orderBy = "id";
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		if (currentPage != null) {
			if (currentPage.matches("\\d+")) {
				this.currentPage = Integer.valueOf(currentPage);
			} else {
				this.currentPage = 1;
			}
		} else {
			this.currentPage = 1;
		}
	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public int getNumberOfComputer() {
		return numberOfComputer;
	}

	public void setNumberOfComputer() {
		this.numberOfComputer = myServ.countNumberOfComputers(search, bool, orderBy, currentPage, NUMBER_OF_COMPUTER_BY_PAGE);
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage() {
		this.numberOfPage = (int) (Math.ceil(numberOfComputer
				/ NUMBER_OF_COMPUTER_BY_PAGE) + 1);
	}

	public ArrayList<ComputerDto> getComputerDTOs() {
		return computerDTOs;
	}

	public void setComputerDTOs() {
		this.computerDTOs = mM.toComputerDtoList(
				myServ.selectComputers(search, bool, orderBy, currentPage, NUMBER_OF_COMPUTER_BY_PAGE), companies);
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public void setCompanies() {
		companies = myServ.selectCompanies();
	}
}
