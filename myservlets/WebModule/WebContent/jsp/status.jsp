<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page	language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>

<html>
<head>
<title>Status</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<script type="text/javascript" src="/CorisWEB/js/jquery-3.2.0.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<style>
.centerDiv {
		border:2px solid black;
        width: 400px;
        height: 300px;
        background-color: white;

        position:absolute; /*it can be fixed too*/
        left:0; right:0;
        top:0; bottom:0;
        margin:auto;

        /*this to solve "the content will not be cut when the window is smaller than the content": */
        max-width:100%;
        max-height:100%;
        overflow:auto;
    }
</style>
<%

String msg =  (String) request.getAttribute("STATUS_MSG");
String isError = (String) request.getAttribute("IS_ERROR");

String color = "success";
if (isError != null && "Y".equals(isError))
	color = "danger";
%>
</head>
<body>
<div class="centerDiv">
	
	<nav class="navbar navbar-dark bg-<%=color %>">
		<div class="container-fluid mb-3">
			 <a class="navbar-brand" href="#">Status</a>
		</div>
	</nav>
		
	<div class="container" style="margin-top:1em">
		<div class="alert alert-<%=color %>">
			<%=msg %>
		</div>
		
		<!--
		<button style="position:absolute;bottom:5px;right:5px;margin:0;padding:5px 3px;" class="btn btn-primary" onclick="window.close(); ">Close</button>
		-->
		
	</div>
</div>
</body>
</html>
