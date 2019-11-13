<!DOCTYPE html>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header -->
<head>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
</head>

<!-- navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a href="/CorisWEB/PSCReferralsServlet" class="navbar-brand">Problem Solving Courts</a>
	<button type="button" class="navbar-toggler ml-auto" data-toggle="collapse" data-target="#navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="navbar-collapse collapse" id="navigation">
		<div class="nav navbar-nav ml-auto">
			<div class="nav-item dropdown">
				<!-- <a href="/CorisWEB/PSCReferralsServlet?mode=switch" class="nav-item nav-link">Referral Overview</a>
				<span class="std-divider"></span>
				<a href="javascript:void(0);" class="nav-item nav-link text-dark">Link</a> -->
			</div>
			
			<!-- 
			<span class="std-divider"></span>
			<div class="nav-item dropdown">
				<a href="javascript:void(0);" class="nav-item nav-link" data-toggle="dropdown">Help</a>
				<div class="dropdown-menu dropdown-menu-right">
					<a href="javascript:void(0);" class="dropdown-item">FAQ</a>
					<a href="javascript:void(0);" class="dropdown-item">Contact Help Desk</a>
				</div>
			</div>
			-->
			<span class="std-divider"></span>
			<a href="/CorisWEB/LogoutServlet" class="nav-item nav-link">Logout</a>
		</div>
	</div>
</nav>
