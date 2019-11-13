<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attypresent.AttyPresentBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.corisweb.report.CorisReportAttorneyAppearanceReportCriteria"%>
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
CorisReportAttorneyAppearanceReportCriteria rptCriteria = request.getAttribute("myCriteria")==null? new CorisReportAttorneyAppearanceReportCriteria(request):(CorisReportAttorneyAppearanceReportCriteria)request.getAttribute("myCriteria");

@SuppressWarnings("unchecked")
List<AttyPresentBO> appearanceList = (List<AttyPresentBO>) request.getAttribute("appearanceList");
@SuppressWarnings("unchecked")
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
@SuppressWarnings("unchecked")
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");
@SuppressWarnings("unchecked")
List<StateBO> statesList = (List<StateBO>) request.getAttribute("statesList");
@SuppressWarnings("unchecked")
List<PersonnelBO> judgeJusticeList = (List<PersonnelBO>) request.getAttribute("judgeJusticeList");
@SuppressWarnings("unchecked")
List<PersonnelBO> judgeDistrictList = (List<PersonnelBO>) request.getAttribute("judgeDistrictList");

URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisReportAttorneyAppearanceServlet");
URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/CorisAttorneyDetailsServlet");

String paramsSearch = "mode=find";
String paramsSaveReport = "mode=SAVE";
String paramsEmailReport = "mode=EMAIL";
String paramsAttorneyDetails = "mode=attorneyDetails";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Attorney Appearances</title>
    
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
					<strong>Attorney Appearances</strong>
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
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Judge<span class="text-danger">!</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<select class="form-control" id="judgeId" name="judgeId">
										</select>
									</div>
								</div>
							</div>							

							<div class="form-group col-md-8 order-md-4">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Attorney Bar Number<span class="text-danger">!</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<input type="text" class="form-control" id="barNum" name="barNum" value="<%=TextUtil.print(rptCriteria.getBarNum()) %>" maxlength="10" autofocus required>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-8 order-md-5">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Attorney Bar State<span class="text-danger">!</span></strong></label>
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
														
							<div class="form-group col-md-8 order-md-6">
								<div class="row align-items-center">
									<label class="control-label col-sm-10 col-md-10 text-sm-right"><strong>Start/End Date<span class="text-danger">*</span></strong></label>
									<div class="col-sm-8 col-md-13">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="startDate" name="startDate" value="<%=(rptCriteria.getStartDate() != null)?dateFormat.format(rptCriteria.getStartDate()):""%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="endDate" name="endDate" value="<%=(rptCriteria.getEndDate() != null)?dateFormat.format(rptCriteria.getEndDate()):""%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						<div class="form-row">
						
							<div class="form-group col-sm-20 col-md-23 clearfix">
								<span class="text-danger"><strong>*</strong></span> Required
								<br><span class="text-danger"><strong>!</strong></span> Judge or Attorney Bar Number/State Required
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
								//if changes are made to this table, be sure to make the changes in the PDF iText table/header/columns, too, and Excel in CorisReportAttorneyAppearanceReportGenerator.java, and also in the corisReportAttorneyAppearance.js file where the table is initialized (the date columns)
								int colspan1 = 9; //keep this updated as table changes so that the last column is not orderable
								%>
								<th>Judge Name</th>
								<th>Attorney Name</th>
								<th>Attorney Bar Number</th>
								<th>Attorney Bar State</th>
								<th>Location</th>
								<th>Count Misc</th>
								<th>Count Trial</th>
								<th>Attorney Email Address</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							if (appearanceList != null && appearanceList.size() > 0) {
								int counter = 1;
								for (AttyPresentBO attyPresentBO : appearanceList) {
									counter++;
									%>
									<tr>
										<td><%=TextUtil.print(attyPresentBO.get("judge_last_name")) + ", " + TextUtil.print(attyPresentBO.get("judge_first_name"))%></td>
										<td><%=TextUtil.print(attyPresentBO.get("attorney_last_name")) + ", " + TextUtil.print(attyPresentBO.get("attorney_first_name"))%></td>
										<td><%=attyPresentBO.getBarNum() %></td>
										<td><%=TextUtil.print(attyPresentBO.getBarState()) %></td>
										<td><%=TextUtil.print(attyPresentBO.get(LocationBO.LOCNDESCR)) %></td>
										<td><%=(Integer.parseInt((String) attyPresentBO.get("count_misc")) - Integer.parseInt((String) attyPresentBO.get("count_trial")))%></td>
										<td><%=attyPresentBO.get("count_trial") %></td>
										<td><%=TextUtil.print(attyPresentBO.get(AttorneyBO.EMAILADDRESS)) %></td>
										<td nowrap>
											<i title="View and Print Details" id="attyDetailsBtn" onclick="attorneyDetails('<%=attyPresentBO.getBarNum() %>','<%=attyPresentBO.getBarState() %>');" class="text-primary fas fa-address-card fa-lg"></i>
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
	    pageVars.appearanceListSize = 0;
	    <% if(appearanceList != null && appearanceList.size() > 0){ %>
		    pageVars.appearanceListSize = <%=appearanceList.size() %>;
	    <% } %>
	    pageVars.locnCode = '<%= rptCriteria.getLocnCode() %>';
	    pageVars.courtType = '<%= rptCriteria.getCourtType() %>';
	    pageVars.colspan1 = <%= colspan1 %>;
	    pageVars.maxResults = <%= Constants.MAX_RESULTS %>;
		pageVars.urlSearch = '<%= urlCryptor.urlEncrypt(paramsSearch) %>';
		pageVars.urlSaveReport = '<%= urlCryptor.urlEncrypt(paramsSaveReport) %>';
		pageVars.urlEmailReport = '<%= urlCryptor.urlEncrypt(paramsEmailReport) %>';
		pageVars.urlAttorneyDetails = '<%= urlCryptor2.urlEncrypt(paramsAttorneyDetails) %>';
		
		function loadJudge(courtType) {
			if (courtType == "D") {
				var judgeDistrictOptions = '<option value=""></option>';
	    		<%
				if (judgeDistrictList.size() > 0) {
					for (PersonnelBO personnelBO : judgeDistrictList) {
						%>
						if ('<%=personnelBO.getLocnCode() %>' == $('#locnCode').val()) {
							judgeDistrictOptions += '<option value="<%=personnelBO.getUseridSrl()%>" <%=(rptCriteria.getJudgeId() == personnelBO.getUseridSrl())?"selected":"" %>><%=TextUtil.print(personnelBO.getLastName()) +", "+ TextUtil.print(personnelBO.getFirstName())%></option>';
						}
						<%
					}
				}
				%>
				$('#judgeId').html(judgeDistrictOptions);
			} else if (courtType == "J") {
				var judgeJusticeOptions = '<option value=""></option>';
	    		<%
				if (judgeJusticeList.size() > 0) {
					for (PersonnelBO personnelBO : judgeJusticeList) {
						%>
						if ('<%=personnelBO.getLocnCode() %>' == $('#locnCode').val()) {
							judgeJusticeOptions += '<option value="<%=personnelBO.getUseridSrl()%>" <%=(rptCriteria.getJudgeId() == personnelBO.getUseridSrl())?"selected":"" %>><%=TextUtil.print(personnelBO.getLastName()) +", "+ TextUtil.print(personnelBO.getFirstName())%></option>';
						}
						<%
					}
				}
				%>
				$('#judgeId').html($('#judgeJusticeOptions').html());
			}
		}
	</script>
	
	<script src="/CorisWEB/js/corisReportAttorneyAppearance.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
</body>
</html>
<%
errorMessage = null;
appearanceList = null;
locationDistrictList = null;
locationJusticeList = null;
statesList = null;
judgeJusticeList = null;
judgeDistrictList = null;
rptCriteria = null;
urlCryptor = null;
urlCryptor2 = null;
paramsSearch = null;
paramsSaveReport = null;
paramsEmailReport = null;
paramsAttorneyDetails = null;
%>