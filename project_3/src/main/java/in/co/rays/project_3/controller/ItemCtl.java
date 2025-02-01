package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.ItemDTO;
import in.co.rays.project_3.dto.ItemDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.ItemModelInt;
import in.co.rays.project_3.model.ItemModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * user functionality controller.to perform add,delete and update operation
 * 
 * @author Sourabh Sharma
 *
 */
@WebServlet(urlPatterns = { "/ctl/ItemCtl" })
public class ItemCtl extends BaseCtl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ItemCtl.class);

	protected void preload(HttpServletRequest request) {
		ItemModelInt model = ModelFactory.getInstance().getItemModel();
		try {
			/* Map<Integer, String> map = new HashMap<Integer, String>(); */
			Map<Integer, String> map = new HashMap();
			
			map.put(1, "Electronic");
			map.put(2, "Stationery");
			map.put(3, "Grocery");
			
			request.setAttribute("categoryList", map);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("-------------validate started-------------");

		if (DataValidator.isNull(request.getParameter("title"))) {
			request.setAttribute("title", PropertyReader.getValue("error.require", "Title"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("title"))) {
			request.setAttribute("title", "Title must contains alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("overview"))) {
			request.setAttribute("overview", PropertyReader.getValue("error.require", "Overview"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("cost"))) {
			request.setAttribute("cost", PropertyReader.getValue("error.require", "Cost"));
			pass = false;
		} /*
			 * else if (!DataValidator.isLong(request.getParameter("cost"))) {
			 * request.setAttribute("cost", "Please Enter Valid Cost"); pass = false; }
			 */

		if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "Purchase Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.date", "Purchase Date"));
			pass = false;
		}
		
		  if (DataValidator.isNull(request.getParameter("category"))) {
		  request.setAttribute("category", PropertyReader.getValue("error.require","Category")); pass = false; }
		 
		
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		ItemDTO dto = new ItemDTO();

	
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setTitle(DataUtility.getString(request.getParameter("title")));
		dto.setOverview(DataUtility.getString(request.getParameter("overview")));
		dto.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));
		dto.setCost(DataUtility.getDouble(request.getParameter("cost")));
		dto.setCategory(DataUtility.getString(request.getParameter("category")));

		populateBean(dto, request);
		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));

		ItemModelInt model = ModelFactory.getInstance().getItemModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			ItemDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		
		ItemModelInt model = ModelFactory.getInstance().getItemModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			ItemDTO dto = (ItemDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Item is successfully Update", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setSuccessMessage("Item is successfully saved", request);
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			ItemDTO dto = (ItemDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.ITEM_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ITEM_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ITEM_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("ItemCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ITEM_VIEW;
	}

}
