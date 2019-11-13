<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partytype.PartyTypeBO"%>
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
String caseNum = "";
if(!TextUtil.isEmpty((String) request.getAttribute("caseNum"))){
	caseNum = (String) request.getAttribute("caseNum");
}
String locnCode = (String) request.getAttribute("locnCode");
String courtType = (String) request.getAttribute("courtType");
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");
List<CommonPartyBO> batchCommonPartyDistrictList = (List<CommonPartyBO>) request.getAttribute("batchCommonPartyDistrictList");
List<CommonPartyBO> batchCommonPartyJusticeList = (List<CommonPartyBO>) request.getAttribute("batchCommonPartyJusticeList");

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyAttachDetachBatchServlet");
URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/CorisAttorneyAttachDetachLookupServlet");

String paramsVerifyCaseInput = "mode=find";
String paramsAddAttachAttorney = "mode=attorneyLookup&action=addAttorney&tabName=batch&tableName=attyAttachTable";
String paramsAddDetachAttorney = "mode=attorneyLookup&action=addAttorney&tabName=batch&tableName=attyDetachTable";
String paramsSaveAttachDetachAttorney = "mode=update";
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
					<strong>Batch Cases</strong>
				</div>
				
				<div class="card-body">
				
					<form id="attachDetachForm" name="attachDetachForm" novalidate>
					
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
											<select class="form-control" id="courtType" name="courtType" onchange="clearForm(false); loadLocation($(this).val()); loadCommonParty();" required>
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
											<select class="form-control" id="locnCode" name="locnCode" onchange="loadCommonParty(); clearForm(false);" required>
											</select>
										</div>
									</div>
								</div>
	
							</div>					
						
						</div>

						<%//****************************************************
						// LOCATION AND COURT TYPE - END
						//****************************************************%>
						
						<%//*************START Cases and Parties*************** %>
						
						<%//use 6 columns for the case numbers %>
						<div class="row mt-4">
							<div class='input-group col-md-24 col-24 mb-4'>
								<strong>Case Number(s)<span class="text-danger">*</span></strong>
							</div>
							<input type="text" class="form-control col-md-3 col-3 ml-3 mb-4" id="caseNum1" name="caseNum1" value="<%=(!TextUtil.isEmpty(caseNum))?TextUtil.print(caseNum):"" %>" maxlength="9" onkeyup="verifyCaseInput(this, 2);" required>
						</div>
						
						<div class="form-row mt-8">
						
							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<div class='input-group col-md-24 col-24 mb-4'>
										<strong>Party Type<span class="text-danger">!</span></strong>
									</div>
									<div class="col-sm-11 col-md-12">
										<%//there are only 5 Party Types needed, which is why they are hard-coded, because there is no flag/type in the party_type table %>
										<select class="form-control" id="partyType" name="partyType">
											<option value=""></option>
											<option value="DEF">Defendant</option>
											<option value="MIN">Minor Child</option>
											<option value="PET">Petitioner</option>
											<option value="PLA">Plaintiff</option>
											<option value="RES">Respondent</option>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<div class='input-group col-md-24 col-24 mb-4'>
										<strong>Common Party<span class="text-danger">!</span></strong>
									</div>
									<div class="col-sm-11 col-md-12">
										<%//for batch, populate from the common_party table, based on location %>
										<select class="form-control" id="commonParty" name="commonParty">
										</select>
									</div>
								</div>
							</div>
							
						</div>	
						
						<br/>
						<span class="text-danger">*</span> Required field.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="text-danger">!</span> One or the other is required.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="text-danger">^</span> Required if Common Party is selected.

						<%//*************END Cases and Parties*************** %>
				
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
						
						<%//****************************************************
						// Attorneys to Detach
						//****************************************************%>
						<div class="mt-12 mb-4">
							<strong>Attorney(s) to Detach</strong><button type="button" class="btn btn-primary float-right" onclick="addDetachAttorney();">Add Attorney(s) to Detach</button>
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
									<%//rows are added/removed dynamically %>
								</tbody>
							</table>
							
						</div>
						
						<div class="row mt-4 ml-3">
							<div class='mb-1 col-24'>
								<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="clearBtn" onclick="clearForm(true);">Clear</button>
								<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="saveBtn" onclick="saveAttachDetachAttorney();">Save</button>
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

			$('#caseNum1').focus();
			verifyCaseInput($('#caseNum1').val(), 2);
			loadLocation('<%=courtType%>');
			loadCommonParty();

		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function verifyCaseInput(currentInput, nextInputFieldNumber){
			if($(currentInput).val() != undefined && $(currentInput).val() != "" && $(currentInput).val() != null){
				if($(currentInput).val().length == 9){
					//check to see if this case has already been input into one of the fields
					var caseCount = 1;
					var arr = new Array();
					var duplicate = "";
					$('[id^=caseNum]').each(function(){
						arr[caseCount] = $('#caseNum'+caseCount).val();
						for(i=1; i < caseCount; i++){
							if($('#caseNum'+caseCount).val() == arr[i]){
								duplicate += $('#caseNum'+caseCount).val()+" is already in the list.<br>";
								$('#caseNum'+caseCount).val("");
								$('#caseNum'+caseCount).removeClass("text-danger");
								$('#caseNum'+caseCount).focus();
							}
						}
						caseCount++;
					});				
					if(duplicate != ""){
						appUX.pop.declare("Information", duplicate, 300, 'auto', appUX.pop.styles.INFO);
					}else{
						//check to see if this is a valid case number for the location and court type
						appUX.ajax.call("<%=urlCryptor.urlEncrypt(paramsVerifyCaseInput) %>",
							function (err, data, xhr) {
								var jsonObj = JSON.parse(data);
								if(!err){
									if(!jsonObj.error){
										if($('#caseNum'+nextInputFieldNumber).length < 1){
											$(currentInput).after('<input type="text" class="form-control col-md-3 col-3 ml-3 mb-4" id="caseNum'+nextInputFieldNumber+'" name="caseNum'+nextInputFieldNumber+'" value="" maxlength="9" onkeyup="verifyCaseInput(this, '+ (nextInputFieldNumber+1) +');">');
											$('#caseNum'+nextInputFieldNumber).focus();
										}
										//mark the correct case number input in green
										$(currentInput).removeClass("text-danger").addClass("text-success");
									}else{
										//display an error message and mark the incorrect case number input in red
										$(currentInput).removeClass("text-success").addClass("text-danger");
										$(currentInput).focus();
										message = "Case number "+$(currentInput).val()+" is invalid.";
										appUX.pop.declare("Warning", message, 300, 'auto', appUX.pop.styles.WARNING);
									}
								}
							}, 
							'POST', 
							'&caseNum='+$(currentInput).val()+'&locnCode='+$('#locnCode').val()+'&courtType='+$('#courtType').val()
						);
					}
				}
			}
		}
		
		function addAttachAttorney(){
			if($('#caseNum1').val() != "" && document.getElementById('caseNum1').className.indexOf("text-danger") < 0){
				var data = "locnCode="+$('#locnCode').val()+"&courtType="+$('#courtType').val();
				data += "&parentId="+appUX.pop.getSelfHandle().id;
				var cornId = "attorneySearch";
				var title = "Attorney Lookup";
				var url = "<%=urlCryptor2.urlEncrypt(paramsAddAttachAttorney) %>&"+data;
				var width = 1100;
				var height = 600;
				var style = appUX.pop.styles.LIGHT;
				appUX.pop.modal(cornId, title, url, width, height, style);
			}else{
				message = "At least one Case Number is required before selecting an attorney to attach.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			}

		}
		
		function addDetachAttorney(){
			if($('#caseNum1').val() != "" && document.getElementById('caseNum1').className.indexOf("text-danger") < 0){
				var data = "locnCode="+$('#locnCode').val()+"&courtType="+$('#courtType').val();
				data += "&parentId="+appUX.pop.getSelfHandle().id;
				var cornId = "attorneySearch";
				var title = "Attorney Lookup";
				var url = "<%=urlCryptor2.urlEncrypt(paramsAddDetachAttorney) %>&"+data;
				var width = 1100;
				var height = 600;
				var style = appUX.pop.styles.LIGHT;
				appUX.pop.modal(cornId, title, url, width, height, style);
			}else{
				message = "At least one Case Number is required before selecting an attorney to detach.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			}
		}
		
		function saveAttachDetachAttorney(){
			var data = $('#attachDetachForm').serialize();
			var caseCount = -1; //there will always be one extra field
			$('[id^=caseNum]').each(function(){
				caseCount++;
			});
			data += "&caseCount="+caseCount;
			var valid = true;
			var attyAttachCount = 0;
			var attyDetachCount = 0;
			var message2 = "";
			$('#attyAttachTable tr').each(function(){
				var barNum = $(this).find('td:first-child').html();    
				var barState = $(this).find('td:nth-child(2)').html();
			    if(!isNaN(barNum)){
					attyAttachCount++;
					data += "&barNumAttach"+attyAttachCount+"="+barNum;
					data += "&barStateAttach"+attyAttachCount+"="+barState;
				}
			});
			$('#attyDetachTable tr').each(function(){
				var barNum = $(this).find('td:first-child').html();    
				var barState = $(this).find('td:nth-child(2)').html();
				if(!isNaN(barNum)){
					attyDetachCount++;
					data += "&barNumDetach"+attyDetachCount+"="+barNum;
					data += "&barStateDetach"+attyDetachCount+"="+barState;
				}
			});
			data += "&attyAttachCount="+attyAttachCount;
			data += "&attyDetachCount="+attyDetachCount;
			if($("#caseNum1").val() == ""){
				valid = false;
				message2 += "Case Number is required.<br>";
				$("#caseNum1").focus();
			}
			if($("#partyType").val() == "" && $("#commonParty").val() < 1){
				valid = false;
				message2 += "Please select at least one party type or common party.<br>";
			}
			if($("#partyType").val() != "" && $("#commonParty").val() > 0){
				valid = false;
				message2 += "Please select only one party or common party.<br>";
			}
			if(attyAttachCount <= 0 && attyDetachCount <= 0){
				valid = false;
				message2 += "Please select at least one attorney to attach or detach.<br>";
			}
			if(valid){
				message = "Changes are being processed and may take a while to finish. Window may be closed.";
				appUX.pop.declare("Information", message, 300, 'auto', appUX.pop.styles.INFO);
				clearForm(true);
				appUX.ajax.call("<%=urlCryptor.urlEncrypt(paramsSaveAttachDetachAttorney) %>", 
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
		
		function loadCommonParty(){
			$('#commonParty').find('option').remove();
			$('#commonParty').append($('<option></option>').val(""));
			var courtType = $('#courtType').val();
			var locnCode = $('#locnCode').val();
			if(courtType == "D"){
				<% if(batchCommonPartyDistrictList.size() > 0){
					int partyNumber = 0;
					String partyLastName = "";
					String partyFirstName = "";
					String tempCourtType = "";
					String tempLocnCode = "";
					int counter1 = 1;
					for(CommonPartyBO temp : batchCommonPartyDistrictList){
							partyNumber = (Integer) temp.getPartyNum();
							partyLastName = (String) temp.get(PartyBO.LASTNAME);
							partyFirstName = (String) temp.get(PartyBO.FIRSTNAME);
							tempCourtType = (String) temp.getCourtType();
							tempLocnCode = (String) temp.getLocnCode();
							%>
							if(courtType == '<%=tempCourtType%>' && locnCode == '<%=tempLocnCode%>'){
								$('#commonParty').append(
<%-- 									 $('<option></option>').val("<%=TextUtil.print(partyNumber)%>").html("<%=TextUtil.print(partyLastName)%><%=(!TextUtil.isEmpty(partyFirstName))?", "+TextUtil.print(partyLastName):""%>") --%>
									 $('<option></option>').val("<%=TextUtil.print(partyNumber)%>").html("<%=TextUtil.print(partyLastName)%><%=(!TextUtil.isEmpty(partyFirstName))?", "+TextUtil.print(partyLastName):""%> (Party Number: <%=TextUtil.print(partyNumber)%>)")
								);						
	 						}
							<%
					}
				} %>			
			}
			if(courtType == "J"){
				<% if(batchCommonPartyJusticeList.size() > 0){
					int partyNumber = 0;
					String partyLastName = "";
					String partyFirstName = "";
					String tempCourtType = "";
					String tempLocnCode = "";
					int counter1 = 1;
					for(CommonPartyBO temp : batchCommonPartyJusticeList){
							partyNumber = (Integer) temp.getPartyNum();
							partyLastName = (String) temp.get(PartyBO.LASTNAME);
							partyFirstName = (String) temp.get(PartyBO.FIRSTNAME);
							tempCourtType = (String) temp.getCourtType();
							tempLocnCode = (String) temp.getLocnCode();
							%>
							if(courtType == '<%=tempCourtType%>' && locnCode == '<%=tempLocnCode%>'){
								$('#commonParty').append(
									 $('<option></option>').val("<%=TextUtil.print(partyNumber)%>").html("<%=TextUtil.print(partyNumber)%> : <%=TextUtil.print(partyLastName)%><%=(!TextUtil.isEmpty(partyFirstName))?", "+TextUtil.print(partyLastName):""%> (Party Number: <%=TextUtil.print(partyNumber)%>)")
								);						
	 						}
							<%
					}
				} %>			
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
		
		function clearForm(all){
			$('#caseNum1').val("");
			var caseCount = 2;
			$('[id^=caseNum]').each(function(){
				$('#caseNum'+caseCount).remove();
				caseCount++;
			});
			if(all){
				$('#locnCode').val("<%=locnCode%>");
				$('#courtType').val("<%=courtType%>");
			}
			$('#partyType').val("");
			$('#commonParty').val("");
			$('#attyAttachTable').DataTable().clear().draw();
			$('#attyDetachTable').DataTable().clear().draw();
			$('#caseNum1').focus();			
		}
		
		function appendRow(listBarNum, listState, listLastName, listFirstName, tableName){
			//this is called from corisAttorneyAttachDetachLookup.jsp
			$('#'+tableName).DataTable().row.add([ listBarNum, listState, listLastName, listFirstName, '<i id="'+listState+listBarNum+'" onclick="removeRow('+listBarNum+', \''+listState+'\', \''+tableName+'\')" title="Remove from List" class="text-danger fas fa-minus-circle fa-lg" style="cursor: pointer;"></i>' ]).draw();
		}
		
		function removeRow(barNum, barState, tableName) {
 			$('#'+tableName).DataTable().row( $('#'+barState+barNum).parents('tr') ).remove().draw();
 			$('#casesTable').DataTable().clear().draw();
		}
				
	</script>
</body>
</html>
<%
caseNum = null;
locnCode = null;
courtType = null;
batchCommonPartyDistrictList = null;
batchCommonPartyJusticeList = null;
locationDistrictList = null;
locationJusticeList = null;
urlCryptor = null;
urlCryptor2 = null;
paramsVerifyCaseInput = null;
paramsAddAttachAttorney = null;
paramsAddDetachAttorney = null;
paramsSaveAttachDetachAttorney = null;
%>