<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.constants.PSCDefnConstants"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewardcourtxref.PsRewardCourtXrefBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctioncourtxref.PsSanctionCourtXrefBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreward.PsRewardBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanction.PsSanctionBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphase.PsPhaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>

<%
	PsReferralBO referralDetails = (PsReferralBO) request.getAttribute("referralDetails");
	List<PsRewardCourtXrefBO> rewardsList = (List<PsRewardCourtXrefBO>) request.getAttribute("rewardsList");
	List<PsSanctionCourtXrefBO> sanctionsList = (List<PsSanctionCourtXrefBO>) request.getAttribute("sanctionsList");
	PsReferralHistoryBO historyDetails = (PsReferralHistoryBO) request.getAttribute("historyDetails");
	List<PsRewardBO> currentRewards = (List<PsRewardBO>) request.getAttribute("currentRewards");
	List<PsSanctionBO> currentSanctions = (List<PsSanctionBO>) request.getAttribute("currentSanctions");
	List<PsPhaseBO> phaseDetails = (List<PsPhaseBO>) request.getAttribute("phaseDetails");
	int terminatedStatusId =new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSCODE,PSCDefnConstants.STATUS_TERMINATE_CODE).find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
	DateFormat dateFormat = Constants.dateFormatCoris;
	boolean canEdit = referralDetails.getPsStatusDefnId() != terminatedStatusId && historyDetails.getDmDocid() == 0;
	String checkedStatus = "";
	int intCaseNum = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,referralDetails.getPsReferralId())
											.find(PsReferralCaseBO.CASEIDENTIFIER).getCaseidentifier();
	String parentCornId = (String)request.getAttribute("parentCornId");
	boolean minuteDateUpdated = request.getAttribute("minuteDateUpdated")==null?false:(Boolean)request.getAttribute("minuteDateUpdated");
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
				<strong>Details <%if(!canEdit) {%><span>(<%=historyDetails.getDmDocid()>0?"Minute is CORIS signed":"Referral has been terminated" %>)</span><%} %></strong>
				<button id="generalUpdateBtn" title="Delete Minute" onclick="deleteMinute(<%=historyDetails.getPsHistoryId() %>, <%=referralDetails.getPsReferralId() %>, <%=historyDetails.getMeId() %>);" class="btn btn-primary float-right" style="margin: 0;">Delete Minute</button>
			</div>
			<div class="card-body">
				<div id="displayReferralHeader"></div>
			</div>

			<div class="card-header card-footer">
				<strong>General Information</strong> 
				<button id="generalUpdateBtn" title="Edit General Information" <%=canEdit?"":"disabled" %> onclick="generalUpdate(<%=referralDetails.getPsReferralId() %>, <%=historyDetails.getPsHistoryId() %>);" class="btn btn-primary float-right" style="margin: 0;">Edit General Information</button>
			</div>
			<div class="card-body" id="generalInfo" >
				<table class="table table-borderless table-sm" style="width: 80%;">
					<tbody>
						<%
						if(historyDetails != null){
						%>
							<tr>
								<td><strong>Appearance Type:</strong></td>
								<td><%=TextUtil.print(new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID,historyDetails.getPsActionDefnId()).find(PsActionDefnBO.PSACTIONDESCR).getPsActionDescr()) %></td>
							</tr>
							<tr>
								<td><strong>Description:</strong></td>
								<td><%= TextUtil.print(historyDetails.getPsMinutesEventTitle()) %></td>
							</tr>
							<tr>
								<td><strong>Hearing Date:</strong></td>
								<td><%=historyDetails.getPsActionDate()!=null?dateFormat.format(historyDetails.getPsActionDate()):"" %>
									<span style="color:red"><%=minuteDateUpdated?" (Hearing date has been changed. Window can be closed.)":"" %></span>
								</td>
							</tr>
							<tr>
								<td><strong>Current Phase:</strong></td>
								<td><%=TextUtil.print((String) referralDetails.get(PsPhaseDefnBO.PSPHASEDESCR)) %></td>
							</tr>
							<tr>
								<td><strong>Notes:</strong></td>
								<td><%=TextUtil.print(historyDetails.getPsActionNote()) %></td>
							</tr>
					<% } %>
					</tbody>
				</table>
			</div>

			<div class="card-header card-footer">
				<strong>Incentives</strong> 
				<% if(historyDetails != null){ %>
				<button id="rewardInsertBtn" title="Add Incentive" <%=canEdit?"":"disabled" %> onclick="rewardInsert(<%=referralDetails.getPsReferralId() %>, <%=historyDetails.getPsHistoryId() %>, <%=historyDetails.getMeId() %>);" class="btn btn-primary float-right" style="margin: 0;">Add Incentive</button>
				<% } %>   
			</div>
			<div class="card-body">
			<% if(currentRewards.size() > 0){ %>
				<table class="table table-borderless table-sm">
					<thead class="bg-dark text-white">
						<tr>
							<th style="width: 30%;">Incentive</th>
							<th style="width: 60%;">Note</th>
							<th style="width: 10%;"></th>
						</tr>
					</thead>
					<tbody>
						<%
						String psRewardDescr = "";
						String psRewardCode = "";
						int psRewardDefnId = 0;
						int currentRewardDefnId = 0;
						int editPsRewardId = 0;
						String psRewardNote = "";
						dateFormat = Constants.dateFormatCoris;
						Date psRewardDate = new Date();
						checkedStatus = "";
						if(rewardsList.size() > 0){
							for(PsRewardCourtXrefBO rewardsListBO : rewardsList) {
								psRewardDescr = (String) rewardsListBO.get(PsRewardDefnBO.PSREWARDDESCR);
								psRewardCode = (String) rewardsListBO.get(PsRewardDefnBO.PSREWARDCODE);
								psRewardDefnId = rewardsListBO.getPsRewardDefnId();
								for(PsRewardBO currentRewardsBO : currentRewards) {
									currentRewardDefnId = currentRewardsBO.getPsRewardDefnId();
									if(currentRewardDefnId == psRewardDefnId){
										checkedStatus = "checked";
										editPsRewardId = currentRewardsBO.getPsRewardId();
										psRewardDate = currentRewardsBO.getPsRewardDate();
										psRewardNote = currentRewardsBO.getPsRewardNote();
										if(psRewardNote == null){
											psRewardNote = "";
										}
										break;
									}
								}
								if("checked".equals(checkedStatus)){
									%>
									<tr>
										<td>
											<%=psRewardDescr %>
										</td>
										<td>
											<%=psRewardNote %>
										</td>
										<td style="white-space: nowrap;">
											<i id="rewardUpdateBtn" title="Edit" onclick="<%=canEdit?"rewardUpdate(" + referralDetails.getPsReferralId() + "," + historyDetails.getPsHistoryId() + ", " + historyDetails.getMeId()+ ", " + editPsRewardId + ")":""%>" class="<%=canEdit?"text-warning":"text-muted" %> far fa-edit fa-lg fa-fw" style="cursor: pointer;"></i>
											<i id="rewardDeleteBtn" title="Delete" onclick="<%=canEdit?"rewardDelete(" + editPsRewardId + ", '" + psRewardCode + "', '" + psRewardDescr + "')":"" %>" class="<%=canEdit?"text-danger":"text-muted" %> fas fa-minus-circle fa-lg fa-fw" style="cursor: pointer;"></i>
										</td>
									</tr>
									<%
								}
								checkedStatus = "";
							}//for
						}
						%>
					</tbody>
				</table>
				<% } else { %>
				No results.
				<% } %>
			</div>

			<div class="card-header card-footer">
				<strong>Sanctions</strong> 
				<% if(historyDetails != null){ %>
				<button id="sanctionInsertBtn" title="Add Sanction" <%=canEdit?"":"disabled" %> onclick="sanctionInsert(<%=referralDetails.getPsReferralId() %>, <%=historyDetails.getPsHistoryId() %>, <%=historyDetails.getMeId() %>);" class="btn btn-primary float-right" style="margin: 0;">Add Sanction</button>
				<% } %>
			</div>
			<div class="card-body">
			<% if(currentSanctions.size() > 0){ %>
				<table class="table table-borderless table-sm">
					<thead class="bg-dark text-white">
						<tr>
							<th style="width: 30%;">Sanction</th>
							<th style="width: 60%;">Note</th>
							<th style="width: 10%;"></th>
						</tr>
					</thead>
					<tbody>
						<%
						String psSanctionDescr = "";
						String psSanctionCode = "";
						int psSanctionDefnId = 0;
						int currentSanctionDefnId = 0;
						int editPsSanctionId = 0;
						String psSanctionNote = "";
						dateFormat = Constants.dateFormatCoris;
						Date psSanctionDate = new Date();
						checkedStatus = "";
						int psSanctionAmount = 0;
						String psSanctionUnit = "";
						if(sanctionsList.size() > 0){
							for(PsSanctionCourtXrefBO sanctionsListBO : sanctionsList) {
								psSanctionDescr = (String) sanctionsListBO.get(PsSanctionDefnBO.PSSANCTIONDESCR);
								psSanctionCode = (String) sanctionsListBO.get(PsSanctionDefnBO.PSSANCTIONCODE);
								psSanctionDefnId = sanctionsListBO.getPsSanctionDefnId();
								for(PsSanctionBO currentSanctionsBO : currentSanctions) {
									currentSanctionDefnId = currentSanctionsBO.getPsSanctionDefnId();
									psSanctionAmount = currentSanctionsBO.getPsSanctionAmount();
									psSanctionUnit = currentSanctionsBO.getPsSanctionUnit();
									if(currentSanctionDefnId == psSanctionDefnId){
										checkedStatus = "checked";
										editPsSanctionId = currentSanctionsBO.getPsSanctionId();
										psSanctionDate = currentSanctionsBO.getPsSanctionDate();
										psSanctionNote = currentSanctionsBO.getPsSanctionNote();
										if(psSanctionNote == null){
											psSanctionNote = "";
										}
										break;
									}
								}
								if("checked".equals(checkedStatus)){
									%>
									<tr>
										<td style="white-space: nowrap;">
											<%=psSanctionDescr %> <%=(psSanctionAmount > 0 && !TextUtil.isEmpty(psSanctionUnit))?" ("+psSanctionAmount+" "+psSanctionUnit+")":"" %>
										</td>
										<td>
											<%=psSanctionNote %>
										</td>
										<td style="white-space: nowrap;">
											<i id="sanctionUpdateBtn" title="Edit" onclick="<%=canEdit?"sanctionUpdate(" + referralDetails.getPsReferralId() + ", " + historyDetails.getPsHistoryId() + "," + historyDetails.getMeId() +"," + editPsSanctionId +");":""%>" class="<%=canEdit?"text-warning":"text-muted" %> far fa-edit fa-lg fa-fw" style="cursor: pointer;"></i>
											<i id="sanctionDeleteBtn" title="Delete" onclick="<%=canEdit?"sanctionDelete(" + editPsSanctionId + ",'" + psSanctionCode + "','" + psSanctionDescr+ "');":""%>" class="<%=canEdit?"text-danger":"text-muted" %> fas fa-minus-circle fa-lg fa-fw" style="cursor: pointer;"></i>
										</td>
									</tr>
									<%
								}
								checkedStatus = "";
							}//for
						}
						%>
					</tbody>
				</table>
				<% } else { %>
				No results.
				<% } %>
			</div>
		</div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			displayReferralHeader('<%=intCaseNum%>');
		});
		
		function phaseChange(psReferralId, psHistoryId){
			var cornId = "phaseChange";
			var title = "Phase Change";
			var url = "/CorisWEB/PSCMinutesServlet?psHistoryId="+psHistoryId+"&psReferralId="+psReferralId+"&mode=addEditPhase";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function generalUpdate(psReferralId, psHistoryId){
			var cornId = "modalCornFourth";
			var title = "Minutes Edit";
			var url = "/CorisWEB/PSCMinutesServlet?psHistoryId="+psHistoryId+"&psReferralId="+psReferralId+"&mode=addEditGeneral";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
			
		function sanctionInsert(psReferralId, psHistoryId, meId){
			var cornId = "sanctionInsert";
			var title = "Add Sanction";
			var url = "/CorisWEB/PSCMinutesServlet?psHistoryId="+psHistoryId+"&meId="+meId+"&psReferralId="+psReferralId+"&mode=addEditSanction";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function sanctionDelete(editPsSanctionId, psSanctionCode, psSanctionDescr){
			var title = "Delete Sanction";
			var message = "Would you like to delete Sanction "+psSanctionCode+" - "+psSanctionDescr+"?";
			var trueText = "Delete";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		
			function confirmCallback(action) {
				if (action) {
					var url = "/CorisWEB/PSCMinutesServlet";
					appUX.ajax.call(url, 
						function (err, data, xhr) { 
							appUX.pop.refreshCornFrame("minutesDetails", true);
							message = "Changes have been saved.";
							appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
							corn.close(); 
						}, 
						'POST', 
						'editPsSanctionId='+editPsSanctionId+'&mode=deleteSanction'
					);
				} else {
					corn.close();
				}
			}
		}
		
		function sanctionUpdate(psReferralId, psHistoryId, meId, editPsSanctionId){
			var cornId = "sanctionEdit";
			var title = "Edit Sanction";
			var url = "/CorisWEB/PSCMinutesServlet?psHistoryId="+psHistoryId+"&meId="+meId+"&editPsSanctionId="+editPsSanctionId+"&psReferralId="+psReferralId+"&mode=addEditSanction";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function rewardInsert(psReferralId, psHistoryId, meId){
			var cornId = "rewardInsert";
			var title = "Add Incentive";
			var url = "/CorisWEB/PSCMinutesServlet?psHistoryId="+psHistoryId+"&meId="+meId+"&psReferralId="+psReferralId+"&mode=addEditReward";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function rewardDelete(editPsRewardId, psRewardCode, psRewardDescr){
			var title = "Delete Reward";
			var message = "Would you like to delete Reward "+psRewardCode+" - "+psRewardDescr+"?";
			var trueText = "Delete";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		
			function confirmCallback(action) {
				if (action) {
					var url = "/CorisWEB/PSCMinutesServlet";
					appUX.ajax.call(url, 
						function (err, data, xhr) { 
							appUX.pop.refreshCornFrame("minutesDetails", true);
							message = "Changes have been saved.";
							appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
							corn.close(); 
						}, 
						'POST', 
						'editPsRewardId='+editPsRewardId+'&mode=deleteReward'
					);
				} else {
					corn.close();
				}
			}
		

		}
		
		function rewardUpdate(psReferralId, psHistoryId, meId, editPsRewardId){
			var cornId = "rewardEdit";
			var title = "Edit Reward";
			var url = "/CorisWEB/PSCMinutesServlet?psHistoryId="+psHistoryId+"&meId="+meId+"&editPsRewardId="+editPsRewardId+"&psReferralId="+psReferralId+"&mode=addEditReward";
			var width = appUX.pop.windowSize().width - 10;
			var height = appUX.pop.windowSize().height - 40;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function deleteMinute(psHistoryId, psReferralId, meId){
			var title = "Delete Sanction";
			var message = "Would you like to delete the Minute?";
			if('<%=historyDetails.getDmDocid() > 0 %>' == 'true'){
				message = "The minute has been signed. Would you still like to delete the Minute?";
			}
			var trueText = "Delete";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		
			function confirmCallback(action) {
				if (action) {
					var url = "/CorisWEB/PSCMinutesServlet";
					appUX.ajax.call(url, 
						function (err, data, xhr) { 
							if(err){
									var width = 300;
									var height = "auto";
									var style = appUX.pop.styles.DANGER;
								 	var message = "";
								 	message = xhr.responseText;
								 	appUX.pop.notify(message, width, height, style);
							}else {
								/* appUX.pop.refreshCornFrame("minutesDetails", true); */
								message = "Changes have been saved.";
								appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
								corn.close();
								appUX.pop.refreshCornFrame('<%=parentCornId%>',true, '{"from_edit":"true","meId":"0"}');
								appUX.pop.getSelfHandle().close();
							}
						}, 
						'POST', 
						'psHistoryId='+psHistoryId+'&psReferralId='+psReferralId+'&meId='+meId+'&mode=deleteminute'
					);
				} else {
					corn.close();
				}
			}
		}
	</script>
</body>
</html>
