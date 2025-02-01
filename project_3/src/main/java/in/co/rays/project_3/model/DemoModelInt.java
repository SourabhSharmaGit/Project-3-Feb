package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.DemoDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface DemoModelInt {
	
	public long add(DemoDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(DemoDTO dto)throws ApplicationException;
	public void update(DemoDTO dto)throws ApplicationException,DuplicateRecordException;
	public DemoDTO findByPK(long pk)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(DemoDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(DemoDTO dto)throws ApplicationException;

}
