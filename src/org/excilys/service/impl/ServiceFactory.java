package org.excilys.service.impl;

public enum ServiceFactory {
INSTANCE;

	public static ServiceFactory getInstance() {
		return ServiceFactory.INSTANCE;
	}

	public static ComputerServiceImpl getComputerServ() {
		return ComputerServiceImpl.INSTANCE;
	}

	public static CompanyServiceImpl getCompanyServ() {
		return CompanyServiceImpl.INSTANCE;
	}
}
