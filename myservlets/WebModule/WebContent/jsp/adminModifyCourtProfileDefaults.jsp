<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.agency.AgencyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.county.CountyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.custodylocation.CustodyLocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.govtype.GovTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	String locnCode = TextUtil.getParamAsString(request, "locnCode");
	ProfileBO profileBO = (ProfileBO) request.getAttribute("profile");
	
	List<PersonnelBO> judges = (List<PersonnelBO>) request.getAttribute("judges");
	List<PersonnelBO> clerks = (List<PersonnelBO>) request.getAttribute("clerks");
	List<AgencyBO> agencies = (List<AgencyBO>) request.getAttribute("agencies");
	List<CustodyLocationBO> custodyLocations = (List<CustodyLocationBO>) request.getAttribute("custodyLocations");
	List<GovTypeBO> govTypes = (List<GovTypeBO>) request.getAttribute("govTypes");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Modify Court Defaults</title>
	
	<!-- styles -->
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<!-- scripts -->
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<!-- this is used for the popcorn window -->
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script>
	
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();   // enable tooltips
		
		$("#judgeId").focus();
	});
	
	function closePop(){
		var corn = appUX.pop.getSelfHandle();
		corn.close();
	}
	
	function updateForm(judgeId, clerkId, warrExpDeflt, warrGracePeriod, ftaGracePeriod, delinqPeriod, altDelinqPeriod, overpayRetLimit, mediaType, custodyLocationCode, ncicCourtOri, ncicOriDescr, receiptCopies, govCode, supervisorCntVer, reversalVerify, childSupportLbl, certNoticeLbl, depositSlips, defltSentTrack, creditCardAccept, viewReceipt, petnModJudge, postJdmtIntFlag, surcharge, printPath, executePath) {
		
	   	appUX.ajax.call('/CorisWEB/AdminCourtProfileServlet?mode=updateDefaults'
	   	+ '&courtType=<%= courtType %>'  
	   	+ '&court=<%= courtType %>' 
	   	+ '&locnCode=<%= locnCode %>'
	    + '&judgeId=' + judgeId
	    + '&clerkId=' + clerkId
	    + '&warrExpDeflt=' + warrExpDeflt
	    + '&warrGracePeriod=' + warrGracePeriod
	    + '&ftaGracePeriod=' + ftaGracePeriod
	    + '&delinqPeriod=' + delinqPeriod
	    + '&altDelinqPeriod=' + altDelinqPeriod
	    + '&overpayRetLimit=' + overpayRetLimit
	    + '&mediaType=' + mediaType
	    + '&custodyLocationCode=' + custodyLocationCode
	    + '&ncicCourtOri=' + ncicCourtOri
	    + '&ncicOriDescr=' + ncicOriDescr
	    + '&receiptCopies=' + receiptCopies
	    + '&govCode=' + govCode
	    + '&supervisorCntVer=' + supervisorCntVer
	    + '&reversalVerify=' + reversalVerify
	    + '&childSupportLbl=' + childSupportLbl
	    + '&certNoticeLbl=' + certNoticeLbl
	    + '&depositSlips=' + depositSlips
	    + '&defltSentTrack=' + defltSentTrack
	    + '&creditCardAccept=' + creditCardAccept
	    + '&viewReceipt=' + viewReceipt
	    + '&petnModJudge=' + petnModJudge
	    + '&postJdmtIntFlag=' + postJdmtIntFlag
	    + '&surcharge=' + surcharge
	    + '&printPath=' + printPath
	    + '&executePath=' + executePath
	   	, function (err, data, xhr) { closePop(); });
	
	}
	
</script>

