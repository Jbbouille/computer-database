package org.excilys.dto;

import javax.validation.constraints.Size;

import org.excilys.validator.DateIntDisc;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ComputerDto {

	private String id;

	@NotEmpty(message = "{NotEmpty.computerDto.name}")
	@Size(min = 2, message = "{Size.computerDto.name}")
	private String name;

	@DateIntDisc
	private String introduced;

	@DateIntDisc
	private String discontinued;

	@Range(min = -1, max = 42, message = "{Range.computerDto.companyId}")
	private String companyId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public ComputerDto() {
	}
}
