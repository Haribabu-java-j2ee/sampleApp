<!DOCTYPE HTML>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables" %>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactioncourtxref.PsActionCourtXrefBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psterminationdefn.PsTerminationDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO"%>
<%@page import="gov.utcourts.problemsolving.xo.PsStatusActionXrefXO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusactionxref.PsStatusActionXrefBO"%>
<%@page import="gov.utcourts.problemsolving.constants.PSCDefnConstants"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
	List<PsStatusActionXrefBO> statuses = null; 
	String mode = "edit";
	boolean fromEdit = true;
	String courtType = (String)request.getSession().getAttribute(SessionVariables.COURT_TYPE);
	int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
	int referralId = TextUtil.getParamAsInt(request,"referralId");
	int psCourtId = TextUtil.getParamAsInt(request,"psCourtId");
	PsReferralBO ref = (PsReferralBO) new PsReferralBO().where(PsReferralBO.PSREFERRALID, referralId).find();
	if(ref.getPsReferralId() == 0){ //for adding new referrals
		mode = "add";
		ref.setPsStatusDefnId(new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSCODE,PSCDefnConstants.STATUS_REFERRED_CODE)
												  .find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId());
		ref.setCourtProfileId(Integer.parseInt(new KaseBO(courtType).where(KaseBO.INTCASENUM,intCaseNum)
																	.find(KaseBO.LOCNCODE).getLocnCode()));
		int deleteStatusId = new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSCODE, PSCDefnConstants.STATUS_DELETE_CODE).find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
		int refCount = new PsReferralBO().where(PsReferralBO.PSSTATUSDEFNID,Exp.NOT_EQUALS,deleteStatusId).includeFields(PsReferralBO.PSREFERRALID)
							.includeTables(new PsReferralCaseBO().includeFields(PsReferralCaseBO.NO_FIELDS).where(PsReferralCaseBO.CASEIDENTIFIER,intCaseNum))
						  	.addForeignKey(PsReferralBO.PSREFERRALID, PsReferralCaseBO.PSREFERRALID).count(PsReferralBO.PSREFERRALID).find(PsReferralBO.PSREFERRALID).getCount();
		fromEdit = refCount > 0;
		
	}
	if(psCourtId > 0){
		ref.setPsCourtDefnId(psCourtId);
	}
	List<CourtProfileBO> courtProfiles = new CourtProfileBO().orderBy(CourtProfileBO.COURTTITLE).where(CourtProfileBO.COURTTYPE,courtType).search();
	List<PsCourtDefnBO> psCourts = new PsCourtDefnBO().includeFields(PsCourtDefnBO.ALL_FIELDS)
									  .includeTables(new PsCourtLocationXrefBO().where(PsCourtLocationXrefBO.COURTPROFILEID,ref.getCourtProfileId()))
									  .addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsCourtLocationXrefBO.PSCOURTDEFNID)
									  .orderBy(PsCourtDefnBO.PSCODEDESCR,DirectionType.ASC)
									  .search();
	
	PsStatusDefnBO refStatus = new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSDEFNID,ref.getPsStatusDefnId()).find();
	String parentCornId = TextUtil.getParamAsString(request, "parentCornId");
