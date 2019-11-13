<!DOCTYPE html>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>


<html>
<head>
<title>attorneyManagement</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<script type="text/javascript" src="/CorisWEB/js/fontawesome.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script type="text/javascript" src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/datetime-moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>



<script type="text/javascript" src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script type="text/javascript" src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<%
	boolean defaultAttnyHist = false;
	boolean defaultAttnyCase = false;
%>
</head>
<body>
	<div class="container-fluid">
		<div class="card mx-2 my-4">
			<div class="card-header font-weight-bold">
				Attorney Management
			</div>
			<div class="card-body">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a href="#attorneyHistory" class="nav-link <%= defaultAttnyHist ? "active" : "" %>" id="byHist" onclick="searchRadioClicked('byHist');clearTableData()" data-toggle="tab">Attorney History</a>
					</li>
					<li class="nav-item">
						<a href="#attorneyCases" class="nav-link <%= defaultAttnyCase ? "active" : "" %>" id="byCase" onclick="searchRadioClicked('byCase');clearTableData()" data-toggle="tab">Attorney Cases</a>
					</li>
				</ul>
				
				<form id="lookup" name="lookup">
				</form>
			</div>
		</div>
	</div>

</body>
</html>