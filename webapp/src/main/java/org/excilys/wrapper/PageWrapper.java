package org.excilys.wrapper;

import java.util.ArrayList;

import org.excilys.dto.ComputerDto;
import org.excilys.mapper.ModelMapper;
import org.excilys.model.Company;
import org.excilys.service.CompanyService;
import org.excilys.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

@Controller
public class PageWrapper {
	
	@Autowired
	private ComputerService computerServ;
	
	@Autowired
	private CompanyService companyServ;

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
		this.numberOfComputer = computerServ.countNumberOfComputers(search, bool, orderBy, currentPage, NUMBER_OF_COMPUTER_BY_PAGE);
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
				computerServ.selectComputers(search, bool, orderBy, currentPage, NUMBER_OF_COMPUTER_BY_PAGE), companies);
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public void setCompanies() {
		companies = companyServ.selectCompanies();
	}
}
