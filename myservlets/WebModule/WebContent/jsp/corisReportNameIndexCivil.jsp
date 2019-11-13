<%@page import="gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO"%>
<%@page import="gov.utcourts.coriscommon.util.CorisSecurityCommon"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.corisweb.report.CorisReportNameIndexCivilReportCriteria"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
SimpleDateFormat dateFormat = Constants.dateFormatCoris;
CorisReportNameIndexCivilReportCriteria rptCriteria = request.getAttribute("myCriteria")==null? new CorisReportNameIndexCivilReportCriteria(request):(CorisReportNameIndexCivilReportCriteria)request.getAttribute("myCriteria");
DecimalFormat currencyFormat = new DecimalFormat("$#,###,###.00");

@SuppressWarnings("unchecked")
List<KaseBO> reportList = (List<KaseBO>) request.getAttribute("reportList");
@SuppressWarnings("unchecked")
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
@SuppressWarnings("unchecked")
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");
@SuppressWarnings("unchecked")
List<CaseTypeBO> caseTypeList = (List<CaseTypeBO>) request.getAttribute("caseTypeList");
@SuppressWarnings("unchecked")
List<String> caseType = (List<String>) request.getAttribute("caseType");
@SuppressWarnings("unchecked")
Map<String, Integer> totalCounts = (Map<String, Integer>) request.getAttribute("totalCounts"); 

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisReportNameIndexCivilServlet");

