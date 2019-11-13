<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%
int psGoalDefnId = TextUtil.getParamAsInt(request, "psGoalDefnId");
String psGoalCode = TextUtil.getParamAsString(request, "psGoalCode");
String psGoalDescr = TextUtil.getParamAsString(request, "psGoalDescr");
int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
String mode = TextUtil.getParamAsString(request, "mode");
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsCourtDefnBO> courtCodesList = (List<PsCourtDefnBO>) request.getAttribute("courtCodesList");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Goal Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Goal Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Goal Code: <%=psGoalCode %>
				<% } %>
				</strong>
			</div>
			<div class="card-body">
				<input type="hidden" id="psGoalDefnId" value="<%=psGoalDefnId %>">
					<form action="#" method="post">
					<div class="row">
						<div class="input-group m-4">
							<label for="psGoalCode" style="width: 33%; text-align: right; padding-right: 3px;">Goal Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="psGoalCode" placeholder="Enter code" maxlength="8" <%="edit".equalsIgnoreCase(mode)?"disabled":"" %> <%="edit".equalsIgnoreCase(mode)?"value='"+psGoalCode+"'":"" %> required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Goal Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="psGoalDescr" style="width: 33%; text-align: right; padding-right: 3px;">Goal Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="psGoalDescr" placeholder="Enter description" maxlength="40" <%="edit".equalsIgnoreCase(mode)?"value='"+psGoalDescr+"'":"" %> required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Goal Description is required and must be 40 or less letters or spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="psCourtDefnId" style="width: 33%; text-align: right; padding-right: 3px;">Court Code</label>
							<select class="form-control" id="psCourtDefnId" required>
								<%
								if(courtCodesList.size() > 0) {
									int courtDefnId = 0;
									String courtCode = "";
									for (PsCourtDefnBO courtCodeBO : courtCodesList) {
										courtDefnId = courtCodeBO.getPsCourtDefnId();
										courtCode = courtCodeBO.getPsCourtCode();
										%>
											<option value="<%=courtDefnId %>" <%=courtDefnId==psCourtDefnId?"selected":"" %>><%=courtCode %></option>
										<%
									}
									courtCode = null;
								}
								%>
							</select>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Court Code is required.</span></div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel" onclick="closePop();">Cancel</button>
						<% if("add".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save"onclick="saveForm($('#psGoalCode').val(), $('#psGoalDescr').val(), $('#psCourtDefnId').val());">Save</button>
						<% } else if("edit".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save" onclick="updateForm(<%=psGoalDefnId %>, $('#psGoalDescr').val(), $('#psCourtDefnId').val());">Save</button>
						<% } %>
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
		
		function updateForm(psGoalDefnId, psGoalDescr, psCourtDefnId) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr and court code (code will be from the database so should be ok)
			$('#psGoalDescr').removeClass("is-invalid").addClass("is-valid");
			$('#psCourtDefnId').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedCourtId = true;
			if(psCourtDefnId < 1) { validatedCourtId = false; } //can't be an empty string
			if(psGoalDescr == "") { validatedDesc = false; } //can't be an empty string
			if(psGoalDescr == null) { validatedDesc = false; } //must contain a value
			if(psGoalDescr.length > 40) { validatedDesc = false; } //must be 40 chars long or shorter
			res = cleanCharacters(document.getElementById("psGoalDescr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters

			if(validatedDesc && validatedCourtId){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceGoalDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalGoalDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'psGoalDefnId='+psGoalDefnId+'&psGoalDescr='+psGoalDescr+'&psCourtDefnId='+psCourtDefnId+'&update=true&mode=edit&displayDisabled='+disabled
				);
			}else{
				if(!validatedDesc){
					//highlight the field in red and display message
					$('#psGoalDescr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedCourtId){
					//highlight the field in red and display message
					$('#psCourtDefnId').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		
		function saveForm(psGoalCode, psGoalDescr, psCourtDefnId) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr and code (courtCodeId will be from the database, so should be good)
			$('#psGoalDescr').removeClass("is-invalid").addClass("is-valid");
			$('#psGoalCode').removeClass("is-invalid").addClass("is-valid");
			$('#psCourtDefnId').removeClass("is-invalid").addClass("is-valid");
			var validatedGoalDesc = true;
			var validatedGoalCode = true;
			var validatedCourtId = true;
			if(psCourtDefnId == "") { validatedCourtId = false; } //can't be an empty string
			if(psGoalDescr == "") { validatedGoalDesc = false; } //can't be an empty string
			if(psGoalCode == "") { validatedGoalCode = false; } //can't be an empty string
			if(psGoalDescr == null) { validatedGoalDesc = false; } //must contain a value
			if(psGoalCode == null) { validatedGoalCode = false; } //must contain a value
			if(psGoalDescr.length > 40) { validatedGoalDesc = false; } //must be 40 chars long or shorter
			if(psGoalCode.length > 8) { validatedGoalCode = false; } //must be 8 chars long or shorter
			res = cleanCharactersCharsOnly(document.getElementById("psGoalCode")); //removes invalid characters
			if(!res) { validatedGoalCode = false; } //must be valid characters
			res = cleanCharacters(document.getElementById("psGoalDescr")); //removes invalid characters
			if(!res) { validatedGoalDesc = false; } //must be valid characters
		
			//make goal code be all uppercase
			psGoalCode = psGoalCode.toUpperCase();
		
			if(validatedGoalDesc && validatedGoalCode && validatedCourtId){

				appUX.ajax.call("/CorisWEB/PSCMaintenanceGoalDefnServlet", 
					function (err, data, xhr){ 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalGoalDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'psGoalCode='+psGoalCode+'&psGoalDescr='+psGoalDescr+'&psCourtDefnId='+psCourtDefnId+'&save=true&mode=add&displayDisabled='+disabled
				); 
			}else {
				if(!validatedGoalDesc){
					//highlight the field in red and display message
					$('#psGoalDescr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedGoalCode){
					//highlight the field in red and display message
					$('#psGoalCode').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedCourtId){
					//highlight the field in red and display message
					$('#psCourtDefnId').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		
	</script>
</body>
</html>
<%
//cleanup
psGoalCode = null;
psGoalDescr = null; 
mode = null;
displayDisabled = null;
courtCodesList = null;
%>
