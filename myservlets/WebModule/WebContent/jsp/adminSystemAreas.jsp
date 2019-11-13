<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO"%>
<%@page import="java.util.List"%>
<%
	List<SystemareaDefnBO> results = (List<SystemareaDefnBO>) request.getAttribute("results");
	String courtType = (String) request.getAttribute("courtType");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define System Areas</title>
	
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
			parent.showSystemAreas("<%= courtType %>");
		}
		
		function add() {
			parent.modifySystemArea('add', 'Add System Area', 0, '<%= courtType %>');
		}
		
		function edit(areaId, description) {
			parent.modifySystemArea('edit', 'Edit System Area - ' + description, areaId, '<%= courtType %>');
		}
		
		function remove(areaId, description) {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this system area (" + description + ")?", "Yes", "No", 450, 'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call('/CorisWEB/AdminSystemAreaServlet?mode=delete&courtType=<%= courtType %>&areaId=' + areaId, function (err, data, xhr) { refresh(); });
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
					<table id="systemAreaTable" class="table table-hover table-condensed table-highlight" style="width:100%">
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
								for (SystemareaDefnBO area : results){
						%>
									<tr>
										<td><a href="#" data-target="#detailModal" onclick="edit(<%= area.getAreaid() %>, '<%= area.getDescription() %>');"><%= area.getAreaid() %></a></td>
										<td><%= area.getDescription() %></td>
										<td><button type="button" class="btn btn-primary btn-sm float-right" title="Delete" onclick="remove(<%= area.getAreaid() %>, '<%= area.getDescription() %>');">Delete</button></td>
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
