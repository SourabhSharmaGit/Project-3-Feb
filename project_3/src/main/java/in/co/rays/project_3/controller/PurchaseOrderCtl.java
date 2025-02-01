package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto. PurchaseOrderDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PurchaseOrderModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "PurchaseOrderCtl", urlPatterns = { "/ctl/PurchaseOrderCtl" })
public class  PurchaseOrderCtl extends BaseCtl{
	@Override
	protected void preload(HttpServletRequest request) {

	
		Map<Integer, String> map = new HashMap();
		map.put(1, "Cooler");
		map.put(2, "Heater");
		map.put(3, "AC");
		
		
		request.setAttribute("imp", map);
	    
	}
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("totalCost"))) {
			request.setAttribute("totalCost", PropertyReader.getValue("error.require", "totalCost"));
			pass = false;


		} else if (!DataValidator.isInteger(request.getParameter("totalCost"))) {
			request.setAttribute("totalCost", " Only numbers are allowed");
			System.out.println(pass);
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "product"));
			pass = false;		
		}
		/*
			 * else if (!DataValidator.isFloat(request.getParameter("paymentterm"))) {
			 * request.setAttribute("paymentterm", "Only numbers are allowed"); pass =
			 * false; }
			 */
//		if (!OP_UPDATE.equalsIgnoreCase(request.getParameter("operation"))) {
			
			
			if (DataValidator.isNull(request.getParameter("orderDate"))) {
				request.setAttribute("orderDate", PropertyReader.getValue("error.require", "orderDate"));
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
		 PurchaseOrderDTO dto = new  PurchaseOrderDTO();
		
         
   
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		 dto.setProduct(DataUtility.getString(request.getParameter("product")));
         dto.setOrderDate(DataUtility.getDate(request.getParameter("orderDate")));
         dto.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		 dto.setTotalCost(DataUtility.getInt(request.getParameter("totalCost")));
		 
		 long cdt=DataUtility.getLong(request.getParameter("myDate"));

	    	if(cdt>0){
	    	dto.setOrderDate(DataUtility.getTimeStamp(cdt));
	    	}else{
	    	dto.setOrderDate(DataUtility.getCurrentTimeStamp());
	    	}
        populateBean(dto,request);
		

		return dto;

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PurchaseOrderModelInt model = ModelFactory.getInstance().getPurchaseOrderModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			 PurchaseOrderDTO dto;
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
		PurchaseOrderModelInt model = ModelFactory.getInstance().getPurchaseOrderModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			 PurchaseOrderDTO dto = ( PurchaseOrderDTO) populateDTO(request);
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

			 PurchaseOrderDTO dto = ( PurchaseOrderDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView. PURCHASE_ORDER_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. PURCHASE_ORDER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. PURCHASE_ORDER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}
	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView. PURCHASE_ORDER_VIEW;
	}

	


}