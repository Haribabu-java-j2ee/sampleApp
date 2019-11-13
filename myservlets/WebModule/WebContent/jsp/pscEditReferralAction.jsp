<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.constants.PSCDefnConstants"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.problemsolving.xo.PsStatusActionXrefXO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pstermination.PsTerminationBO"%>
<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.problemsolving.xo.PsActionDefnXO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psterminationdefn.PsTerminationDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/fragments/NoCache.jspf"%>
<html>
<head>
<title>pscEditReferralAction</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<%
	List<PsActionDefnBO> actionList = null;
	int psReferralId = URLEncryption.getParamAsInt(request, "psReferralId");
	int psHistoryId = URLEncryption.getParamAsInt(request, "psHistoryId");
	int psActionId = URLEncryption.getParamAsInt(request, "actionId");
	PsReferralBO ref = new PsReferralBO().where(PsReferralBO.PSREFERRALID,psReferralId).find();
	String mode = psHistoryId==0?"Add":"Edit";
	PsReferralHistoryBO historyBO = null;
	String actionDescr = "";
	if("Edit".equalsIgnoreCase(mode)){
		historyBO = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSHISTORYID,psHistoryId).find();
		actionDescr = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID, psActionId).find(PsActionDefnBO.PSACTIONDESCR).getPsActionDescr();
	}else {
		historyBO = new PsReferralHistoryBO();
		historyBO.setPsReferralId(psReferralId);
		historyBO.setPsActionDate(Calendar.getInstance().getTime());
		actionList = PsStatusActionXrefXO.getStatusActions(ref.getPsStatusDefnId()); 
		/* actionList = PsStatusActionXrefXO.getActionsWithDefaultStatus(); */
	}
	List<PsTerminationDefnBO> reasons = new PsTerminationDefnBO().orderBy(PsTerminationDefnBO.PSTERMINATIONDESCR).search();
	int intCaseNum = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId)
											.find(PsReferralCaseBO.CASEIDENTIFIER).getCaseidentifier();
	HashMap<String, Integer> codeIdMap = PsActionDefnXO.getPsActionDefnCodeIdMap();
	int termDefnId = new PsTerminationBO().where(PsTerminationBO.PSHISTORYID,historyBO.getPsHistoryId()).find(PsTerminationBO.PSTERMINATIONDEFNID).getPsTerminationDefnId();
	Date referredDate = new PsReferralBO().where(PsReferralBO.PSREFERRALID,psReferralId).find(PsReferralBO.PSREFERRALDATE).getPsReferralDate();
	String parentCornId = URLEncryption.getParamAsString(request, "parentCornId");
	int meId = URLEncryption.getParamAsInt(request, "meId");

 %>
