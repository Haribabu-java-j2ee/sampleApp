<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%
int psTerminationDefnId = TextUtil.getParamAsInt(request, "psTerminationDefnId");
String psTerminationCode = TextUtil.getParamAsString(request, "psTerminationCode");
String psTerminationDescr = TextUtil.getParamAsString(request, "psTerminationDescr");
String mode = TextUtil.getParamAsString(request, "mode");
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Termination Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Termination Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Termination Code: <%=psTerminationCode %>
				<% } %>
				</strong>
			</div>
			<div class="card-body">
					<form action="#" method="post">
					<input type="hidden" id="psTerminationDefnId" name="psTerminationDefnId" value="<%=psTerminationDefnId %>">
					<div class="row">
						<div class="input-group m-4">
							<label for="psTerminationCode" style="width: 33%; text-align: right; padding-right: 3px;">Termination Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="psTerminationCode" placeholder="Enter code" maxlength="8" <%="edit".equalsIgnoreCase(mode)?"disabled":"" %> <%="edit".equalsIgnoreCase(mode)?"value='"+psTerminationCode+"'":"" %> required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Termination Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="psTerminationDescr" style="width: 33%; text-align: right; padding-right: 3px;">Termination Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="psTerminationDescr" placeholder="Enter description" <%="edit".equalsIgnoreCase(mode)?"value='"+psTerminationDescr+"'":"" %> maxlength="40" required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Termination Description is required and must be 40 or less letters or spaces.</span></div>
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
				saveForm($('#psTerminationCode').val(), $('#psTerminationDescr').val());
			});
			$('#updateBtn').on('click', function(){
				updateForm('<%=psTerminationDefnId %>', $('#psTerminationDescr').val());
			});
		});
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		function updateForm(psTerminationDefnId, psTerminationDescr) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr (code will be from the database so should be ok)
			$('#psTerminationDescr').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			if(psTerminationDescr == "") { validatedDesc = false; } //can't be an empty string
			if(psTerminationDescr == null) { validatedDesc = false; } //must contain a value
			if(psTerminationDescr.length > 40) { validatedDesc = false; } //must be 40 chars long or shorter
			res = cleanCharacters(document.getElementById("psTerminationDescr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters

			if(validatedDesc){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceTerminationDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalTerminationDefinitionsPrimary', false);
						closePop(); 
					}, 
					'POST', 
					'psTerminationDefnId='+psTerminationDefnId+'&psTerminationDescr='+psTerminationDescr+'&update=true&mode=edit&displayDisabled=<%=displayDisabled%>'
				);
			}else{
				//highlight the field in red and display message
				$('#psTerminationDescr').removeClass("is-valid").addClass("is-invalid");
			}
		}
		function saveForm(psTerminationCode, psTerminationDescr) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr and code
			$('#psTerminationDescr').removeClass("is-invalid").addClass("is-valid");
			$('#psTerminationCode').removeClass("is-invalid").addClass("is-valid");
			var validatedTerminationDesc = true;
			var validatedTerminationCode = true;
			if(psTerminationDescr == "") { validatedTerminationDesc = false; } //can't be an empty string
			if(psTerminationCode == "") { validatedTerminationCode = false; } //can't be an empty string
			if(psTerminationDescr == null) { validatedTerminationDesc = false; } //must contain a value
			if(psTerminationCode == null) { validatedTerminationCode = false; } //must contain a value
			if(psTerminationDescr.length > 40) { validatedTerminationDesc = false; } //must be 40 chars long or shorter
			if(psTerminationCode.length > 8) { validatedTerminationCode = false; } //must be 8 chars long or shorter
			res = cleanCharacters(document.getElementById("psTerminationDescr")); //removes invalid characters
			if(!res) { validatedTerminationDesc = false; } //must be valid characters
			res = cleanCharactersCharsOnly(document.getElementById("psTerminationCode")); //removes invalid characters
			if(!res) { validatedTerminationCode = false; } //must be valid characters
		
			//make termination code be all uppercase
			psTerminationCode = psTerminationCode.toUpperCase();
		
			if(validatedTerminationDesc && validatedTerminationCode){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceTerminationDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalTerminationDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'psTerminationCode='+psTerminationCode+'&psTerminationDescr='+psTerminationDescr+'&save=true&mode=add&displayDisabled='+disabled
				); 
			}else {
				if(!validatedTerminationDesc){
					//highlight the field in red and display message
					$('#psTerminationDescr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedTerminationCode){
					//highlight the field in red and display message
					$('#psTerminationCode').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
	</script>
</body>
</html>
<%
//cleanup
psTerminationCode = null;
psTerminationDescr = null; 
mode = null;
displayDisabled = null;
%>
