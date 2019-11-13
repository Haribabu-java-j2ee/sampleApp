<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%
	List<PageDefnBO> results = (List<PageDefnBO>) request.getAttribute("results");
	HashMap<Integer, String> parentMenus = (HashMap<Integer, String>) request.getAttribute("parentMenus");
	String courtType = (String) request.getAttribute("courtType");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Pages</title>
	
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
			parent.showPages("<%= courtType %>");
		}
		
		function add() {
			parent.modifyPage('add', 'Add Page', 0, '<%= courtType %>');
		}
		
		function edit(pageId, description) {
			parent.modifyPage('edit', 'Edit Page - ' + description, pageId, '<%= courtType %>');
		}
		
		function showRoles(pageId, description) {
			parent.showPageRoles('Page Roles - ' + description, '<%= courtType %>', pageId);
		}
		
		function showAreas(pageId, description) {
			parent.showPageSystems('Page Areas - ' + description, '<%= courtType %>', pageId);
		}
		
		function publish() {
			parent.publishPage('publish', 'Publish Pages', 0, '<%= courtType %>');
		}
		
		function remove(pageId) {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this page?", "Yes", "No", 300, 'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call('/CorisWEB/AdminPageServlet?mode=delete&courtType=<%= courtType %>&pageId=' + pageId, function (err, data, xhr) { refresh(); });
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
					<table id="pageTable" class="table table-hover table-condensed table-highlight" style="width:100%">
						<thead>
							<tr>
								<th>ID</th>
								<th>Description</th>
								<th>Parent Menu</th>
								<th>Is Available?</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="resultBody"> 
						<%
							if (results != null && results.size() > 0) {
								for (PageDefnBO pageBO : results){
						%>
									<tr>
										<td><a href="#" data-target="#detailModal" onclick="edit(<%= pageBO.getPageid() %>, '<%= pageBO.getDescription() %>');"><%= pageBO.getPageid() %></a></td>
										<td><%= pageBO.getDescription() %></td>
										<td><%= TextUtil.print(parentMenus.get((Integer) pageBO.get(PagesystemXrefBO.PARENTPAGESYSTEMXREFID))) %></td>
										<td><%= pageBO.getIsavailable() %></td>
										<td>
											<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Delete" onclick="remove(<%= pageBO.getPageid() %>);">Delete</button>
											<button type="button" class="btn btn-primary btn-sm float-right ml-2" title="Roles" onclick="showRoles(<%= pageBO.getPageid() %>, '<%= pageBO.getDescription() %>');">Roles</button>
											<button type="button" class="btn btn-primary btn-sm float-right" title="Areas" onclick="showAreas(<%= pageBO.getPageid() %>, '<%= pageBO.getDescription() %>');">Areas</button>
										</td>
									</tr> 
						<%
								} 
							} 
						%>
							<tr><td colspan="5">&nbsp;</td></tr> <!-- make table scrollable all the way to the bottom -->
							<tr><td colspan="5">&nbsp;</td></tr>
						</tbody>
					</table>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right" title="Add" onclick="add();">Add</button>
					<button type="button" class="btn btn-primary btn-sm float-left" title="Publish" onclick="publish()">Publish</button>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
%>
</body>
</html>
