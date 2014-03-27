package org.excilys.model;

public class Company {
	
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Company comp = (Company) obj;
		if(this.id == comp.id ) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "Company -- id :"+id+", name :"+name;
	}

	public Company() {
	}
}
