package org.excilys.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


public class ComputerDto {

	private String id;
	
	@NotEmpty(message="{NotEmpty.computerDto.name}") @Size(min=2, message="{Size.computerDto.name}")
	private String name;
	
	@Pattern(regexp="^|(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$", message="{Pattern.computerDto.introduced}")
	private String introduced;

	@Pattern(regexp="^|(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$", message="{Pattern.computerDto.discontinued}")
	private String discontinued;
	
	@Range(min = -1, max = 42, message="{Range.computerDto.companyId}")
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