</head>
<body>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div class="input-group m-4">
					<label for="judgeId" style="width: 45%; text-align: right; padding-right: 3px;">Presiding Judge </label>
					<select class="form-control" id="judgeId" name="judgeId">
						<% 
							int selectedJudge = profileBO.getJudgeId();
							if (judges != null) {
								for (PersonnelBO judge : judges) {
						%>
									<option value="<%= judge.getUseridSrl() %>" <%= selectedJudge == judge.getUseridSrl() ? "selected" : "" %>><%= judge.getLogname() %></option>
						<%  
								}
							} 
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="clerkId" style="width: 45%; text-align: right; padding-right: 3px;">Clerk of Court </label>
					<select class="form-control" id="clerkId" name="clerkId">
						<% 
							int selectedClerk = profileBO.getAdminId(); 
							if (clerks != null) {
								for (PersonnelBO clerk : clerks) {
						%>
									<option value="<%= clerk.getUseridSrl() %>" <%= selectedClerk == clerk.getUseridSrl() ? "selected" : "" %>><%= clerk.getLogname() %></option>
						<%  
								}
							}
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="warrExpDeflt" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="In months (For misdemeanors)">Warrant Expiration</a></label>
					<input class="form-control" id="warrExpDeflt" name="warrExpDeflt" value="<%= profileBO.getWarrExpDeflt() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="warrGracePeriod" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="In days">Warrant Grace Period</a></label>
					<input class="form-control" id="warrGracePeriod" name="warrGracePeriod" value="<%= profileBO.getWarrGracePeriod() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="ftaGracePeriod" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="In days">FTA Grace Period</a></label>
					<input class="form-control" id="ftaGracePeriod" name="ftaGracePeriod" value="<%= profileBO.getFtaGracePeriod() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="delinqPeriod" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="In days">Delinquent Period</a></label>
					<input class="form-control" id="delinqPeriod" name="delinqPeriod" value="<%= profileBO.getDelinqPeriod() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="altDelinqPeriod" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="In days">Photo Radar Delinquent Period</a></label>
					<input class="form-control" id="altDelinqPeriod" name="altDelinqPeriod" value="<%= profileBO.getAltDelinqPeriod() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="overpayRetLimit" style="width: 45%; text-align: right; padding-right: 3px;">Overpay Return Limit</label>
					<input class="form-control" id="overpayRetLimit" name="overpayRetLimit" value="<%= profileBO.getOverpayRetLimit() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="mediaType" style="width: 45%; text-align: right; padding-right: 3px;">Incourt Recording Type</label>
					<select class="form-control" id="mediaType" name="mediaType">
						<% 
							String selectedMediaType = profileBO.getMediaType(); 
						%>
							<option value="A" <%= "A".equals(selectedMediaType) ? "selected" : "" %>>Audio</option>
							<option value="C" <%= "C".equals(selectedMediaType) ? "selected" : "" %>>CAT/CIC</option>
							<option value="V" <%= "V".equals(selectedMediaType) ? "selected" : "" %>>Video</option>
							<option value="N" <%= "N".equals(selectedMediaType) ? "selected" : "" %>>None</option>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="custodyLocationCode" style="width: 45%; text-align: right; padding-right: 3px;">Default Custody Location </label>
					<select class="form-control" id="custodyLocationCode" name="custodyLocationCode">
						<% 
							String selectedLocation = profileBO.getDefltCustodyLocn(); 
							if (custodyLocations != null) {
								for (CustodyLocationBO custodyLocation : custodyLocations) {
						%>
									<option value="<%= custodyLocation.getCustodyLocationCode() %>" <%= custodyLocation.getCustodyLocationCode().equalsIgnoreCase(selectedLocation) ? "selected" : "" %>><%= custodyLocation.getCustodyLocationName() %></option>
						<%  
								}
							}
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="ncicCourtOri" style="width: 45%; text-align: right; padding-right: 3px;">NCIC Court ORI Code</label>
					<input class="form-control" id="ncicCourtOri" name="ncicCourtOri" value="<%= profileBO.getNcicCourtOri() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="ncicOriDescr" style="width: 45%; text-align: right; padding-right: 3px;">ORI Description</label>
					<input class="form-control" id="ncicOriDescr" name="ncicOriDescr" value="<%= profileBO.getNcicOriDescr() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="receiptCopies" style="width: 45%; text-align: right; padding-right: 3px;">Receipt Copy Count</label>
					<input class="form-control" id="receiptCopies" name="receiptCopies" value="<%= profileBO.getReceiptCopies() %>" \>
				</div>
				<div class="input-group m-4">
					<label for="govCode" style="width: 45%; text-align: right; padding-right: 3px;">Default Charge Gov Code </label>
					<select class="form-control" id="govCode" name="govCode">
						<% 
							String selectedGovType = profileBO.getDefltGovCod(); 
							if (govTypes != null) {
								for (GovTypeBO govType : govTypes) {
						%>
									<option value="<%= govType.getGovCode() %>" <%= govType.getGovCode().equalsIgnoreCase(selectedGovType) ? "selected" : "" %>><%= govType.getGovCode() %></option>
						<%  
								}
							}
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="supervisorCntVer" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="To verify cash count if cashier is absent">Require 2nd Supervisor</a></label>
					<input class="form-check-inline" type="checkbox" id="supervisorCntVer" <%= "Y".equalsIgnoreCase(profileBO.getSupervisorCntVer()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="reversalVerify" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="To verify transaction reversals">Require Supervisor</a></label>
					<input class="form-check-inline" type="checkbox" id="reversalVerify" <%= "Y".equalsIgnoreCase(profileBO.getReversalVerify()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="childSupportLbl" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="For child support payments">Generate Mailing Labels</a></label>
					<input class="form-check-inline" type="checkbox" id="childSupportLbl" <%= "Y".equalsIgnoreCase(profileBO.getChildSupportLbl()) ? "checked" : "" %>>
				</div>	
				<div class="input-group m-4">
					<label for="certNoticeLbl" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="For certificate of notification">Generate Mailing Labels</a></label>
					<input class="form-check-inline" type="checkbox" id="certNoticeLbl" <%= "Y".equalsIgnoreCase(profileBO.getCertNoticeLbl()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="depositSlips" style="width: 45%; text-align: right; padding-right: 3px;">Deposit Slip Processing</label>
					<input class="form-check-inline" type="checkbox" id="depositSlips" <%= "Y".equalsIgnoreCase(profileBO.getDepositSlips()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="defltSentTrack" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="From incourt">Automatic Tracking</a></label>
					<input class="form-check-inline" type="checkbox" id="defltSentTrack" <%= "Y".equalsIgnoreCase(profileBO.getDefltSentTrack()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="creditCardAccept" style="width: 45%; text-align: right; padding-right: 3px;">Credit Cards Accepted</label>
					<input class="form-check-inline" type="checkbox" id="creditCardAccept" <%= "Y".equalsIgnoreCase(profileBO.getCreditCardAccept()) ? "checked" : "" %> <% if ("D".equals(courtType)) { %>disabled<% } %>>
				</div>
				<div class="input-group m-4">
					<label for="viewReceipt" style="width: 45%; text-align: right; padding-right: 3px;">Default View Receipts on Pymts</label>
					<input class="form-check-inline" type="checkbox" id="viewReceipt" <%= "Y".equalsIgnoreCase(profileBO.getViewReceipt()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="petnModJudge" style="width: 45%; text-align: right; padding-right: 3px;"><a href="#" data-toggle="tooltip" data-placement="right" data-title="If Petition to Modify">Reassign Judge</a></label>
					<input class="form-check-inline" type="checkbox" id="petnModJudge" <%= "Y".equalsIgnoreCase(profileBO.getPetnModJudge()) ? "checked" : "" %>>
				</div>
				<div class="input-group m-4">
					<label for="postJdmtIntFlag" style="width: 45%; text-align: right; padding-right: 10px;">Post Judgment Interest</label>
					<div class="form-check form-radio-inline" style="width: 32%; align: right;">
						<input type="radio" class="form-check-input" id="postJdmtIntFlag" name="postJdmtIntFlag" value="Y" <%= "Y".equalsIgnoreCase(profileBO.getPostJdmtIntFlag()) ? "checked" : "" %> <% if ("D".equals(courtType)) { %>disabled<% } %>>
						<label class="form-check-label" for="inlineCheckbox1">Revenue and Trust</label>
					</div>
					<div class="form-check form-radio-inline" style="width: 23%; align: right;">
						<input type="radio" class="form-check-input" id="postJdmtIntFlag" name="postJdmtIntFlag" value="T" <%= "T".equalsIgnoreCase(profileBO.getPostJdmtIntFlag()) ? "checked" : "" %> <% if ("D".equals(courtType)) { %>disabled<% } %>>
						<label class="form-check-label" for="inlineCheckbox1">Trust Only</label>
					</div>
				</div>
				<div class="input-group m-4">
					<label for="surcharge" style="width: 45%; text-align: right; padding-right: 10px;">Default Sentence Surcharge</label>
					<div class="form-check form-radio-inline" style="width: 30%; align: right;">
						<input type="radio" class="form-check-input" id="surcharge" name="surcharge" value="I" <%= "I".equalsIgnoreCase(profileBO.getSurcharge()) ? "checked" : "" %>>
						<label class="form-check-label" for="inlineCheckbox1">Include Surcharge</label>
					</div>
					<div class="form-check form-radio-inline" style="width: 25%; align: right;">
						<input type="radio" class="form-check-input" id="surcharge" name="surcharge" value="A" <%= "A".equalsIgnoreCase(profileBO.getSurcharge()) ? "checked" : "" %>>
						<label class="form-check-label" for="inlineCheckbox1">Add Surcharge</label>
					</div>
				</div>
				<div class="input-group m-4">
					<label for="printPath" style="width: 45%; text-align: right; padding-right: 3px;">Print Path</label>
					<input class="form-control" id="printPath" name="printPath" value="<%= profileBO.getPrintPath() %>" disabled>
				</div>
				<div class="input-group m-4">
					<label for="executePath" style="width: 45%; text-align: right; padding-right: 3px;">Execute Path</label>
					<input class="form-control" id="executePath" name="executePath" value="<%= profileBO.getExecutePath() %>" disabled>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml" title="Update" onclick="updateForm($('#judgeId').val(), $('#clerkId').val(), $('#warrExpDeflt').val(), $('#warrGracePeriod').val(), $('#ftaGracePeriod').val(), $('#delinqPeriod').val(), $('#altDelinqPeriod').val(), $('#overpayRetLimit').val(), $('#mediaType').val(), $('#custodyLocationCode').val(), $('#ncicCourtOri').val(), $('#ncicOriDescr').val(), $('#receiptCopies').val(), $('#govCode').val(), $('#supervisorCntVer:checked').val(), $('#reversalVerify:checked').val(), $('#childSupportLbl:checked').val(), $('#certNoticeLbl:checked').val(), $('#depositSlips:checked').val(), $('#defltSentTrack:checked').val(), $('#creditCardAccept:checked').val(), $('#viewReceipt:checked').val(), $('#petnModJudge:checked').val(), $('#postJdmtIntFlag:checked').val(), $('#surcharge:checked').val(), $('#printPath').val(), $('#executePath').val());">Update</button>
				</div>
			</form>
		</div>
		<br><br><br>
	</main>
<%
	// cleanup
	mode = null;
%>
</body>
</html>
