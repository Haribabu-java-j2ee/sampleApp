<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	int pageId = TextUtil.getParamAsInt(request, "pageId");
	String pageDescription = (pageId > 0) ? new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).find().getDescription() : "";
	PageroleXrefBO pageRoleXref = (PageroleXrefBO) request.getAttribute("pageRole");
	List<RoleDefnBO> availableRoles = (List<RoleDefnBO>) request.getAttribute("availableRoles");
	List<AccesslevelDefnBO> availableAccessLevels = (List<AccesslevelDefnBO>) request.getAttribute("availableAccessLevels");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Page Roles</title>
	
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

<script>

	$(document).ready(function(){
		<% // make sure the form focus is the first form field %>
		$("#roleId").focus();
	});

	function closePop(){
		var corn = appUX.pop.getSelfHandle();
		corn.close();
	}
	
	function refresh() {
		parent.showPageRoles('Page Roles - <%= pageDescription %>', '<%= courtType %>', <%= pageId %>);
	}
		
	function updateForm(roleId, accessLevelId) {
		appUX.ajax.call('/CorisWEB/AdminPageRoleServlet?mode=update&pageRoleXrefId=<%= pageRoleXref != null ? pageRoleXref.getPagerolexrefid() : 0 %>&pageId=<%= pageId %>&courtType=<%= courtType %>&roleId=' + roleId + '&accessLevelId=' + accessLevelId, function (err, data, xhr) { refresh(); closePop(); });
	}
	
</script>

</head>
<body>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div class="input-group m-4">
					<label for="isAvailable" style="width: 20%; text-align: right; padding-right: 3px;">Role <span class="text-danger">*</span></label>
					<select class="form-control" id="roleId" name="roleId">
						<% 
							if (availableRoles != null && availableRoles.size() > 0) { 
								for (RoleDefnBO role : availableRoles) {
									String selected = pageRoleXref != null && pageRoleXref.getRoleId() == role.getRoleId() ? "selected" : "";
						%>
									<option <%= selected %> value="<%= role.getRoleId() %>"><%= role.getDescription() %></option>
						<% 
								} 
							}
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="isAvailable" style="width: 20%; text-align: right; padding-right: 3px;">Access Level <span class="text-danger">*</span></label>
					<select class="form-control" id="accessLevelId" name="accessLevelId">
						<% 
							if (availableAccessLevels != null && availableAccessLevels.size() > 0) { 
								for (AccesslevelDefnBO level : availableAccessLevels) {
									String selected = pageRoleXref != null && pageRoleXref.getAccesslevelid() == level.getAccesslevelid() ? "selected" : "";
						%>
									<option <%= selected %> value="<%= level.getAccesslevelid() %>"><%= level.getDescription() %></option>
						<% 
								} 
							}
						%>
					</select>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml" title="Update" onclick="updateForm($('#roleId').val(), $('#accessLevelId').val());">Update</button>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
	mode = null;
%>
</body>
</html>
