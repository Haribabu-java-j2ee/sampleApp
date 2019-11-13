<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="java.util.List"%>
<%
	List<RoleDefnBO> results = (List<RoleDefnBO>) request.getAttribute("results");
	String courtType = (String) request.getAttribute("courtType");
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
			parent.showRoles("<%= courtType %>");
		}
		
		function add() {
			parent.modifyRole('add', 'Add Role', 0, '<%= courtType %>');
		}
		
		function publish() {
			parent.publishRole('publish', 'Publish Roles', 0, '<%= courtType %>');
		}
		
	</script>

</head>
<body>
	
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-body">
				<div id="displayResults"></div>
			</div>
			<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
				<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
				<button type="button" class="btn btn-primary btn-sm float-right ml-2" title="Add" onclick="add();">Add</button>
				<button type="button" class="btn btn-primary btn-sm float-left" title="Publish" onclick="publish()">Publish</button>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
			displayResults();
		});
		
		function displayResults() {
			appUX.ajax.call("/CorisWEB/AdminRoleServlet", 
				function (err, data, xhr) { 
					$("#displayResults").html(data); 
				}, 
				'POST', 
				'mode=displayResults&courtType=<%= courtType %>'
			); 
		}
	</script>
</body>
</html>
