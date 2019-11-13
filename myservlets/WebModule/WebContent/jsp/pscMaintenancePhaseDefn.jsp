<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Phase Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Phase Definitions</strong>
			</div>
			<div class="card-body">
				<form class="form-inline" id="search" name="search" action="#" method="post">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="displayRemoved" name="displayRemoved" value="Display Removed" checked onclick="displayList()">
						<label class="form-check-label" for="defaultCheck1">Display Disabled</label>
					</div>
				</form>
			</div>
			<div class="card-header card-footer">
				<strong>Results</strong> <button onclick="add()" class="btn btn-secondary btn-sm float-right">Add</button>
			</div>
			<div class="card-body">
				<div class="table-responsive" id="displayResults"></div>
			</div>
		</div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			displayList();
		});
		function displayList() {
			var displayRemovedToggle = $('#displayRemoved:checked').val();
			var displayRemoved = "false"; //don't display the disabled
			if(displayRemovedToggle) {
				displayRemoved = "true"; //display the disabled
			}
			appUX.ajax.call("/CorisWEB/PSCMaintenancePhaseDefnServlet?showRemoved=" + displayRemoved + "&mode=list", 
					function (err, data, xhr){ 
							$("#displayResults").html(data);
					}
			);
		}
		function add() {
			var cornId = "modalCornPrimary";
			var title = "Add";
			var url = "/CorisWEB/PSCMaintenancePhaseDefnServlet?mode=add"; 
			var width = 600;
			var height = 400;
			var style = appUX.pop.styles.PRIMARY;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
	</script>
</body>
</html>
