<%@page import="in.co.rays.project_3.dto.ItemDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.controller.ItemListCtl"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Item List</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/welcome.jpg');
	background-size: 100%;
	padding-top: 60px;
	background-repeat: no-repeat;
	background-size: cover;
}

.p1 {
	padding: 4px;
	width: 200px;
	font-size: bold;
}

.text {
	text-align: center;
}
</style>
</head>
<%@include file="Header.jsp"%>
<body class="hm">
	<div>
		<form class="pb-5" action="<%=ORSView.ITEM_LIST_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.ItemDTO"
				scope="request"></jsp:useBean>
			<%
				Map map = (Map) request.getAttribute("categoryList");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				
				List list = ServletUtility.getList(request);

				Iterator<ItemDTO> it = list.iterator();
				if (list.size() != 0) {
			%>	
			<center>
				<h1 class="text-dark font-weight-bold pt-4">
					<u>ITEM LIST</u>
				</h1>
			</center>
			<br>
			<div class="row">
				<div class="col-md-4"></div>
				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>

			<div class="row">

				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<input type="text" name="title" placeholder="Title Name"
						class="form-control"
						value="<%=ServletUtility.getParameter("title", request)%>">
				</div>
				&emsp;
				<div class="col-sm-2">
					<input type="text" name="overview" placeholder="Enter Overview"
						class="form-control"
						value="<%=ServletUtility.getParameter("overview", request)%>">
				</div>
				&emsp;
				<div class="col-sm-2">
					<input type="text" name="cost" placeholder="Enter Cost"
						class="form-control"
						value="<%=ServletUtility.getParameter("cost", request)%>">
				</div>
				&emsp;
				<div class="col-sm-2">
					<input type="date" name="purchaseDate" class="form-control"
						value="<%=ServletUtility.getParameter("purchaseDate", request)%>">
				</div>
				&emsp;
				<div class="col-sm-2"><%=HTMLUtility.getListMap("category", String.valueOf(dto.getCategory()), map)%></div>

				
				<div class="col-sm-1"></div>
			</div>
			
			</br>
			<div class="text-center">
				<input type="submit" class="btn btn-primary btn-md"
						style="font-size: 15px" name="operation"
						value="<%=ItemListCtl.OP_SEARCH%>">&emsp; 
				<input type="submit" class="btn btn-dark btn-md" style="font-size: 15px"
						name="operation" value="<%=ItemListCtl.OP_RESET%>"></div>

			</br>
			<div style="margin-bottom: 20px;" class="table-responsive">
				<table class="table table-bordered table-light table-hover">
					<thead>
						<tr style="background-color: #46aed7;">

							<th width="8%"  class="text"><input type="checkbox" id="select_all"
								name="Select" class="text"> Select All</th>
							<th width="5%" class="text">S.NO</th>
							<th width="15%" class="text">Title</th>
							<th width="15%" class="text">Overview</th>
							<th width="20%" class="text">Cost</th>
							<th width="10%" class="text">Purchase Date</th>
							<th width="10%" class="text">Category</th>
							<th width="5%" class="text">Edit</th>
						</tr>
					</thead>
					<%
						while (it.hasNext()) {
								dto = it.next();

					%>
					<tbody>
						<tr>
							<td align="center">
							<input type="checkbox" class="checkbox" name="ids" 
							value="<%=dto.getId()%>"></td>
							<td class="text"><%=index++%></td>
							<td class="text"><%=dto.getTitle()%></td>
							<td class="text"><%=dto.getOverview()%></td>
							<td class="text"><%=dto.getOverview()%></td>
							<td class="text"><%=DataUtility.getDateString(dto.getPurchaseDate())%></td>
							<td class="text"><%=map.get(Integer.parseInt(dto.getCategory()))%></td>
							<td class="text"><a href="ItemCtl?id=<%=dto.getId()%>">Edit</a></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						value="<%=ItemListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td><input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=ItemListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						class="btn btn-danger btn-md" style="font-size: 17px"
						value="<%=ItemListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						style="padding: 5px;" value="<%=ItemListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
				<tr></tr>
			</table>

			<%
				}
				if (list.size() == 0) {
			%>
			<center>
				<h1 style="font-size: 40px; color: #162390;">Item List</h1>
			</center>
			</br>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>

				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div style="padding-left: 48%;">
					<input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=ItemListCtl.OP_BACK%>">
				</div>

				<div class="col-md-4"></div>
			</div>

			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>

	</div>

</body>
<%@include file="FooterView.jsp"%>
</html>