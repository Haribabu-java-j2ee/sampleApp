<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%
int psActionDefnId = TextUtil.getParamAsInt(request, "psActionDefnId");
String psActionCode = TextUtil.getParamAsString(request, "psActionCode");
String psActionDescr = TextUtil.getParamAsString(request, "psActionDescr");
int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
String mode = TextUtil.getParamAsString(request, "mode");
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsStatusDefnBO> statusCodesList = (List<PsStatusDefnBO>) request.getAttribute("statusCodesList");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Action Definitions</title>
	<%//styles and javascript %>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Action Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Action Code: <%=psActionCode %>
				<% } %>
				</strong>
			</div>
			<div class="card-body" >
				<form action="#" method="post">
				<input type="hidden" id="psActionDefnId" value="<%=psActionDefnId %>">
				<div class="row">
					<div class="input-group m-4">
						<label for="psActionCode" style="width: 33%; text-align: right; padding-right: 3px;">Action Code <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="psActionCode" placeholder="Enter code" maxlength="8" <%="edit".equalsIgnoreCase(mode)?"disabled":"" %> <%="edit".equalsIgnoreCase(mode)?"value='"+psActionCode+"'":"" %> required autofocus>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Action Code is required and must be 8 or less letters, no spaces.</span></div>
					</div>
				</div>
				<div class="row">
					<div class="input-group m-4">
						<label for="psActionDescr" style="width: 33%; text-align: right; padding-right: 3px;">Action Description <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="psActionDescr" placeholder="Enter description" <%="edit".equalsIgnoreCase(mode)?"value='"+psActionDescr+"'":"" %> maxlength="60" required>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Action Description is required and must be 60 or less characters. Invalid characters have been removed. Please verify and save again.</span></div>
					</div>
				</div>					
				<div class="row">
					<div class="input-group m-4">
						<label for="psStatusDefnId" style="width: 33%; text-align: right; padding-right: 3px;">Status Code</label>
						<select class="form-control" id="psStatusDefnId">
							<option value=""></option>
							<%
							if(statusCodesList.size() > 0) {
								int statusDefnId = 0;
								String statusCode = "";
								for (PsStatusDefnBO statusCodeBO : statusCodesList) {
									statusDefnId = statusCodeBO.getPsStatusDefnId();
									statusCode = statusCodeBO.getPsStatusCode();
									%>
										<option value="<%=statusDefnId %>" <%if(psStatusDefnId == statusDefnId){ %> selected <%} %>><%=statusCode %></option>
									<%
								}
								statusCode = null;
							}
							%>
						</select>
					</div>
				</div>
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" id="cancelBtn" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel">Cancel</button>
					<% if("add".equals(mode)) { %>
						<button type="button" id="saveBtn" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save">Save</button>
					<% } else if("edit".equals(mode)) { %>
						<button type="button" id="updateBtn" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save">Save</button>
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
		$(document).ready(function(){
		
			$('#cancelBtn').on('click', function(){
				closePop();
			});	
			
			$('#saveBtn').on('click', function(){
				saveForm($('#psActionCode').val(), $('#psActionDescr').val(), $('#psStatusDefnId').val());
			});
			
			$('#updateBtn').on('click', function(){
				updateForm($('#psActionDefnId').val(), $('#psActionDescr').val(), $('#psStatusDefnId').val());
			});
			
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function updateForm(psActionDefnId, psActionDescr, psStatusDefnId) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr (code will be from the database so should be ok)
			$('#psActionDescr').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			if(psActionDescr == "") { validatedDesc = false; } //can't be an empty string
			if(psActionDescr == null) { validatedDesc = false; } //must contain a value
			if(psActionDescr.length > 60) { validatedDesc = false; } //must be 60 chars long or shorter
			res = cleanCharacters(document.getElementById("psActionDescr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			if(validatedDesc){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceActionDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalActionDefinitionsPrimary', false);
						closePop(); 
					}, 
					'POST', 
					'psActionDefnId='+psActionDefnId+'&psActionDescr='+psActionDescr+'&psStatusDefnId='+psStatusDefnId+'&update=true&mode=edit&displayDisabled='+disabled
				);
			}else{
				//highlight the field in red and display message
				$('#psActionDescr').removeClass("is-valid").addClass("is-invalid");
			}
		}
		
		function saveForm(psActionCode, psActionDescr, psStatusDefnId) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr and code (statusCodeId will be from the database, so should be good)
			$('#psActionDescr').removeClass("is-invalid").addClass("is-valid");
			$('#psActionCode').removeClass("is-invalid").addClass("is-valid");
			var validatedActionDesc = true;
			var validatedActionCode = true;
			if(psActionDescr == "") { validatedActionDesc = false; } //can't be an empty string
			if(psActionCode == "") { validatedActionCode = false; } //can't be an empty string
			if(psActionDescr == null) { validatedActionDesc = false; } //must contain a value
			if(psActionCode == null) { validatedActionCode = false; } //must contain a value
			if(psActionDescr.length > 60) { validatedActionDesc = false; } //must be 60 chars long or shorter
			if(psActionCode.length > 8) { validatedActionCode = false; } //must be 8 chars long or shorter
			res = cleanCharactersCharsOnly(document.getElementById("psActionCode")); //removes invalid characters
			if(!res) { validatedActionCode = false; } //must be valid characters
			res = cleanCharacters(document.getElementById("psActionDescr")); //removes invalid characters
			if(!res) { validatedActionDesc = false; } //must be valid characters
					
			//make action code be all uppercase
			psActionCode = psActionCode.toUpperCase();
		
			if(validatedActionDesc && validatedActionCode){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceActionDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalActionDefinitionsPrimary', false);
							closePop();
						} 
					}, 
					'POST', 
					'psActionCode='+psActionCode+'&psActionDescr='+psActionDescr+'&psStatusDefnId='+psStatusDefnId+'&save=true&mode=add&displayDisabled='+disabled
				); 
			}else {
				if(!validatedActionDesc){
					//highlight the field in red and display message
					$('#psActionDescr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedActionCode){
					//highlight the field in red and display message
					$('#psActionCode').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
	
	</script>
</body>
</html>
