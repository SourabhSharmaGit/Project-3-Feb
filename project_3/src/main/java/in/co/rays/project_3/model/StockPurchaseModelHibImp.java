package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.StockPurchaseDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class StockPurchaseModelHibImp implements StockPurchaseModelInt {


	public long add(StockPurchaseDTO dto) throws ApplicationException, DuplicateRecordException {

		System.out.println("add method");
		// TODO Auto-generated method stub

		
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);
			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in StockPurchase Add " + e.getMessage());
		} finally {
			session.close();
		}
		/* log.debug("Model add End"); */
		return dto.getId();

	}

	public void delete(StockPurchaseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
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
			throw new ApplicationException("Exception in StockPurchase Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(StockPurchaseDTO dto) throws ApplicationException, DuplicateRecordException {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	public StockPurchaseDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		StockPurchaseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (StockPurchaseDTO) session.get(StockPurchaseDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting StockPurchase by pk");
		} finally {
			session.close();
		}

		return dto;
	}
	

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			
			Criteria criteria = session.createCriteria(StockPurchaseDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  StockPurchases list");
		} finally {
			session.close();
		}

		return list;
	}

	public List search(StockPurchaseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(StockPurchaseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		System.out.println("search method starts");
		Session session = null;
		ArrayList<StockPurchaseDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(StockPurchaseDTO.class);
			if (dto != null) {
				System.out.println("dto is not null");

				if (dto.getId() != null) {
					System.out.println("id = "+dto.getId());
					criteria.add(Restrictions.like("id", dto.getId()));

				}
				if (dto.getQuantity() > 0) {
					System.out.println("Q. = "+dto.getQuantity());

					criteria.add(Restrictions.eq("quantity", dto.getQuantity()));
				}

				if (dto.getPurchasePrice() > 0) {
					System.out.println("pp = "+dto.getPurchasePrice());

					criteria.add(Restrictions.eq("purchasePrice", dto.getPurchasePrice()));
				}
				
				if (dto.getPurchaseDate() != null && dto.getPurchaseDate().getDate() > 0) {
					System.out.println("pd = "+dto.getPurchaseDate());

					criteria.add(Restrictions.eq("purchaseDate", dto.getPurchaseDate()));
				}
				if (dto.getOrderType() != null && dto.getOrderType().length() > 0) {
					System.out.println("Order = "+dto.getOrderType());
					criteria.add(Restrictions.eq("orderType", dto.getOrderType()));
				}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<StockPurchaseDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search" + e.getMessage());
		
		} finally {

			session.close();
		}
		if (list.isEmpty()) {
			System.out.println("list is empty");
		} else {
			System.out.println("list is NOT empty");
		}
		return list;
	}

}
