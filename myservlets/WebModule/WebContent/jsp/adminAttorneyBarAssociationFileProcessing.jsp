<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
	String process = (String) request.getAttribute("process");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Attorney Bar Association File Processing</title>
	
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
	<script src="/CorisWEB/js/adminAttorneyBarAssociationFileProcessing.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

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
			appUX.pop.alert("Failure - Utah Bar Association File Processing", 300, "auto", appUX.pop.styles.ERROR);
		</script>
	<% } %>
	<% if ("SUCCESS".equals(process)) { %>
		<script>
			appUX.pop.alert("Success - Utah Bar Association File Processing", 300, "auto", appUX.pop.styles.SUCCESS);
		</script>
	<% } %>

	<div class="container-fluid">
		<form name="adminAttorneyBarAssociationFileProcessing" action="/CorisWEB/AdminAttorneyBarAssociationFileProcessingServlet" method="post" enctype="multipart/form-data">
			<div class="card m-2">
				<div class="card-header text-center">
					<strong>Select Attorney Bar Association File for Processing</strong>
				</div>
				<div class="card-body text-center">
					<strong></strong>
				</div>
				<div class="card-body text-center">
					<input type="file" name="fileName" id="fileNameId" value="" size="75" tabindex="1" />
				</div>
				<div class="mt-5 text-danger text-center">
					<strong>Email Address on non-production servers will NOT be updated</strong>
				</div>
				<div class="mt-5 text-center">
					<strong>CORIS Servers</strong>
				</div>
				<div class="row ml-2 mb-4">
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input" name="productionDistrict" id="productionDistrict" value="" checked>
						<label class="form-check-label" for="productionDistrict">Production District</label>
					</div>
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
				<div class="mt-5 text-center">
					<strong>CARE Servers</strong>
				</div>
				<div class="row ml-2 mb-4">
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input" name = "productionCare" id="productionCare" value="" checked>
						<label class="form-check-label" for="productionCare">Production</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name = "testCare" id="testCare" value="" checked >
						<label class="form-check-label" for="testCare">Test</label>
					</div>
					<div class="form-check form-check-inline text-danger">
						<input type="checkbox" class="form-check-input" name = "developmentCare" id="developmentCare" value="" checked>
						<label class="form-check-label" for="developmentJustice">Development</label>
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
