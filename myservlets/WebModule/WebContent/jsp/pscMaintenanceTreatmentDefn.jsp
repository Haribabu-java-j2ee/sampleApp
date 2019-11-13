<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Treatment Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Treatment Definitions</strong>
			</div>
			<div class="card-body">
				<form class="form-inline" id="search" name="search" action="#" method="post">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="displayDisabled" name="displayDisabled" value="Display Disabled" onclick="displayList()">
						<label class="form-check-label" for="displayDisabled">Display Disabled</label>
					</div>
				</form>
			</div>
			<div class="card-header card-footer text-dark">
				Results <button onclick="add()" id="addBtn" class="btn btn-secondary btn-sm float-right">Add</button>
			</div>
			<div class="card-body">
				<div id="displayResults"></div>
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
		function add() {
			var cornId = "modalCornPrimary";
			var title = "Add";
			var url = "/CorisWEB/PSCMaintenanceTreatmentDefnServlet?code=&descr=&mode=add";
			var width = 600;
			var height = 400;
			var style = appUX.pop.styles.PRIMARY;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		function displayList() {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = "false"; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = "true"; //display the disabled
			}
			appUX.ajax.call("/CorisWEB/PSCMaintenanceTreatmentDefnServlet", 
				function (err, data, xhr) { 
					$("#displayResults").html(data);
				}, 
				'POST', 
				'displayResults=true&displayDisabled='+disabled
			);
		}
	</script>
</body>
</html>
