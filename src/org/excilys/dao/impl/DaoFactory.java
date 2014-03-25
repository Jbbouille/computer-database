package org.excilys.dao.impl;

public class DaoFactory {
	
	private static DaoFactory instanceFactory;
	private static ComputerDaoImpl instanceComputerDao;
	private static CompanyDaoImpl instanceCompanyDao;


	public static DaoFactory getInstance() {
		if (instanceFactory == null) instanceFactory = new DaoFactory();
		return instanceFactory;
	}
	
	private DaoFactory() {
	}

	public static ComputerDaoImpl getInstanceComputerDao() {
		if (instanceComputerDao == null)
			instanceComputerDao = new ComputerDaoImpl();
		return instanceComputerDao;
	}
	
	public static CompanyDaoImpl getInstanceCompanyDao() {
		if (instanceCompanyDao == null)
			instanceCompanyDao = new CompanyDaoImpl();
		return instanceCompanyDao;
	}
}
