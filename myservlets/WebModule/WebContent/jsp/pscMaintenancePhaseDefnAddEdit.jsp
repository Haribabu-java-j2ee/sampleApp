<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	PsPhaseDefnBO phase = (PsPhaseDefnBO)request.getAttribute("phaseDefn");
	if("add".equalsIgnoreCase(mode)){
		mode = "create";
	}else {
		mode = "update";
	}
	List<PsActionDefnBO> psActions = new PsActionDefnBO().search(); 
%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Phase Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid" style="text-align:center" id="errorMessage"></div>

	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("create".equals(mode)) { %>
						Add New Phase Definition
				<% } else if("update".equals(mode)) { %>
						Edit Phase Definition
				<% } %>
				</strong>
			</div>
			<div class="card-body">
				<form action="#" method="post">
					<div class="row">
						<div class="input-group m-4">
							<label for="phaseCode" style="width: 33%; text-align: right; padding-right: 3px;">Phase Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="phaseCode" name="phaseCode" placeholder="Enter phase code" value="<%=TextUtil.print(phase.getPsPhaseCode()) %>"
								maxlength="8" required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Phase Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">						
						<div class="input-group m-4">
							<label for="psActionId" style="width: 33%; text-align: right; padding-right: 3px;">Action <span class="text-danger">*</span></label>
							<select class="form-control" id="psActionId" name="psActionId">
								<% for(PsActionDefnBO act:psActions){ %>
									<option value="<%=act.getPsActionDefnId() %>" <%=act.getPsActionDefnId()==phase.getPsActionDefnId()?"selected":"" %>><%=act.getPsActionDescr() %></option>
								<%} %>
							</select>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Action is required.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="descr" style="width: 33%; text-align: right; padding-right: 3px;">Phase Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="descr" name="descr" value="<%=TextUtil.print(phase.getPsPhaseDescr()) %>"
									placeholder="Enter description" maxlength="30" required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Phase Description is required and must be 40 or less letters or spaces.</span></div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel" onclick="closePop();">Cancel</button>
						<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save"
							onclick="saveForm('<%=phase.getPsPhaseDefnId() %>',  $('#phaseCode').val(), $('#descr').val(), $('#psActionId').val());">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function validate(phaseCode, descr, psActionId){
			var valid = false;
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#phaseCode').removeClass("is-invalid").addClass("is-valid");
			$('#psActionId').removeClass("is-invalid").addClass("is-valid");
			var validatedPhaseCode = true;
			var validatedAction = true;
			var validatedDescr = true;
			if(psActionId && psActionId < 1) { validatedAction = false; } //must have a value
			if(!phaseCode || phaseCode.length > 8) { validatedPhaseCode = false; } //must be 8 chars long or shorter
			if(descr && descr.length > 40) { validatedDescr = false; } //must be only 40 chars long
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDescr = false; } //must be valid characters
			res = cleanCharacters(document.getElementById("phaseCode")); //removes invalid characters
			if(!res) { validatedPhaseCode = false; } //must be valid characters
			
			if(validatedPhaseCode && validatedDescr && validatedAction){
				valid = true;
			}else {
				if(!validatedPhaseCode){
					//highlight the field in red and display message
					$('#phaseCode').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedAction){
					$('#psActionId').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedDescr){
					$('#descr').removeClass("is-valid").addClass("is-invalid");
				}
			}
			return valid;
		}
		
		function saveForm(phaseId, phaseCode, descr, psActionId) {
	
			if(validate(phaseCode,descr,psActionId)){
				//make code be all uppercase
				phaseCode = phaseCode.toUpperCase();
				appUX.ajax.call("/CorisWEB/PSCMaintenancePhaseDefnServlet?phaseDefnId=" + phaseId + "&phaseCode=" + phaseCode + "&descr=" + descr
								+ "&mode=<%=mode %>" + "&actionId=" + psActionId, 
								function (err, data, xhr){ 
									if(err){
										$('#errorMessage').html('<span style="color:red;">'+ xhr.responseText + '</span>');
									}else{
										appUX.pop.refreshCornFrame('modalPhaseDefinitionsPrimary', false);
										closePop();
									}
								});
			}
		}
		
	</script>
</body>
</html>
