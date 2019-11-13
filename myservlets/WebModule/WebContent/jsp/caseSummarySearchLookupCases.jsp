<!DOCTYPE html>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.coriscommon.sp.GetCaseTitle"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="java.util.List"%>

<script type="text/javascript" src="/CorisWEB/js/casesummarysearchlookupcases.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<%
String logName = URLEncryption.getParamAsString(request, "logName");
//String theUrl = "courtType="+courtType+"&logName="+logName;
String theUrl = "logName="+logName;

//encrypt the URL
URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CaseSummarySearchLookupServlet");

//get the list of cases
List<KaseBO> casesList = (List<KaseBO>) request.getAttribute("casesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<table id="caseTable" name="caseTable" class="col-24 table table-hover table-highlight table-responsive-sm table-activate">
	<thead>
		<tr class="bg-dark text-light">
			<th>Case Number</th>
			<th>Case Title</th>
			<th>Court Type</th>
			<th>Case Type</th>
			<th>Location</th>
			<th class="sorter-false">Actions</th>
		</tr>
	</thead>
	<tbody>
<%
//display the list of results
int intCaseNum = 0;
String caseNum = "";
String courtType = "";
String courtTypeDescr = "";
String caseType = "";
String caseTypeDescr = "";
String locnCode = "";
String locnCodeDescr = "";
String caseTitle = "";
if(casesList != null && casesList.size() > 0) {
	for (KaseBO kaseBO : casesList) {
		intCaseNum = kaseBO.getIntCaseNum();
		caseNum = kaseBO.getCaseNum();
		courtType = kaseBO.getCourtType();
		locnCodeDescr = (String) kaseBO.get(LocationBO.LOCNDESCR);
		caseTypeDescr = (String) kaseBO.get(CaseTypeBO.DESCR);
		caseTitle = GetCaseTitle.getCaseTitle(logName, intCaseNum, courtType, null);
		//get the court description
		if("D".equals(courtType)) { courtTypeDescr = "District"; }
		if("J".equals(courtType)) { courtTypeDescr = "Justice"; }
		caseType = kaseBO.getCaseType();
		locnCode = kaseBO.getLocnCode();
		%>
		<tr>
			<td><%=caseNum%></td>
			<td><%=caseTitle%></td>
			<td><%=courtTypeDescr%></td>
			<td><%=caseTypeDescr%></td>
			<td><%=locnCodeDescr%></td>
			<td>
				<form id="<%=caseNum%><%=locnCode%>" name="<%=caseNum%><%=locnCode%>" class="form-inline" novalidate>
					<div>
						<%if (!caseNum.contains("Case Exists")){%>
							<a href="#" title="Case Summary" id="detailsBtn" onclick="displayDetail('<%= urlCryptor.urlEncrypt(theUrl + "&mode=getEvents&courtType=" + courtType + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&caseTitle=" + caseTitle + "&locnCodeDescr=" + locnCodeDescr) %>');">
								<i class="text-info fas fa-info-circle fa-lg fa-fw"></i>								
							</a>
							<a href="#" title="View Case History All" onclick="displayPDF('<%= urlCryptor.urlEncrypt(theUrl + "&funcId=all&mode=getPDF&courtType=" + courtType + "&caseNum=" + caseNum + "&locnCode=" + locnCode) %>');">
								<i class="text-warning fas fa-landmark fa-lg fa-fw"></i>								
							</a>
							<a href="#" title="Email Public Case History All" onclick="emailPDF('<%= urlCryptor.urlEncrypt(theUrl + "&funcId=all&mode=getPDF&courtType=" + courtType + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&email=Y" + "&emailType=PUBLIC" ) %>');">
								<i class="text-dark fas fa-envelope-open fa-lg fa-fw"></i>								
							</a>						
						<%}%>
					</div>
				</form>
			</td>
		</tr>
		<% 
	}
	//cleanup
} else {
	%>
	<tr>
		<td colspan="6">No Results.</td>
	</tr>
	<% 
}
%>

	</tbody>
</table>

<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/fontawesome.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
//this script MUST STAY HERE AT THE TOP OF THE PAGE or it doesn't work....no idea why

function refreshCaseSummaryDetails() {
	displayDetail(lastClicked);
}

var lastClicked = "";

function displayDetail(encryptedUrl){
	lastClicked = encryptedUrl;
	
	appUX.pop.modal('viewCaseSummary', 'Case Summary Details', encryptedUrl, appUX.pop.windowSize().width - 10, appUX.pop.windowSize().height - 40, 'primary');
}

//this is to display the Case History All or Single PDF
function displayPDF(encryptedUrl){

	//loading spinner html
	var htmlDisplay = 
		'<div style="width: 100%; text-align: center; padding: 20px;">'
			+ '<span style="padding: 5px; font-size: 16px; border: 1px solid rgba(0,0,0,.25); color: rgba(0,0,0,.5);">'
				+ '<span class="fa fa-spinner fa-spin"></span>'
				+ ' Loading'
			+ '</span>'
		+ '</div>'
	;
	//open a new window
	var pdf = window.open("", "PDF", "status=0,title=0,width=800,height=600,resizable=yes,scrollbars=1");
	if(pdf) {
		pdf.document.write(htmlDisplay); //display loading spinner
		pdf.location.href = encryptedUrl; //replace the window with the contents of the PDF
	} else {
		var message = "To view this PDF, please allow popups in the browser.";
		appUX.pop.notify(message, 300, 'auto', appUX.pop.styles.DANGER);
	}
}

$(document).ready(function(){
	$("#caseTable").tablesorter();
	<% if (casesList != null && casesList.size() == 1) { %>
		$('#detailsBtn').click();
	<% } %>
	<!--Change to >= when searching both District and Justice-->
	<% if (casesList != null && casesList.size() >= Constants.MAX_RESULTS) { %> 
  		appUX.pop.declare("Maximum Results Reached", "Your search results may have been limited.", 300, "auto", appUX.pop.styles.DANGER);
  	<% } %>
});
</script>
<%
//cleanup
casesList = null;
caseNum = null;
courtType = null;
courtTypeDescr = null;
caseType = null;
caseTypeDescr = null;
locnCode = null;
locnCodeDescr = null;
%>
