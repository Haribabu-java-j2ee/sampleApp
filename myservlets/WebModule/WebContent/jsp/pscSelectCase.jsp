<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO"%>
<%@page import="java.util.Date"%>
<%
String mode = URLEncryption.getParamAsString(request, "mode");
String courtType = URLEncryption.getParamAsString(request, "courtType");
int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
AccesslevelDefnBO accessLevelDefnBO = (AccesslevelDefnBO) request.getAttribute("accessLevel");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Admin - Define Access Levels</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div class="input-group m-4">
					<label for="intCaseNum" style="width: 20%; text-align: right; padding-right: 3px;">Case Number <span class="text-danger">*</span></label>
					<input class="form-control" id="caseNum" name="caseNum" autofocus value="<%= TextUtil.print(intCaseNum) %>" />
					<div class="invalid-feedback">Case Number is required.</div>
				</div>
				<div class="input-group m-4">
					<label for="courtType" style="width: 20%; text-align: right; padding-right: 3px;">Court Type <span class="text-danger">*</span></label>
					<input class="form-control" id="courtType" name="courtType" value="<%= courtType %>" />
					<div class="invalid-feedback">Court Type is required.</div>
				</div>
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Continue" onclick="updateForm($('#caseNum').val(), $('#courtType').val());">Continue</button>
				</div>
			</form>
		</div>
	</main>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			// make sure the form focus is the first form field 
			$("#caseNum").focus();
		});
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		function updateForm(caseNum, courtType) {
			$('#caseNum').removeClass("is-invalid");
			$('#caseNum').addClass("is-valid");
			$('#courtType').removeClass("is-invalid");
			$('#courtType').addClass("is-valid");
			var validData = true;
			if(!caseNum){
				validData = false;
				$('#caseNum').removeClass("is-valid");
				$('#caseNum').addClass("is-invalid");
			}
			if(!courtType){
				validData = false;
				$('#courtType').removeClass("is-valid");
				$('#courtType').addClass("is-invalid");
			}
			if(validData){
				parent.selectCase(caseNum, courtType);
				closePop();
			}
		}
	</script>
</body>
</html>
<%
// cleanup
mode = null;
%>
