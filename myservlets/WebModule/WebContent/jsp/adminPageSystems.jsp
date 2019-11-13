<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO"%>
<%@page import="java.util.List"%>
<%
	List<PagesystemXrefBO> results = (List<PagesystemXrefBO>) request.getAttribute("results");
	String courtType = (String) request.getAttribute("courtType");
	int pageId = TextUtil.getParamAsInt(request, "pageId");
	String pageDescription = (pageId > 0) ? new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).find().getDescription() : "";
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Areas</title>
	
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
			parent.showPages("<%= courtType %>");
			
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function refresh() {
			parent.showPageSystems('Page Areas - <%= pageDescription %>', '<%= courtType %>', <%= pageId %>);
		}
		
		function add() {
			parent.modifyPageSystem('add', 'Add Page Area', '<%= courtType %>', <%= pageId %>, 0);
		}
		
		function edit(pageSystemXrefId, description) {
			parent.modifyPageSystem('edit', 'Edit Page Area - ' + description, '<%= courtType %>', <%= pageId %>, pageSystemXrefId);
		}
		
		function remove(pageSystemXrefId, description) {
			if (description != '')
				description = " (" + description + ")";
				 
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this page area reference" + description + "?", "Yes", "No", 450, 'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call('/CorisWEB/AdminPageSystemServlet?mode=delete&courtType=<%= courtType %>&pageSystemXrefId=' + pageSystemXrefId, function (err, data, xhr) { refresh(); });
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
					<table id="pageSystemTable" class="table table-hover table-condensed table-highlight" style="width:100%">
						<thead>
							<tr>
								<th>ID</th>
								<th>Area Description</th>
								<th>Parent Menu</th>
								<th>Display Order</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="resultBody"> 
						<%
							if (results != null && results.size() > 0) {
								for (PagesystemXrefBO pageSystem : results){
						%>
									<tr>
										<td><a href="#" data-target="#detailModal" onclick="edit(<%= pageSystem.getPagesystemxrefid() %>, '<%= pageSystem.get(SystemareaDefnBO.DESCRIPTION) %>');"><%= pageSystem.getPagesystemxrefid() %></a></td>
										<td><%= pageSystem.get(SystemareaDefnBO.DESCRIPTION) %></td>
										<td><%= TextUtil.print(pageSystem.get("description")) %></td>
										<td><%= pageSystem.getDisplayorder() %></td>
										<td><button type="button" class="btn btn-primary btn-sm float-right" title="Delete" onclick="remove(<%= pageSystem.getPagesystemxrefid() %>, '<%= pageSystem.get(SystemareaDefnBO.DESCRIPTION) %>');">Delete</button></td>
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
