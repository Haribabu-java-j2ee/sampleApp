<%@page import="gov.utcourts.coriscommon.util.CorisSecurityCommon"%>
<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.corisweb.report.CorisAttorneysForCaseReportCriteria"%>
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
SimpleDateFormat dateFormat = Constants.dateFormatCoris;
CorisAttorneysForCaseReportCriteria rptCriteria = request.getAttribute("myCriteria")==null? new CorisAttorneysForCaseReportCriteria(request):(CorisAttorneysForCaseReportCriteria)request.getAttribute("myCriteria");
HashMap<String, Boolean> userAccess = (HashMap<String, Boolean>) request.getAttribute("userAccess");

@SuppressWarnings("unchecked")
List<KaseBO> attyList = (List<KaseBO>) request.getAttribute("attyList");
@SuppressWarnings("unchecked")
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
@SuppressWarnings("unchecked")
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneysForCaseServlet");
URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/CorisAttorneyDetailsServlet");

String paramsSearchAttorneysForCase = "mode=find";
String paramsUpdateAttachDatetime = "mode=update&action=updateAttachDatetime";
String paramsUpdateDetachDatetime = "mode=update&action=updateDetachDatetime";
String paramsSaveReport = "mode=SAVE";
String paramsEmailReport = "mode=EMAIL";
String paramsAttorneyDetails = "mode=attorneyDetails";
String paramsAttorneyDetach = "mode=update&action=detachAttorney";
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
					<strong>Attorneys for Case</strong>
				</div>
				
				<div class="card-body">
				
					<form id="searchAttyForm" name="searchAttyForm" action="<%=urlCryptor.urlEncrypt(paramsSearchAttorneysForCase)%>" novalidate>
						<input type="hidden" id="orderBy" name="orderBy" value="">
						<input type="hidden" id="format" name="format" value="">
						
						<div class="form-row">
						
							<div class="form-group col-md-8 order-md-1">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Court Type <span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="courtType" name="courtType" required>
											<option value="D" <%=("D".equals(rptCriteria.getCourtType()))?"selected":"" %>>District</option>
											<option value="J" <%=("J".equals(rptCriteria.getCourtType()))?"selected":"" %>>Justice</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-2">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Location <span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="locnCode" name="locnCode" required>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group col-md-8 order-md-3">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Case Number <span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<input type="text" class="form-control" id="caseNum" name="caseNum" value="<%=TextUtil.print(rptCriteria.getCaseNum()) %>" maxlength="9" autofocus required>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-4">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Attach Date Start/End</strong></label>
									<div class="col-sm-8 col-md-13">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="attachDatetimeStart" name="attachDatetimeStart" value="<%=(rptCriteria.getAttachDatetimeStart() != null)?dateFormat.format(rptCriteria.getAttachDatetimeStart()):""%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="attachDatetimeEnd" name="attachDatetimeEnd" value="<%=(rptCriteria.getAttachDatetimeEnd() != null)?dateFormat.format(rptCriteria.getAttachDatetimeEnd()):""%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-5">
								<div class="row">
									<div class="col-sm-8 col-md-13 offset-sm-10">
										<div class="form-check form-check-inline">
											<input type="checkbox" class="form-check-input" id="checkboxIncludeDetached" name="checkboxIncludeDetached" <%=(rptCriteria.isIncludeDetached())?"checked":"" %>>
											<label class="form-check-label"><strong>Include Detached</strong></label>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						<div class="form-row">
						
							<div class="form-group col-sm-20 col-md-23 clearfix">
								<span class="text-danger"><strong>*</strong></span> Required
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn">Clear</button>
								<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn">Search</button>
							</div>
							
						</div>
						
					</form>
					
				</div>
				
				<div class="card-header card-footer">
					<strong>Results 
					<%
					if (rptCriteria.getCaseNum() != null && !"".equals(rptCriteria.getCaseNum())) {
					%>
						for Case
						<a href="javascript:void(0);" title="View Case History All" onclick="displayCaseHistory('<%= CaseHistoryAllUtil.getEncryptedUrl(rptCriteria.getCaseNum(), rptCriteria.getCourtType(), rptCriteria.getLocnCode(),  rptCriteria.getLogName()) %>');">
							<%=TextUtil.print(rptCriteria.getCaseNum()) %>									
						</a>
					<%
					} 
					%>
					</strong>
				</div>
					
				<div class="card-body">

					<table id="resultsTable" class="table hover row-border compact">
						<thead class="text-light bg-dark">
							<tr>
								<% 
								//if changes are made to this table, be sure to make the changes in the PDF iText table/header/columns, too, and Excel in CorisAttorneysForCaseReportGenerator.java, and also in the corisAttorneysForCase.js file where the table is initialized (the date columns)
								int colspan1 = 9; //keep this updated as table changes so that the last column is not orderable
								if (rptCriteria.isIncludeDetached()) { 
									colspan1 = 11; 
								} 
								%>
								<th>Party Type</th>
								<th>Party Name</th>
								<th>Attorney Name</th>
								<th>Bar No</th>
								<th>Bar St</th>
								<th>Business Phone</th>
								<th>Attach Date</th>
								<th>Attached By</th>
								<% if (rptCriteria.isIncludeDetached()) { %>
									<th>Detach Date</th>
									<th>Detached By</th>
								<% } %>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							if (attyList != null && attyList.size() > 0) {
								int counter = 1;
								for (KaseBO kaseBO : attyList) {
									counter++;
									%>
									<tr>
										<td><%=TextUtil.print(kaseBO.get(PartyCaseBO.PARTYCODE)) %></td>
										<td>
											<% if (CorisSecurityCommon.hasPartyAccess(userAccess, kaseBO.getCaseType(), (String) kaseBO.get(PartyCaseBO.PARTYCODE))) { %>
												<%=TextUtil.print(kaseBO.get("party_last_name")) %> <%=(!TextUtil.isEmpty((String) kaseBO.get("party_first_name")))?", "+TextUtil.print(kaseBO.get("party_first_name")):"" %>
											<% } else { %>
												***, ***
											<% } %>
										</td>
										<td>
											<%
											if (!TextUtil.isEmpty((String) kaseBO.get("attorney_last_name"))) {
												%>
												<%=TextUtil.print(kaseBO.get("attorney_last_name")) %>
												<%
												if (!TextUtil.isEmpty((String) kaseBO.get("attorney_first_name"))) {
													%>
													<%=TextUtil.print(", "+TextUtil.print(kaseBO.get("attorney_first_name"))) %>
													<%
												}
											} else if ("Y".equals(kaseBO.get(PartyCaseBO.PROSE))) {
												%>
												PRO SE
												<%
											}
											%>
										</td>
										<td><%=((Integer) kaseBO.get(AttyPartyBO.BARNUM) > 0)?kaseBO.get(AttyPartyBO.BARNUM):"" %></td>
										<td><%=TextUtil.print(kaseBO.get(AttyPartyBO.BARSTATE)) %></td>
										<td><%=TextUtil.print(kaseBO.get(AttorneyBO.BUSPHONE)) %></td>
										<td data-sort="<%=(kaseBO.get(AttyPartyBO.ATTACHDATETIME) != null)?dateFormat.format(kaseBO.get(AttyPartyBO.ATTACHDATETIME)):"" %>">
											<div id="displayAttachDatetime<%=counter%>">
												<% if (kaseBO.get(AttyPartyBO.ATTACHDATETIME) != null) { %>
													<a href="javascript:void()" title="Edit Attach Date" onclick="editDatetime('<%=dateFormat.format(kaseBO.get(AttyPartyBO.ATTACHDATETIME)) %>', <%=counter%>, 'Attach');"><%=dateFormat.format(kaseBO.get(AttyPartyBO.ATTACHDATETIME)) %></a>
												<% } %>
											</div>
											<div id="editAttachDatetime<%=counter%>" style="display: none;">
												<div class="row">
													<div class="col input-group date" id='datetimepicker3<%=counter%>'>
														<input type="text" class="form-control" id="attyAttachDatetime<%=counter %>" name="attyAttachDatetime<%=counter %>" value="<%=(kaseBO.get(AttyPartyBO.ATTACHDATETIME) != null)?dateFormat.format(kaseBO.get(AttyPartyBO.ATTACHDATETIME)):"" %>">
														<span class="input-group-text input-group-addon">
															<span class="fa fa-calendar"></span>
														</span>
													</div>
													<i title="Save" onclick="updateDatetime(event, <%=kaseBO.get(AttyPartyBO.ATTYPARTYID)%>, $('#attyAttachDatetime<%=counter %>').val(), <%=counter %>, 'Attach');" class="mt-1 text-success fas fa-save fa-lg"></i>
													<i title="Cancel" onclick="cancelDatetimeEdit(<%=counter %>, 'Attach');" class="mt-1 ml-1 text-secondary far fa-times-circle fa-lg"></i>
												</div>
											</div>
										</td>
										<td><%=TextUtil.print(kaseBO.get("attached_logname")) %></td>
										<% if (rptCriteria.isIncludeDetached()) {  %>
											<td data-sort="<%=(kaseBO.get(AttyPartyBO.DETACHDATETIME) != null)?dateFormat.format(kaseBO.get(AttyPartyBO.DETACHDATETIME)):"" %>">
												<div id="displayDetachDatetime<%=counter%>">
													<% if (kaseBO.get(AttyPartyBO.DETACHDATETIME) != null) { %>
														<a href="javascript:void()" title="Edit Detach Date" onclick="editDatetime('<%=dateFormat.format(kaseBO.get(AttyPartyBO.DETACHDATETIME)) %>', <%=counter%>, 'Detach');"><%=dateFormat.format(kaseBO.get(AttyPartyBO.DETACHDATETIME)) %></a>
													<% } %>
												</div>
												<div id="editDetachDatetime<%=counter%>" style="display: none;">
													<div class="row">
														<div class="col input-group date" id='datetimepicker3<%=counter%>'>
															<input type="text" class="form-control" id="attyDetachDatetime<%=counter %>" name="attyDetachDatetime<%=counter %>" value="<%=(kaseBO.get(AttyPartyBO.DETACHDATETIME) != null)?dateFormat.format(kaseBO.get(AttyPartyBO.DETACHDATETIME)):"" %>">
															<span class="input-group-text input-group-addon">
																<span class="fa fa-calendar"></span>
															</span>
														</div>
														<i title="Save" onclick="updateDatetime(event, <%=TextUtil.print(kaseBO.get(AttyPartyBO.ATTYPARTYID))%>, $('#attyDetachDatetime<%=counter %>').val(), <%=counter %>, 'Detach');" class="mt-1 text-success fas fa-save fa-lg"></i>
														<i title="Cancel" onclick="cancelDatetimeEdit(<%=counter %>, 'Detach');" class="mt-1 ml-1 text-secondary far fa-times-circle fa-lg"></i>
													</div>
												</div>
											</td>
											<td><%=TextUtil.print(kaseBO.get("detached_logname")) %></td>
										<% } %>
										<td nowrap>
											<i title="View and Print Details" id="attyDetailsBtn" onclick="attorneyDetails('<%=kaseBO.get(AttyPartyBO.BARNUM) %>','<%=kaseBO.get(AttyPartyBO.BARSTATE) %>');" class="text-primary fas fa-address-card fa-lg"></i>
											<% if (kaseBO.get(AttyPartyBO.DETACHDATETIME) == null) { %>
												<i title="Detach Attorney from Case <%=rptCriteria.getCaseNum() %>" onclick='attorneyDetach(<%=kaseBO.get(AttyPartyBO.ATTYPARTYID) %>);' class="text-danger fas fa-user-slash fa-lg"></i>
											<% } else { %>
												<i title="Attorney is Detached" class="text-muted fas fa-user-slash fa-lg"></i>
											<% } %>
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
							<button type="button" class="btn btn-primary ml-2 mr-2 float-right" id="attachBtn">Attach Attorney</button>
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
    	
    	<div id="locationJusticeOptions" style="display: none;">
    		<%
			if (locationJusticeList.size() > 0) {
				for (LocationBO locationBO : locationJusticeList) {
					%>
					 <option value="<%=TextUtil.print(locationBO.getLocnCode())%>" <%=("Y".equals(locationBO.get(PersonnelBO.DEFLTLOGIN)))?"selected":"" %>><%=TextUtil.print(locationBO.getLocnDescr())%></option>
					<%
				}
			}
			%>
    	</div>
    	
    	<div id="locationDistrictOptions" style="display: none;">
    		<%
			if (locationDistrictList.size() > 0) {
				for (LocationBO locationBO : locationDistrictList) {
					%>
					 <option value="<%=TextUtil.print(locationBO.getLocnCode())%>" <%=("Y".equals(locationBO.get(PersonnelBO.DEFLTLOGIN)))?"selected":"" %>><%=TextUtil.print(locationBO.getLocnDescr())%></option>
					<%
				}
			}
			%>
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
	    pageVars.attyListSize = 0;
	    <% if(attyList != null && attyList.size() > 0){ %>
		    pageVars.attyListSize = <%=attyList.size() %>;
	    <% } %>
	    pageVars.locnCode = '<%= rptCriteria.getLocnCode() %>';
	    pageVars.courtType = '<%= rptCriteria.getCourtType() %>';
	    pageVars.colspan1 = <%= colspan1 %>;
	    pageVars.maxResults = <%= Constants.MAX_RESULTS %>;
		pageVars.urlSearchAttorneysForCase = '<%= urlCryptor.urlEncrypt(paramsSearchAttorneysForCase) %>';
		pageVars.urlUpdateAttachDatetime = '<%= urlCryptor.urlEncrypt(paramsUpdateAttachDatetime) %>';
		pageVars.urlUpdateDetachDatetime = '<%= urlCryptor.urlEncrypt(paramsUpdateDetachDatetime) %>';
		pageVars.urlSaveReport = '<%= urlCryptor.urlEncrypt(paramsSaveReport) %>';
		pageVars.urlEmailReport = '<%= urlCryptor.urlEncrypt(paramsEmailReport) %>';
		pageVars.urlAttorneyDetails = '<%= urlCryptor2.urlEncrypt(paramsAttorneyDetails) %>';
		pageVars.urlAttorneyDetach = '<%= urlCryptor.urlEncrypt(paramsAttorneyDetach) %>';
	</script>
	
	<script src="/CorisWEB/js/corisAttorneysForCase.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
</body>
</html>
<%
dateFormat = null;
attyList = null;
locationDistrictList = null;
locationJusticeList = null;
rptCriteria = null;
urlCryptor = null;
urlCryptor2 = null;
paramsSearchAttorneysForCase = null;
paramsUpdateAttachDatetime = null;
paramsUpdateDetachDatetime = null;
paramsSaveReport = null;
paramsEmailReport = null;
paramsAttorneyDetails = null;
paramsAttorneyDetach = null;
%>