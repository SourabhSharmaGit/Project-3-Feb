package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PurchaseOrderDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PurchaseOrderModelHibImp implements PurchaseOrderModelInt {


	@Override
	public long add(PurchaseOrderDTO dto) throws ApplicationException, DuplicateRecordException {

		 PurchaseOrderDTO existDto = null;

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
			throw new ApplicationException("Exception in   PurchaseOrderDTO Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(PurchaseOrderDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in   PurchaseOrderDTO Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update( PurchaseOrderDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;

		/*
		 * Transaction tx = null;  PurchaseOrderDTO exesistDto = findByLogin(dto.getLogin());
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
			throw new ApplicationException("Exception in   PurchaseOrderDTO update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public  PurchaseOrderDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		 PurchaseOrderDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = ( PurchaseOrderDTO) session.get( PurchaseOrderDTO.class, pk);

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
			Criteria criteria = session.createCriteria( PurchaseOrderDTO.class);
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
	public List search( PurchaseOrderDTO dto, int pageNo, int pageSize) throws ApplicationException {
		System.out.println("Search method starts");

		Session session = null;
		ArrayList< PurchaseOrderDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria( PurchaseOrderDTO.class);
			if (dto != null) {

				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				
				if (dto.getProduct() != null && dto.getProduct().length() > 0) {
					criteria.add(Restrictions.like("product", dto.getProduct() + "%"));
				}
				if (dto.getOrderDate() != null) {
					criteria.add(Restrictions.eq("orderDate", dto.getOrderDate()));
				}
				if (dto.getQuantity() > 0) {
					criteria.add(Restrictions.eq("quantity", dto.getQuantity()));
				}
				if (dto.getTotalCost() >0) {
					criteria.add(Restrictions.eq("totalCost", dto.getTotalCost()));
				}

			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList< PurchaseOrderDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in   PurchaseOrderDTO search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search( PurchaseOrderDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}



}
