<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>

<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Coris - Admin</title>
	
	<%//styles %>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<%//scripts %>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script src="/CorisWEB/js/admin.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script>
	
		$(document).ready(function(){
			displayHeader();   // display the header
		});
		
		function displayHeader() {
	    	appUX.ajax.call('/CorisWEB/AdminServlet?displayHeader=true', function (err, data, xhr) { $("#displayHeader").html(data); });
		}

	</script>
</head>
<body>
	
	<!-- header -->
	<div id="displayHeader">
		<% // the header from the adminHeader.jsp will be displayed here %>
	</div>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">

			<div class="row std-panel-primary std-panel-md">Admin</div>
			
			<div class="row std-section">
				<p></p>
			</div>
			
		</div>
	</main>
	
</body>
</html>
