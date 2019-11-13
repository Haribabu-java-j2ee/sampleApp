<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil.Role"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil"%>
<%@page import="gov.utcourts.corisweb.report.CorisAttorneyLookupReportCriteria"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
CorisAttorneyLookupReportCriteria rptCriteria = request.getAttribute("myCriteria")==null? new CorisAttorneyLookupReportCriteria(request):(CorisAttorneyLookupReportCriteria)request.getAttribute("myCriteria");

@SuppressWarnings("unchecked")
List<StateBO> statesList = (List<StateBO>)request.getAttribute("statesList");
@SuppressWarnings("unchecked")
List<AttorneyBO> attorneyList = (List<AttorneyBO>) request.getAttribute("attorneyList");

int colspan2 = 0;

boolean hasRole = false;
if(RoleUtil.hasRole(request, Role.COURT_SERVICES, Role.IT_ADMIN)){
	hasRole = true;
}

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyDetailsServlet");
URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/CorisAttorneyCasesServlet");
URLEncryption urlCryptor3 = new URLEncryption("/CorisWEB/CorisAttorneyLookupServlet");

String paramsAttorneyDetails = "mode=attorneyDetails";
String paramsAttorneyCases = "mode=searchAttorneyCases";
String paramsAttorneyAdd = "action=add";
String paramsSearchForm = "mode=find";
String paramsViewPDF = "mode=getPDF&action=display";
String paramsEmailPDF = "mode=getPDF&action=email";
String paramsSaveReport = "mode=SAVE";
String paramsEmailReport = "mode=EMAIL";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Attorney Management</title>
    
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

    <!-- main content -->
    <main>
		<div class="container-fluid">
		
			<% if (!TextUtil.isEmpty(TextUtil.print(errorMessage))) { %>
			<div class="alert alert-danger text-center m-2"><%=TextUtil.print(errorMessage) %></div>
			<% } %>

			<div class="card m-2">
				
				<div class="card-header">
					<strong>Attorney Lookup</strong>
				</div>
				
				<div class="card-body">
				
					<form id="searchAttorneysForm" name="searchAttorneysForm" action="<%=urlCryptor3.urlEncrypt(paramsSearchForm)%>" novalidate>
						<input type="hidden" id="orderBy" name="orderBy" value="">
						<input type="hidden" id="format" name="format" value="">
						<input type="hidden" id="showDetails" name="showDetails" value="<%=rptCriteria.getShowDetails() %>">
						
						<div class="form-row">
						
							<div class="form-group col-md-8 order-md-1">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Bar Number <span class="text-danger">!</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<input type="text" class="form-control" id="barNum" name="barNum" value="<%=(rptCriteria.getBarNum() > 0)?rptCriteria.getBarNum():"" %>" maxlength="10">
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-3">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Attorney Last Name <span class="text-danger">!</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<input type="text" class="form-control" id="attyLastName" name="attyLastName" value="<%=TextUtil.print(rptCriteria.getAttyLastName()) %>" maxlength="30">
									</div>
								</div>
							</div>

						</div>
						<div class="form-row">

							<div class="form-group col-md-8 order-md-2">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Bar State <span class="text-danger">!</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="barState" name="barState">
												<%
												if(statesList != null){
													String selected = "";
													for(StateBO stateBO : statesList){
														if(stateBO.getStateCode().equals(rptCriteria.getBarState())){
															selected = "selected";
														}else if("".equals(selected) && "UT".equals(stateBO.getStateCode()) && TextUtil.isEmpty(rptCriteria.getBarState())){
															selected = "selected";
														}else{
															selected = "";
														}
														%>
														<option value="<%=stateBO.getStateCode() %>" <%=selected %>><%=stateBO.getName() %></option>
														<%
													}
												}
												%>
											</select>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-4">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Attorney First Name</strong></label>
									<div class="col-sm-8 col-md-13">
										<input type="text" class="form-control" id="attyFirstName" name="attyFirstName" value="<%=TextUtil.print(rptCriteria.getAttyFirstName()) %>" maxlength="30">
									</div>
								</div>
							</div>
							
						</div>
						<div class="form-row">
						
							<div class="form-group col-sm-20 col-md-23 clearfix">
								<span class="text-danger"><strong>!</strong></span> Bar Number/State or Attorney Last Name Required
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn">Clear</button>
								<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn">Search</button>
							</div>
							
						</div>
						
					</form>
					
				</div>
				
				<div class="card-header card-footer">
					<strong>Results</strong>
					<% if (hasRole) {%>
						<span class="float-right"><button type="button" id="addAttyBtn" class="btn btn-primary">Add Attorney</button></span>
					<% } %>
				</div>
					
				<div class="card-body">

					<table id="searchResultsTable" class="table hover row-border compact">
						<thead class="text-light bg-dark">
							<tr>
								<% colspan2 = 11; //keep this updated as table changes so that the last column is not orderable %>
								<th>Last Name</th>
								<th>First Name</th>
								<th>Bar Number</th>
								<th>Bar State</th>
								<th>Bar Status</th>
								<th>City</th>
								<th>State</th>
								<th>Country</th>
								<th>Business Phone</th>
								<th>Email</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							if(attorneyList != null && attorneyList.size() > 0){
								for(AttorneyBO attorneyBO : attorneyList){ 
									%>
									<tr>
										<td><%=TextUtil.print(attorneyBO.getLastName()) %></td>
										<td><%=TextUtil.print(attorneyBO.getFirstName()) %></td>
										<td><%=attorneyBO.getBarNum() %></td>
										<td><%=TextUtil.print(attorneyBO.getBarState()) %></td>
										<td><%=TextUtil.print(attorneyBO.getBarStatus()) %></td>
										<td><%=TextUtil.print(attorneyBO.getCity()) %></td>
										<td><%=TextUtil.print(attorneyBO.getStateCode()) %></td>
										<td><%=TextUtil.print(attorneyBO.getCountry()) %></td>
										<td><%=TextUtil.print(attorneyBO.getBusPhone()) %></td>
										<td><%=TextUtil.print(attorneyBO.getEmailAddress()) %></td>
										<td nowrap>
											<i title="View and Print Details" id="attyDetailsBtn" onclick="attorneyDetails(<%=attorneyBO.getBarNum() %>,'<%=attorneyBO.getBarState() %>');" class="text-primary fas fa-address-card fa-lg"></i>
											<i title="Cases for Attorney" onclick='attorneyCases("<%=TextUtil.print(attorneyBO.getFirstName())%>", "<%=TextUtil.print(attorneyBO.getLastName())%>", <%=attorneyBO.getBarNum()%>,"<%=TextUtil.print(attorneyBO.getBarState())%>");' class="text-primary far fa-folder fa-lg"></i>
										</td>
									</tr>
									<% 
								}
							} %>
						</tbody>
					</table>
					
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='mt-1 col-24'>
							<button type="button" class="btn btn-secondary ml-2 mr-2 float-right" id="closeBtn">Close</button>
							<div class="btn-group float-right" role="group">
								<button id="btnEmail" type="button" class="btn btn-primary ml-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Email As</button>
								<div class="dropdown-menu" id="btnEmail" aria-labelledby="btnEmail" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
									<a class="dropdown-item" href="#" id="emailPDFBtn">PDF</a> 
									<a class="dropdown-item" href="#" id="emailExcelBtn">Excel</a>
								</div>
							</div>
							<div class="btn-group float-right" role="group">
								<button id="btnSave" type="button" class="btn btn-primary ml-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Save As</button>
								<div class="dropdown-menu" id="btnSave" aria-labelledby="btnSave" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
									<a class="dropdown-item" href="#" id="savePDFBtn">PDF</a> 
									<a class="dropdown-item" href="#" id="saveExcelBtn">Excel</a>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
    	</div>
    	
    	<br><br> <%//always include this so that content doesn't go below the bottom footer bar where the buttons are located %>

    </main>

    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script>
	    var pageVars = window.pageVars || {};
	    pageVars.attorneyListSize = 0;
	    <% if(attorneyList != null && attorneyList.size() > 0){ %>
		    pageVars.attorneyListSize = <%=attorneyList.size() %>;
	    <% } %>
	    pageVars.maxResults = <%= Constants.MAX_RESULTS %>;
		pageVars.urlSaveReport = '<%= urlCryptor3.urlEncrypt(paramsSaveReport) %>';
		pageVars.urlEmailReport = '<%= urlCryptor3.urlEncrypt(paramsEmailReport) %>';
		pageVars.urlAttorneyDetails = '<%=urlCryptor.urlEncrypt(paramsAttorneyDetails) %>';
		pageVars.urlAttorneyCases = '<%=urlCryptor2.urlEncrypt(paramsAttorneyCases) %>';
		pageVars.urlAttorneyAdd = '<%=urlCryptor.urlEncrypt(paramsAttorneyAdd) %>';
		pageVars.urlSearchForm = '<%=urlCryptor3.urlEncrypt(paramsSearchForm) %>';
	</script>
	
	<script src="/CorisWEB/js/corisAttorneyLookup.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
</body>
</html>
<%
rptCriteria = null;
statesList = null;
attorneyList = null;
urlCryptor = null;
urlCryptor2 = null;
urlCryptor3 = null;
paramsAttorneyDetails = null;
paramsAttorneyCases = null;
paramsAttorneyAdd = null;
paramsSearchForm = null;
paramsViewPDF = null;
paramsEmailPDF = null;
paramsSaveReport = null;
paramsEmailReport = null;
%>