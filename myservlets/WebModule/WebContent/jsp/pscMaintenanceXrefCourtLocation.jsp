<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Court - Location Cross Reference</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body style="height: 100vh;">
	<div class="container-fluid">
		<div class="row std-panel-primary std-panel-md">Court - Location Cross Reference</div>
		<div id="displayDetails"></div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			displayList("/CorisWEB/PSCMaintenanceXrefCourtLocationRedoServlet");
		});
		function displayList() {
			var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationServlet";
			appUX.ajax.call(url, 
				function(err, data, xhr) {
					$("#displayDetails").html(data);
				}, 
				'POST', 
				'mode=displayDetails'
			); 
		}
		function addRecord(courtProfileId, psCourtDefnId) {
			var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationServlet";
			appUX.ajax.call(url, 
				function (err, data, xhr) { 
					/*do nothing*/ 
				}, 
				'POST', 
				'courtProfileId='+courtProfileId+'&psCourtDefnId='+psCourtDefnId+'&mode=add'
			);
		}
		function deleteRecord(psCourtLocationXrefId){
			var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationServlet";
			appUX.ajax.call(url, 
				function (err, data, xhr) { 
					/*do nothing*/ 
				}, 
				'POST', 
				'psCourtLocationXrefId='+psCourtLocationXrefId+'&mode=delete'
			);
		}
	</script>
</body>
</html>
