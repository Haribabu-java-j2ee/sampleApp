<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.util.UserUtil"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>

<%
	String currentUser = (String) request.getAttribute(UserUtil.USER_FULL_NAME);
	String criticalMessage = (String) request.getAttribute(UserUtil.CRITICAL_MESSAGE);	
	String minStr = "";
	String sysVer = Constants.SYSTEM_VERSION;
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>CORIS</title>
	
	<link rel="stylesheet" href="/CorisWEB/css/select2<%= minStr %>.css?nocache=<%= sysVer %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit<%= minStr %>.css?nocache=<%= sysVer %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn<%= minStr %>.css?nocache=<%= sysVer %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-nav<%= minStr %>.css?nocache=<%= sysVer %>">
	
</head>
<body>
	<!-- header -->
	<header id="app-nav-header">
		<div class="header-item-left">
			<div class="menu-left-toggler">
				<span class="menu-bars">
					<span class="menu-bar-top"></span>
					<span class="menu-bar-middle"></span>
					<span class="menu-bar-bottom"></span>
				</span>
				&nbsp;&nbsp;&nbsp;
				<span class="menu-desc">CORIS</span>
			</div>
		</div>
		<div class="header-item-right">
			<div class="menu-right-toggler">
				<span class="menu-desc"><font color="red"><%= criticalMessage %></font><%= currentUser %></span>
				&nbsp;&nbsp;&nbsp;
				<span class="menu-dots">
					<span class="menu-dot-top"></span>
					<span class="menu-dot-middle"></span>
					<span class="menu-dot-bottom"></span>
				</span>
			</div>
		</div>
	</header>
	
	<!-- navigation -->
	<nav id="app-nav-menu"></nav>
	
	<!-- scripts -->
	<script src="/CorisWEB/js/fontawesome<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/jquery.slim<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/select2<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-toolkit<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-popcorn<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-ajax<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-storage<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/app-nav<%= minStr %>.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/main.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/admin.js?nocache=<%= sysVer %>"></script>
	<script src="/CorisWEB/js/psc.js?nocache=<%= sysVer %>"></script>
<%
	// display the selected destination if coming from sso
 	String destination = URLEncryption.getParamAsString(request, "destination");
 	String parameterString = URLEncryption.getParamAsString(request, "parameterString");
 	
	int width = 1200;
	int height = 800;
 	
 	String title = "Put your title on menu.jsp - " + destination;
 	if ("AdminCourtProfileServlet".equals(destination)) {						
 		title = "Court Profile";
 	} else if ("CaseHistServlet".equals(destination)) { 							
 		title = "Add Case History Note";
 		width = 500;
		height = 300;
 	} else if ("CaseSummarySearchLookupServlet".equals(destination)) {			
 		title = "Case Summary Search";
	} else if ("CaseTrackingServlet".equals(destination)) {		
 		title = "Manage Case Tracking";
	} else if ("CorisAttorneyAttachDetachBatchServlet".equals(destination)) { 		
 		title = "Batch Attorney Attach/Detach";
	} else if ("CorisAttorneyAttachDetachSingleServlet".equals(destination)) {		
 		title = "Attach Attorney";
	} else if ("CorisAttorneyAttachDetachTransferServlet".equals(destination)) {	
 		title = "Attorney Mass Transfer";
	} else if ("CorisAttorneyBarNumberFixServlet".equals(destination)) {
 		title = "Attorney Bar Number Fix";
	} else if ("CorisAttorneyCasesServlet".equals(destination)) {			
 		title = "Attorney Cases";
	} else if ("CorisAttorneyDetailsServlet".equals(destination)) { 				
 		title = "Attorney Details";
	} else if ("CorisAttorneyLookupServlet".equals(destination)) {			
 		title = "Attorney Lookup";
 	} else if ("CorisAttorneysForCaseServlet".equals(destination)) { 				
 		title = "Attorneys for Case";
 	} else if ("CorisCaseHistoryAllServlet".equals(destination)) {			
 		title = "Case History";
 	} else if ("CorisJudgeCommRotationServlet".equals(destination)) { 				
 		title = "Change Judge/Commissioner Assignment";
 	} else if ("CorisPartyIDServlet".equals(destination)) {			
 		title = "Party ID Details";
 	} else if ("CorisReportCasePendingAgeServlet".equals(destination)) { 				
 		title = "Age of Pending Caseload";
 		width = 300;
		height = 500;
 	} else if ("CorisReportCivilWarrantServlet".equals(destination)) {			
 		title = "Civil Warrant Report";
 	} else if ("CorisReportCivilWarrantServlet".equals(destination)) {			
 		title = "Civil Warrant Report";
 	} else if ("CorisReportDepositSummaryServlet".equals(destination)) {
 		title = "Deposit Summary";
 		width = 500;
		height = 500;
	} else if ("CorisReportNameIndexCivilServlet".equals(destination)) { 		
 		title = "Name Index - Civil";
 	} else if ("CorisReportNameIndexCriminalServlet".equals(destination)) { 		
 		title = "Name Index - Criminal";
 	} else if ("CorisReportWarrantServlet".equals(destination)) {		
 		title = "Warrant Report";
 	} else if ("CorisReportWarrantWithPaymentsMadeServlet".equals(destination)) {	
 		title = "Warrant With Payments Made Report";
 	} else if ("DomesticRelationsInjunctionServlet".equals(destination)) {
 		title = "Create Domestic Relations Injunction";
 	} else if ("OsdcEligibleLookupServlet".equals(destination)) {		
 		title = "OSDC Search";
 	} else if ("PSCReferralsServlet".equals(destination)) {				
 		title = "Problem Solving Court";
 	} else if ("CorisReportChargeDispositionServlet".equals(destination)) {				
 		title = "Charge Disposition Report";
		width = 300;
		height = 500;
 	} else if ("CorisReportMoneyWithOutACaseServlet".equals(destination)) {				
 		title = "Money Without A Case";
		width = 300;
		height = 500;
 	} else if ("CorisReportUnclaimedPropertyServlet".equals(destination)) {
 		title = "Unclaimed Property";
 	} else if ("CorisReportLateFilingServlet".equals(destination)) {				
 		title = "Late Filing";
 	} else if ("CorisReportUserLoginReportServlet".equals(destination)) {				
 		title = "User Login";
 	} else if ("CorisReportCaseStayLookupServlet".equals(destination)) {				
 		title = "Case Stay";
 	} else if ("CorisAttorneyCasesServlet".equals(destination)) {				
 		title = "Attorney Cases";
 	}
 	
 	if (!TextUtil.isEmpty(destination)) {	
 %>
	 	<script>	 	
			var cornId = 'ssoCornPrimary';
			var title = '<%= title %>';
			var url = '/CorisWEB/<%= destination + parameterString %>';
			var width = <%= width %>;
			var height = <%= height %>;
			var style = appUX.pop.styles.PRIMARY;
			appUX.pop.modeless(cornId, title, url, width, height, style);
		</script>
<%  } %>	

</body>
</html>


