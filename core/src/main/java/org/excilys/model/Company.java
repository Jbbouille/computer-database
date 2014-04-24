package org.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "company")
public class Company {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
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
		if (obj == null)
			return false;
		Company comp = (Company) obj;
		if (this.id == comp.id)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return name;
	}

	public Company() {
	}
}
