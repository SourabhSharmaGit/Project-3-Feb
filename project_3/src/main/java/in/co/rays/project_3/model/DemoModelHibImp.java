package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.DemoDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public abstract class DemoModelHibImp implements DemoModelInt {

	@Override
	public long add(DemoDTO dto) throws ApplicationException, DuplicateRecordException {

		DemoDTO existDto = null;

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);

			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in  DemoDTO Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(DemoDTO dto) throws ApplicationException {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in  DemoDTO Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(DemoDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;

		/*
		 * Transaction tx = null; DemoDTO exesistDto = findByLogin(dto.getLogin());
		 * 
		 * if (exesistDto != null && exesistDto.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("Login id already exist"); }
		 * 
		 */ Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in  DemoDTO update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public DemoDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		DemoDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (DemoDTO) session.get(DemoDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(DemoDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banks list");
		} finally {
			session.close();
		}

		return list;
	}

	/*
	 * @Override public List list(int pageNo, int pageSize) throws
	 * ApplicationException { // TODO Auto-generated method stub return null; }
	 */
	@Override
	public List search(DemoDTO dto, int pageNo, int pageSize) throws ApplicationException {
		System.out.println("Search method starts");

		Session session = null;
		ArrayList<DemoDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(DemoDTO.class);
			if (dto != null) {

				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}
				if (dto.getPreLoad() != null && dto.getPreLoad().length() > 0) {
					criteria.add(Restrictions.like("preLoad", dto.getPreLoad() + "%"));
				}
				if (dto.getDemoDate() != null) {
					criteria.add(Restrictions.eq("demoDate", dto.getDemoDate()));
				}
				if (dto.getQuantity() > 0) {
					criteria.add(Restrictions.eq("quantity", dto.getQuantity()));
				}

			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<DemoDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in  DemoDTO search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(DemoDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

}