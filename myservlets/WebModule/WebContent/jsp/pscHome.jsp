<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Home</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	
	<div id="displayHeader"></div>
	
	<div class="container-fluid">

		<div class="row std-panel-primary std-panel-md">Home</div>
		
		<div class="row std-section">
			<p>Welcome to Problem Solving Courts! Please click on a menu item above to continue.</p>
		</div>
		
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){
		
			//display the header
			displayHeader();

		});
		
		function displayHeader() {
			appUX.ajax.call("/CorisWEB/PSCHomeServlet", 
				function (err, data, xhr) { 
					$("#displayHeader").html(data);
				}, 
				'POST', 
				'displayHeader=true'
			);
		}
	</script>
</body>
</html>
