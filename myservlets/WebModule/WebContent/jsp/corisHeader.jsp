<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/CorisWEB/CorisHomeLookupServlet">CORIS</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-dark" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbar-dark">
		<div class="nav navbar-nav mr-auto">
			<a class="nav-item nav-link active" href="/CorisWEB/CaseSummarySearchLookupServlet">Case Summary Search</a>
			<!--a class="nav-item nav-link" href="#">Future Link</a-->
			<!--a class="nav-item nav-link" href="#">Future Link</a-->
			
		</div>
		<div class="nav navbar-nav">
			<a class="nav-item nav-link" href="/CorisWEB/LogoutServlet" id="logOutBtn" name="logOutBtn" >Logout</a>
		</div>
	</div>
</nav>
