<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.commonparty.CommonPartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
String caseNum = (String) request.getAttribute("caseNum");
String locnCode = (String) request.getAttribute("locnCode");
String courtType = (String) request.getAttribute("courtType");

List<KaseBO> singleCasePartyList = (List<KaseBO>) request.getAttribute("singleCasePartyList");
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyAttachDetachSingleServlet");

String paramsVerifyCaseInput = "mode=find&action=validateCaseNum";
String paramsGetParties = "mode=find&action=getParties";
String paramsSaveAttachDetachAttorney = "mode=update&action=single";
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
					<strong>Single Case</strong>
				</div>
				
				<div class="card-body">
				
					<form id="attachDetachForm" name="attachDetachForm" action="javascript:void();" novalidate>
					
						<%//****************************************************
						// LOCATION AND COURT TYPE - START
						//****************************************************%>

						<div id="locnCourtContent">				
							
							<div class="form-row mt-8">
							
								<div class="form-group col-md-8 col-lg-8">
									<div class="row align-items-center">
										<div class='input-group col-md-24 col-24 mb-4'>
											<strong>Court Type<span class="text-danger">*</span></strong>
										</div>
										<div class="col-sm-11 col-md-12">
											<select class="form-control" id="courtType" name="courtType" onchange="clearForm(false); loadLocation($(this).val());" required>
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
											<select class="form-control" id="locnCode" name="locnCode" onchange="clearForm(false);" required>
											</select>
										</div>
									</div>
								</div>
	
							</div>					
						
						</div>

						<%//****************************************************
						// LOCATION AND COURT TYPE - END
						//****************************************************%>
						
						<%//*************START Case and Parties*************** %>
						<div class="row mt-4">
							<div class='input-group col-md-24 col-24 mb-4'>
								<strong>Case Number<span class="text-danger">*</span></strong>
							</div>
							<input type="text" class="form-control col-md-3 col-3 ml-3 mr-4 mb-4" id="caseNum0" name="caseNum0" onkeyup="verifyCaseInput(this);" value="<%=(!TextUtil.isEmpty(caseNum))?TextUtil.print(caseNum):"" %>" maxlength="9" required>
						</div>
						<div class="row mt-4">
							<div class='input-group col-md-24 col-24'>
								<strong>Party (Parties)<span class="text-danger">*</span></strong>
								<div class="form-check col-24 mt-4">
									<input type="checkbox" class="form-check-input" id="selectParties" name="selectParties">
									<label class="form-check-label"><strong>Select/Deselect All</strong></label>
								</div>
							</div>
						</div>
						<div id="singlePartiesDiv" class="row mt-4">
							<%// dynamically filled with corisAttorneyAttachDetachParties.jsp %>
						</div>
						<%//*************END Case and Parties*************** %>

						<%//****************************************************
						// Attorneys to Attach
						//****************************************************%>
						<div class="mt-12 mb-4">
							<strong>Attorney(s) to Attach</strong><button type="button" class="btn btn-primary float-right" onclick="addAttachAttorney();">Add Attorney(s) to Attach</button>
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
									<%//rows are added/removed dynamically %>
								</tbody>
							</table>
							
						</div>
						
						<div class="row mt-4 ml-3">
							<div class='mb-1 col-24'>
								<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="clearBtn" onclick="clearForm(true);">Clear</button>
								<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="saveBtn" onclick="saveAttachDetachAttorney(event);">Save</button>
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
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){
	
			$('#selectParties').change(function(){
				if($('#selectParties').prop( "checked" )){
					//check all
					$("[id^=selectParties]").prop( "checked", true );
				}else{
					//uncheck all
					$("[id^=selectParties]").prop( "checked", false );
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
			
			$('.dataTables_empty').remove();
			$('#attyAttachTable').DataTable().clear().draw();

			verifyCaseInput($('#caseNum0').val());
			$('#caseNum0').focus();
			getParties('<%=caseNum%>');
			loadLocation('<%=courtType%>');
			
			$("svg").on( "mouseenter", function() { //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
				$(this).css("cursor", "pointer" );
			});
			$("svg").on( "mouseleave", function() {
				$(this).css("cursor", "default" );
			});

		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function verifyCaseInput(currentInput){
			//check to see if this is a valid case number for the location and court type
			if($(currentInput).val() != "" && $(currentInput).val() != null && $(currentInput).val() != "undefined"){
				if($(currentInput).val().length == 9){
					appUX.ajax.call('<%=urlCryptor.urlEncrypt(paramsVerifyCaseInput) %>',
						function (err, data, xhr) {
							var jsonObj = JSON.parse(data);
							if(!err){
								if(!jsonObj.error){
									//mark the correct case number input in green
									$(currentInput).removeClass("text-danger").addClass("text-success");
									//get the parties
									getParties($(currentInput).val());
								}else{
									//display an error message and mark the incorrect case number input in red
									$(currentInput).removeClass("text-success").addClass("text-danger");
									$(currentInput).focus();
									message = "Case number "+$(currentInput).val()+" is invalid.";
									appUX.pop.declare("Warning", message, 300, 'auto', appUX.pop.styles.WARNING);
									//and clear the form, except for the location/court type
									clearForm(false);
								}
							}
						}, 
						'POST', 
						'&caseNum='+$(currentInput).val()+'&locnCode='+$('#locnCode').val()+'&courtType='+$('#courtType').val()
					);
				}
			}
		}
		
		function getParties(caseNum){
			appUX.ajax.call('<%=urlCryptor.urlEncrypt(paramsGetParties) %>', 
				function (err, data, xhr) {
					if(!err){
						//update the parties area
						$('#singlePartiesDiv').html(data);
					}
				}, 
				'POST', 
				'&caseNum='+caseNum+'&locnCode='+$('#locnCode').val()+'&courtType='+$('#courtType').val()
			);
		}
		
		function addAttachAttorney(){
			if($('#caseNum0').val() != "" && document.getElementById('caseNum0').className.indexOf("text-danger") < 0){
				var data = "mode=attorneyLookup&action=addAttorney&tabName=single&tableName=attyAttachTable";
				data += "&caseNum="+$('#caseNum0').val();
				data += "&locnCode="+$('#locnCode').val()+"&courtType="+$('#courtType').val();
				data += "&parentId="+appUX.pop.getSelfHandle().id;
				var cornId = "attorneySearch";
				var title = "Attorney Lookup";
				var url = "/CorisWEB/CorisAttorneyAttachDetachLookupServlet?"+data;
				var width = 1100;
				var height = 600;
				var style = appUX.pop.styles.LIGHT;
				appUX.pop.modal(cornId, title, url, width, height, style);
			}else{
				message = "Case Number is required before selecting an attorney to attach.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			}
		}
		
		function saveAttachDetachAttorney(e){
			e.preventDefault();
			var data = $('#attachDetachForm').serialize();
			data += "&caseNum0="+$('#caseNum0').val(); //making sure this one gets picked up
			var partyCount = 0;
			$('[id^=selectParties]').each(function(){
				partyCount++;
			});
			data += "&partyCount="+partyCount;
			var valid = true;
			var attyAttachCount = 0;
			var message2 = "";
			var barNum = 0;
			var barState = "";
			$('#attyAttachTable tr').each(function(){
				barNum = $(this).find('td:first-child').html();    
				barState = $(this).find('td:nth-child(2)').html();
			    if(!isNaN(barNum)){
					attyAttachCount++;
					data += "&barNumAttach"+attyAttachCount+"="+barNum;
					data += "&barStateAttach"+attyAttachCount+"="+barState;
				}
			});
			data += "&attyAttachCount="+attyAttachCount;
			if($("#caseNum0").val() == ""){
				valid = false;
				message2 += "Case Number is required.<br>";
				$("#caseNum0").focus();
			}
			if(data.indexOf("selectParties") < 0 && $("#caseNum0").val() != ""){
				valid = false;
				message2 += "Please select at least one party.<br>";
			}
			if(attyAttachCount <= 0){
				valid = false;
				message2 += "Please select at least one attorney to attach.<br>";
			}
			if(valid){
				message = "Changes are being processed and may take a while to finish. Window may be closed.";
				appUX.pop.declare("Information", message, 300, 'auto', appUX.pop.styles.INFO);
				clearForm(true);
				appUX.ajax.call('<%=urlCryptor.urlEncrypt(paramsSaveAttachDetachAttorney) %>', 
					function (err, data, xhr) {
						var jsonObj = JSON.parse(data);
						if (err) {
							message = "There was an error. "+xhr.responseText;
							appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
						}else if (jsonObj.error) {
							message = jsonObj.errorMessage;
							appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
						}
					}, 
					'POST', 
					'&'+data
				);
			}else{
				appUX.pop.alert(message2, 400, 'auto', appUX.pop.styles.DANGER);
			}
		}
		
		function clearForm(all){
			$('#caseNum0').val("");
			if(all){
				$('#locnCode').val("<%=locnCode%>");
				$('#courtType').val("<%=courtType%>");
			}
			$('#selectParties').prop( "checked", false );
			$("#singlePartiesDiv").html('<div class="form-check col-24 text-danger">Please enter Case Number to display parties for that case.</div>');
			$('#attyAttachTable').DataTable().clear().draw();
			$('#caseNum0').focus();
		}
		
		function appendRow(listBarNum, listState, listLastName, listFirstName, tableName){
			//this is called from corisAttorneyAttachDetachLookup.jsp
			$('#'+tableName).DataTable().row.add([ listBarNum, listState, listLastName, listFirstName, '<i id="'+listState+listBarNum+'" onclick="removeRow('+listBarNum+', \''+listState+'\')" title="Remove from List" class="text-danger fas fa-minus-circle fa-lg" style="cursor: pointer;"></i>' ]).draw();
		}
		
		function removeRow(barNum, barState) {
 			$('#attyAttachTable').DataTable().row( $('#'+barState+barNum).parents('tr') ).remove().draw();
		}
		
		function loadLocation(courtType) {
			if (courtType == "D") {
				$('#locnCode').html($('#locationDistrictOptions').html());
			} else if (courtType == "J") {
				$('#locnCode').html($('#locationJusticeOptions').html());
			}
			$('#locnCode').val('<%=locnCode%>');
		}
		
	</script>
</body>
</html>
<%
caseNum = null;
locnCode = null;
courtType = null;
singleCasePartyList = null;
locationDistrictList = null;
locationJusticeList = null;
urlCryptor = null;
paramsVerifyCaseInput = null;
paramsGetParties = null;
paramsSaveAttachDetachAttorney = null;
%>