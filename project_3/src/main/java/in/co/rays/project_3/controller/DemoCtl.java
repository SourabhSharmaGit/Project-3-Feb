package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto. DemoDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.DemoModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "DemoCtl", urlPatterns = { "/ctl/DemoCtl" })
public class  DemoCtl extends BaseCtl{
	@Override
	protected void preload(HttpServletRequest request) {

	
		Map<Integer, String> map = new HashMap();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		
		
		request.setAttribute("imp", map);
	    
	}
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;


		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", " Only numbers are allowed");
			System.out.println(pass);
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("preLoad"))) {
			request.setAttribute("preLoad", PropertyReader.getValue("error.require", "preLoad"));
			pass = false;		
		}
		/*
			 * else if (!DataValidator.isFloat(request.getParameter("paymentterm"))) {
			 * request.setAttribute("paymentterm", "Only numbers are allowed"); pass =
			 * false; }
			 */
//		if (!OP_UPDATE.equalsIgnoreCase(request.getParameter("operation"))) {
			
			
			if (DataValidator.isNull(request.getParameter("demoDate"))) {
				request.setAttribute("demoDate", PropertyReader.getValue("error.require", "demoDate"));
				pass = false;
			
			}
					
			if (DataValidator.isNull(request.getParameter("quantity"))) {
				request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
				pass = false;


			} else if (!DataValidator.isInteger(request.getParameter("quantity"))) {
				request.setAttribute("quantity", " Only numbers are allowed");
				System.out.println(pass);
				pass = false;

			}
			
				
		
	
			return pass;
}

//		return pass;
//		}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		 DemoDTO dto = new  DemoDTO();
		
         
   
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		 dto.setName(DataUtility.getString(request.getParameter("name")));
		 dto.setPreLoad(DataUtility.getString(request.getParameter("preLoad")));
         dto.setDemoDate(DataUtility.getDate(request.getParameter("demoDate")));

         dto.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
         

        populateBean(dto,request);
		

		return dto;

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		DemoModelInt model = ModelFactory.getInstance().getDemoModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			 DemoDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		DemoModelInt model = ModelFactory.getInstance().getDemoModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			 DemoDTO dto = ( DemoDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);
					
					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {
					
					try {
						 model.add(dto);
					 
						 ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);
				
				
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			 DemoDTO dto = ( DemoDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView. DEMO_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. DEMO_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. DEMO_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}
	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView. DEMO_VIEW;
	}

	


}