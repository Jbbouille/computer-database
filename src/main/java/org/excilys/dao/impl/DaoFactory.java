package org.excilys.dao.impl;

public enum DaoFactory {
	INSTANCE;
	
	public static DaoFactory getInstance() {
		return DaoFactory.INSTANCE;
	}

	public static ComputerDaoImpl getInstanceComputerDao() {
		return ComputerDaoImpl.INSTANCE;
	}
	
	public static CompanyDaoImpl getInstanceCompanyDao() {
		return CompanyDaoImpl.INSTANCE;
	}
	
	public static LogDaoImpl getInstanceLogDao(){
		return LogDaoImpl.INSTANCE;
	}
}
