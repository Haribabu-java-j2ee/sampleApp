<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="java.util.Date"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	RoleDefnBO roleDefnBO = (RoleDefnBO) request.getAttribute("role");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Role</title>
	
	<!-- styles -->
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<!-- scripts -->
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<!-- this is used for the popcorn window -->
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script>

	$(document).ready(function(){
		// make sure the form focus is the first form field 
		$("#description").focus();
	});

	function closePop(){
		var corn = appUX.pop.getSelfHandle();
		corn.close();
	}
	
	function refresh() {
		parent.showRoles("<%= courtType %>");
	}
		
	function updateForm(description, overrideRoleId) {
		// do some validation of description
		$('#description').removeClass("is-invalid");
		$('#description').addClass("is-valid");
		
		var validatedText = true;
		//if (description == "") { validatedText = false; } // can't be an empty string
		//if (description == null) { validatedText = false; } // must contain a value
		if (description.length > 255) { validatedText = false; } // must be 255 chars long or shorter
		// if description.search(/[^a-zA-Z ]+/) != -1) { validatedText = false; } // must be only chars/spaces
		if (validatedText) {

		   	appUX.ajax.call('/CorisWEB/AdminRoleServlet?mode=update&roleId=<%= roleDefnBO != null ? roleDefnBO.getRoleId() : 0 %>&courtType=<%= courtType %>&description=' + description + '&overrideRoleId=' + overrideRoleId, function (err, data, xhr) { refresh(); closePop(); });
		   	
		} else {
			// highlight the field in red and display message
			$('#description').removeClass("is-valid");
			$('#description').addClass("is-invalid");
		}
	}
	
</script>

</head>
<body>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<% if (roleDefnBO == null) { %>
					<div class="input-group m-4">
						<label for="roleid" style="width: 20%; text-align: right; padding-right: 3px;">Role Id  </label>
						<input class="form-control" id="overrideRoleId" name="overrideRoleId" value="" \>
						<div class="invalid-feedback">Set the role id if you know the role id you want, otherwise leave it blank.</div>
					</div>
				<% } %>
				<div id="myModal" class="input-group m-4">
					<label for="description" style="width: 20%; text-align: right; padding-right: 3px;">Description <span class="text-danger">*</span></label>
					<input class="form-control" id="description" name="description" autofocus value="<%= roleDefnBO != null ? TextUtil.print(roleDefnBO.getDescription()) : "" %>" \>
					<div class="invalid-feedback">Description is required and must be 255 or less letters or spaces.</div>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml" title="Update" onclick="updateForm($('#description').val(), $('#overrideRoleId').val());">Update</button>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
	mode = null;
%>
</body>
</html>