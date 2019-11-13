<!DOCTYPE html>

<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.constants.Constants"%>

<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script src="/CorisWEB/js/admin.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/psc.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<!-- header -->
<header class="std-header d-none d-md-block">
	<div class="container-fluid">
		<span>Welcome: User Name</span> <span class="float-right">Admin</span>
	</div>
</header>

<!-- navigation -->
<nav class="navbar navbar-light navbar-expand-md p-md-0 sticky-top std-navbar">
	<button type="button" class="navbar-toggler ml-auto" data-toggle="collapse" data-target="#navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="navbar-collapse collapse" id="navigation">
		<div class="nav navbar-nav">
			<span class="std-divider"></span>
		</div>
		<div class="nav navbar-nav ml-auto">
			<div class="nav-item dropdown">
				<a href="javascript:void(0);" class="nav-link dropdown-toggle text-dark" data-toggle="dropdown">Page Registry</a>
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:showAccessLevels('D')" class="dropdown-item">Define Access Levels</a></li>
					<li><a href="javascript:showPages('D')" class="dropdown-item">Define Pages</a></li>
					<li><a href="javascript:showRoles('D')" class="dropdown-item">Define Roles</a></li>
					<li><a href="javascript:showSystemAreas('D');" class="dropdown-item">Define System Areas</a></li>
					<li><a href="javascript:showProfiles('D');" class="dropdown-item">Define Court Profiles</a></li>
				</ul>
			</div>
			<div class="nav-item dropdown">
				<a href="javascript:void(0);" class="nav-link dropdown-toggle text-dark" data-toggle="dropdown">Problem Solving</a>
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:showActionDefn()" class="dropdown-item">Action Definitions</a></li>
					<li><a href="javascript:showAppearanceDefn()" class="dropdown-item">Appearance Definitions</a></li>
					<li><a href="javascript:showEvaluationDefn()" class="dropdown-item">Evaluation Definitions</a></li>
					<li><a href="javascript:showGoalDefn()" class="dropdown-item">Goal Definitions</a></li>
					<li><a href="javascript:showPhaseDefn()" class="dropdown-item">Phase Definitions</a></li>
					<li><a href="javascript:showRewardDefn()" class="dropdown-item">Reward Definitions</a></li>
					<li><a href="javascript:showSanctionDefn()" class="dropdown-item">Sanction Definitions</a></li>
					<li><a href="javascript:showStatusDefn()" class="dropdown-item">Status Definitions</a></li>
					<li><a href="javascript:showTerminationDefn()" class="dropdown-item">Termination Definitions</a></li>
					<li><a href="javascript:showTreatmentDefn()" class="dropdown-item">Treatment Definitions</a></li>
					<li><hr/></li>
					<li><a href="javascript:showActionCourtXref()" class="dropdown-item">Action - Court Cross Reference</a></li>
					<li><a href="javascript:showCourtLocationXref()" class="dropdown-item">Court - Location Cross Reference</a></li>
					<li><a href="javascript:showRewardCourtXref()" class="dropdown-item">Reward - Court Cross Reference</a></li>
					<li><a href="javascript:showSanctionCourtXref()" class="dropdown-item">Sanction - Court Cross Reference</a></li>
					<li><a href="javascript:showStatusActionXref()" class="dropdown-item">Status - Action Cross Reference</a></li>
				</ul>
			</div>
			<span class="std-divider"></span>
			<a href="/CorisWEB/logout.jsp" class="nav-item nav-link text-dark">Logout</a>
		</div>
	</div>
</nav>
