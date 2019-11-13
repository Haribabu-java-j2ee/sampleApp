<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO"%>
<%@page import="java.util.List"%>
<%
	List<AccesslevelDefnBO> results = (List<AccesslevelDefnBO>) request.getAttribute("results");
	String courtType = (String) request.getAttribute("courtType");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Access Levels</title>
	
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
			parent.showAccessLevels("<%= courtType %>");
		}
		
		function add() {
			parent.modifyAccessLevel('add', 'Add Access Level', 0, '<%= courtType %>');
		}
		
		function edit(accessLevelId, description) {
			parent.modifyAccessLevel('edit', 'Edit Access Level - ' + description, accessLevelId, '<%= courtType %>');
		}
		
		function remove(accessLevelId, description) {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this access level (" + description + ")?", "Yes", "No", 450, 'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call('/CorisWEB/AdminAccessLevelServlet?mode=delete&accessLevelId=' + accessLevelId + "&courtType=<%= courtType %>", function (err, data, xhr) { refresh(); });
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
					<table id="accessLevelTable" class="table table-hover table-condensed table-highlight" style="width:100%">
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
								for (AccesslevelDefnBO accessLevel : results){
						%>
									<tr>
										<td><a href="#" data-target="#detailModal" onclick="edit(<%= accessLevel.getAccesslevelid() %>, '<%= accessLevel.getDescription() %>');"><%= accessLevel.getAccesslevelid() %></a></td>
										<td><%= accessLevel.getDescription() %></td>
										<td><button type="button" class="btn btn-primary btn-sm float-right" title="Delete" onclick="remove(<%= accessLevel.getAccesslevelid() %>, '<%= accessLevel.getDescription() %>');">Delete</button></td>
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
					<button type="button" class="btn btn-primary btn-sm float-right" title="Add" onclick="add();">Add</button>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
%>
</body>
</html>
