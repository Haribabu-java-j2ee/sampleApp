<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctioncourtxref.PsSanctionCourtXrefBO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanction.PsSanctionBO"%>
<%

	SimpleDateFormat dateFormat = Constants.dateFormatCoris;
	PsReferralHistoryBO historyDetails = (PsReferralHistoryBO) request.getAttribute("historyDetails");
	int psReferralId = historyDetails.getPsReferralId();
	int psHistoryId = historyDetails.getPsHistoryId();
	Date psSanctionDate = historyDetails.getPsActionDate();
	int meId = historyDetails.getMeId();
	int editPsSanctionId = (Integer) request.getAttribute("editPsSanctionId");
	List<PsSanctionCourtXrefBO> sanctionsList = (List<PsSanctionCourtXrefBO>) request.getAttribute("sanctionsList");
	List<PsSanctionBO> currentSanctions = (List<PsSanctionBO>) request.getAttribute("currentSanctions");
	int intCaseNum = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId)
											.find(PsReferralCaseBO.CASEIDENTIFIER).getCaseidentifier();
	int psSanctionId = 0;
	int psSanctionDefnId = 0;
	String psSanctionNote = "";
	int psSanctionAmount = 0;
	String psSanctionUnit = "";
	for(PsSanctionBO currentSanctionsBO : currentSanctions){
		psSanctionId = currentSanctionsBO.getPsSanctionId();
		if(psSanctionId == editPsSanctionId){
			psSanctionNote = currentSanctionsBO.getPsSanctionNote();
			psSanctionAmount = currentSanctionsBO.getPsSanctionAmount();
			psSanctionUnit = currentSanctionsBO.getPsSanctionUnit();
			psSanctionDefnId = currentSanctionsBO.getPsSanctionDefnId();
			break;
		}
	}

