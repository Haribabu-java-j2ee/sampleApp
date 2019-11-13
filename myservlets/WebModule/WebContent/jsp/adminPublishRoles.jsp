<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.application.ApplicationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO"%>
<%@page import="java.util.List"%>
<%
	List<String> dbServers = (List<String>) request.getAttribute("dbServers");
	
	String fromServer = (String) request.getAttribute("fromServer");
	String toServer = (String) request.getAttribute("toServer");
	
	List<RoleDefnBO> fromRoleList = (List<RoleDefnBO>) request.getAttribute("fromRoleDefnList");
	List<RoleapplicXrefBO> fromXrefList = (List<RoleapplicXrefBO>) request.getAttribute("fromXrefList");
	
	List<RoleDefnBO> toRoleList = (List<RoleDefnBO>) request.getAttribute("toRoleDefnList");
	List<RoleapplicXrefBO> toXrefList = (List<RoleapplicXrefBO>) request.getAttribute("toXrefList");
	
	String errorMessage = (String) request.getAttribute(Constants.ERROR_MESSAGE);
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Publish Roles</title>
	
	<!-- styles -->
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<!-- scripts -->
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<!-- this is used for the popcorn window -->
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

	<script src="/CorisWEB/js/admin.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

	<script>
		<% if (!TextUtil.isEmpty(errorMessage)) { %>
			appUX.pop.declare("Error", '<%= errorMessage %>', 300, 'auto', appUX.pop.styles.DANGER);
		<% } %>

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function compare() {
			var fromServer = $('#fromServer').val();
			var toServer = $('#toServer').val();
			appUX.ajax.call("/CorisWEB/AdminPublishRolesServlet", 
				function (err, data, xhr) { 
					$("#displayResults").html(data);
				}, 
				'POST', 
				'mode=compare&fromServer=' + fromServer + '&toServer=' + toServer
			); 
		}
		
		function publish(roleId) {
			appUX.ajax.call("/CorisWEB/AdminPublishRolesServlet", 
				function (err, data, xhr) { 
					$("#displayResults").html(data);
				}, 
				'POST', 
				'mode=publish&fromServer=<%= fromServer %>&toServer=<%= toServer %>&roleId=' + roleId
			); 
		}
		 
		function deleteRole(roleId, description) {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this role (" + description + ") from <%= toServer %>?", "Yes", "No", 550, 'auto', appUX.pop.styles.INFO,
				function(result){
					if (result) {
						appUX.ajax.call("/CorisWEB/AdminPublishRolesServlet", 
							function (err, data, xhr) { 
								$("#displayResults").html(data); 
							},
							'POST',
							'mode=delete&fromServer=<%= fromServer %>&toServer=<%= toServer %>&roleId=' + roleId
						);
					}
					corn.close();
				}
			);
		}
	</script>

</head>
<body>
	<div id="displayResults" class="container-fluid">
		<div class="card m-2">
			<div>
				<table class="table table-borderless" style="width: 100%;" id="tblRoleCompare">
					<thead><tr><th style="width: 46%;">From Server</th><th style="width: 2%;"></th><th style="width: 46%;">To Server</th><th style="width: 6%;"></th></tr></thead><tbody>
					<tr>
						<td>
							<div class="form-group">
								<select class="form-control" id="fromServer">
									<% for (String server : dbServers) { %>
										<option <%= server.equals(fromServer) ? "selected" : "" %> value="<%= server %>"><%= server %></option>
									<% } %>
								</select>
							</div>
						</td>
						<td></td>
						<td>
							<div class="form-group">
								<select class="form-control" id="toServer">
									<% for (String server : dbServers) { %>
										<option <%= server.equals(toServer) ? "selected" : "" %> value="<%= server %>"><%= server %></option>
									<% } %>
								</select>
							</div>
						</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" title="Compare" onclick="javascript:compare();">Compare</button>
						</td>
					</tr>
					<tr>
						<td>
							<table>
						<%
							if (fromRoleList != null)
								for (RoleDefnBO role : fromRoleList) {
						%>
									<tr><td>
										<table>
											<tr>
												<td width="325px" nowrap>
													<%= role.getDescription() %> (<%= role.getRoleId() %>)
												</td>
												<td align="right" height="24">
													<button type="button" class="btn btn-primary btn-sm" title="Publish" onclick="javascript:publish(<%= role.getRoleId() %>);">></button>
												</td>
											</tr>
											<tr>
												<td>
													<table>
														<%	if (fromXrefList != null) 
																for (RoleapplicXrefBO roleApllicXrefBO : fromXrefList) {
																	if (role.getRoleId() == roleApllicXrefBO.getRoleId()) {
														%>
																		<tr>
																			<td width="10%"></td>
																			<td width="90%"><%=  roleApllicXrefBO.get(ApplicationBO.APPLICNAME) %> (<%=  roleApllicXrefBO.get(ApplicationBO.APPLICID) %>)</td>
																		</tr>
														<%
																	}
																}
														%>
													</table>
												</td>
												<td></td>
											</tr>
										</table>
									</td></tr>
						<%
								}
						%>
							</table>
						</td>
						<td></td>
						<td>
							<table>
						<%
							if (toRoleList != null)
								for (RoleDefnBO role : toRoleList) {
						%>
									<tr><td>
										<table>
											<tr>
												<td width="325px" nowrap>
													<%= role.getDescription() %> (<%= role.getRoleId() %>) <i onclick="deleteRole('<%= role.getRoleId() %>', '<%= role.getDescription() %>')" title="Delete" style="cursor: pointer;" class="text-warning far fa-trash-alt fa-lg fa-fw"></i>
												</td>
												<td align="right" height="24"></td>
											</tr>
											<tr>
												<td>
													<table>
														<%	if (toXrefList != null) 
																for (RoleapplicXrefBO roleApllicXrefBO : toXrefList) {
																	if (role.getRoleId() == roleApllicXrefBO.getRoleId()) {
														%>
																		<tr>
																			<td width="10%"></td>
																			<td width="90%"><%=  roleApllicXrefBO.get(ApplicationBO.APPLICNAME) %> (<%=  roleApllicXrefBO.get(ApplicationBO.APPLICID) %>)</td>
																		</tr>
														<%
																	}
																}
														%>
													</table>
												</td>
											</tr>
										</table>
									</td></tr>
						<%
								}
						%>
							</table>
						</td>
						<td></td>
					</tr>
				</tbody></table>
				<br><br><br>
			</div>
			<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
				<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
			</div>
		</div>
	</div>
</body>
</html>