</head>
<body>
<div class="container-fluid">
	<div class="card m-2">
		<div class="card-header">
			<strong>Status Details (<%=psReferralId %>)</strong>
		</div>
		<div class="card-body">
			<div id="displayReferralHeader"></div>
		</div>
		<div class="card-header card-footer">
			<strong><%=mode %> Referral Status</strong>
		</div>
		<div class="card-body" >			
			<form id="actionForm" name="actionForm" novalidate>
				<input type="hidden" id="psHistoryId" name="psHistoryId" value="<%=historyBO.getPsHistoryId() %>">
				<input type="hidden" id="psReferralId" name="psReferralId" value="<%=historyBO.getPsReferralId() %>">
				<div class="row">
						<div class='input-group mb-1 col-12'>
							<label class="col-10" style="text-align: right;">Status Actions<span class="text-danger">*</span></label>
							<%if("edit".equalsIgnoreCase(mode)) {%>
								<input type="text" class="col-16 form-control" value="<%=actionDescr %>" disabled>							
							<%}else{ %>
							<select class="col-16 form-control" id="actionId" name="actionId" onchange="toggleTermination(this.value)">
								<option value=""></option>
								<%
								for(PsActionDefnBO stvo:actionList){ %>
										<option value="<%=stvo.getPsActionDefnId() %>" <%=stvo.getPsActionDefnId()==historyBO.getPsActionDefnId()?"selected":"" %>><%=stvo.getPsActionDescr() %></option>
								<% } %>
							</select>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Status Action is required.</span></div>
							<%} %>
						</div>
						<div class='input-group date mb-1 col-12'>
							<div class='col input-group date' id='datetimepicker1'>
								<label class="col-8" style="text-align: right;">Status Date<span class="text-danger">*</span></label>
								<input type="text" id="actionDate" name="actionDate" class="col-16 form-control" value="<%=Constants.dateFormatCoris.format(historyBO.getPsActionDate()) %>" maxlength="10">
								<span class="input-group-text input-group-addon">
									<span class="fa fa-calendar"></span>
								</span>
								<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Action date is required and must be between the referral date and today</span></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div id="terminationReason" name="terminationReason" class="input-group mb-1 col-12" <%=termDefnId>0?"":"style=display:none;"%>>
							<label class="col-10" style="text-align: right;">Termination Reason<span class="text-danger">*</span></label>
							<select class="form-control" id="terminateId" name="terminateId" <%="edit".equalsIgnoreCase(mode)?"disabled":""%>>
								<option value="0"></option>
								<% for(PsTerminationDefnBO tmn:reasons){ %>
								<option value="<%=tmn.getPsTerminationDefnId() %>" <%=termDefnId==tmn.getPsTerminationDefnId()?"selected":"" %>><%=tmn.getPsTerminationDescr() %></option>
								<%} %>
							</select>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Terminate reason is required.</span></div>
						</div>
					</div> 
					<div class="row">
						<div class='input-group mb-1 col-24'>
							<label class="col-5" style="text-align: right;">Note</label>
							<textarea class="col-20 form-control" id="note" name="note" rows="3" maxlength="250" onkeyup="characterCount(this);"><%=TextUtil.print(historyBO.getPsActionNote()) %></textarea>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 83%;">Note may only contain standard characters and numbers or be left blank.</span></div>
							<div style="margin: 0; padding: 0; width: 100%;"><div class="float-right"><span id="characterCountId">0</span>/250</div></div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn">Cancel</button>
						<% if(psHistoryId > 0){ %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="actionUpdateBtn">Save</button>
						<% } else { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="actionInsertBtn">Save & New</button>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="actionSaveBtn">Save</button>
						<% } %>
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
<script src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript">
	$(document).ready(function(){
			$('#datetimepicker1').datetimepicker({ 
				format: 'MM/DD/YYYY',
				icons: {
	                    previous: "fa fa-angle-left",
	                    next: "fa fa-angle-right"
	                }
			});
			$('#actionSaveBtn').on('click', function(){
				validateAction(false);
			});
			$('#actionUpdateBtn').on('click', function(){
				validateAction(false);
			});
			$('#actionInsertBtn').on('click', function(){
				validateAction(true);
			});
			$('#cancelBtn').on('click', function(){
				closePop();
			});
			displayReferralHeader('<%=intCaseNum%>');
		});

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		function characterCount(elem) {
			$('#characterCountId').html(elem.value.length);
		}
		function validateAction(startNew){
			$('#actionId').removeClass("is-invalid");
			$('#actionDate').removeClass("is-invalid");
			$('#note').removeClass("is-invalid");
			$('#terminateId').removeClass("is-invalid");
			var valid = true;
            if($('#actionId').val() == "" && '<%=mode.equalsIgnoreCase("add")%>'=='true') {
                valid = false;
				$('#actionId').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#actionId').removeClass("is-invalid").addClass("is-valid");
            }
            var actDate = $('#actionDate').val();
			if(!actDate || new Date(actDate) > new Date() || new Date(actDate) < new Date('<%=referredDate%>')){
				valid = false;
				$('#actionDate').removeClass("is-valid");
				$('#actionDate').addClass("is-invalid");
			}
            res = cleanCharacters(document.getElementById("note")); //removes invalid characters
            if(!res || $('#note').val().length > 250) {
                valid = false;
                $('#note').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#note').removeClass("is-invalid").addClass("is-valid");
            }
            if($('#terminationReason').css('display') != 'none' && $('#terminateId').val()=='0'){
				valid = false;
				$('#terminateId').removeClass("is-valid");
				$('#terminateId').addClass("is-invalid");
			}
	        if(valid){
	        	<% if(psActionId > 0){ %>
	        		updateAction();
	        	<% }else{ %>
	        		insertAction(startNew);
	        	<% } %>
				$('#actionId').removeClass("is-valid");
				$('#note').removeClass("is-valid");
			}
		}
		function insertAction(startNew){
			appUX.ajax.call("/CorisWEB/PSCReferralActionServlet", 
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
						appUX.pop.refreshCornFrame("viewActions", true);
						appUX.pop.refreshCornFrame("<%=parentCornId%>",true,'{"from_edit":"true","meId":"<%=meId%>"}');
						if(startNew){
							appUX.pop.refreshCornFrame("addAction", true);
							appUX.pop.refreshCornFrame("<%=parentCornId%>",true,'{"from_edit":"true","meId":"<%=meId%>"}');
						}else{
							closePop();
						}
					}
				}, 
				'POST', 
				$('form').serialize()
			);
		}
		function updateAction(){
			appUX.ajax.call("/CorisWEB/PSCReferralActionServlet", 
				function (err, data, xhr) {
					var jsonObj = JSON.parse(data);
					if (err) {
						message = "There was an error. "+xhr.responseText;
						appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
					}else if (jsonObj.erro0r) {
						message = jsonObj.errorMessage;
						appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
					} else {
						message = "Changes have been saved.";
						appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
						appUX.pop.refreshCornFrame("viewActions", true);
						appUX.pop.refreshCornFrame("<%=parentCornId%>",true,'{"from_edit":"true","meId":"<%=meId%>"}');
						closePop();
					}
				}, 
				'POST', 
				$('form').serialize()
			);
		}
		
		function toggleTermination(actionId){
		
			if(<%=codeIdMap.get(PSCDefnConstants.ACTION_TERMINATE_CODE) %> == actionId){
				$('#terminationReason').show();
				$('#terminationNote').show();
			}else{
				$('#terminationReason').hide();
				$('#terminationNote').hide();
			}
			
		}
</script>
</body>
</html>