<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewardcourtxref.PsRewardCourtXrefBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreward.PsRewardBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%
SimpleDateFormat dateFormat = Constants.dateFormatCoris;
PsReferralHistoryBO historyDetails = (PsReferralHistoryBO) request.getAttribute("historyDetails");
int psHistoryId = historyDetails.getPsHistoryId();
Date psRewardDate = historyDetails.getPsActionDate();
int psReferralId = historyDetails.getPsReferralId();
int meId = historyDetails.getMeId();
int editPsRewardId = (Integer) request.getAttribute("editPsRewardId");
List<PsRewardCourtXrefBO> rewardsList = (List<PsRewardCourtXrefBO>) request.getAttribute("rewardsList");
List<PsRewardBO> currentRewards = (List<PsRewardBO>) request.getAttribute("currentRewards");
int intCaseNum = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId)
											.find(PsReferralCaseBO.CASEIDENTIFIER).getCaseidentifier();
int psRewardId = 0;
int psRewardDefnId = 0;
String psRewardNote = "";
for(PsRewardBO currentRewardsBO : currentRewards){
	psRewardId = currentRewardsBO.getPsRewardId();
	if(psRewardId == editPsRewardId){
		psRewardNote = currentRewardsBO.getPsRewardNote();
		psRewardDefnId = currentRewardsBO.getPsRewardDefnId();
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
				<strong>Add Incentive</strong>
			</div>
			<div class="card-body" >			
				<% if(rewardsList.size() > 0){ %>
				<form id="rewardForm" name="rewardForm" novalidate>
					<div class="row">
						<div class='input-group mb-1 col-12'>
							<label class="col-8" style="text-align: right;">Incentive<span class="text-danger">*</span></label>
							<select class="col-16 form-control" id="reward" name="reward">
								<option value=""></option>
								<%
								String psRewardDescr = "";
								String psRewardCode = "";
								int listPsRewardDefnId = 0;
								int tempPsRewardDefnId = 0;
								int tempPsRewardId = 0;
								boolean show = true;
								for(PsRewardCourtXrefBO rewardsListBO : rewardsList) {
									psRewardDescr = (String) rewardsListBO.get(PsRewardDefnBO.PSREWARDDESCR);
									psRewardCode = (String) rewardsListBO.get(PsRewardDefnBO.PSREWARDCODE);
									listPsRewardDefnId = rewardsListBO.getPsRewardDefnId();
									for(PsRewardBO currentRewardsBO : currentRewards){
										tempPsRewardDefnId = currentRewardsBO.getPsRewardDefnId();
										tempPsRewardId = currentRewardsBO.getPsRewardId();
										if(tempPsRewardDefnId == listPsRewardDefnId && tempPsRewardId != editPsRewardId){
											show = false;
										}
									}
									if(show){
										%>
										<option value="<%=listPsRewardDefnId %>" <%=listPsRewardDefnId==psRewardDefnId?"selected":"" %>><%=psRewardDescr %></option>
										<%
									}		
									show = true;
								} 
								%>
							</select>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Incentive is required.</span></div>
						</div>
						<div class='input-group date mb-1 col-12'>
							<label class="col-8" style="text-align: right;">Date<span class="text-danger">*</span></label>
							<input type="text" class="col-16 form-control" value="<%=dateFormat.format(psRewardDate) %>" maxlength="10" disabled>
						</div>
					</div>
					<div class="row">
						<div class='input-group mb-1 col-24'>
							<label class="col-4" style="text-align: right;">Note</label>
							<textarea class="col-20 form-control" id="note" name="note" rows="3" maxlength="250" onkeyup="characterCount(this);"><%="".equals(psRewardNote)?"":TextUtil.print(psRewardNote) %></textarea>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 83%;">Note may only contain standard characters and numbers or be left blank.</span></div>
							<div style="margin: 0; padding: 0; width: 100%;"><div class="float-right"><span id="characterCountId">0</span>/250</div></div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn">Cancel</button>
						<% if(editPsRewardId > 0){ %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="rewardUpdateBtn">Save</button>
						<% } else { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="rewardInsertBtn">Save & New</button>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="rewardSaveBtn">Save</button>
						<% } %>
					</div>
				</form>
				<% }else{ %>
				<span class="text-warning">No incentives are available for this particular court.</span>
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
			$('#rewardSaveBtn').on('click', function(){
				validateReward(false);
			});
			$('#rewardUpdateBtn').on('click', function(){
				validateReward(false);
			});
			$('#rewardInsertBtn').on('click', function(){
				validateReward(true);
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
		function validateReward(startNew){
			$('#reward').removeClass("is-invalid");
			$('#note').removeClass("is-invalid");
			var valid = true;
			var message = "";
            if($('#reward').val() == "") {
                valid = false;
				$('#reward').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#reward').removeClass("is-invalid").addClass("is-valid");
            }
            res = cleanCharacters(document.getElementById("note")); //removes invalid characters
            if(!res || $('#note').val().length > 250) {
                valid = false;
                $('#note').removeClass("is-valid").addClass("is-invalid");
            }else{
            	$('#note').removeClass("is-invalid").addClass("is-valid");
            }
	        if(valid){
	        	<% if(editPsRewardId > 0){ %>
	        		updateReward();
	        	<% }else{ %>
	        		insertReward(startNew);
	        	<% } %>
				$('#reward').removeClass("is-valid");
				$('#note').removeClass("is-valid");
			}
		}
		function insertReward(startNew){
			var data = $('#rewardForm').serialize();
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
							appUX.pop.refreshCornFrame("rewardInsert", true);
						}else{
							closePop();
						}
					}
				}, 
				'POST', 
				'psReferralId=<%=psReferralId%>&psHistoryId=<%=psHistoryId%>&date=<%=dateFormat.format(psRewardDate) %>&mode=insertReward&'+data
			);
		}
		function updateReward(){
			var data = $('#rewardForm').serialize();
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
				'psReferralId=<%=psReferralId%>&psHistoryId=<%=psHistoryId%>&date=<%=dateFormat.format(psRewardDate) %>&editPsRewardId=<%=editPsRewardId%>&mode=updateReward&'+data
			);
		}
	</script>
</body>
</html>