String paramsSearch = "mode=find";
String paramsSaveReport = "mode=SAVE";
String paramsEmailReport = "mode=EMAIL";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Interactive Reports</title>
    
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
					<strong>Name Index - Civil</strong>
				</div>

				<div class="card-body">
				
					<form id="searchForm" name="searchForm" action="<%=urlCryptor.urlEncrypt(paramsSearch)%>" novalidate>
						<input type="hidden" id="orderBy" name="orderBy" value="">
						<input type="hidden" id="format" name="format" value="">
						
						<div class="form-row">
						
							<div class="form-group col-md-8 order-md-1">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Court Type<span class="text-danger">*</span></strong></label>
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
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Location<span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="locnCode" name="locnCode" required>
										</select>
									</div>
								</div>
							</div>
									
							<div class="form-group col-md-8 order-md-3">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Type of Date<span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="typeOfDate" name="typeOfDate" required>
											<option value="F">Filing Date</option>
											<option value="D">Disposition Date</option>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group col-md-8 order-md-4">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Report Date Start/End<span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="reportDateStart" name="reportDateStart" value="<%=(rptCriteria.getReportDateStart() != null)?dateFormat.format(rptCriteria.getReportDateStart()):""%>" required>
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="reportDateEnd" name="reportDateEnd" value="<%=(rptCriteria.getReportDateEnd() != null)?dateFormat.format(rptCriteria.getReportDateEnd()):""%>" required>
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-5">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Case Type<span class="text-danger">*</span></strong><span class="text-info fas fa-info-circle fa-lg" title="Hold down Ctrl (Windows) / Command (Mac) button to select multiple Case Types"></span></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="caseType" name="caseType" multiple size="4" required>
										</select>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-6">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Report Category<span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="reportCategory" name="reportCategory" required>
											<option value="V" <%=("V".equals(rptCriteria.getCategory()))?"selected":"" %>>Civil</option>
											<option value="D" <%=("D".equals(rptCriteria.getCategory()))?"selected":"" %>>Domestic</option>
											<option value="P" <%=("P".equals(rptCriteria.getCategory()))?"selected":"" %>>Probate</option>
											<option value="S" <%=("S".equals(rptCriteria.getCategory()))?"selected":"" %>>Small Claims</option>
											<option value="A" <%=("A".equals(rptCriteria.getCategory()))?"selected":"" %>>All</option>
										</select>
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
					<strong>Results</strong>
				</div>
				
				<div class="card-body">

					<table id="resultsTable" class="table hover row-border compact">
						<thead class="text-light bg-dark">
							<tr>
								<% 
								//if changes are made to this table, be sure to make the changes in the PDF iText table/header/columns, too, and Excel in CorisReportNameIndexCivilReportGenerator.java, and also in the corisReportNameIndexCivil.js file where the table is initialized (the date columns)
								int colspan1 = 16; //keep this updated as table changes
								%>
								<th>Defendant Name</th>
								<th>Plaintiff Name</th>
								<th>Filed By</th>
								<th>Filed Date</th>
								<th>Filing Type</th>
								<th>Case Number</th>
								<th>Case Type</th>
								<th>Case Title</th>
								<th>Amount in Controversy</th>
								<th>Waiver Status</th>
								<th>Amount Paid/Credit</th>
								<th>Disposition</th>
								<th>Disposition Date</th>
								<th>Original Judge</th>
								<th>Current Judge</th>
								<th>Commissioner</th>
							</tr>
						</thead>
						<tbody>
							<%
							if (reportList != null && reportList.size() > 0) {
								int counter = 1;
								HashMap<String, Boolean> caseAccessLevel = CorisSecurityCommon.getUserSecurityLevels(rptCriteria, null);
								for (KaseBO kaseBO : reportList) {
									counter++;
									%>
									<tr>
										<td>
										<% if (CorisSecurityCommon.hasPartyAccess(caseAccessLevel, kaseBO.getCaseType(), (String) kaseBO.get(PartyCaseBO.PARTYCODE))) { %>
											<%=TextUtil.print(kaseBO.get("def_last_name")) %> <%=(!TextUtil.isEmpty((String) kaseBO.get("def_first_name")))?", "+TextUtil.print(kaseBO.get("def_first_name")):"" %>
        								<% } else { %>
        									Protected
										<% } %>
										</td>									
										<td>
										<% if (CorisSecurityCommon.hasPartyAccess(caseAccessLevel, kaseBO.getCaseType(), (String) kaseBO.get(PartyCaseBO.PARTYCODE))) { %>
											<%=TextUtil.print(kaseBO.get("pla_last_name")) %> <%=(!TextUtil.isEmpty((String) kaseBO.get("pla_first_name")))?", "+TextUtil.print(kaseBO.get("pla_first_name")):"" %>
        								<% } else { %>
        									Protected
										<% } %>
										</td>									
										<td><%=TextUtil.print(kaseBO.get(PersonnelBO.LOGNAME)) %></td>
										<td data-sort="<%=(kaseBO.getFilingDate() != null)?dateFormat.format(kaseBO.getFilingDate()):"" %>">
											<%=(kaseBO.getFilingDate() != null)?dateFormat.format(kaseBO.getFilingDate()):"" %>
										</td>
										<td><%=TextUtil.print(kaseBO.getFilingType()) %></td>
										<td>
											<a href="javascript:void(0);" title="View Case History All" onclick="displayCaseHistory('<%= CaseHistoryAllUtil.getEncryptedUrl(kaseBO.getCaseNum(), rptCriteria.getCourtType(), rptCriteria.getLocnCode(), rptCriteria.getLogName()) %>');">
												<%=TextUtil.print(kaseBO.getCaseNum()) %>									
											</a>
										</td>
										<td><%=TextUtil.print(kaseBO.get("case_type_descr")) %></td>
										<td><%=TextUtil.print(kaseBO.get("case_title")) %></td>
										<td class="text-right">
											<%=(kaseBO.get(CivilCaseBO.AMTINCONTROVERSY) != null && !"0".equals(kaseBO.get(CivilCaseBO.AMTINCONTROVERSY)))?currencyFormat.format(kaseBO.get(CivilCaseBO.AMTINCONTROVERSY)):"" %>
										</td>
										<td><%=TextUtil.print(kaseBO.get("waiver_status")) %></td>
										<td class="text-right">
											<%=(kaseBO.get("amount_paid") != null && !"0".equals(kaseBO.get("amount_paid")))?currencyFormat.format(new BigDecimal((String) kaseBO.get("amount_paid"))):"" %>
										</td>
										<td><%=TextUtil.print(kaseBO.get("disp_type_descr")) %></td>
										<td data-sort="<%=(kaseBO.getDispDate() != null)?dateFormat.format(kaseBO.getDispDate()):"" %>">
											<%=(kaseBO.getDispDate() != null)?dateFormat.format(kaseBO.getDispDate()):"" %>
										</td>
										<td><%=TextUtil.print(kaseBO.get("original_judge_last_name")) %><%=(!TextUtil.isEmpty((String) kaseBO.get("original_judge_first_name")))?", ":"" %><%=TextUtil.print(kaseBO.get("original_judge_first_name")) %></td>
										<td><%=TextUtil.print(kaseBO.get("current_judge_last_name")) %><%=(!TextUtil.isEmpty((String) kaseBO.get("current_judge_first_name")))?", ":"" %><%=TextUtil.print(kaseBO.get("current_judge_first_name")) %></td>
										<td><%=TextUtil.print(kaseBO.get("commissioner_last_name")) %><%=(!TextUtil.isEmpty((String) kaseBO.get("commissioner_first_name")))?", ":"" %><%=TextUtil.print(kaseBO.get("commissioner_first_name")) %></td>
									</tr>
									<% 
								} 
							} %>
						</tbody>
					</table>

					<table id="totalsTable">
						<tbody>					
							<tr>
								<td>
									<% 
									int totalCases = 0;
									if (totalCounts != null && caseTypeList != null) {
										for (CaseTypeBO caseTypeBO : caseTypeList) {
											if (totalCounts.get(caseTypeBO.getCaseType()) != null) {
												%>
												<br>Total <%=caseTypeBO.getDescr()+": "+totalCounts.get(caseTypeBO.getCaseType()) %>
												<%
												totalCases += totalCounts.get(caseTypeBO.getCaseType());
											}
										}
										if (totalCases > 0) {
											%>
											<br><br>Total Cases: <%=totalCases %>
											<% 
										}
									} 
									%>
								</td>
							</tr>
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

    	<div id="caseTypeCivilOptions" style="display: none;">
    		<%
			if (caseTypeList.size() > 0) {
				for (CaseTypeBO caseTypeBO : caseTypeList) {
					if ("V".equals(caseTypeBO.getCategory())) {
						boolean selected = false;
						for (String string : caseType) {
 							if (string.equals(caseTypeBO.getCaseType())) {
 								selected = true;
 							}
						}
						%>
						<option value="<%=TextUtil.print(caseTypeBO.getCaseType())%>" <%=(selected)?"selected":"" %>><%=TextUtil.print(caseTypeBO.getDescr())%></option>
						<%
					}
				}
			}
			%>
    	</div>

    	<div id="caseTypeDomesticOptions" style="display: none;">
    		<%
			if (caseTypeList.size() > 0) {
				for (CaseTypeBO caseTypeBO : caseTypeList) {
					if ("D".equals(caseTypeBO.getCategory())) {
						boolean selected = false;
						for (String string : caseType) {
 							if (string.equals(caseTypeBO.getCaseType())) {
 								selected = true;
 							}
						}
						%>
						<option value="<%=TextUtil.print(caseTypeBO.getCaseType())%>" <%=(selected)?"selected":"" %>><%=TextUtil.print(caseTypeBO.getDescr())%></option>
						<%
					}
				}
			}
			%>
    	</div>
    	
    	<div id="caseTypeProbateOptions" style="display: none;">
    		<%
			if (caseTypeList.size() > 0) {
				for (CaseTypeBO caseTypeBO : caseTypeList) {
					if ("P".equals(caseTypeBO.getCategory())) {
						boolean selected = false;
						for (String string : caseType) {
 							if (string.equals(caseTypeBO.getCaseType())) {
 								selected = true;
 							}
						}
						%>
						<option value="<%=TextUtil.print(caseTypeBO.getCaseType())%>" <%=(selected)?"selected":"" %>><%=TextUtil.print(caseTypeBO.getDescr())%></option>
						<%
					}
				}
			}
			%>
    	</div>

    	<div id="caseTypeSmallClaimsOptions" style="display: none;">
    		<%
			if (caseTypeList.size() > 0) {
				for (CaseTypeBO caseTypeBO : caseTypeList) {
					if ("S".equals(caseTypeBO.getCategory())) {
						boolean selected = false;
						for (String string : caseType) {
 							if (string.equals(caseTypeBO.getCaseType())) {
 								selected = true;
 							}
						}
						%>
						<option value="<%=TextUtil.print(caseTypeBO.getCaseType())%>" <%=(selected)?"selected":"" %>><%=TextUtil.print(caseTypeBO.getDescr())%></option>
						<%
					}
				}
			}
			%>
    	</div>

    	<div id="caseTypeAllOptions" style="display: none;">
    		<%
			if (caseTypeList.size() > 0) {
				for (CaseTypeBO caseTypeBO : caseTypeList) {
					if ("V".equals(caseTypeBO.getCategory()) || "D".equals(caseTypeBO.getCategory()) || "P".equals(caseTypeBO.getCategory()) || "M".equals(caseTypeBO.getCategory()) || "S".equals(caseTypeBO.getCategory())) {
						boolean selected = false;
						for (String string : caseType) {
 							if (string.equals(caseTypeBO.getCaseType())) {
 								selected = true;
 							}
						}
						%>
						<option value="<%=TextUtil.print(caseTypeBO.getCaseType())%>" <%=(selected)?"selected":"" %>><%=TextUtil.print(caseTypeBO.getDescr())%></option>
						<%
					}
				}
			}
			%>
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
	    pageVars.locnCode = '<%= rptCriteria.getLocnCode() %>';
	    pageVars.courtType = '<%= rptCriteria.getCourtType() %>';
	    pageVars.colspan1 = <%= colspan1 %>;
		pageVars.urlSearch = '<%= urlCryptor.urlEncrypt(paramsSearch) %>';
		pageVars.urlSaveReport = '<%= urlCryptor.urlEncrypt(paramsSaveReport) %>';
		pageVars.urlEmailReport = '<%= urlCryptor.urlEncrypt(paramsEmailReport) %>';
	</script>
	
	<script src="/CorisWEB/js/corisReportNameIndexCivil.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

</body>
</html>
<%
errorMessage = null;
dateFormat = null;
rptCriteria = null;
reportList = null;
locationDistrictList = null;
locationJusticeList = null;
caseTypeList = null;
urlCryptor = null;
paramsSearch = null;
paramsSaveReport = null;
paramsEmailReport = null;
%>