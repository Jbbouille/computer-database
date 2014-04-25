package org.excilys.dao.impl;

import java.util.List;

import org.excilys.dao.ComputerDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

		List<Computer> myList = mSession.createCriteria(Computer.class)
				.add(Restrictions.idEq(id)).list();

		return myList.get(0);
	}

	@Override
	public int countNumberComputers(String myLikeParam) throws DaoException {

		Session mSession = sf.getCurrentSession();

		int myCount = ((Long) mSession
				.createCriteria(Computer.class)
				.createAlias("companyId", "ca", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(
						(Restrictions.ilike("name", "%" + myLikeParam + "%")),
						(Restrictions.ilike("ca.name", "%" + myLikeParam + "%"))))
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();

		return myCount;
	}

	@Override
	public List<Computer> selectComputers(String myLikeParam, String myOrder,
			int startLimit, int numberOfRow) throws DaoException {

		Session mSession = sf.getCurrentSession();

		Order orderBy;

		if (myOrder.contains("desc")) {
			orderBy = Order.desc(myOrder.replace(" desc", ""));
		} else {
			orderBy = Order.asc(myOrder);
		}

		List<Computer> myList = mSession
				.createCriteria(Computer.class)
				.createAlias("companyId", "ca", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(
						(Restrictions.ilike("name", "%" + myLikeParam + "%")),
						(Restrictions.ilike("ca.name", "%" + myLikeParam + "%"))))
				.addOrder(orderBy).setFirstResult(startLimit)
				.setMaxResults(numberOfRow).list();

		return myList;
	}
}
