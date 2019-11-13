<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import = "gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO" %>
<%
String code = TextUtil.getParamAsString(request, "code");
String descr = TextUtil.getParamAsString(request, "descr");
String courtType = TextUtil.getParamAsString(request, "courtId");
String evalDefnId = TextUtil.getParamAsString(request, "evalDefnId");
String mode = TextUtil.getParamAsString(request, "mode");
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsCourtDefnBO> courtTypeList = (List<PsCourtDefnBO>) request.getAttribute("codesList2");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Evaluation Definitions</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Evaluation Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Evaluation Code: <%=code %>
				<% } %>
				</strong>
			</div>
			<div class="card-body" >

				<form action="#" method="post">
				<input type="hidden" id="evalDefnId" name="evalDefnId" value="<%= evalDefnId %>">
					<div class="row">
						<div class="input-group m-4">
							<label for="code" style="width: 33%; text-align: right; padding-right: 3px;">Evaluation Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="code" name="code" placeholder="Enter code" maxlength="8" <%="edit".equalsIgnoreCase(mode)?"disabled":"" %> <%="edit".equalsIgnoreCase(mode)?"value='"+code+"'":"" %> required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Evaluation Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="descr" style="width: 33%; text-align: right; padding-right: 3px;">Evaluation Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="descr" name="descr" placeholder="Enter description" maxlength="30" <%="edit".equalsIgnoreCase(mode)?"value='"+descr+"'":"" %> required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Evaluation Description is required and must be 30 or less letters or spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="courtType" style="width: 33%; text-align: right; padding-right: 3px;">Court Type <span class="text-danger">*</span></label>
							<select class="form-control" id="courtType" name="courtType" required>
								<%
								if(courtTypeList != null && courtTypeList.size() > 0) {
									String psCourtType = "";
									for (PsCourtDefnBO courtTypeBO : courtTypeList) {
										psCourtType = courtTypeBO.getPsCodeDescr();
								 %>
							 	<option value="<%= courtTypeBO.getPsCourtDefnId() %>"><%=psCourtType %></option>
							 <%
							 }
							 psCourtType = null;
							}
							  %>
							</select> 
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Court Type is required.</span></div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel" onclick="closePop();">Cancel</button>
						<% if("add".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save"onclick="saveForm($('#code').val(), $('#descr').val(), $('#courtType').val(), $('#evalDefnId').val());">Save</button>
						<% } else if("edit".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save" onclick="updateForm($('#code').val(), $('#descr').val(), $('#courtType').val(),$('#evalDefnId').val());">Save</button>
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
		
		function updateForm(code, descr, courtType, evalDefnId) {
			//do some validation of descr (code will be from the database so should be ok)
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#courtType').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedCourtType = true;
			if(descr == "") { validatedDesc = false; } //can't be an empty string
			if(descr == null) { validatedDesc = false; } //must contain a value
			if(descr.length > 30) { validatedDesc = false; } //must be 30 chars long or shorter
			if(courtType == null) { validatedCourtType = false;}
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			
			code = code.toUpperCase();
	
			if(validatedDesc){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceEvaluationDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalEvaluationDefinitionsPrimary', false);
						closePop();
					}, 
					'POST', 
					'code='+code+'&descr='+descr+'&courtId='+courtType+'&evalDefnId='+evalDefnId+'&mode=edit&displayResults=true&displayDisabled=<%=displayDisabled%>'
				);
			}else{
				if(!validatedDesc){
					//highlight the field in red and display message
					$('#descr').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		
		function saveForm(code, descr, courtType) {
			//do some validation of descr and code
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#code').removeClass("is-invalid").addClass("is-valid");
			$('#courtType').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedCode = true;
			if(descr == "") { validatedDesc = false; } //can't be an empty string
			if(code == "") { validatedCode = false; } //can't be an empty string
			if(courtType == "") { validatedCourtType = false; }
			if(descr == null) { validatedDesc = false; } //must contain a value
			if(code == null) { validatedCode = false; } //must contain a value
			if(courtType == null) { validatedCourtType = false; }
			if(descr.length > 30) { validatedDesc = false; } //must be 30 chars long or shorter
			if(code.length > 8) { validatedCode = false; } //must be 8 chars long or shorter
			res = cleanCharactersCharsOnly(document.getElementById("code")); //removes invalid characters
			if(!res) { validatedCode = false; } //must be valid characters
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			
			//make code be all uppercase
			code = code.toUpperCase();
	
			if(validatedDesc && validatedCode){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceEvaluationDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalEvaluationDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'code='+code+'&descr='+descr+'&courtId='+courtType+'&mode=add&displayResults=true&displayDisabled=<%=displayDisabled%>'
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
				if(!validatedCourtType){
					$('#courtType').removeClass("is-valid").addClass("is-invalid");
				}
			}
		}
		
	</script>
</body>
</html>
<%
//cleanup
code = null;
descr = null;
courtType = null;
mode = null;
displayDisabled = null;
%>
