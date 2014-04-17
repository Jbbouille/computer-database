package org.excilys.model;

import org.joda.time.DateTime;

public class Computer {

	private int id;
	private String name;
	private DateTime introduced;
	private DateTime discontinued;
	private int companyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}

	public DateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = discontinued;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Computer(int id, String name, DateTime introduced, DateTime discontinued,
			int companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Computer comp = (Computer) obj;
		if(this.id == comp.id ) return true;
		else return false;
	}
	
	@Override
	public int hashCode() {
		return this.companyId;
	}
	
	@Override
	public String toString() {
		return "Computer -- id :" + id + ", Name :" + name + ", Introduced :"
				+ introduced + ", Discontinued :" + discontinued;
	}

	public Computer() {
	}
}
