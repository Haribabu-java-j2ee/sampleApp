<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<title>logout</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css">
		<script type="text/javascript" src="/CorisWEB/js/jquery-3.2.0.min.js"></script>
		<script type="text/javascript" src="/CorisWEB/js/app-toolkit.min.js"></script>
	</head>
	<body onload="document.forwardToLoginPage.submit()">
		<form name="forwardToLoginPage" action="/CorisWEB/LoginServlet"></form>
			<div class="container-fluid mb-3">
		
			<nav class="navbar navbar-dark bg-dark">
				 <a class="navbar-brand" href="#">CORIS</a>
			</nav>
		</div>	
		<div class="container">
			<div class="alert alert-danger">
				<strong>Session Expired: </strong> You reached this page because of session expiration or error.
			</div>
		</div>
	</body>
</html>