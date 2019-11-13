<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO"%>
<%@page import="java.util.List"%>
<%
	List<PageroleXrefBO> results = (List<PageroleXrefBO>) request.getAttribute("results");
	String courtType = (String) request.getAttribute("courtType");
	int pageId = TextUtil.getParamAsInt(request, "pageId");
	String pageDescription = (pageId > 0) ? new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).find().getDescription() : "";
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Roles</title>
	
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
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function refresh() {
			parent.showPageRoles('Page Roles - <%= pageDescription %>', '<%= courtType %>', <%= pageId %>);
		}
		
		function add() {
			parent.modifyPageRole('add', 'Add Page Role', '<%= courtType %>', <%= pageId %>, 0);
		}
		
		function edit(pageRoleXrefId, description) {
			parent.modifyPageRole('edit', 'Edit Page Role - ' + description, '<%= courtType %>', <%= pageId %>, pageRoleXrefId);
		}
		
		function remove(pageRoleXrefId, description) {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this page role reference (" + description + ")?", "Yes", "No", 500, 'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call('/CorisWEB/AdminPageRoleServlet?mode=delete&pageId=<%= pageId %>&courtType=<%= courtType %>&pageRoleXrefId=' + pageRoleXrefId, function (err, data, xhr) { refresh(); });
				}
				corn.close();
			});
		}
		
	</script>

</head>
<body>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div style="width:100%">
					<table id="pageRoleTable" class="table table-hover table-condensed table-highlight" style="width:100%">
						<thead>
							<tr>
								<th>ID</th>
								<th>Description</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="resultBody"> 
						<%
							if (results != null && results.size() > 0) {
								for (PageroleXrefBO pageRole : results) {
									String description = (String) pageRole.get(RoleDefnBO.DESCRIPTION);
						%>
									<tr>
										<td><a href="#" data-target="#detailModal" onclick="edit(<%= pageRole.getPagerolexrefid() %>, '<%= description %>');"><%= pageRole.getPagerolexrefid() %></a></td>
										<td><%= description %></td>
										<td><button type="button" class="btn btn-primary btn-sm float-right" title="Delete" onclick="remove(<%= pageRole.getPagerolexrefid() %>, '<%= description %>');">Delete</button></td>
									</tr> 
						<%
								} 
							} 
						%>
						</tbody>
					</table>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right" title="Add" onclick="add()">Add</button>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
%>
</body>
</html>
