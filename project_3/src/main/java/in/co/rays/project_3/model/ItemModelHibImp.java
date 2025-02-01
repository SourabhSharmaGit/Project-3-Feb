package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.CourseDTO;
import in.co.rays.project_3.dto.ItemDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ItemModelHibImp implements ItemModelInt {

	public long add(ItemDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		System.out.println(dto.getId());
		Session session = null;
		Transaction tx = null;
		ItemModelInt itemModel = ModelFactory.getInstance().getItemModel();

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in item Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	public void delete(ItemDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in item Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(ItemDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		ItemModelInt itemModel = ModelFactory.getInstance().getItemModel();
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in item update" + e.getMessage());
		} finally {
			session.close();
		}
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
			Criteria criteria = session.createCriteria(ItemDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  item list");
		} finally {
			session.close();
		}
		return list;
	}

	public List search(ItemDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(ItemDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		System.out.println("search method starts");
		Session session = null;
		ArrayList<ItemDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ItemDTO.class);
			System.out.println("createCriteria");

			if (dto != null) {
				if (dto.getTitle() != null) {
					criteria.add(Restrictions.like("title", dto.getTitle()+"%"));
				}
				if (dto.getOverview() != null) {
					criteria.add(Restrictions.like("overview", dto.getOverview()+"%"));
				}
				if (dto.getCost() != null) {
					criteria.add(Restrictions.eq("cost", dto.getCost()));
				}
				if (dto.getPurchaseDate() != null) {
					criteria.add(Restrictions.eq("purchaseDate", dto.getPurchaseDate()));
				}
				if (dto.getCategory() != null) {
					criteria.add(Restrictions.like("category", dto.getCategory()+"%"));
				}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				System.out.println("pagination");

				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<ItemDTO>) criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in  item search");
		} finally {
			System.out.println("finally");

			session.close();
		}
		if (list.isEmpty()) {
			System.out.println("list is empty");
		} else {
			System.out.println("list is NOT empty");
		}
		System.out.println("return list;");

		return list;
	}
	
	//////////////////////////////////////////////////////

	public List search2(ItemDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		System.out.println("search 2 method starts");
		Session session = null;
		ArrayList<ItemDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ItemDTO.class);
			
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<ItemDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
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


	public ItemDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ItemDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (ItemDTO) session.get(ItemDTO.class, pk);

		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting item by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public ItemDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
