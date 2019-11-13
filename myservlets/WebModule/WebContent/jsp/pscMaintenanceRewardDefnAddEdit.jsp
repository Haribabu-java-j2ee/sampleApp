<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewardleveldefn.PsRewardLevelDefnBO"%>
<%
String code = TextUtil.getParamAsString(request, "code");
String descr = TextUtil.getParamAsString(request, "descr");
String rewDefnId = TextUtil.getParamAsString(request, "rewDefnId");
String mode = TextUtil.getParamAsString(request, "mode");
int currentRewardLevelId = TextUtil.getParamAsInt(request, "level");
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsRewardLevelDefnBO> rewardLevelDefnList = (List<PsRewardLevelDefnBO>)request.getAttribute("rewardLevelDefnList");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Reward Definition</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>
				<% if("addPopup".equals(mode)) { %>
					Add New Reward Definition
				<% } else if("editPopup".equals(mode)) { %>
					Edit Reward Code: <%=code %>
				<% } %>
				</strong>
			</div>
			<div class="card-body">
			<form action="#" method="post">
				<input type="hidden" id="rewDefnId" name="rewDefnId" value="<%=rewDefnId %>">
				<div class="row">
					<div class="input-group m-4">
						<label for="code" style="width: 33%; text-align: right; padding-right: 3px;">Reward Code <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="code" name="code" placeholder="Enter code" maxlength="8" <%="editPopup".equalsIgnoreCase(mode)?"disabled":"" %> <%="editPopup".equalsIgnoreCase(mode)?"value='"+code+"'":"" %> required autofocus>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Reward Code is required and must be 8 or less letters, no spaces.</span></div>
					</div>
				</div>
				<div class="row">
					<div class="input-group m-4">
						<label for="descr" style="width: 33%; text-align: right; padding-right: 3px;">Reward Description <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="descr" name="descr" placeholder="Enter description" <%="editPopup".equalsIgnoreCase(mode)?"value='"+descr+"'":"" %> maxlength="30" required>
						<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;"><span class="float-right" style="width: 66%;">Reward Description is required and must be 30 or less letters or spaces.</span></div>
					</div>
				</div>
				<div class="row">
					<div class="input-group m-4">
						<label for="level" style="width: 33%; text-align: right; padding-right: 3px;">Reward Level</label>
						<select class="form-control" id="level" name="level">
							<% 
							if(rewardLevelDefnList != null && rewardLevelDefnList.size() > 0){
								int rewardLevelId = 0; 
								String rewardLevelDescr = "";
								for (PsRewardLevelDefnBO results : rewardLevelDefnList) { 
									rewardLevelId = results.getRewardLevelSrl();
									rewardLevelDescr = results.getRewardLevelDescr();
									%>
									<option value="<%=TextUtil.print(rewardLevelId) %>" <%=rewardLevelId == currentRewardLevelId?"selected":"" %>><%=TextUtil.print(rewardLevelDescr) %></option>
									<% 
								}
							} 
							%>
						</select>
					</div>
				</div>
				<div class="std-fixed bottom bg-light popcorn-ftr active">
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel" onclick="closePop();">Cancel</button>
						<% if("addPopup".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save" onclick="saveForm($('#code').val(), $('#descr').val());">Save</button>
						<% } else if("editPopup".equals(mode)) { %>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save" onclick="updateForm($('#code').val(), $('#descr').val());">Save</button>
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
		function updateForm(code, descr, rewDefnId) {
			//do some validation of descr (code will be from the database so should be ok)
			$('#code').removeClass("is-invalid").addClass("is-valid");
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			if(code == "") { validatedCode = false; } //can't be an empty string
			if(code == null) { validatedCode = false; } //must contain a value
			if(descr == "") { validatedDesc = false; } //can't be an empty string
			if(descr == null) { validatedDesc = false; } //must contain a value
			if(descr.length > 30) { validatedDesc = false; } //must be 30 chars long or shorter
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
						
			code = code.toUpperCase();
	
			if(validatedDesc){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceRewardDefnServlet", 
					function (err, data, xhr) { 
						appUX.pop.refreshCornFrame('modalRewardDefinitionsPrimary', false);
						closePop();
					}, 
					'POST', 
					'code='+code+'&descr='+descr+'&level='+$('#level').val()+'&rewDefnId='+rewDefnId+'&mode=edit&displayResults=true&displayDisabled=<%=displayDisabled%>'
				);
			}else{
				//highlight the field in red and display message
				$('#descr').removeClass("is-valid").addClass("is-invalid");
			}
		}
		function saveForm(code, descr) {
			//do some validation of descr and code
			$('#descr').removeClass("is-invalid").addClass("is-valid");
			$('#code').removeClass("is-invalid").addClass("is-valid");
			var validatedDesc = true;
			var validatedCode = true;
			if(descr == "") { validatedDesc = false; } //can't be an empty string
			if(code == "") { validatedCode = false; } //can't be an empty string
			if(descr == null) { validatedDesc = false; } //must contain a value
			if(code == null) { validatedCode = false; } //must contain a value
			if(descr.length > 30) { validatedDesc = false; } //must be 30 chars long or shorter
			if(code.length > 8) { validatedCode = false; } //must be 8 chars long or shorter
			res = cleanCharacters(document.getElementById("descr")); //removes invalid characters
			if(!res) { validatedDesc = false; } //must be valid characters
			res = cleanCharactersCharsOnly(document.getElementById("code")); //removes invalid characters
			if(!res) { validatedCode = false; } //must be valid characters
			
			//make code be all uppercase
			code = code.toUpperCase();
	
			if(validatedDesc && validatedCode){
				appUX.ajax.call("/CorisWEB/PSCMaintenanceRewardDefnServlet", 
					function (err, data, xhr) { 
						if(err){
							var message = xhr.responseText.indexOf("Unique Constraint") > 0?"Code must be unique. Please rename.":xhr.responseTest;
							appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.DANGER);
						}else{
							appUX.pop.refreshCornFrame('modalRewardDefinitionsPrimary', false);
							closePop();
						}
					}, 
					'POST', 
					'code='+code+'&descr='+descr+'&level='+$('#level').val()+'&mode=add&displayResults=true&displayDisabled=<%=displayDisabled%>'
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
			}
		}
	</script>
</body>
</html>
<%
//cleanup
code = null;
descr = null;
mode = null;
displayDisabled = null;
%>
