<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
SimpleDateFormat dateFormat = Constants.dateFormatCoris;
String locnCode = (String) request.getAttribute("locnCode");
String courtType = (String) request.getAttribute("courtType");
String caseType = (String) request.getAttribute("caseType");
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");
List<CaseTypeBO> caseTypeDistrictList = (List<CaseTypeBO>) request.getAttribute("caseTypeDistrictList");
List<CaseTypeBO> caseTypeJusticeList = (List<CaseTypeBO>) request.getAttribute("caseTypeJusticeList");
Date filingDatetimeStart = null;
Date filingDatetimeEnd = null;

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyAttachDetachLookupServlet");
URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/CorisAttorneyAttachDetachTransferServlet");

String paramsAddAttorney = "mode=attorneyLookup&tabName=transfer";
String paramsSaveAttachDetachAttorney = "mode=update";
String paramsUpdateCasesCount = "mode=find&tabName=transfer&tableName=attyDetachTable";
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
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
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
					<strong>Mass Transfer</strong>
				</div>
				
				<div class="card-body">
				
					<form id="attachDetachForm" name="attachDetachForm" novalidate>

						<div id="locnCourtContent">				
							
							<div class="form-row mt-8">
							
								<div class="form-group col-md-8 col-lg-8">
									<div class="row align-items-center">
										<div class='input-group col-md-24 col-24 mb-4'>
											<strong>Court Type<span class="text-danger">*</span></strong>
										</div>
										<div class="col-sm-11 col-md-12">
											<select class="form-control" id="courtType" name="courtType" onchange="resetCaseCount(); loadLocation($(this).val()); loadCaseType($(this).val());" required>
												<option value="D" <%=("D".equals(courtType))?"selected":"" %>>District</option>
												<option value="J" <%=("J".equals(courtType))?"selected":"" %>>Justice</option>
											</select>
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-8 col-lg-8">
									<div class="row align-items-center">
										<div class='input-group col-md-24 col-24 mb-4'>
											<strong>Location<span class="text-danger">*</span></strong>
										</div>
										<div class="col-sm-11 col-md-12">
											<select class="form-control" id="locnCode" name="locnCode" onchange="resetCaseCount();" required>
											</select>
										</div>
									</div>
								</div>
	
							</div>					
						
						</div>

						<div class="form-row mt-8">
						
							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<div class='input-group col-md-24 col-24 mb-4'>
										<strong>Filing Date Start/End</strong>
									</div>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="filingDatetimeStart" name="filingDatetimeStart" onclick="resetCaseCount();" value="<%=(filingDatetimeStart != null)?dateFormat.format(filingDatetimeStart):""%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="filingDatetimeEnd" name="filingDatetimeEnd" onclick="resetCaseCount();" value="<%=(filingDatetimeEnd != null)?dateFormat.format(filingDatetimeEnd):""%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<div class='input-group col-md-24 col-24 mb-4'>
										<strong>Case Type Category</strong>
									</div>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="caseCategory" name="caseCategory" onchange="resetCaseCount();">
											<option value="">All</option>
											<option value="D">D - Domestic</option>
											<option value="P">P - Probate</option>
											<option value="R">R - Criminal</option>
											<option value="S">S - Small Claim</option>
											<option value="V">V - Civil</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<div class='input-group col-md-24 col-24 mb-4'>
										<strong>Case Type</strong>
									</div>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="caseType" name="caseType" onchange="resetCaseCount();">
										</select>
									</div>
								</div>
							</div>
							
						</div>					
						
						<div class="mt-12 mb-4">
							<strong>Number of Cases: </strong><span id="casesCount"></span> <button type="button" class="btn btn-primary ml-2" id="recalcBtn" onclick="updateCasesCount();">Recalculate Cases Count</button>
						</div>

						<div class="mt-12 mb-4">
							<strong>Attorney to Attach</strong><button id="addAttachBtn" name="addAttachBtn" type="button" class="btn btn-primary float-right" onclick="addAttorney('attach');">Add Attorney to Attach</button>
							<table id="attyAttachTable" class="table hover row-border compact">
								<thead class="text-light bg-dark">
									<tr>
										<% int colspan4 = 5; //keep this updated as table changes so that the last column is not orderable %>
										<th>Bar Number</th>
										<th>Bar State</th>
										<th>Attorney Last Name</th>
										<th>Attorney First Name</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<%//rows are added/removed dynamically from addToList() in corisAttorneyAttachDetachLookup.jsp %>
								</tbody>
							</table>
							
						</div>

						<div class="mt-12 mb-4">
							<strong>Attorney to Detach</strong><button id="addDetachBtn" name="addDetachBtn" type="button" class="btn btn-primary float-right" onclick="addAttorney('detach');">Add Attorney to Detach</button>
							<table id="attyDetachTable" class="table hover row-border compact">
								<thead class="text-light bg-dark">
									<tr>
										<% int colspan9 = 5; //keep this updated as table changes so that the last column is not orderable %>
										<th>Bar Number</th>
										<th>Bar State</th>
										<th>Attorney Last Name</th>
										<th>Attorney First Name</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<%//rows are added/removed dynamically from addToList() in corisAttorneyAttachDetachLookup.jsp %>
								</tbody>
							</table>
							
						</div>
						
						<div class="row mt-4 ml-3">
							<div class='mb-1 col-24'>
								<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="clearBtn" onclick="clearForm();">Clear</button>
								<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="transferBtn" onclick="saveAttachDetachAttorney();">Transfer Cases</button>
							</div>
						</div>

					</form>
					
				</div>
				
			</div>

    	</div>

    	<div id="locationJusticeOptions" style="display: none;">
    		<%
			if (locationJusticeList.size() > 0) {
				for (LocationBO locationBO : locationJusticeList) {
					%>
					 <option value="<%=TextUtil.print(locationBO.getLocnCode())%>"><%=TextUtil.print(locationBO.getLocnDescr())%></option>
					<%
				}
			}
			%>
    	</div>
    	
    	<div id="locationDistrictOptions" style="display: none;">
    		<%
			if (locationDistrictList.size() > 0) {
				for (LocationBO locationBO : locationDistrictList) {
					%>
					 <option value="<%=TextUtil.print(locationBO.getLocnCode())%>"><%=TextUtil.print(locationBO.getLocnDescr())%></option>
					<%
				}
			}
			%>
    	</div>
    	
    </main>


    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){

			$('[id^=datetimepicker]').datetimepicker({ 
				format: 'MM/DD/YYYY',			
				icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
			});
				
		    $('#attyAttachTable').DataTable( {
		    	"retrieve": true,
		    	"paging": false,
		    	"info": false,
		    	"searching": false,
				"columnDefs": [
    				{ "orderable": false, "targets": <%= colspan4 - 1 %> }
				]
		    });
			$('#attyAttachTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
			
		    $('#attyDetachTable').DataTable( {
		    	"retrieve": true,
		    	"paging": false,
		    	"info": false,
		    	"searching": false,
				"columnDefs": [
    				{ "orderable": false, "targets": <%= colspan9 - 1 %> }
				]
		    });
			$('#attyDetachTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
			
			$('.dataTables_empty').remove();
			$('#attyAttachTable').DataTable().clear().draw();
			$('#attyDetachTable').DataTable().clear().draw();

			$('#recalcBtn').hide();
			loadLocation('<%=courtType%>');
			loadCaseType('<%=courtType%>');

		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function addAttorney(type){
			var valid = true;
			var attyAttachCount = 0;
			var attyDetachCount = 0;
			var barNum = 0;
			var barState = "";
			var message2 = "";
			if(type == "attach"){
				$('#attyAttachTable tr').each(function(){
					barNum = $(this).find('td:first-child').html();    
					barState = $(this).find('td:nth-child(2)').html();
					if(!isNaN(barNum)){
						attyAttachCount++;
					}
				});
				if(attyAttachCount > 0){
					valid = false;
					message2 += "Please select only one attorney to attach.<br>";
				}
			}
			if(type == "detach"){
				$('#attyDetachTable tr').each(function(){
					barNum = $(this).find('td:first-child').html();    
					barState = $(this).find('td:nth-child(2)').html();
					if(!isNaN(barNum)){
						attyDetachCount++;
					}
				});
				if(attyDetachCount > 0){
					valid = false;
					message2 += "Please select only one attorney to detach.<br>";
				}
			}
			if(valid){
				var data = "";
				if(type == "attach"){
					data += "tableName=attyAttachTable";
				}else if(type == "detach"){
					data += "tableName=attyDetachTable";
				}
				data += "&locnCode="+$('#locnCode').val()+"&courtType="+$('#courtType').val();
				data += "&parentId="+appUX.pop.getSelfHandle().id;
				var cornId = "attorneySearch";
				var title = "Attorney Lookup";
				var url = "<%=urlCryptor.urlEncrypt(paramsAddAttorney) %>&"+data;
				var width = 1100;
				var height = 600;
				var style = appUX.pop.styles.LIGHT;
				appUX.pop.modal(cornId, title, url, width, height, style);
			}else{
				appUX.pop.declare("Warning", message2, 300, 'auto', appUX.pop.styles.WARNING);
			}
		}
		
		function appendRow(listBarNum, listState, listLastName, listFirstName, tableName){
			$('#'+tableName).DataTable().row.add([ listBarNum, listState, listLastName, listFirstName, '<i id="'+listState+listBarNum+'" onclick="removeRow('+listBarNum+', \''+listState+'\', \''+tableName+'\')" title="Remove from List" class="text-danger fas fa-minus-circle fa-lg" style="cursor: pointer;"></i>' ]).draw();
			//this function is called from corisAttorneyAttachDetachLookup.jsp
			if(tableName == "attyDetachTable"){
	 			$('#addDetachBtn').attr("disabled", true); 
				updateCasesCount(listBarNum, listState);
			}else if(tableName == "attyAttachTable"){
	 			$('#addAttachBtn').attr("disabled", true); 
 			}
		}
		
		function removeRow(barNum, barState, tableName) {
 			if(tableName == "attyDetachTable"){
 				$('#casesCount').html("");
 				$('#recalcBtn').hide();
	 			$('#addDetachBtn').attr("disabled", false); 
 			}else if(tableName == "attyAttachTable"){
	 			$('#addAttachBtn').attr("disabled", false); 
 			}
 			$('#'+tableName).DataTable().row( $('#'+barState+barNum).parents('tr') ).remove().draw();
 			$('#'+tableName).DataTable().clear().draw();
		}
		
		function saveAttachDetachAttorney(){
			var data = $('#attachDetachForm').serialize();
			var valid = true;
			var attyAttachCount = 0;
			var attyDetachCount = 0;
			var attyPartyIdCount = 0;
			var message2 = "";
			var barNumAttach = 0;
			var barStateAttach = "";
			var barNumDetach = 0;
			var barStateDetach = "";
			$('#attyAttachTable tr').each(function(){
				barNumAttach = $(this).find('td:first-child').html();    
				barStateAttach = $(this).find('td:nth-child(2)').html();
			    if(!isNaN(barNumAttach)){
					attyAttachCount++;
					data += "&barNumAttach"+attyAttachCount+"="+barNumAttach;
					data += "&barStateAttach"+attyAttachCount+"="+barStateAttach;
				}
			});
			$('#attyDetachTable tr').each(function(){
				barNumDetach = $(this).find('td:first-child').html();    
				barStateDetach = $(this).find('td:nth-child(2)').html();
				if(!isNaN(barNumDetach)){
					attyDetachCount++;
					data += "&barNum="+barNumDetach;
					data += "&barState="+barStateDetach;
				}
			});
			if(attyAttachCount > 1){
				valid = false;
				message2 += "Please select one attorney or no attornies to attach.<br>";
			}
			if(attyDetachCount > 1 || attyDetachCount == 0){
				valid = false;
				message2 += "Please select one attorney to detach.<br>";
			}
			if($('#casesCount').text() <= 0 && attyDetachCount > 1){
				valid = false;
				message2 += "There are no cases. Please select an attorney with current cases and/or different filters, then click \"Recalculate Cases Count\" button.<br>";				
			}
			if (barNumAttach == barNumDetach && barStateAttach == barStateDetach && barNumDetach > 0) {
				valid = false;
				message2 += "Attach and detach attorney may not be the same.<br>";
			}
			var endYear = new Date($('#filingDatetimeEnd').val()).getFullYear(); 
			var startYear = new Date($('#filingDatetimeStart').val()).getFullYear();
			if (endYear - startYear > 10) {
				valid = false;
				message2 += "Please adjust Filing Date range to have a maximum of a 10 year span.<br>";
			}
			if(valid){
				var title = "Mass Transfer";
				var message = 'You have selected to process '+$("#casesCount").text()+' cases by detaching Attorney Bar Number/Bar State '+barNumDetach+'/'+barStateDetach;
				if (attyAttachCount > 0) {
					message += ' and attaching Attorney Bar Number/Bar State '+barNumAttach+'/'+barStateAttach;
				}
				message += '. The length of time to process this request could take a while. An email will be sent when complete. Would you like to continue?';
				var trueText = "Yes, Continue";
				var falseText = "Cancel";
				var width = 300;
				var height = "auto";
				var style = appUX.pop.styles.PRIMARY;
				var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
				
				function confirmCallback(action) {
					corn.close();
					if(action){
						clearForm();
						message = "Changes are being processed and may take a while. An email will be sent when complete. Window may be closed.";
						appUX.pop.declare("Information", message, 300, 'auto', appUX.pop.styles.INFO);
						appUX.ajax.call("<%=urlCryptor2.urlEncrypt(paramsSaveAttachDetachAttorney) %>", 
							function (err, data, xhr) {
								//email will be sent from servlet to indicate success or error
							}, 
							'POST', 
							'&'+data
						);
					}
				}
			}else{
				appUX.pop.alert(message2, 400, 'auto', appUX.pop.styles.DANGER);
			}
		}
		
		function loadLocation(courtType) {
			if (courtType == "D") {
				$('#locnCode').html($('#locationDistrictOptions').html());
			} else if (courtType == "J") {
				$('#locnCode').html($('#locationJusticeOptions').html());
			}
			$('#locnCode').val('<%=locnCode%>');
		}
		
		function loadCaseType(courtType){
			$('#caseType').find('option').remove();
			$('#caseType').append(
				 $('<option></option>').val("").html("All")
			);
			<%
			String thisCaseType = "";
			String thisDescr = "";
			%>
			if(courtType == "J"){
				<%
				if(caseTypeJusticeList.size() > 0){
					for(CaseTypeBO caseTypeBO : caseTypeJusticeList){
						thisCaseType = caseTypeBO.getCaseType();
						thisDescr = caseTypeBO.getDescr();
						%>
						$('#caseType').append(
							 $('<option></option>').val("<%=thisCaseType%>").html("<%=thisCaseType%> - <%=thisDescr%>")
						);
						<%
						if(thisCaseType.equals(caseType)){
							%>
							$('#caseType').val('<%=thisCaseType%>');
							<%
						}													
					}
				}
				%>
			}
			if(courtType == "D"){
				<%
				if(caseTypeDistrictList.size() > 0){
					for(CaseTypeBO caseTypeBO : caseTypeDistrictList){
						thisCaseType = caseTypeBO.getCaseType();
						thisDescr = caseTypeBO.getDescr();
						%>
						$('#caseType').append(
							 $('<option></option>').val("<%=thisCaseType%>").html("<%=thisCaseType%> - <%=thisDescr%>")
						);
						<%
						if(thisCaseType.equals(caseType)){
							%>
							$('#caseType').val('<%=thisCaseType%>');
							<%
						}													
					}
				}
				%>
			}
			<%
			thisCaseType = null;
			thisDescr = null;
			%>
		}
		
		function resetCaseCount(){
			$('#attyDetachTable tr').each(function(){
				barNum = $(this).find('td:first-child').html();    
				if(!isNaN(barNum)){
					$('#casesCount').html("");
					$('#recalcBtn').show();
				}
			});
		}
		
		function clearForm(){
			$('#locnCode').val("<%=locnCode%>");
			$('#courtType').val("<%=courtType%>");
			$('#filingDatetimeStart').val("");
			$('#filingDatetimeEnd').val("");
			$('#caseCategory').val("");
			$('#caseType').val("");
			$('#attyAttachTable').DataTable().clear().draw();
			$('#attyDetachTable').DataTable().clear().draw();
			$('#addAttachBtn').attr("disabled", false);
			$('#addDetachBtn').attr("disabled", false);  
			$('#casesCount').html("");
			$('#recalcBtn').hide();
		}

		function updateCasesCount(barNum, barState){ 
			//for mass transfer only
			//this function is also called from corisAttorneyAttachDetachLookup.jsp
			var valid = true;
			var locnCode = $("#locnCode").val();
			var courtType = $("#courtType").val();
			var filingDatetimeStart = $("#filingDatetimeStart").val();
			var filingDatetimeEnd = $("#filingDatetimeEnd").val();
			var caseCategory = $("#caseCategory").val();
			var caseType = $("#caseType").val();
			var casesCount = 0;
			if(isNaN(barNum)){
				$('#attyDetachTable tr').each(function(){
					barNum = $(this).find('td:first-child').html();
					barState = $(this).find('td:nth-child(2)').html();
				});
			}
		    if(isNaN(barNum)){
				valid = false;
			}
			if(valid){
				$('#casesCount').html("Calculating number of cases...please wait.");
				$('#recalcBtn').hide();
				message = "Calculating number of cases...please wait.";
				appUX.pop.declare("Information", message, 300, 'auto', appUX.pop.styles.INFO);
				appUX.ajax.call("<%=urlCryptor2.urlEncrypt(paramsUpdateCasesCount) %>", 
					function (err, data, xhr) {
						var jsonObj = JSON.parse(data);
						if (err) {
							message = "There was an error while calculating number of cases. "+xhr.responseText;
							appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
						}else if (jsonObj.casesCount) {
							casesCount = jsonObj.casesCount;
						}else if (jsonObj.errorMessage) {
							message = jsonObj.errorMessage;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}
						if(casesCount > 0){
								$("#casesCount").html(casesCount);
						}else{
								$('#casesCount').html("0");
						}
					}, 
					'POST', 
					'&filingDatetimeStart='+filingDatetimeStart+'&filingDatetimeEnd='+filingDatetimeEnd+'&caseCategory='+caseCategory+'&caseType='+caseType+'&courtType='+courtType+'&locnCode='+locnCode+'&barNum='+barNum+'&barState='+barState
				);
			}
		}
		
	</script>
</body>
</html>
<%
locnCode = null;
courtType = null;
locationDistrictList = null;
locationJusticeList = null;
dateFormat = null;
caseType = null;
caseTypeDistrictList = null;
caseTypeJusticeList = null;
filingDatetimeStart = null;
filingDatetimeEnd = null;
%>