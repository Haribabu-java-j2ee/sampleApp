<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import = "gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO" %>
<%
int appearDefnId = URLEncryption.getParamAsInt(request,"appearDefnId");
String mode = "add";
PsAppearanceDefnBO appearDefn = new PsAppearanceDefnBO();
if(appearDefnId > 0){
	mode="edit";
	appearDefn = appearDefn.where(PsAppearanceDefnBO.PSAPPEARANCEDEFNID,appearDefnId).find();
}
String code = appearDefn.getPsAppearanceCode();
String descr = appearDefn.getPsAppearanceDescr();
int actionId = appearDefn.getPsActionDefnId();
String minText = appearDefn.getPsMinutesText();
if(minText == null){
	minText = "";
}
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsActionDefnBO> actionCodeList = new PsActionDefnBO().where(PsActionDefnBO.REMOVEDFLAG, "N").orderBy(PsActionDefnBO.PSACTIONCODE).search();
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Appearance Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Appearance Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Appearance Code: <%=code %>
				<% } %>
				</strong>
			</div>
			<div class="card-body" >
				<form action="#" method="post">
					<input type="hidden" id="appearDefnId" name="appearDefnId" value="<%= appearDefnId %>">
					<div class="row">
						<div class="input-group m-4">
							<label for="code" style="width: 33%; text-align: right; padding-right: 3px;">Appearance Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="code" name="code" placeholder="Enter code" maxlength="8" <%="edit".equalsIgnoreCase(mode)?"disabled":"" %> <%="edit".equalsIgnoreCase(mode)?"value='"+code+"'":"" %> required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Appearance Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="descr" style="width: 33%; text-align: right; padding-right: 3px;">Appearance Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="descr" name="descr" placeholder="Enter description" <%="edit".equalsIgnoreCase(mode)?"value='"+descr+"'":"" %> maxlength="60" required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Treatment Description is required and must be 60 or less letters or spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="actionId" style="width: 33%; text-align: right; padding-right: 3px;">Action Code <span class="text-danger">*</span></label>
							<select class="form-control" id="actionId" name="actionId" required>
								<%
								if(actionCodeList != null && actionCodeList.size() > 0) {
									String psActionCode = "";
									for (PsActionDefnBO actionCodeBO : actionCodeList) {
										psActionCode = actionCodeBO.getPsActionDescr();
								 %>
							 	<option value="<%= actionCodeBO.getPsActionDefnId() %>" <%=actionCodeBO.getPsActionDefnId()==actionId?"selected":"" %>><%=psActionCode %></option>
							 <%
							 }
							 psActionCode = null;
							}
							  %>
							</select> 
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Court Type is required.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="minText" style="width: 33%; text-align: right; padding-right: 3px;"> Minutes Text <span class="text-danger">*</span></label>
							<textarea class="form-control" style="width: 33%;" id="minText" name="minText" placeholder="Enter Minutes Text" maxlength="120" style="height: 75px;" required><%=minText %></textarea>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Minutes Text is required.</span></div>						
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel" onclick="closePop();">Cancel</button>
						<% if("add".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save"onclick="saveForm($('#code').val(), $('#descr').val(), $('#actionId').val(), $('#minText').val());">Save</button>
						<% } else if("edit".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save" onclick="updateForm($('#code').val(), $('#descr').val(), $('#actionId').val(), $('#appearDefnId').val(), $('#minText').val());">Save</button>
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
		
		function updateForm(code, descr, actionId, appearDefnId, minText) {
			//do some validation of descr (code will be from the database so should be ok)
			$('#code').removeClass("is-invalid").addClass("is-valid");
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#actionId').removeClass("is-invalid").addClass("is-valid");
			$('#minText').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedMinText = true;
			if(descr == "") { validatedDesc = false; } //can't be an empty string
			if(descr == null) { validatedDesc = false; } //must contain a value
			if(descr.length > 60) { validatedDesc = false; } //must be 60 chars long or shorter
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			if(minText.length > 120) { validatedMinText = false; } //must be 120 chars long or shorter
			if(minText == "") { validatedMinText = false; } //can't be an empty string
			res = cleanCharacters(document.getElementById("minText")); //removes invalid characters
			if(!res) { validatedMinText = false; } //must be valid characters
						
			code = code.toUpperCase();
			
			if(validatedDesc && validatedMinText){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceAppearanceDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalAppearanceDefinitionsPrimary', true);
						closePop();
					}, 
					'POST', 
					'code='+code+'&descr='+descr+'&actionId='+actionId+'&appearDefnId='+appearDefnId+'&minText='+minText+'&mode=edit&displayResults=true&displayDisabled=<%=displayDisabled%>'
				);
			}else{
				if(!validatedDesc){
					//highlight the field in red and display message
					$('#descr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedMinText){
					//highlight the field in red and display message
					$('#minText').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		
		function saveForm(code, descr, actionId, minText) {
			//do some validation of descr and code
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#code').removeClass("is-invalid").addClass("is-valid");
			$('#actionId').removeClass("is-invalid").addClass("is-valid");
			$('#minText').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedCode = true;
			var validatedActionId = true;
			var validatedMinText = true;
			if(descr == "") { validatedDesc = false; } //can't be an empty string
			if(code == "") { validatedCode = false; } //can't be an empty string
			if(minText == "") { validatedMinText = false; } //can't be an empty string
			if(descr == null) { validatedDesc = false; } //must contain a value
			if(code == null) { validatedCode = false; } //must contain a value
			if(actionId == null) { validatedActionId = false; } //must contain a value
			if(minText == null) { validatedMinText = false; } //must contain a value
			if(descr.length > 30) { validatedDesc = false; } //must be 30 chars long or shorter
			if(code.length > 8) { validatedCode = false; } //must be 8 chars long or shorter
			if(minText.length > 120) { validatedMinText = false; } //must be 120 chars long or shorter
			res = cleanCharactersCharsOnly(document.getElementById("code")); //removes invalid characters
			if(!res) { validatedCode = false; } //must be valid characters
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			res = cleanCharacters(document.getElementById("minText")); //removes invalid characters
			if(!res) { validatedMinText = false; } //must be valid characters

			//make code be all uppercase
			code = code.toUpperCase();
	
			if(validatedDesc && validatedCode && validatedMinText){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceAppearanceDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalAppearanceDefinitionsPrimary', false);
						closePop();
					}, 
					'POST', 
					'code='+code+'&descr='+descr+'&actionId='+actionId+'&minText='+minText+'&mode=add&displayResults=true&displayDisabled=<%=displayDisabled%>'
				);
			}else {
				if(!validatedDesc){
					//highlight the field in red and display message
					$('#descr').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedCode){
					//highlight the field in red and display message
					$('#code').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedActionId){
					$('#actionId').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validatedMinText){
					$('#minText').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		
	</script>
</body>
</html>
