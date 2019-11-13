<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
String mode = TextUtil.getParamAsString(request, "mode");
int barNum = TextUtil.getParamAsInt(request, "barNum");
String barState = TextUtil.getParamAsString(request, "barState");
String attyLastName = TextUtil.getParamAsString(request, "attyLastName");
String attyFirstName = TextUtil.getParamAsString(request, "attyFirstName");
List<StateBO> statesList = (List<StateBO>)request.getAttribute("statesList");
String action = (String) request.getAttribute("action");
String tableName = (String) request.getAttribute("tableName");
String tabName = (String) request.getAttribute("tabName");
String caseNum = (String) request.getAttribute("caseNum");
String parentId = (String) request.getAttribute("parentId");

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyDetailsServlet");
URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/CorisAttorneyCasesServlet");
URLEncryption urlCryptor3 = new URLEncryption("/CorisWEB/CorisAttorneyAttachDetachLookupServlet");

String paramsAttorneyAdd = "action=add";
String paramsAttorneyDetails = "mode=attorneyDetails";
String paramsAttorneyCases = "mode=attorneyCases";
String paramsSearchForm = "mode=find&action="+action+"&tabName="+tabName+"&tableName="+tableName+"&caseNum="+caseNum;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Attorney Management</title>
    
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

    <!-- main content -->
    <main>
		<div class="container-fluid">

			<% if (!TextUtil.isEmpty(TextUtil.print(errorMessage))) { %>
			<div class="alert alert-danger text-center m-2"><%=TextUtil.print(errorMessage) %></div>
			<% } %>
					
			<div class="card m-2">
				
				<div class="card-header">
					<strong>Attorney Lookup</strong>
				</div>
				
				<div class="card-body">
					
					<form id="searchAttorneysForm" name="searchAttorneysForm" onsubmit="searchForm(event);">
						
						<div class="form-row">
							
							<div class="col-12">
							
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Bar Number <span class="text-danger">!</span></strong></label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="barNum" name="barNum" value="<%=(barNum > 0)?barNum:"" %>" maxlength="10">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Bar State <span class="text-danger">!</span></strong></label>
										<div class="col-sm-12 col-md-13">
											<select class="form-control" id="barState" name="barState">
												<%
												if(statesList != null){
													String stateName = "";
													String stateCode = "";
													String selected = "";
													for(StateBO temp : statesList){
														stateCode = temp.getStateCode();
														stateName = temp.getName();
														if(stateCode.equals(barState)){
															selected = "selected";
														}else if("".equals(selected) && "UT".equals(stateCode) && TextUtil.isEmpty(barState)){
															selected = "selected";
														}else{
															selected = "";
														}
														%>
														<option value="<%=stateCode %>" <%=selected %>><%=stateName %></option>
														<%
													}
												}
												%>
											</select>
										</div>
									</div>
								</div>
							
							</div>
							<div class="col-12">
							
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Attorney Last Name <span class="text-danger">!</span></strong></label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="attyLastName" name="attyLastName" value="" maxlength="30">
										</div>
									</div>
								</div>
	
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Attorney First Name</strong></label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="attyFirstName" name="attyFirstName" value="" maxlength="30">
										</div>
									</div>
								</div>
							
							</div>
							
						</div>
						
						<div class="form-row">
							<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onclick="clearForm();">Clear</button>
								<button type="submit" class="btn btn-primary ml-2 float-right" id="searchBtn">Search</button>
							</div>
						</div>
						
					</form>
					<strong><span class="text-danger">!</span></strong> Bar Number/State or Attorney Last Name Required

				</div>
				
				<div class="card-header card-footer">
					<strong>Selected Attorney(s)</strong>
				</div>						
				
				<div class="card-body">
					
					<%//***************************************************** %>
					<%//START This table is only for adding attach/detach attorneys from the Attach/Detach Attorney tab %>
					<%//***************************************************** %>
					<div class="mb-4">
						<table id="selectedAttyTable" class="table hover row-border compact">
							<thead class="text-light bg-dark">
								<tr>
									<th>Bar Number</th>
									<th>Bar State</th>
									<th>Attorney Last Name</th>
									<th>Attorney First Name</th>
								</tr>
							</thead>
							<tbody>
								<%//rows are added dynamically in the javascript function addToList() which is called from corisAttorneyAttachDetachLookupTable.jsp %>
							</tbody>
						</table>
					</div>
					 
					<%//***************************************************** %>
					<%//END This table is only for adding attach/detach attorneys from the Attach/Detach Attorney tab %>
					<%//***************************************************** %>

				</div>
				
				<div class="card-header card-footer">
					<strong>Results</strong>
				</div>						
				
				<div class="card-body">
				
					<div id="attorneyLookupTable">

						<%//This is just a place holder for the results table when the page is first loaded.
						//The table itself is in corisAttorneyAttachDetachLookupTable.jsp %>
						<table id="searchResultsTable" class="table hover row-border compact">
							<thead class="text-light bg-dark">
								<tr>
									<th>Last Name</th>
									<th>First Name</th>
									<th>Bar Number</th>
									<th>Bar State</th>
									<th>Bar Status</th>
									<th>City</th>
									<th>State</th>
									<th>Country</th>
									<th>Business Phone</th>
									<th>Email</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="bodyResults">
							</tbody>
						</table>
						
					</div>
					<br>
					<br>
				</div>	

			</div>
			
			<div class="std-fixed bottom bg-light popcorn-ftr active">
				<div class='mt-2 col-24'>
					<button type="button" class="btn btn-primary ml-2 float-right" id="selectBtn" onclick="closePop();">Done</button>
				</div>
			</div>

    	</div>
    </main>

    <!-- footer -->
    <footer></footer>

    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
		
		    $('#selectedAttyTable').DataTable( {
		    	"retrieve": true,
		    	"paging": false,
		    	"info": false,
		    	"searching": false
		    } );
			$('#selectedAttyTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
	
		    $('#searchResultsTable').DataTable( {
		        "retrieve": true,
				"oLanguage": {
					"sSearch": "Filter results:"
				},
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
		    } );
			$('#searchResultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
			
			$('#barNum').focus();

		});
		
		var parentCornId = appUX.pop.getSelfHandle().id;

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function attorneyAdd(){
			var cornId = "attorneyAdd";
			var title = "Attorney Add";
			var url = "<%=urlCryptor.urlEncrypt(paramsAttorneyAdd) %>&parentCornId=" + parentCornId;
			var width = 800;
			var height = 525;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}

		function attorneyDetails(barNum, barState){
			var cornId = "attorneyDetails";
			var title = "Attorney Details";
			var url = "<%=urlCryptor.urlEncrypt(paramsAttorneyDetails) %>&barNum=" + barNum + "&barState=" + barState + "&parentCornId=" + parentCornId;
			var width = 800;
			var height = 525;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}

		function attorneyCases(firstName, lastName, barNum, barState){
			var cornId = "attorneyCases";
			var title = "Cases for Attorney";
			var url = "<%=urlCryptor.urlEncrypt(paramsAttorneyCases) %>&firstName=" + firstName +"&lastName=" + lastName + "&barNum=" + barNum + "&barState=" + barState;
			var width = 900;
			var height = 600;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}

		function clearForm(){
			$('#barNum').val("");
			$('#attyLastName').val("");
			$('#attyFirstName').val("");
			$('#searchResultsTable').DataTable().clear().draw();
			$('#barNum').focus();
		}
		
		function searchForm(e){
			if (e) { e.preventDefault() };
			var isValid = true;
			var message = "";
			$('#barNum').val($.trim($('#barNum').val()));
			$('#attyLastName').val($.trim($('#attyLastName').val()));
			$('#attyFirstName').val($.trim($('#attyFirstName').val()));
			var barNum = $('#barNum').val();
			var attyLastName = $('#attyLastName').val();
			var attyFirstName = $('#attyFirstName').val();
			if ((isNaN($('#barNum').val()) || $('#barNum').val() == "" || $('#barNum').val() == null) && ($('#attyLastName').val() == "" || $('#attyLastName').val() == null)) {
				isValid = false;
				message += "Bar Number/State or Attorney Last Name is required for search. Bar Number may only contain numbers.<br>";
				$('#barNum').focus();
			}
			if (isValid) {
				var data = $('#searchAttorneysForm').serialize();
				appUX.ajax.call("<%=urlCryptor3.urlEncrypt(paramsSearchForm) %>", 
					function (err, data, xhr) {
						$('#attorneyLookupTable').html(data);
						$('#barNum').focus();
					}, 
					'POST', 
					'&' + data
				);
			} else {
				appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
			}
		}
		
		//this is used by corisAttorneyAttachDetachLookupTable.jsp
		function addToList(listBarNum, listState, listLastName, listFirstName){
			var parentCorn = appUX.pop.getCornHandle("<%=parentId%>");
			var barNum = 0;
			var barState = "";
			var barNumTemp = 0;
			var barStateTemp = "";
			var exists = false;
			parentCorn.frameWindow.$('#<%=tableName%> tr').each(function(){
				barNum = $(this).find('td:first-child').html();    
				barState = $(this).find('td:nth-child(2)').html();
				if(!isNaN(barNum)){
					if(barNum == listBarNum && barState == listState){
						//attorney has already been added to the list, so move on
						exists = true;
					}
				}
			});
			if(!exists){
				$('.dataTables_empty').remove();
				$('#selectedAttyTable').DataTable().row.add([ listBarNum, listState, listLastName, listFirstName ]).draw();
				parentCorn.frameWindow.appendRow(listBarNum, listState, listLastName, listFirstName, '<%=tableName%>');
			}else{
				message = "Attorney is already in the list.";
				appUX.pop.declare("Information", message, 300, 'auto', appUX.pop.styles.INFO);
			}
			if('<%=tabName%>' == "transfer"){
				closePop(); //we only want to allow one attorney to be selected on the mass transfer page
			}
		}
		
		function refreshPage(paramObj){
			$('#barNum').val(paramObj.barNum);
			$('#barState').val(paramObj.barState);
			searchForm(event);
		}
		
	</script>
</body>
</html>