%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Minutes - Add/Edit</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Details</strong>
			</div>
			<div class="card-body">
				<div id="displayReferralHeader"></div>
			</div>
			<div class="card-header card-footer">
				<strong>Add Sanction</strong>
			</div>
			<div class="card-body" >
				<% if(sanctionsList.size() > 0){ %>			
				<form id="sanctionForm" name="sanctionForm" novalidate>
					<div class="row">
						<div class='input-group mb-1 col-12'>
							<label class="col-8" style="text-align: right;">Sanction<span class="text-danger">*</span></label>
							<select class="col-16 form-control" id="sanction" name="sanction">
								<option value=""></option>
								<%
								String psSanctionDescr = "";
								String psSanctionCode = "";
								int listPsSanctionDefnId = 0;
								int tempPsSanctionDefnId = 0;
								int tempPsSanctionId = 0;
								boolean show = true;
								for(PsSanctionCourtXrefBO sanctionsListBO : sanctionsList) {
									psSanctionDescr = (String) sanctionsListBO.get(PsSanctionDefnBO.PSSANCTIONDESCR);
									psSanctionCode = (String) sanctionsListBO.get(PsSanctionDefnBO.PSSANCTIONCODE);
									listPsSanctionDefnId = sanctionsListBO.getPsSanctionDefnId();
									for(PsSanctionBO currentSanctionsBO : currentSanctions){
										tempPsSanctionDefnId = currentSanctionsBO.getPsSanctionDefnId();
										tempPsSanctionId = currentSanctionsBO.getPsSanctionId();
										if(tempPsSanctionDefnId == listPsSanctionDefnId && tempPsSanctionId != editPsSanctionId){
											show = false;
										}
									}
									if(show){
										%>
										<option value="<%=listPsSanctionDefnId %>" <%=listPsSanctionDefnId==psSanctionDefnId?"selected":"" %>><%=psSanctionDescr %></option>
										<%
									}		
									show = true;
								} %>
							</select>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Sanction is required.</span></div>
						</div>
						<div class='input-group date mb-1 col-12'>
							<label class="col-8" style="text-align: right;">Date<span class="text-danger">*</span></label>
							<input type="text" class="col-16 form-control" value="<%=dateFormat.format(psSanctionDate) %>" maxlength="10" disabled>
						</div>
						<div class='input-group mb-1 col-12'>
						</div>
					</div>
					<div class="row" id="displayAmountUnit" style=display:none;>
						<div class='input-group mb-1 col-12'>
							<label class="col-8" style="text-align: right;">Amount</label>
							<input type="text" class="col-16 form-control" id="amount" name="amount" value='<%=psSanctionAmount>0?psSanctionAmount:"" %>' maxlength="10">
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Amount may only contain numbers.</span></div>
						</div>
						<div class='input-group mb-1 col-12'>
							<label class="col-8" style="text-align: right;">Unit</label>
							<select class="col-16 form-control" id="unit" name="unit" >
								<option value=""></option>
								<option value="Hours" <%="Hours".equals(psSanctionUnit)?"selected":"" %>>Hours</option>
								<option value="Days" <%="Days".equals(psSanctionUnit)?"selected":"" %>>Days</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class='input-group mb-1 col-24'>
							<label class="col-4" style="text-align: right;">Note</label>
							<textarea class="col-20 form-control" id="note" name="note" rows="3" onkeyup="characterCount(this);" maxlength="250"><%="".equals(psSanctionNote)?"":TextUtil.print(psSanctionNote) %></textarea>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 83%;">Note may only contain standard characters and numbers or be left blank.</span></div>
							<div style="margin: 0; padding: 0; width: 100%;"><div class="float-right"><span id="characterCountId">0</span>/250</div></div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn">Cancel</button>
						<% if(editPsSanctionId > 0){ %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="sanctionUpdateBtn">Save</button>
						<% } else { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="sanctionInsertBtn">Save & New</button>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="sanctionSaveBtn">Save</button>
						<% } %>
					</div>
				</form>
				<% }else{ %>
				<span class="text-warning">No sanctions are available for this particular court.</span>
				<% } %>
			</div>			
		</div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			$('#sanctionSaveBtn').on('click', function(){
				validateSanction(false);
			});
			$('#sanctionUpdateBtn').on('click', function(){
				validateSanction(false);
			});
			$('#sanctionInsertBtn').on('click', function(){
				validateSanction(true);
			});
			$('#cancelBtn').on('click', function(){
				closePop();
			});
			$('#sanction').on('change', function(){
				if($('#sanction option:selected').val() == 1 || $('#sanction option:selected').val() == 2){ //1=JAIL or 2=COMMSERV
					$('#displayAmountUnit').show();
				}else{
					$('#displayAmountUnit').hide();
				}
			});
			if($('#sanction option:selected').val() == 1 || $('#sanction option:selected').val() == 2){ //1=JAIL or 2=COMMSERV
				$('#displayAmountUnit').show();
				$('#amount').val("<%=psSanctionAmount%>");
				$('#unit').val("<%=psSanctionUnit%>");
			}else{
				$('#displayAmountUnit').hide();
				$('#amount').val("");
				$('#unit').val("");
			}
			displayReferralHeader('<%=intCaseNum%>');
		});

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		function characterCount(elem) {
			$('#characterCountId').html(elem.value.length);
		}
		function validateSanction(startNew){
			$('#sanction').removeClass("is-invalid");
			$('#amount').removeClass("is-invalid");
			$('#unit').removeClass("is-invalid");
			$('#note').removeClass("is-invalid");
			var valid = true;
			var message = "";
            if($('#sanction').val() == "") {
                valid = false;
                $('#sanction').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#sanction').removeClass("is-invalid").addClass("is-valid");
            }
            
            res = cleanCharactersDigitsOnly(document.getElementById("amount")); //removes everything but digits
            if(!res || $('#amount').val().length > 10) {
                valid = false;
                $('#amount').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#amount').removeClass("is-invalid").addClass("is-valid");
            }
            res = cleanCharacters(document.getElementById("unit")); //removes invalid characters
            if(!res || $('#unit').val().length > 10) {
                valid = false;
                $('#unit').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#unit').removeClass("is-invalid").addClass("is-valid");
            }
            res = cleanCharacters(document.getElementById("note")); //removes invalid characters
            if(!res || $('#note').val().length > 250) {
                valid = false;
                $('#note').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#note').removeClass("is-invalid").addClass("is-valid");
            }
	        if(valid){
	        	<% if(editPsSanctionId > 0){ %>
	        		updateSanction();
	        	<% }else{ %>
	        		insertSanction(startNew);
	        	<% } %>
				$('#sanction').removeClass("is-valid");
				$('#amount').removeClass("is-valid");
				$('#unit').removeClass("is-valid");
				$('#note').removeClass("is-valid");
			}
		}
		function insertSanction(startNew){
			var data = $('#sanctionForm').serialize();
			appUX.ajax.call("/CorisWEB/PSCMinutesServlet", 
				function (err, data, xhr) {
					var jsonObj = JSON.parse(data);
					if (err) {
						message = "There was an error. "+xhr.responseText;
						appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
					}else if (jsonObj.error) {
						message = jsonObj.errorMessage;
						appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
					} else {
						message = "Changes have been saved.";
						appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
						appUX.pop.refreshCornFrame("minutesDetails", true);
						if(startNew){
							appUX.pop.refreshCornFrame("sanctionInsert", true);
						}else{
							closePop();
						}
					}
				}, 
				'POST', 
				'psReferralId=<%=psReferralId%>&psHistoryId=<%=psHistoryId%>&date=<%=dateFormat.format(psSanctionDate) %>&mode=insertSanction&'+data
			);
		}
		function updateSanction(){
			var data = $('#sanctionForm').serialize();
			appUX.ajax.call("/CorisWEB/PSCMinutesServlet", 
				function (err, data, xhr) {
					var jsonObj = JSON.parse(data);
					if (err) {
						message = "There was an error. "+xhr.responseText;
						appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
					}else if (jsonObj.error) {
						message = jsonObj.errorMessage;
						appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
					} else {
						message = "Changes have been saved.";
						appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
						appUX.pop.refreshCornFrame("minutesDetails", true);
						closePop();
					}
				}, 
				'POST', 
				'psReferralId=<%=psReferralId%>&psHistoryId=<%=psHistoryId%>&date=<%=dateFormat.format(psSanctionDate) %>&editPsSanctionId=<%=editPsSanctionId%>&mode=updateSanction&'+data
			);
		}
	</script>
</body>
</html>
