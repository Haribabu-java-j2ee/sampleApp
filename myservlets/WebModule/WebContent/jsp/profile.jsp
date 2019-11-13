<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="java.util.Date"%>
<%
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Profile</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script>
	
		function changePassword(currentPassword, newPassword, confirmPassword) {
			var valid = true;
			if (currentPassword == '' || newPassword == '' || confirmPassword == '') {
				appUX.pop.alert("Current, New and Confirm password fields must be entered.", 450, 'auto', appUX.pop.styles.DANGER);
				valid = false;
			} else if (newPassword != confirmPassword) {
				appUX.pop.alert("New and Confirm password fields do not match.", 450, 'auto', appUX.pop.styles.DANGER);
				valid = false;
			}
			if (valid) {
				appUX.ajax.call("/CorisWEB/PasswordServlet", 
					function (err, data, xhr) { 
						
			   			var jsonObj = JSON.parse(data);
						if (jsonObj && jsonObj.error) {
							appUX.pop.alert(jsonObj.error, 400, 'auto', appUX.pop.styles.DANGER);
						} else if (jsonObj && jsonObj.success) {
							appUX.pop.declare("Success", jsonObj.success, 400, 'auto', appUX.pop.styles.SUCCESS);
							closePop();
						}
						
					}, 
					'POST', 
					'action=change&currentPassword=' + currentPassword + '&newPassword=' + newPassword + '&confirmPassword=' + confirmPassword
				); 
			}
		}
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
	
	</script>
</head>
<body style="height: 100vh;">
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div class="card m-2">
					<div class="card-header text-dark">
						<strong>Change Password</strong>
					</div>
					<div class="card-body">
						<div class="form-row mt-4">
							<div class="form-group col-md offset-lg-2 col-lg-6">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-10 control-label text-sm-right">Current Password</label>
									<div class="col-sm-12 col-md-13">
										<input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Enter current password" maxlength="120" required autofocus>
										<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;">
											<span class="float-right">Current password required.</span>
										</div>
									</div>
									<label class="col-sm-8 col-md-10 control-label text-sm-right">New Password</label>
									<div class="col-sm-12 col-md-13">
										<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter new password" maxlength="120" required>
										<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;">
											<span class="float-right">New password required.</span>
										</div>
									</div>
									<label class="col-sm-8 col-md-10 control-label text-sm-right">Confirm Password</label>
									<div class="col-sm-12 col-md-13">
										<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Re-enter new password" maxlength="120" required>
										<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;">
											<span class="float-right">Confirm password required.</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<div class="float-left mt-6 mb-0"></div>
							<div class="float-right">
								<button type="button" onclick="changePassword($('#currentPassword').val(), $('#newPassword').val(), $('#confirmPassword').val());" id="changePasswordBtn" name="changePasswordBtn" class="btn btn-primary ml-2 mt-2 mr-2 float-right">Save</button>
							</div>
						</div>
					</div>
				</div>
			
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Close</button>
				</div>
			</form>
		</div>
	</main>

	<script type="text/javascript" src="/CorisWEB/js/coris.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
</body>
</html>
