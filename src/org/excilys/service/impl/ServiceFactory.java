package org.excilys.service.impl;

public enum ServiceFactory {
INSTANCE;

	public ServiceFactory getInstance() {
		return ServiceFactory.INSTANCE;
	}

	public ComputerServiceImpl getComputerServ() {
		return ComputerServiceImpl.INSTANCE;
	}

	public CompanyServiceImpl getCompanyServ() {
		return CompanyServiceImpl.INSTANCE;
	}
}
