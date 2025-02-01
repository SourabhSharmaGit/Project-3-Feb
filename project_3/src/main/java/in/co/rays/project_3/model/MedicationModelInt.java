package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.MedicationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface MedicationModelInt {

	public long add(MedicationDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(MedicationDTO dto)throws ApplicationException;
	public void update(MedicationDTO dto)throws ApplicationException,DuplicateRecordException;
	public MedicationDTO findByPK(long pk)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(MedicationDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(MedicationDTO dto)throws ApplicationException;
}
