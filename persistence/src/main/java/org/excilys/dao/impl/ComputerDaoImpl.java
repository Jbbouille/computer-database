package org.excilys.dao.impl;

import java.util.List;

import org.excilys.dao.ComputerDao;
import org.excilys.exception.DaoException;
import org.excilys.model.Computer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

		List<Computer> myList = mSession
				.createQuery("from Computer where id = :id")
				.setParameter("id", id).list();
		return myList.get(0);
	}

	@Override
	public int countNumberComputers(String myName) throws DaoException {

		Session mSession = sf.getCurrentSession();

		String sql = "SELECT count(*) FROM Computer WHERE name like :computerName";

		Long myCount = (Long) mSession.createQuery(sql)
				.setParameter("computerName", "%" + myName + "%")
				.uniqueResult();
		return myCount.intValue();
	}

	@Override
	public List<Computer> selectComputers(String myLikeParam, String myOrder,
			int startLimit, int numberOfRow) throws DaoException {

		Session mSession = sf.getCurrentSession();

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT cu FROM Computer as cu LEFT JOIN cu.companyId as ca WHERE cu.name like :parmLike OR ca.name like :parmLike ORDER BY ")
				.append(myOrder);

		List<Computer> myList = mSession.createQuery(sql.toString())
				.setParameter("parmLike", "%" + myLikeParam + "%")
				.setFirstResult(startLimit).setMaxResults(numberOfRow).list();
		return myList;
	}
}
