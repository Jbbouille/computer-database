package org.excilys.dao;

import org.excilys.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ComputerDao extends CrudRepository<Computer, Integer> {

	Page<Computer> findByNameContainingOrCompanyIdNameContaining(String name,
			String companyName, Pageable page);
}
