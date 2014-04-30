package org.excilys.webservice;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.excilys.model.Company;
import org.excilys.model.Computer;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MyService {

	@WebMethod
	void insertComputer(Computer myComputer);

	@WebMethod
	void deleteComputer(Computer myComputer);

	@WebMethod
	void updateComputer(Computer myComputer);

	@WebMethod
	Computer selectComputer(Integer id);

	@WebMethod
	ArrayList<Computer> selectComputers(String myLikeParam, Boolean bool,
			String orderBy, int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE);

	@WebMethod
	int countNumberOfComputers(String myName, Boolean bool, String orderBy,
			int currentPage, int NUMBER_OF_COMPUTER_BY_PAGE);

	@WebMethod
	ArrayList<Company> selectCompanies();
}
