<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>

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
			
			function loadJsp(courtType, intCaseNum){
                $("#loadModalBody").load("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + courtType + "&intCaseNum=" + intCaseNum + "&userId=86203");
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
		  <div class="row">
		  	<div class="col-12 bg-primary">
		  	<h4 class="text-light">Case Eligible for Debt Collection</h4>
		  	</div>
		  </div>
		  <form>
		  	<div class="row">
		        <div class="row col-6 form-group">
		      		<label class="control-label col-4" for="caseNum">Case Num:</label>
		      		<div class="col-8">
		        		<input type="text" class="form-control" id="caseNum" placeholder="Enter case num" name="caseNum">
		        	</div>
		      	</div>
		    
		    	<div class="row col-6 form-group">
		        	<label class="control-label col-4" for="ssn">SSN:</label>
		      		<div class="col-8">
		        	<input type="text" class="form-control" id="ssn" placeholder="Enter SSN" name="ssn">
		      		</div>
		   		 </div>
		   </div>
		   <div class="row">	
		        <div class="row col-6 form-group">
		      		<label class="control-label col-4" for="lastName">Last Name:</label>
		      		<div class="col-8">
		        		<input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName">
		        	</div>
		      	</div>
		   		
		    	<div class="row col-6 form-group">
		        	<label class="control-label col-4" for="firstName">First Name:</label>
		      		<div class="col-8">
		        	<input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName">
		      		</div>
		   		</div>
			</div>
			<div class="row">    
			    <div class="row col-6 form-group">
		        	<label class="control-label col-4" for="judge">Judge:</label>
		      		<div class="col-8">
		        		<select class="form-control" id="judge">
					        <option value="1">All Judges</option>
					        <option value="2">Judge 2</option>
					        <option value="3">Judge 3</option>
					    </select>
		      		</div>
		   		</div>
		   		
		   		
		    	<div class="row col-6 form-group">
		        	<label class="control-label col-4" for="accountType">Account Type:</label>
		      		<div class="col-8">
		        		<select class="form-control" id="accountType">
					        <option value="1">Fine/Fee/Trust</option>
					        <option value="2">Type 2</option>
					        <option value="3">Type 3</option>
					    </select>
		      		</div>
		   		</div>
		   	</div>
		   	<div class="row">
		   		<div class="row col-6 form-group">
		   			<label class="control-label col-4" for="startDate">Sentenced from:</label>
		   			<div class="col-8">
						<div class="row">
						    <div class='col-6 input-group date' id='datetimepicker1'>
			                    <input type='text' class="form-control" />
			                    <span class="input-group-text input-group-addon">
			                        <span class="fa fa-calendar"></span>
			                    </span>
			                </div>
			                
						    <div class='col-6 input-group date' id='datetimepicker2'>
			                    <input type='text' class="form-control" />
			                    <span class="input-group-text input-group-addon">
			                        <span class="fa fa-calendar" ></span>
			                    </span>
			                </div>
						</div>
					</div>
				</div>
				
				<div class="row col-6 form-group">
		        	<label class="control-label col-4" for="overDue">Overdue:</label>
		      		<div class="col-8">
		        		<select class="form-control" id="overDue">
					        <option value="1">Less than 30 days</option>
					        <option value="2">30 days</option>
					        <option value="3">60 days</option>
					        <option value="3">90 days</option>
					    </select>
		      		</div>
		   		</div>
		   	</div>
		   	<div class="row">
		   		<div class="row col-6 form-group">
		        	<label class="control-label col-4" for="balance">Balance:</label>
		      		<div class="col-8">
		        	<input type="text" class="form-control" id="balance" value="100" name="balance">
		      		</div>
		   		</div>
		   		
		   		<div class="row col-6 form-group">
		        	<label class="control-label col-4" for="caseType">Case Type:</label>
		      		<div class="col-8">
		        		<select class="form-control" id="caseType">
					        <option value="1">All Case Types</option>
					        <option value="2">Type 1</option>
					        <option value="3">Type 2</option>
					        <option value="3">Type 3</option>
					    </select>
		      		</div>
		   		</div>
		   	</div>
		   	
		   	<div class="row">
			    <div class="col-12">
			    	<div class="btn-toolbar mb-2 float-right">
			    	<button type="button" class="btn btn-primary btn-sm mr-1">Reset</button>
		    		<button type="button" class="btn btn-primary btn-sm mr-1" data-toggle="modal" data-target="#stack1">Find Case</button>
		    		</div>
		    	</div>
		    </div>	
	    	<div class="row">
		    	<div class="col-12 border border-secondary p-2">
		    	<table id="caseTable" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th>Case</th>
							<th>Name</th>
							<th>Due Date</th>
							<th>Last Pay Date</th>
							<th>Sentence Date</th>
							<th>State Status</th>
							<th>Local Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#" data-toggle="modal" data-target="#detailModal" onclick="loadJsp('D', 5166);">123453</a></td>
							<td>John Doe</td>
							<td>01/01/2018</td>
							<td></td>
							<td>12/01/2017</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><a href="#" data-toggle="modal" data-target="#detailModal" onclick="loadJsp('D', 5166);">523453</a></td>
							<td>Jane Doe</td>
							<td>02/01/2018</td>
							<td></td>
							<td>01/01/2017</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><a href="#" data-toggle="modal" data-target="#detailModal" onclick="loadJsp('D', 5166);">623453</a></td>
							<td>Fang Smith</td>
							<td>05/01/2018</td>
							<td></td>
							<td>02/01/2017</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><a href="#" data-toggle="modal" data-target="#detailModal" onclick="loadJsp('D', 5166);">723453</a></td>
							<td>Kevin Job</td>
							<td>05/11/2018</td>
							<td></td>
							<td>02/13/2017</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><a href="#" data-toggle="modal" data-target="#detailModal" onclick="loadJsp('D', 5166);">823453</a></td>
							<td>Selar Musk</td>
							<td>04/01/2018</td>
							<td></td>
							<td>03/23/2017</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><a href="#" data-toggle="modal" data-target="#detailModal" onclick="loadJsp('D', 5166);">923453</a></td>
							<td>Rick Gates</td>
							<td>03/01/2018</td>
							<td></td>
							<td>12/04/2017</td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
		 </form>
		</div>
		
		<div id="detailModal" class="modal fade">
			<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header" style="background-color: #e5a645; color: white;">
							<h4 class="modal-title">Case Account Details</h4>
				        	<button type="button" class="close" data-dismiss="modal">&times;</button>
				        	
				      	</div>
				      	<div class="modal-body" style="background-color: #e1ffc5;">
				        	<div id="loadModalBody">Some text in the modal.</div>
				      	</div>
				      	<div class="modal-footer" style="background-color: #e1ffc5;">
				        	<button type="button" class="btn btn-secondary btn-sm"  data-dismiss="modal">Close</button>
				      	</div>
					</div>
			</div>
		</div>
	</body>
</html>
