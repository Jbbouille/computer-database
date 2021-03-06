package org.excilys.dto;

import javax.validation.constraints.Size;

import org.excilys.validator.DateIntDisc;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * The DTo of computer containing the name of the company instead of the id
 * 
 * @author excilys
 * 
 */
public class ComputerDto {

	/**
	 * Paramater of the DTO validate by the validator
	 */
	private String id;

	@NotEmpty(message = "{NotEmpty.computerDto.name}")
	@Size(min = 2, message = "{Size.computerDto.name}")
	private String name;

	@DateIntDisc
	private String introduced;

	@DateIntDisc
	private String discontinued;

	@Range(min = -1, max = 42, message = "{Range.computerDto.companyId}")
	private int companyId;

	private String companyName;

	/**
	 * Getter, Setter & constructor of the bean
	 * 
	 */
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyId) {
		this.companyName = companyId;
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
	
	@Override
	public String toString() {
		return "Name :"+name+" intro : "+introduced+" dicon : "+discontinued+" companyId : "+companyId+" companyName : "+companyName;
	}
}
