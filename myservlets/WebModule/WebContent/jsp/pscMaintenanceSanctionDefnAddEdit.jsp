<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctionleveldefn.PsSanctionLevelDefnBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanction.PsSanctionBO"%>
<%
String mode = TextUtil.getParamAsString(request,"mode");
PsSanctionDefnBO sanctionDefn = (PsSanctionDefnBO)request.getAttribute("sanctionDefn");
List<PsSanctionLevelDefnBO> sanctionLevelDefnList = (List<PsSanctionLevelDefnBO>)request.getAttribute("sanctionLevelDefnList");
int currentSanctionLevelId = sanctionDefn.getPsSanctionLevel();
if("add".equalsIgnoreCase(mode)){  
	mode = "create";
}else {
	mode = "update";
}
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Problem Solving Court - Sanction Definition</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid" style="text-align:center" id="errorMessage">
	</div>
	
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("add".equals(mode)) { %>
					Add New Sanction Definition
				<% } else if("edit".equals(mode)) { %>
					Edit Sanction Definition
				<% } %>
				</strong>
			</div>
			<div class="card-body">
				<form action="#" method="post">
					<div class="row">
						<div class="input-group m-4">
							<label for="phaseCode" style="width: 33%; text-align: right; padding-right: 3px;">Sanction Code <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="sanctionCode" name="sanctionCode" <%="update".equalsIgnoreCase(mode)?"disabled":"" %>
								placeholder="Enter sanction definition code" value="<%=TextUtil.print(sanctionDefn.getPsSanctionCode()) %>"
								maxlength="8" required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Sanction Definition Code is required and must be 8 or less letters, no spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="descr" style="width: 33%; text-align: right; padding-right: 3px;">Sanction Description <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="descr" name="descr" value="<%=TextUtil.print(sanctionDefn.getPsSanctionDescr()) %>"
									placeholder="Enter description" maxlength="30" required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Sanction Definition Description is required and must be 40 or less letters or spaces.</span></div>
						</div>
					</div>
					<div class="row">
						<div class="input-group m-4">
							<label for="level" style="width: 33%; text-align: right; padding-right: 3px;">Sanction Level</label>
							<select class="form-control" id="level" name="level">
								<% 
								if(sanctionLevelDefnList != null && sanctionLevelDefnList.size() > 0){
									int sanctionLevelId = 0; 
									String sanctionLevelDescr = "";
									for (PsSanctionLevelDefnBO results : sanctionLevelDefnList) { 
										sanctionLevelId = results.getSanctionLevelSrl();
										sanctionLevelDescr = results.getSanctionLevelDescr();
										%>
										<option value="<%=TextUtil.print(sanctionLevelId) %>" <%=sanctionLevelId == currentSanctionLevelId?"selected":"" %>><%=TextUtil.print(sanctionLevelDescr) %></option>
										<% 
									}
								} 
								%>
							</select>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel" onclick="closePop();">Cancel</button>
						<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save"
							onclick="saveForm($('#sanctionCode').val(), $('#descr').val(), $('#level').val());">Save</button>
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
		function validate(sanctionCode, descr){
			var valid = false;
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#sanctionCode').removeClass("is-invalid").addClass("is-valid");
			var validSanctionCode = true;
			var validSanctionDescr = true;
			if(!sanctionCode || sanctionCode.length > 8) { validSanctionCode = false; } //must be 8 chars long or shorter
			if(!descr || descr.length > 40) {validSanctionDescr = false; }
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validSanctionDescr = false; } //must be valid characters
			res = cleanCharactersCharsOnly(document.getElementById("sanctionCode")); //removes invalid characters
			if(!res) { validSanctionCode = false; } //must be valid characters
			
			if(validSanctionCode && validSanctionDescr){
				valid = true;
			}else {
				if(!validSanctionCode){
					//highlight the field in red and display message
					$('#sanctionCode').removeClass("is-valid").addClass("is-invalid");
				}
				if(!validSanctionDescr){
					//highlight the field in red and display message
					$('#descr').removeClass("is-valid").addClass("is-invalid");
				}
			}
			
			return valid;
		}
		function saveForm(sanctionCode, descr, level) {
			//do some validation of descr and code
			var mode = '<%=mode %>';
			
			if(validate(sanctionCode, descr)){
				//make code be all uppercase
				sanctionCode = sanctionCode.toUpperCase();
	
				appUX.ajax.call("/CorisWEB/PSCMaintenanceSanctionDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalSanctionDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'sanctionCode='+sanctionCode+'&descr='+descr+'&level='+level+'&mode='+mode
				);
			}
		}
	</script>
</body>
</html>
