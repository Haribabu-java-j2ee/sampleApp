<!DOCTYPE HTML>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.courtscommon.constants.BaseConstants"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
<%@page import="gov.utcourts.problemsolving.constants.PSCDefnConstants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psparticipant.PsParticipantBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="java.util.List"%>
<%
	int intCaseNum = URLEncryption.getParamAsInt(request,Constants.INT_CASE_NUM);
	String corisMinuteParams = (String) request.getAttribute("corisMinuteParams");
	boolean corisDeleteMin = "deleteminute".equalsIgnoreCase(URLEncryption.getParamAsString(request,"mode"));
%>
<html lang="en-US">
	<head>
	<title>Problem Solving Courts - Referral Overview</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Referral Details</strong>
			</div>
			<div class="card-body">
				<div id="displayReferralHeader"></div>
			</div>
			<div class="card-header card-footer">
				<div class="float-left mb-0 mt-2 pb-0">
					<strong>Referrals</strong>
				</div>
				<div class="float-left ml-12 mt-2 mb-0 pb-0">  
					<input type="checkbox" id="displayRemoved" name="displayRemoved" value="Display Removed" onclick="displayList('true')">
				</div>
				<div class="float-left mt-2 mb-0 pb-0 ml-2">  
					<label for="displayRemoved">Display Removed</label>
				</div>
				<button id="btnAddReferral" class="btn btn-primary float-right"  onclick="addReferral()">New Referral</button>
			</div>
			<div class="card-body" >
				<div class="table-responsive" id="displayResults"></div>
			</div>
		</div>
	</div>
	
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		var parentCornId = appUX.pop.getSelfHandle().id;
		$(document).ready(function(){
			displayReferralHeader('<%=intCaseNum%>');
			if('<%=corisDeleteMin%>'=='true'){
				corisDeleteMinute();
			}else if('<%=intCaseNum > 0%>'=='true'){
				displayList();
			}
		});
		
		function displayList(refresh_from_edit, me_id) {
			var disabledToggle = $('#displayRemoved:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			var url = "/CorisWEB/PSCReferralsServlet?mode=list&refreshAfterEdit=" + refresh_from_edit + 
						"&displayDeleted=" + disabled + '&<%=corisMinuteParams %>';
			appUX.ajax.call(url, function (err, data, xhr){ $("#displayResults").html(data);});
		}	
		
		function openMinutes(psReferralId, psReferralHistoryId, meId, minuteDateUpdated){
			var url = null;
			if(psReferralHistoryId == 0 && meId > 0){
				url = "/CorisWEB/PSCReferralsServlet?psReferralId="+psReferralId+"&mode=createMinuteHistory" + '&<%=corisMinuteParams %>'; 
				appUX.ajax.call(url, function(err, data, xhr)
									{ 
										if(err){
											appUX.pop.declare("No Minute", xhr.responseText, 300, 'auto', appUX.pop.styles.DANGER);
											displayList(true);
										}else {
											displayList();
										}
										
									});
			}else if(meId > 0){
				url = "/CorisWEB/PSCMinutesServlet?psReferralId="+psReferralId+"&mode=displayDetails" + "&psHistoryId=" + psReferralHistoryId + 
					"&meId=" + meId +"&parentCornId=" + parentCornId + "&minuteDateUpdated=" + minuteDateUpdated;
				var cornId = "minutesDetails";
				var title = "Minutes";
				var width = appUX.pop.windowSize().width/2;
				var height = appUX.pop.windowSize().height - 100;
				var style = appUX.pop.styles.PRIMARY;
				appUX.pop.modal(cornId, title, url, width, height, style);
			}
		}
		
		function deleteReferral(refId, parentCornId){
		
			//set the remove flag in the database to "Y"
			var title = "Delete Referral -- " + refId;
			var message = "Would you like to delete referral " + refId + "?";
			var trueText = "Delete";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
				
			function confirmCallback(action){
				if(action){
					appUX.ajax.call("/CorisWEB/PSCEditReferralServlet", 
							function (err, data, xhr) { 
								if(err){
									var width = 300;
									var height = "auto";
									var style = appUX.pop.styles.DANGER;
								 	var message = "";
								 	message = xhr.responseText;
								 	appUX.pop.notify(message, width, height, style);
								}else{
									//parent.displayList();
									appUX.pop.refreshCornFrame(parentCornId,true, '{"from_edit":"true","meId":0}')
									corn.close();
								}
							}, 
							'POST', 
							'mode=deleteReferral&referralId=' + refId
						);
				 }else {
					corn.close();
				}
			}
		} 
		
		function editReferral(referralId, caseIntNum){
			var cornId = "modalCornPrimary";
			var title = "Add Referral";
			if(referralId > 0){
				title = "Edit Referral";
			}
			var url = "/CorisWEB/jsp/pscEditReferral.jsp?intCaseNum=" + caseIntNum + "&referralId=" + referralId + "&parentCornId=" + parentCornId;
			var width = 500;
			var height = 600;
			var style = appUX.pop.styles.PRIMARY;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function addReferral(){
			editReferral('0', '<%=intCaseNum%>');
		}
		
		function corisDeleteMinute(){
			//set the remove flag in the database to "Y"
			var meId = '<%=URLEncryption.getParamAsInt(request,"meId")%>';
			var courtType = '<%=URLEncryption.getParamAsString(request,"courtType")%>';
			var intCaseNum = '<%=URLEncryption.getParamAsInt(request,"intCaseNum")%>';
			appUX.ajax.call("/CorisWEB/PSCMinutesServlet", 
							function (err, data, xhr) { 
								if(err){
									var width = 300;
									var height = "auto";
									var style = appUX.pop.styles.DANGER;
								 	var message = "";
								 	message = xhr.responseText;
								 	appUX.pop.notify(message, width, height, style);
								}else {
									displayList(true,"0");
								}
							}, 
							'POST', 
							'mode=deleteminute&intCaseNum=' + intCaseNum + '&courtType=' + courtType + '&meId=' + meId
						);
		}
		
		function refreshPage(jsonObj){
			var params = JSON.parse(jsonObj);
			if(params.meId && params.meId.length > 0){
				displayList(params.from_edit,params.meId);
			}else {
				displayList(params.from_edit);
			}
		}
		
	</script>
</body>
</html>
