<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<!-- header -->
<header class="std-header d-none d-md-block">
	<div class="container-fluid">
		<span>Welcome: </span> <span class="float-right">Local Maintenance</span>
	</div>
</header>

<!-- navigation -->
<nav class="navbar navbar-light navbar-expand-md p-md-0 sticky-top std-navbar">
	<span class="d-md-none font-weight-bold">Welcome: </span>
	<button type="button" class="navbar-toggler ml-auto" data-toggle="collapse" data-target="#navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="navbar-collapse collapse" id="navigation">
		<div class="nav navbar-nav ml-auto">
			<div class="nav-item dropdown">
				<a href="javascript:void(0);" class="nav-link dropdown-toggle text-dark" data-toggle="dropdown">Admin</a>
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="/CorisWEB/jsp/localMaintenanceCourtDefaults.jsp" class="dropdown-item">Court Defaults</a></li>
					<li><a href="/CorisWEB/jsp/localMaintenanceProfileMaintenance.jsp" class="dropdown-item">Profile Maintenance</a></li>
				</ul>
			</div>
			<span class="std-divider"></span>
			<a href="/CorisWEB/logout.jsp" class="nav-item nav-link text-dark">Logout</a>
		</div>
	</div>
</nav>
