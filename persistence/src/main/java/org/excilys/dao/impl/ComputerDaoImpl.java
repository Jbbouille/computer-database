package org.excilys.dao.impl;

import java.util.List;

import org.excilys.dao.ComputerDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
import org.excilys.model.QCompany;
import org.excilys.model.QComputer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.OrderSpecifier;

@Repository("computerDao")
public class ComputerDaoImpl implements ComputerDao {

	@Autowired
	SessionFactory sf;

	@Override
	public int insertComputer(Computer myComputer) throws DaoException {
		Session mSession = sf.getCurrentSession();
		mSession.persist(myComputer);
		return myComputer.getId();
	}

	@Override
	public void deleteComputer(Computer myComputer) throws DaoException {
		Session mSession = sf.getCurrentSession();
		mSession.delete(myComputer);
	}

	@Override
	public void updateComputer(Computer myComputer) throws DaoException {
		Session mSession = sf.getCurrentSession();
		mSession.merge(myComputer);
	}

	@Override
	public Computer selectComputer(int id) throws DaoException {

		Session mSession = sf.getCurrentSession();

		HibernateQuery query = new HibernateQuery(mSession);
		QComputer computer = QComputer.computer;

		Computer myComputer = query.from(computer).where(computer.id.eq(id))
				.uniqueResult(computer);

		return myComputer;
	}

	@Override
	public int countNumberComputers(String myLikeParam) throws DaoException {
		Session mSession = sf.getCurrentSession();

		HibernateQuery query = new HibernateQuery(mSession);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;

		Long myCount = query
				.from(computer)
				.leftJoin(computer.companyId, company)
				.where(computer.name.like("%" + myLikeParam + "%").or(
						company.name.like("%" + myLikeParam + "%"))).count();

		return myCount.intValue();
	}

	@Override
	public List<Computer> selectComputers(String myLikeParam, String myOrder,
			int startLimit, int numberOfRow) throws DaoException {

		Session mSession = sf.getCurrentSession();

		HibernateQuery query = new HibernateQuery(mSession);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;

		OrderSpecifier<?> orderBy = null;

		String order = myOrder.replace(" false", "").replace(" true", "");

		System.out.println("myorder" + myOrder + " /" + order);

		switch (order) {
		case "name":
			if (myOrder.contains("true")) {
				orderBy = computer.name.desc();
			} else {
				orderBy = computer.name.asc();
			}
			break;
		case "introduced":
			if (myOrder.contains("true")) {
				orderBy = computer.introduced.desc();
			} else {
				orderBy = computer.introduced.asc();
			}
			break;
		case "discontinued":
			if (myOrder.contains("true")) {
				orderBy = computer.discontinued.desc();
			} else {
				orderBy = computer.discontinued.asc();
			}
			break;
		case "ca.name":
			if (myOrder.contains("true")) {
				orderBy = company.name.desc();
			} else {
				orderBy = company.name.asc();
			}
			break;
		case "id":
			orderBy = computer.id.asc();
			break;
		}

		List<Computer> myList = query
				.from(computer)
				.leftJoin(computer.companyId, company)
				.where(computer.name.like("%" + myLikeParam + "%").or(
						company.name.like("%" + myLikeParam + "%")))
				.orderBy(orderBy).offset(startLimit).limit(numberOfRow)
				.list(computer);

		return myList;
	}
}
