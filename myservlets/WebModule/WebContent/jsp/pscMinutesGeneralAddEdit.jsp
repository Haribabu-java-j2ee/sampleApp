<!DOCTYPE html>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.xo.PsAppearanceDefnXO"%>
<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO" %>
<%@page import="java.util.List"%>
<%
	PsReferralHistoryBO historyDetails = request.getAttribute("historyDetails")!=null?(PsReferralHistoryBO) request.getAttribute("historyDetails"):new PsReferralHistoryBO();
	int psReferralId = historyDetails.getPsReferralId();
	int psHistoryId = historyDetails.getPsHistoryId();
	int meId = historyDetails.getMeId();
	int editPsHistoryId = (Integer) request.getAttribute("editPsHistoryId");
	
	List<PsAppearanceDefnBO> appearanceList  = new PsAppearanceDefnBO().where(PsAppearanceDefnBO.REMOVEDFLAG,Exp.NOT_EQUALS,"Y").orderBy(PsAppearanceDefnBO.PSAPPEARANCEDESCR).search();
	String mode = editPsHistoryId > 0?"updategeneral":"addgeneral";
	List<PsPhaseDefnBO> phases = new PsPhaseDefnBO().orderBy(PsPhaseDefnBO.PSPHASECODE).search();
	int refPhaseId = new PsReferralBO().where(PsReferralBO.PSREFERRALID,psReferralId).find(PsReferralBO.PSPHASEDEFNID).getPsPhaseDefnId();
	int intCaseNum = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId)
											.find(PsReferralCaseBO.CASEIDENTIFIER).getCaseidentifier();
	
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Minutes - General Add/Edit</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
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
				<strong>Edit Details</strong>
			</div>
			<div class="card-body">
				<form action="#" method="post">
				<input type="hidden" name="psReferralId" id="psReferralId" value="<%=psReferralId %>">
				<input type="hidden" name="meId" id="meId" value="<%=meId %>">
				<input type="hidden" name="psHistoryId" id="psHistoryId" value="<%=editPsHistoryId %>">
				<div class="row">
					<div class="input-group mb-1 col-12">
						<label class="col-8" style="width: 33%; text-align: right;">Appearance Type<span class="text-danger">*</span></label>
						<select class="col-16 form-control" id="appearanceType" name="appearanceType" onchange="updateEventTitle(this.value);">
							<% for(PsAppearanceDefnBO vo:appearanceList){ %>
								<option value="<%=vo.getPsActionDefnId() %>" <%=vo.getPsActionDefnId()==historyDetails.getPsActionDefnId()?"selected":"" %>><%=vo.getPsAppearanceDescr() %></option>
							<%} %>
						</select>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Appearance Type is required.</span></div>				
					</div>
					<div class="input-group mb-1 col-12">
						<label class="col-8" style="width: 33%; text-align: right;">Description<span class="text-danger">*</span></label>	
						<input type="text" class="col-16 form-control" id="pscMinuteEventTitle" name="pscMinuteEventTitle" onkeyup="characterCount(this, 'Title');" value="<%=TextUtil.print(historyDetails.getPsMinutesEventTitle()) %>" placeholder="Enter Event Title" maxlength="40">
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Event Title is required and may only contain 40 standard characters and numbers.</span></div>
					</div>
				</div>
				<div class="row">
					<div class="input-group date mb-1 col-12" id='datetimepicker'>
						<label class="col-8" style="width: 33%; text-align: right;">Hearing Date<span class="text-danger">*</span></label>
						<input type="text" class="col-16 form-control" id="hearingDate" name="hearingDate" value="<%= TextUtil.printDate(historyDetails.getPsActionDate()) %>" disabled>
						<!-- <span class="input-group-text input-group-addon">
							<span class="fa fa-calendar"></span>
						</span>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Hearing Date must be selected.</span></div> -->
					</div>
					<div class="input-group mb-1 col-12">
						<label class="col-8" style="width: 33%; text-align: right;">Current Phase<span class="text-danger">*</span></label>
						<select class="col-16 form-control" id="phaseId" name="phaseId">
							<% for(PsPhaseDefnBO fase:phases){ %>
							<option value="<%=fase.getPsPhaseDefnId() %>" <%=fase.getPsPhaseDefnId()==refPhaseId?"selected":"" %>><%=fase.getPsPhaseDescr() %></option>
							<%} %>
						</select>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Current Phase must be selected.</span></div>
					</div>
				</div>
				<div class="row">
					<div class="input-group mb-1 col-24">
						<label class="col-4" style="width: 33%; text-align: right;">Note<span class="text-danger"></span></label>
						<textarea class="col-20 form-control" rows="3" maxlength="250" id="historyNote" name="historyNote" onkeyup="characterCount(this, 'Note');"><%=TextUtil.print(historyDetails.getPsActionNote()) %></textarea>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 83%;">Note may only contain 250 standard characters and numbers.</span></div>
						<div style="margin: 0; padding: 0; width: 100%;"><div class="float-right"><span id="characterCountIdNote">0</span>/250</div></div>
					</div>
				</div>
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn" onclick="appUX.pop.getSelfHandle().close();">Cancel</button>
					<button type="button" id="cancelBtn" class="btn btn-primary ml-2 mt-2 mr-2 float-right" title="Save" onclick="saveForm(this);">Save</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			$('#datetimepicker').datetimepicker({ 
				format: 'MM/DD/YYYY'
			});
			displayReferralHeader('<%=intCaseNum%>');
			if('<%="addgeneral".equals(mode)%>'=='true'){
				$('#pscMinuteEventTitle').val($('#appearanceType option:selected').text());
			}
			$('#appearanceType').on("change", function(){
				if('<%="addgeneral".equals(mode)%>'=='true'){
					$('#pscMinuteEventTitle').val($('#appearanceType option:selected').text());
				}
			});
			$('#characterCountIdNote').html(historyNote.value.length);
		});

		function characterCount(elem, elemName) {
			$('#characterCountId'+elemName).html(elem.value.length);
		}
		function saveForm(btn){
			btn.disabled = 'disabled';
			if(validateData()){
				appUX.ajax.call('/CorisWEB/PSCMinutesServlet?mode=<%=mode%>', 
					function (err, data, xhr) { 
						if(err){
							var width = 300;
							var height = "auto";
							var style = appUX.pop.styles.DANGER;
						 	var message = "";
						 	message = xhr.responseText;
						 	appUX.pop.notify(message, width, height, style);
						}else if('<%="addgeneral".equals(mode)%>'=='true'){
							parent.displayList();
						}else if('<%="updategeneral".equals(mode)%>'=='true'){
							appUX.pop.refreshCornFrame("minutesDetails", true);
						}
						appUX.pop.getSelfHandle().close();
					}, 
					'POST', 
					$('form').serialize()
				);
			}else {
				btn.disabled = '';
			}
		}
		function validateData(){
			var valid = true;
			$('#pscMinuteEventTitle').removeClass("is-invalid").addClass("is-valid");
			$('#appearanceType').removeClass("is-invalid").addClass("is-valid");
			$('#hearingDate').removeClass("is-invalid").addClass("is-valid");
			$('#phaseId').removeClass("is-invalid").addClass("is-valid");
			$('#historyNote').removeClass("is-invalid").addClass("is-valid");

			res = cleanCharacters(document.getElementById("pscMinuteEventTitle")); //removes invalid characters
			if(!$('#pscMinuteEventTitle').val() || !res){
				valid = false;
				$('#pscMinuteEventTitle').removeClass("is-valid").addClass("is-invalid");
			}

			if(!$('#appearanceType').val()){
				valid = false;
				$('#appearanceType').removeClass("is-valid").addClass("is-invalid");
			}

			if(!$('#hearingDate').val()){
				valid = false;
				$('#hearingDate').removeClass("is-valid").addClass("is-invalid");
			}
			
			if(!$('#phaseId').val()){
				valid = false;
				$('#phaseId').removeClass("is-valid").addClass("is-invalid");
			}
			
			res = cleanCharacters(document.getElementById("historyNote")); //removes invalid characters
			if($('#historyNote').val() && $('#historyNote').val().length > 250){
				valid = false;
				$('#historyNote').removeClass("is-valid").addClass("is-invalid");
			}

			return valid;
		}
		
		function updateEventTitle(actionId){
			appUX.ajax.call('/CorisWEB/PSCMinutesServlet?mode=updatetitle', 
					function (err, data, xhr) { 
						if(err){
							var width = 300;
							var height = "auto";
							var style = appUX.pop.styles.DANGER;
						 	var message = "";
						 	message = xhr.responseText;
						 	appUX.pop.notify(message, width, height, style);
						}else {
							$('#pscMinuteEventTitle').val(xhr.responseText);
						}
					}, 
					'POST', 
					'actionId='+actionId
				);
		}
	</script>
</body>
</html>
