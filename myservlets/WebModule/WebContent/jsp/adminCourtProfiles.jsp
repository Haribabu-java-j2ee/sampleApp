<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="java.util.List"%>
<%
	List<ProfileBO> results = (List<ProfileBO>) request.getAttribute("results");
	String courtType = (String) request.getAttribute("courtType");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Court Profiles</title>
	
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
			parent.showProfiles("<%= courtType %>");
		}
		
		function add() {
			parent.modifyCourtProfile('add', 'Add Profile', 0, '<%= courtType %>');
		}
		
		function edit(locnCode, courtType, title) {
			parent.modifyCourtProfile('edit', 'Edit Profile - ' + title, locnCode, courtType);
		}
		
		function remove(profileId) {
			var corn = pop.confirm("Warning", "Are you sure that you want to delete this profile?", "Yes", "No", 300, 'auto', pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call('/CorisWEB/AdminCourtProfileServlet?mode=delete&courtType=<%= courtType %>&profileId=' + profileId, function (err, data, xhr) { refresh(); });
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
					<table id="profileTable" class="table table-hover table-condensed table-highlight" style="width:100%">
						<thead>
							<tr>
								<th>Locn Code</th>
								<th>Court Type</th>
								<th>Court Title</th>
								<th>City</th>
							</tr>
						</thead>
						<tbody id="resultBody"> 
						<%
							if (results != null && results.size() > 0) {
								for (ProfileBO profileBO : results){
						%>
									<tr>
										<td><a href="#" data-target="#detailModal" onclick="edit('<%= profileBO.getLocnCode() %>', '<%= profileBO.getCourtType() %>', '<%= profileBO.getCourtTitle() %>');"><%= profileBO.getLocnCode() %></a></td>
										<td><%= profileBO.getCourtType() %></td>
										<td><%= profileBO.getCourtTitle() %></td>
										<td><%= profileBO.getCity() %></td>
									</tr> 
						<%
								} 
							} 
						%>
						</tbody>
					</table>
					<br>
					<br>
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
