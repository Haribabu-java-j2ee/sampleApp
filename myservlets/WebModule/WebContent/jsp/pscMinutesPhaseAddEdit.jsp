<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO" %>
<%@page import="java.util.List"%>
<%
	String currentPhase = (String) request.getAttribute("currentPhase");
	if(currentPhase == null){ 
		currentPhase = ""; 
	}
	int psReferralId = (Integer) request.getAttribute("psReferralId");
	int psHistoryId = (Integer) request.getAttribute("psHistoryId");
	List<PsPhaseDefnBO> phases = new PsPhaseDefnBO().orderBy(PsPhaseDefnBO.PSPHASECODE).search();
	int refPhaseId = new PsReferralBO().where(PsReferralBO.PSREFERRALID,psReferralId).find(PsReferralBO.PSPHASEDEFNID).getPsPhaseDefnId();
	PsReferralHistoryBO historyDetails = request.getAttribute("historyDetails")!=null?(PsReferralHistoryBO) request.getAttribute("historyDetails"):new PsReferralHistoryBO();
	int editPsHistoryId = (Integer) request.getAttribute("editPsHistoryId");
	String mode = editPsHistoryId > 0?"updategeneral":"addgeneral";
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
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
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
				<strong>Edit Phase</strong>
			</div>
			<div class="card-body">
				<form class="form-inline" action="#" method="post">
				<input type="hidden" name="psReferralId" id="psReferralId" value="<%=psReferralId %>">
				<input type="hidden" name="psHistoryId" id="psHistoryId" value="<%=editPsHistoryId %>">
				<div class="container-fluid">
					<div class="row">
						<div class="col-16 offset-4">
							<div class="input-group m-4">
								<label for="currentPhase" style="width: 40%; text-align: right; padding-right: 3px;">Current Phase:</label>
								<select class="form-control" id="phaseId" name="phaseId">
									<% for(PsPhaseDefnBO fase:phases){ %>
									<option value="<%=fase.getPsPhaseDefnId() %>" <%=fase.getPsPhaseDefnId()==refPhaseId?"selected":"" %>><%=fase.getPsPhaseDescr() %></option>
									<%} %>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" id="cancelBtn" onclick="appUX.pop.getSelfHandle().close();">Cancel</button>
					<button type="button" id="savebtn" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save" onclick="javascript:phaseChange();">Save</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			displayReferralHeader('<%=intCaseNum%>');
		});
		
		function phaseChange(){
			var psHistoryId = <%=psHistoryId%>;
			var psPhaseId = document.getElementById("phaseId").value;
				appUX.ajax.call('/CorisWEB/PSCMinutesServlet', 
					function (err, data, xhr) { 
						if(err){
							var width = 300;
							var height = "auto";
							var style = appUX.pop.styles.DANGER;
						 	var message = "";
						 	message = xhr.responseText;
						 	appUX.pop.notify(message, width, height, style);
						}else {
							parent.location.reload();
						}
						appUX.pop.getSelfHandle().close();
					}, 
					'POST', 
					"psReferralId=<%=psReferralId%>&psHistoryId="+psHistoryId+"&psPhaseId="+psPhaseId+"&mode=addPhase&hearingDate=<%=historyDetails.getPsActionDate()%>"
				);
		
		}
		
		
	</script>
</body>
</html>
