<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.application.ApplicationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO"%>
<%
	String courtType = (String) request.getAttribute("courtType");

	int roleId = URLEncryption.getParamAsInt(request, "roleId");
	String roleDescription = URLEncryption.getParamAsString(request, "roleDescription");
	
	List<RoleapplicXrefBO> xrefList = (List<RoleapplicXrefBO>) request.getAttribute("xrefList");
	List<RoleDefnBO> roleDefnList = (List<RoleDefnBO>) request.getAttribute("roleDefnList");
	List<ApplicationBO> applicationList = (List<ApplicationBO>) request.getAttribute("applicationList");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Admin - Define Roles</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-body">
				<form id="actionForm" action="" method="post">
		
				<table class="table">
					<thead class="bg-dark text-white">
						<tr>
							<% int colspan = 2; %>
							<th>Role</th>
							<th class="sorter-false">Applications</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><%= roleDescription %></td>
							<td>
								<table class="table table-borderless">
									<tbody>
										<tr>
											<td>
												<% for (ApplicationBO applicationBO : applicationList) { %>
													<div class="form-check">
														<input type="checkbox" class="form-check-input" id="application-<%= applicationBO.getApplicId() %>" name="application-<%= applicationBO.getApplicId() %>" 
												<%
														if (xrefList.size() > 0) {
															for (RoleapplicXrefBO xrefBO : xrefList) {
																if (xrefBO.getApplicId() == applicationBO.getApplicId() && xrefBO.getRoleId() == roleId){
												%>
																	checked
												<%
																}
															}
														}
												%>
														>
														<label class="form-check-label"><%= applicationBO.getApplicName() %></label>
													</div>
												<% } %>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" id="cancelBtn" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel">Cancel</button>
					<button type="button" id="updateBtn" class="btn btn-primary btn-sm float-right" title="Update">Update</button>
				</div>
				</form>

			</div>
		</div>
	</div>
	
	<%
		// cleanup
		xrefList = null;
		roleDefnList = null;
		applicationList = null;
	%>
	
	<script>
		$(document).ready(function(){
			$('#cancelBtn').on('click', function(){
				closePop();
			});	
			$('#updateBtn').on('click', function(){
				var data = $('#actionForm').serialize();
				saveRecords(data, $('#roleId').val());
			});
		});
		
		function saveRecords(data, roleId) {
			var url = "/CorisWEB/AdminRoleServlet";
			appUX.ajax.call(url, 
				function (err, data, xhr) { 
					parent.displayList();
					setTimeout(function(){ 
						parent.$('#div' + roleId).show();
						closePop(); 
					}, 500);
				}, 
				'POST', 
				'roleId=<%= roleId %>&courtType=<%= courtType %>&mode=save&' + data
			);
			parent.showRoles('<%= courtType %>');
			closePop();
		}
		
		function closePop() {
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
	</script>
</body>
</html>
