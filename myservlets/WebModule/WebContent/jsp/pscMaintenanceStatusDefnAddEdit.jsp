<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%
int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
String psStatusCode = TextUtil.getParamAsString(request, "psStatusCode");
String psStatusDescr = TextUtil.getParamAsString(request, "psStatusDescr");
String mode = TextUtil.getParamAsString(request, "mode");
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Status Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Status Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Status Code: <%=psStatusCode %>
				<% } %>
				</strong>
			</div>
			<div class="card-body">
				<input type="hidden" id="psStatusDefnId" value="<%=psStatusDefnId %>">
					<form action="#" method="post">
					<div class="row">
						<div class="input-group m-4">
							<label for="psStatusCode" style="width: 33%; text-align: right; padding-right: 3px;">Status Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="psStatusCode" placeholder="Enter code" maxlength="8" <%="edit".equalsIgnoreCase(mode)?"disabled":"" %> <%="edit".equalsIgnoreCase(mode)?"value='"+psStatusCode+"'":"" %> required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Status Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="psStatusDescr" style="width: 33%; text-align: right; padding-right: 3px;">Status Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="psStatusDescr" placeholder="Enter description" maxlength="30" <%="edit".equalsIgnoreCase(mode)?"value='"+psStatusDescr+"'":"" %> required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Status Description is required and must be 30 or less letters or spaces.</span></div>
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
				saveForm($('#psStatusCode').val(), $('#psStatusDescr').val());
			});
			$('#updateBtn').on('click', function(){
				updateForm('<%=psStatusDefnId %>', $('#psStatusDescr').val());
			});
		});
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		function updateForm(psStatusDefnId, psStatusDescr) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr (code will be from the database so should be ok)
			$('#psStatusDescr').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedStatusId = true;
			if(psStatusDescr == "") { validatedDesc = false; } //can't be an empty string
			if(psStatusDescr == null) { validatedDesc = false; } //must contain a value
			if(psStatusDescr.length > 30) { validatedDesc = false; } //must be 30 chars long or shorter
			if(psStatusDefnId < 1) { validatedStatusId = false; } //must contain a value
			res = cleanCharacters(document.getElementById("psStatusDescr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			
			if(validatedDesc && validatedStatusId){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceStatusDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalStatusDefinitionsPrimary', false); 
						closePop(); 
					}, 
					'POST', 
					'psStatusDefnId='+psStatusDefnId+'&psStatusDescr='+psStatusDescr+'&update=true&mode=edit&displayDisabled='+disabled
				);
			}else{
				if(!validatedDesc){
					//highlight the field in red and display message
					$('#psStatusDescr').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		function saveForm(psStatusCode, psStatusDescr) {
			var disabledToggle = $('#displayDisabled:checked').val();
			var disabled = false; //don't display the disabled
			if(disabledToggle != undefined) {
				disabled = true; //display the disabled
			}
			//do some validation of descr and code
			$('#psStatusDescr').removeClass("is-invalid").addClass("is-valid");
			$('#psStatusCode').removeClass("is-invalid").addClass("is-valid");
			var validatedStatusDesc = true;
			var validatedStatusCode = true;
			if(psStatusDescr == "") { validatedStatusDesc = false; } //can't be an empty string
			if(psStatusCode == "") { validatedStatusCode = false; } //can't be an empty string
			if(psStatusDescr == null) { validatedStatusDesc = false; } //must contain a value
			if(psStatusCode == null) { validatedStatusCode = false; } //must contain a value
			if(psStatusDescr.length > 30) { validatedStatusDesc = false; } //must be 30 chars long or shorter
			if(psStatusCode.length > 8) { validatedStatusCode = false; } //must be 8 chars long or shorter
			res = cleanCharacters(document.getElementById("psStatusDescr")); //removes invalid characters
			if(!res) { validatedStatusDesc = false; } //must be valid characters
			res = cleanCharactersCharsOnly(document.getElementById("psStatusCode")); //removes invalid characters
			if(!res) { validatedStatusCode = false; } //must be valid characters
		
			//make status code be all uppercase
			psStatusCode = psStatusCode.toUpperCase();
		
			if(validatedStatusDesc && validatedStatusCode){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceStatusDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalStatusDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'psStatusCode='+psStatusCode+'&psStatusDescr='+psStatusDescr+'&&save=true&mode=add&displayDisabled='+disabled
				); 
			}else {
				if(!validatedStatusDesc){
					//highlight the field in red and display message
					$('#psStatusDescr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedStatusCode){
					//highlight the field in red and display message
					$('#psStatusCode').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
	</script>
</body>
</html>
<%
//cleanup
psStatusCode = null;
psStatusDescr = null; 
mode = null;
displayDisabled = null;
%>
