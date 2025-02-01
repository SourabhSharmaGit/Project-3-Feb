package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.MedicationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public abstract class MedicationModelHibImp implements MedicationModelInt {

	@Override
	public long add(MedicationDTO dto) throws ApplicationException, DuplicateRecordException {

		MedicationDTO existDto = null;

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
			throw new ApplicationException("Exception in  MedicationDTO Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(MedicationDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in  MedicationDTO Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(MedicationDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;

		/*
		 * Transaction tx = null; MedicationDTO exesistDto = findByLogin(dto.getLogin());
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
			throw new ApplicationException("Exception in  MedicationDTO update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public MedicationDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		MedicationDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (MedicationDTO) session.get(MedicationDTO.class, pk);

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
			Criteria criteria = session.createCriteria(MedicationDTO.class);
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
	public List search(MedicationDTO dto, int pageNo, int pageSize) throws ApplicationException {
		System.out.println("Search method starts");

		Session session = null;
		ArrayList<MedicationDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(MedicationDTO.class);
			if (dto != null) {

				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
					System.out.println("id");
				}
				if (dto.getFullName() != null && dto.getFullName().length() > 0) {
					criteria.add(Restrictions.like("fullName", dto.getFullName() + "%"));					System.out.println("id");
					System.out.println("fullname");

				}
				if (dto.getIllness() != null && dto.getIllness().length() > 0) {
					criteria.add(Restrictions.eq("illness", dto.getIllness()));
					System.out.println("illness");

				}
				if (dto.getPrescriptionDate() != null) {
					criteria.add(Restrictions.eq("prescriptionDate", dto.getPrescriptionDate()));
					System.out.println("prescriptionDate = "+dto.getPrescriptionDate());

				}
				if (dto.getDosage() > 0) {
					criteria.add(Restrictions.eq("dosage", dto.getDosage()));
					System.out.println("dosage");

				}

			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<MedicationDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in  MedicationDTO search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(MedicationDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

}