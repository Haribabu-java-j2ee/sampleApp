<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>

<%
	String minStr = "";
	String sysVer = Constants.SYSTEM_VERSION;
	
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage == null) {
		errorMessage = (String) request.getParameter("errorMessage");
	}
	
	String message = "Welcome to CORIS.";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>CORIS - Login</title>
	
	<!-- styles -->
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit<%= minStr %>.css?nocache=<%= sysVer %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn<%= minStr %>.css?nocache=<%= sysVer %>">
	
	<script>
	
		function submitForgotPassword(logname, email) {
			
			$('#logname').removeClass("is-invalid");
			$('#logname').addClass("is-valid");
			var lognameValid = false;
			if (logname.length > 0) { lognameValid = true; } 
			
			$('#email').removeClass("is-invalid");
			$('#email').addClass("is-valid");
			var emailValid = false;
			if (email.length > 0) { emailValid = true; } 
			
			if (lognameValid && emailValid) {
	
			   	appUX.ajax.call('/CorisWEB/PasswordServlet?action=forgot&logname=' + logname + '&email=' + email, 
			   		function (err, data, xhr) { 
			   			var jsonObj = JSON.parse(data);
						if (jsonObj && jsonObj.error) {
							appUX.pop.alert(jsonObj.error, 400, 'auto', appUX.pop.styles.DANGER);
						} else if (jsonObj && jsonObj.success) {
							appUX.pop.declare("Success", jsonObj.success, 400, 'auto', appUX.pop.styles.SUCCESS);
							
							$('#forgotPasswordModal').modal('hide');							
						}
			   		}
			   	);
			   	
			} else {
			
				if (!lognameValid) {
					$('#logname').removeClass("is-valid");
					$('#logname').addClass("is-invalid");
				}
				
				if (!emailValid) {
					$('#email').removeClass("is-valid");
					$('#email').addClass("is-invalid");
				}
				
			}
		
		}

	</script>
</head>
<body>
	<!-- loading screen -->
	<div id="loadingContainer" style="display: none;">
		<div id="loadingCard">
			<div id="loadingMessage">Loading...</div>
		</div>
	</div>
	
	<!-- main content -->
	<main style="background-color: #e6e6e6">
		<div class="container-fluid" style="height: 100vh;">
			<div class="row h-100 justify-content-center align-items-center">
				<div class="card border-dark" style="width: 25rem; box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);">
					<div class="card-header p-0 w-100" style="display: table; background-color: #10659c; color: #EEEEEE;">
						<div class="p-5 w-25" style="display: table-cell; vertical-align: middle;">
							<img src="/CorisWEB/img/ut-courts-judicial-council-seal.png" style="width: 80px; height: 80px;" alt="Judicial Council Seal">
						</div>
						
						<div class="p-5 w-75" style="display: table-cell; vertical-align: middle;">
							<div class="h2">Utah Courts</div>
							<div class="h6">CORIS</div>
						</div>
					</div>
					<div class="card-body p-0">
						<div class="align-items-center" id="noSupportContainer">
							<noscript>
								<div class="m-0 py-4 alert alert-danger text-center">You need to have JavaScript enabled to use this system.</div>
							</noscript>
							
							<div class="m-0 py-4 alert alert-danger text-center" style="display: none;" id="noSupportMessage"></div>
						</div>
						<div id="loginFormContainer">
							<% if (!TextUtil.isEmpty(message)) { %>
								<div class="m-0 py-4 alert alert-info text-center"><%= message %></div>
							<% } %>
							
							<% if (!TextUtil.isEmpty(errorMessage)) { %>
								<div class="m-0 py-4 alert alert-danger text-center"><%= errorMessage %></div>
							<% } %>
							
							<form class="offset-4 col-16 my-12 px-4" id="loginForm" name="loginForm" action="/CorisWEB/LoginServlet" method="post">
								<div class="form-group">
									<input type="text" class="form-control" id="username" name="username" value="" placeholder="Username" tabindex="1" autocomplete="off"/>
								</div>
								<div class="form-group">
									<input type="password" class="form-control" id="password" name="password" value="" placeholder="Password" tabindex="2" autocomplete="off"/>
									<div class="text-center text-danger font-weight-bold invisible" id="capsLockMessage">CAPS LOCK IS ON</div>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block" onclick="submitLoginForm($('#username').val());" tabindex="3">Login</button>
								</div>
							</form>
							<div class="text-center pb-6">
								<button type="button" class="btn btn-link" data-toggle="modal" data-target="#forgotPasswordModal">Forgot/Reset Password?</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<!-- modals -->
	<div class="modal fade" id="forgotPasswordModal" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h6 class="modal-title">Forgot/Reset Password</h6>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Please provide your username and email address. A new password will be generated and emailed to you.</p>
					<div class="form-group row">
						<label for="logname" class="offset-sm-4 col-sm-5 col-form-label text-sm-right">Username:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="logname" name="logname" value=""/>
							<div class="validate"></div>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="email" class="offset-sm-4 col-sm-5 col-form-label text-sm-right">Email:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="email" name="email" value=""/>
							<div class="validate"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="submitForgotPassword($('#logname').val(), $('#email').val());">Submit</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- scripts -->
	<script src="/CorisWEB/js/fontawesome<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/jquery.slim<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-toolkit<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-ajax<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-storage<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/coris-common.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/login.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-popcorn<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	
</body>
</html>
