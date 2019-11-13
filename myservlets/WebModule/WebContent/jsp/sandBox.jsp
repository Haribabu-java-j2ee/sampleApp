<!DOCTYPE HTML>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.constants.Constants"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%


%>
<html lang="en">
	<head>
		<title>Case Eligible for Debt Collection</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
		<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
		<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
		<script type="text/javascript" src="/CorisWEB/js/fontawesome.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
		<script type="text/javascript" src="/CorisWEB/js/jquery-3.2.0.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
  		<script type="text/javascript" src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
  		<script type="text/javascript" src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
  		<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
  		<script type="text/javascript" src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
		<style>
		.modal-lg {
    		max-width: 80% !important;
		}
		</style>
		<script>
			$(document).ready(function() {
				$('#caseTable').DataTable();
				 $('#datetimepicker1').datetimepicker({ 
                	format: 'MM/DD/YYYY'
                });
                $('#datetimepicker2').datetimepicker({ 
                	format: 'MM/DD/YYYY'
                });
			} );
			
			function loadJsp(){
				console.log("ok");
                $("#loadModalBody").load("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=D&intCaseNum=5166&userId=86203");
			}
		</script>
	</head>
	<body>
		<div class="container-fluid mb-3">
			<nav class="navbar navbar-dark bg-dark">
				 <a class="navbar-brand" href="#">CORIS</a>
			</nav>
		</div>	
		
		<div class="container">
		  <h4 class="bg-primary text-light">Case Eligible for Debt Collection</h4>
		
			    <div class="col-12">
			    	<div class="btn-toolbar mb-2">
			    	
		    		<button class="btn btn-primary btn-large" data-toggle="modal" href="#stack1">View Demo</button>
		    		
		    		</div>
		    	</div>
	
		    	
		    	<div id="stack1" class="modal" >
			    	<div class="modal-dialog modal-lg">
						<div class="modal-content">
						  <div class="modal-header">
						    <h3>Stack One</h3>
						    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
						  </div>
						  <div class="modal-body">
						    <p>One fine body?</p>
						    <p>One fine body?</p>
						    <p>One fine body?</p>
						    <input type="text" data-tabindex="1">
						    <input type="text" data-tabindex="2">
						    <button class="btn" data-toggle="modal" href="#stack2">Launch modal</button>
						  </div>
						  <div class="modal-footer">
						    <button type="button" data-dismiss="modal" class="btn">Close</button>
						  </div>
						</div>
					</div>
				</div>
				 
				<div id="stack2" class="modal" tabindex="-1">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
						  <div class="modal-header">
						     <h3>Stack Two</h3>
						     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
						  </div>
						  <div class="modal-body">
						    <p>One fine body?</p>
						    <p>One fine body?</p>
						    <input type="text" data-tabindex="1">
						    <input type="text" data-tabindex="2">
						  </div>
						  <div class="modal-footer">
						    <button type="button" data-dismiss="modal" class="btn">Close</button>
						  </div>
						</div>
					</div>
				</div>

		</div>
	</body>
</html>
