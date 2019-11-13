<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil.Role"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/fragments/NoCache.jspf"%>
<html>
<head>
<title>pscReferralActions</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<%
	int psReferralId = URLEncryption.getParamAsInt(request,"psReferralId");
	int currentStatusId = new PsReferralBO().where(PsReferralBO.PSREFERRALID,psReferralId).includeFields(PsReferralBO.PSSTATUSDEFNID).find(PsReferralBO.PSSTATUSDEFNID).getPsStatusDefnId();
	List<PsReferralHistoryBO> actionHist = new PsReferralHistoryBO()
										.where(PsReferralHistoryBO.PSREFERRALID,psReferralId)
										.where(PsReferralHistoryBO.PSACTIONDEFNID, Exp.NOT, Exp.in(new PsAppearanceDefnBO().includeFields(PsAppearanceDefnBO.PSACTIONDEFNID)))
										.orderBy(PsReferralHistoryBO.PSACTIONDATE,DirectionType.DESC).search();
	int intCaseNum = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId)
											.find(PsReferralCaseBO.CASEIDENTIFIER).getCaseidentifier();
	String parentCornId = URLEncryption.getParamAsString(request, "parentCornId");
	int meId = URLEncryption.getParamAsInt(request, "meId");
 %>
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Referral Statuses (<%=psReferralId %>)</strong>
			</div>
			<div class="card-body">
				<div id="displayReferralHeader"></div>
			</div>
			<div class="card-header card-footer">
				<strong>Status History</strong>
				<button id="actionInsertBtn" title="Add Status" onclick="addAction(<%=psReferralId %>);" class="btn btn-primary float-right" style="margin: 0;">Add Status</button>
			</div>
			<div class="card-body">
				<% if(actionHist.size() > 0){ %>
				<table class="table table-borderless table-sm">
					<thead class="bg-dark text-white">
						<tr>
							<th style="width: 35%;">Status</th>
							<th style="width: 15%;">Status Date</th>
							<th style="width: 33%;">Status Note</th>
							<th style="width: 10%;">Clerk</th>
							<th style="width: 7%;"></th>
						</tr>
					</thead>
					<tbody>
						<%  
							PsActionDefnBO action = null;
							String statusDescr = null;
							boolean currentStatus = false;
							boolean found = false;
							for(PsReferralHistoryBO vo:actionHist){ 
								action = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID,vo.getPsActionDefnId()).find();
								if(!found){
									currentStatus = action.getDefaultPsStatusDefnId() == currentStatusId;
									found = currentStatus;
								}else {
									currentStatus = false;
								}
								
								statusDescr = new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSDEFNID, action.getDefaultPsStatusDefnId())
											.includeFields(PsStatusDefnBO.PSSTATUSDESCR).setReturnNull(false).find(PsStatusDefnBO.PSSTATUSDESCR).getPsStatusDescr();
						%>
						
						<tr>
							<td><%=statusDescr %></td>
							<td><%=TextUtil.printDate(vo.getPsActionDate()) %></td>
							<td><%=TextUtil.print(vo.getPsActionNote()) %></td>
							<td><%=TextUtil.print(vo.getPsLogname()) %></td>
							<td style="white-space: nowrap;">
								<i id="actionUpdateButton" title="Edit" onclick="editAction(<%=psReferralId %>, <%=vo.getPsHistoryId() %>, <%=vo.getPsActionDefnId() %>)" class="<text-warning far fa-edit fa-lg fa-fw"
									style="cursor: pointer;"></i> 
								<% if(RoleUtil.hasRole(request,Role.IT_ADMIN)){ %>
								<i id=actionDeleteButton" title="<%=currentStatus?"Current Status":"Delete"%>" onclick="<%=currentStatus?"":"deleteAction(" + vo.getPsHistoryId() + ",'" + action.getPsActionDescr() + "')"%>"
									class="<%=currentStatus?"text-muted":"text-danger"%> fas fa-minus-circle fa-lg fa-fw" style="cursor: pointer;"></i>
								<%} %>
							</td>
						</tr>
						<%} %>
					</tbody>
				</table>
				<%} %>
			</div>
		</div>
	</div>
	
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			displayReferralHeader('<%=intCaseNum%>');
		});
		
		function addAction(psReferralId){
			editAction(psReferralId)
		}
		function editAction(psReferralId, psHistoryId, actionId){
			var cornId = "addAction";
			var title = "Add Status Action";
			if(psHistoryId && actionId){
				cornId = "editAction";
				title = "Edit Status Action";
			}
			var url = "jsp/pscEditReferralAction.jsp?psHistoryId="+psHistoryId+"&actionId="+actionId+"&psReferralId="+psReferralId + "&parentCornId=<%=parentCornId%>&meId=<%=meId%>";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function deleteAction(psHistoryId,actionDescr){
			var title = "Delete Action";
			var message = "Would you like to delete Action "+ actionDescr + "?";
			var trueText = "Delete";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		
			function confirmCallback(action) {
				if (action) {
					var url = "/CorisWEB/PSCReferralActionServlet";
					appUX.ajax.call(url, 
						function (err, data, xhr) { 
							appUX.pop.refreshCornFrame("viewActions", true);
							message = "Changes have been saved.";
							appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
							corn.close(); 
						}, 
						'POST', 
						'psHistoryId='+psHistoryId+'&mode=deleteAction'
					);
				} else {
					corn.close();
				}
			}
		}
	
	</script>
	
</body>
</html>