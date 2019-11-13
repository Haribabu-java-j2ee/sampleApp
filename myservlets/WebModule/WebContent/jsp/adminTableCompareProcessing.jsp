<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.county.CountyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
	String process = (String) request.getAttribute("process");
	String results = (String) request.getAttribute("results");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title> Table Compare Processing</title>
	
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
	<script src="/CorisWEB/js/adminTableCompareProcessing.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

	<script>

		$(document).ready(function(){
			<% // make sure the form focus is the first form field %>
			$("#fileName").focus();
		});
	
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
	</script>
</head>
<body>
	<% if ("FAILURE".equals(process)) { %>
		<script>
			appUX.pop.alert("Failure - Table Compare Processing (District Production Primary)", 300, "auto", appUX.pop.styles.ERROR);
		</script>
	<% } %>
	<% if ("SUCCESS".equals(process)) { %>
		<script>
			appUX.pop.alert("Success - Table Compare Processing (District Production Primary)", 300, "auto", appUX.pop.styles.SUCCESS);
		</script>
	<% } %>

	<div class="container-fluid">
		<form name="adminTableCompareProcessing" action="/CorisWEB/AdminTableCompareProcessingServlet" method="post" >
			<div class="card m-2">
				<div class="card-header text-center">
					<strong> Table Compare Processing</strong>
				</div>
				<div class="card-body text-center">
					<strong></strong>
				</div>
				<div class="mt-5 text-center">
					<strong>(District Production Primary)</strong>
				</div>
				<div class="row ml-2 mb-4">
					<div class="form-check form-check-inline text-center" >
						<input type="checkbox" class="form-check-input" name="productionDistrict" id="productionDistrict" value="" checked disabled>
						<label class="form-check-label" for="productionDistrict">Production District</label>
					</div>
				</div>
				
				<div class="mt-5 text-danger text-center">
					<strong>Email Address on non-production servers will NOT be updated</strong>
				</div>
				
				<div class="mt-5 text-center">
					<strong>Tables</strong>
				</div>
				<div class="row ml-2 mb-4">
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input" name="attorney" id="attorney" value="" checked>
						<label class="form-check-label" for="Attorney">Attorney</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input" name="country" id="country" value="" checked>
						<label class="form-check-label" for="Country">Country</label>
					</div>
				</div>
				<div class="mt-5 text-center">
					<strong>Coris Servers</strong>
				</div>
				<div class="row ml-2 mb-4">
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input" name = "productionJustice" id="productionJustice" value="" checked>
						<label class="form-check-label" for="productionJustice">Production Justice</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name="training" id="training" value="" checked>
						<label class="form-check-label" for="training">Training</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name="verify" id="verify" value="" checked>
						<label class="form-check-label" for="verify">Verify</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name="testDistrict" id="testDistrict" value="" checked>
						<label class="form-check-label" for="testDistrict">Test District</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name = "testJustice" id="testJustice" value="" checked>
						<label class="form-check-label" for="testJustice">Test Justice</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name="development" id="development" value="" checked>
						<label class="form-check-label" for="development">Development</label>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
		<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
		<button type="button" class="btn btn-primary btn-sm float-right" title="Process" onclick="validate();">Process</button>
	</div>
	
</body>
</html>
