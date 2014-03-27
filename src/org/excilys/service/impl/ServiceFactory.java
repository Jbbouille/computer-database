package org.excilys.service.impl;

public class ServiceFactory {

	private static ServiceFactory instanceFactory;
	private static ComputerServiceImpl instanceComputerServ;
	private static CompanyServiceImpl instanceCompanyServ;

	public static ServiceFactory getInstance() {
		if (instanceFactory == null)
			instanceFactory = new ServiceFactory();
		return instanceFactory;
	}

	public static ComputerServiceImpl getComputerServ() {
		if (instanceComputerServ == null)
			instanceComputerServ = new ComputerServiceImpl();
		return instanceComputerServ;
	}

	public static CompanyServiceImpl getCompanyServ() {
		if (instanceCompanyServ == null)
			instanceCompanyServ = new CompanyServiceImpl();
		return instanceCompanyServ;
	}

	private ServiceFactory() {
	}
}
