<!DOCTYPE html>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	
	<script>
	
		$(document).ready(function(){
		
			// display the header
			displayHeader();

		});
		
		function displayHeader() {
	    	$.ajax({
				type: "POST",
				url: "/CorisWEB/LocalMaintenanceServlet",
				data: { displayHeader: "true" },
				success: function(result){
					$("#displayHeader").html(result);
				}
			});
		}
	</script>
</head>
<body>
	
	<!-- header -->
	<div id="displayHeader">
		<%//the header from the ProblemSolvingCourtsHeader.jsp will be displayed here %>
	</div>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">

			<div class="row std-panel-primary std-panel-md">CORIS > Local Maintenance</div>
			
			
		</div>
	</main>
	
</body>
</html>
