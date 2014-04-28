package org.excilys.dao;

import org.excilys.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDao extends CrudRepository<Company, Integer> {
}