%>
<html>
<head>
	<title>Edit/Add Referral</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
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
			<strong><%="add".equals(mode)?"Add":"Edit" %> Referral</strong> 
		</div>
		<div class="card-body">
	    	<form class="form-inline" action="#" method="post">
	    		<input type="hidden" name="intCaseNum" id="intCaseNum" value="<%=intCaseNum %>"/>
	    		<input type="hidden" name="mode" id="mode" value="<%=mode %>"/>
	    		<input type="hidden" name="referralId" id="referralId" value="<%=referralId %>"/>
				<div style="width:100%; margin:0.5rem !important">
					<div class="row align-items-center">
						<div class='col input-group date' id='datetimepicker1'>
							<label for="referralDate" style="width: 40%; text-align: right; padding-right: 3px;"><strong>Referral Date</strong> <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="referralDate" name="referralDate" value="<%= TextUtil.printDate(ref.getPsReferralDate()) %>">
							<span class="input-group-text input-group-addon">
								<span class="fa fa-calendar"></span>
							</span>
							<div class="invalid-feedback"><span class="float-right">Current or earlier date must be selected</span></div>
						</div>
					</div>
				</div>
				<div class="input-group m-4">
					<label for="chText" style="width: 40%; text-align: right; padding-right: 3px;"><strong>Court Location</strong> <span class="text-danger">*</span></label>
					<select class="form-control" id="courtLocn" name="courtLocn" onclick="updateCourtProfile(this.value);">
						<option value="0"></option>
						<% for(CourtProfileBO cp:courtProfiles){ %>
						<option value="<%=cp.getCourtProfileId() %>" <%=ref.getCourtProfileId()==cp.getCourtProfileId()?"selected":"" %>><%=cp.getCourtTitle() %></option>
						<%} %>
					</select>
					<div class="invalid-feedback"><span class="float-right">Court Location must be selected</span></div>
				</div>
				<div class="input-group m-4">
					<label for="chText" style="width: 40%; text-align: right; padding-right: 3px;"><strong>Problem Solving Court</strong> <span class="text-danger">*</span></label>
					<select class="form-control" id="psCourt" name="psCourt" onclick="checkCourtLocn();">
						<option value="0"></option>
						<% for(PsCourtDefnBO c:psCourts){ %>
						<option value="<%=c.getPsCourtDefnId() %>" <%=ref.getPsCourtDefnId()==c.getPsCourtDefnId()?"selected":"" %>><%=c.getPsCodeDescr() %></option>
						<%} %>
					</select>
					<div class="invalid-feedback"><span class="float-right">Problem Solving Court must be selected</span></div>
				</div>
				<div class="input-group m-4">
					<label for="chText" style="width: 40%; text-align: right; padding-right: 3px;"><strong>Status</strong><span class="text-danger">*</span></label>
					<span class="float-right" id="statusDescr" ><%=refStatus.getPsStatusDescr() %></span>
				</div>
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" title="Cancel" onclick="closePop();">Cancel</button>
					<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" title="Save" onclick="submitForm(this); ">Save</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript">

      $(document).ready(function(){
		$('#datetimepicker1').datetimepicker({ 
			format: 'MM/DD/YYYY',
			icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
		});
		$('#datetimepicker2').datetimepicker({ 
			format: 'MM/DD/YYYY',
			icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
		});
		displayReferralHeader('<%=intCaseNum%>');
	});
	
	function closePop(){
		var corn = appUX.pop.getSelfHandle();
		corn.close();
	}
	
	function validateData(){
		$('#referralDate').removeClass("is-invalid");
		$('#referralDate').addClass("is-valid");
		$('#psCourt').removeClass("is-invalid");
		$('#psCourt').addClass("is-valid");
		$('#courtLocn').removeClass("is-invalid");
		$('#courtLocn').addClass("is-valid");
		/* $('#actionId').removeClass("is-invalid");
		$('#actionId').addClass("is-valid");
		$('#terminateId').removeClass("is-invalid");
		$('#terminateId').addClass("is-valid");
		$('#terminateNote').removeClass("is-invalid");
		$('#terminateNote').addClass("is-valid"); */
		var valid_data = true;
		var refDate = $('#referralDate').val();
		if(!refDate || new Date(refDate) > new Date()){
			valid_data = false;
			$('#referralDate').removeClass("is-valid");
			$('#referralDate').addClass("is-invalid");
		}
		
		if($('#psCourt').val()=='0'){
			valid_data = false;
			$('#psCourt').removeClass("is-valid");
			$('#psCourt').addClass("is-invalid");
		}
		if($('#courtLocn').val()=='0'){
			valid_data = false;
			$('#courtLocn').removeClass("is-valid");
			$('#courtLocn').addClass("is-invalid");
		}
		/* if($('#actionId').val()=='0'){
			valid_data = false;
			$('#actionId').removeClass("is-valid");
			$('#actionId').addClass("is-invalid");
		}
		if($('#terminationReason').css('display') != 'none' && $('#terminateId').val()=='0'){
			valid_data = false;
			$('#terminateId').removeClass("is-valid");
			$('#terminateId').addClass("is-invalid");
		}
		if($('#terminateNote').val() != null && $('#terminateNote').val() != undefined){
			if($('#terminateNote').val().length > 250){
				valid_data = false;
				$('#terminateNote').removeClass("is-valid").addClass("is-invalid");
			}
		} */
		return valid_data;
	}
	
	function characterCount(elem) {
		$('#characterCountId').html(elem.value.length);
	}
	
	function submitForm(btn){
		btn.disabled = 'disabled';
		if(validateData()){
			appUX.ajax.call("/CorisWEB/PSCEditReferralServlet", 
					function (err, data, xhr) { 
						console.log('err=' + err);
						console.log('data=' + data);
						console.log('xhr=' + xhr.responseText)
						var jsonObj = JSON.parse(data);
						if (jsonObj && jsonObj.error) {
							appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							btn.disabled = '';
						}else{
							if('<%=mode%>'=='edit'){
								appUX.pop.refreshCornFrame('<%=parentCornId%>', true, '{"from_edit":"<%=fromEdit%>","meId":""}');
							}else{
								appUX.pop.refreshCornFrame('<%=parentCornId%>',true, '{"from_edit":"<%=fromEdit%>","meId":""}');
							}
							closePop();
						}
					}, 
					'POST', 
					$('form').serialize()
				);
		}else {
			btn.disabled = '';
		}
	}
	
	function updateAction(actionIdWithCode){
		
		var dashPos = actionIdWithCode.indexOf('-');
		var actionId = actionIdWithCode.substring(0,dashPos);
		var actionCode = actionIdWithCode.substring(dashPos+1);
	
		appUX.ajax.call("/CorisWEB/PSCEditReferralServlet", 
					function (err, data, xhr) { 
						if(err){
							var width = 300;
							var height = "auto";
							var style = appUX.pop.styles.DANGER;
						 	var message = "";
						 	message = xhr.responseText;
						 	appUX.pop.notify(message, width, height, style);
						}else if(xhr.responseText && xhr.responseText.length > 0){
							var respString = xhr.responseText;
							var optEndIndex = respString.lastIndexOf('</option>');
							var statusDescr = respString.substring(optEndIndex+9, respString.length);
							
							$('#actionId').html(respString.substring(0,optEndIndex));
							
							if(statusDescr && statusDescr.length > 0){
								$('#statusDescr').text(statusDescr);
							}
							if(actionCode == '<%=PSCDefnConstants.ACTION_TERMINATE_CODE %>'){
								$('#terminationReason').show();
								$('#terminationNote').show();
							}else{
								$('#terminationReason').hide();
								$('#terminationNote').hide();
							}
						}
					}, 
					'POST', 
					'mode=updateAction&actionId=' + actionId  + '&refStatusId=<%=ref.getPsStatusDefnId() %>'
		);
	}
	
	function updateStatus(statusIdWithDescr){
		var dashPos = statusIdWithDescr.indexOf('-');
		var statusId = statusIdWithDescr.substring(0,dashPos);
		var statusDescr = statusIdWithDescr.substring(dashPos+1);
		var message = "Would you like to update status to '" +statusDescr+"'?";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm("Update Status", message, "OK", "Cancel", width, height, style, confirmCallback);
		
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCEditReferralServlet", 
					function (err, data, xhr) { 
						var width = 300;
						var height = "auto";
						var style = appUX.pop.styles.DANGER;
					 	var message = "";
						if(err){
						 	message = xhr.responseText;
						 	appUX.pop.notify(message, width, height, style);
						}else{
							var respString = xhr.responseText;
							var optEndIndex = respString.lastIndexOf('>');
							var terminateStatus = (optEndIndex != respString.length-1);
						 	message = respString.substring(0,optEndIndex+1);
						 	appUX.pop.notify(message, width, height, style);
							$('#actionId').html(respString.substring(0,optEndIndex));
						 	var message = terminateStatus;
						 	appUX.pop.notify(message, width, height, style);
							if(terminateStatus){
								$('#terminationReason').show();
							}else{
								$('#terminationReason').hide();
							}
							corn.close();
						}
					}, 
					'POST', 
					'mode=updateStatus&statusId=' + statusId + '&actionId=' + $('#actionId').val()
				);
		   } else {
				corn.close();
		   }
		}
	}
	
	function updateSpCourt(psCourtId){
		var actionId = $('#actionId').val();
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
								$('#actionId').html(xhr.responseText);
							}
						}, 
						'POST', 
						'mode=updatePsCourt&psCourt=' + psCourtId + '&actionId=' + actionId
					);
	}
	
	function updateCourtProfile(crtProfileId){
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
								$('#psCourt').html(xhr.responseText);
							}
						}, 
						'POST', 
						'mode=updateCourtProfile&crtProfileId=' + crtProfileId + '&psCourtId=' + $('#psCourt').val()
					);
	}
	
	function checkCourtLocn(){
		if($('#courtLocn').val()=='0'){
			appUX.pop.notify("Please select <b>court location</b> first.", 300, "auto", appUX.pop.styles.WARNING);
			$('#courtLocn').focus();
		}
	}
</script>
</body>
</html>
