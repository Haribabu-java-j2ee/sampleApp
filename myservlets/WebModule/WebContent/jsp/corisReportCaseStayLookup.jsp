
<%@page import="gov.utcourts.corisweb.report.CorisReportCaseStayLookupReportCriteria"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.staytype.StayTypeBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.stay.StayBO" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>  
<%
	CorisReportCaseStayLookupReportCriteria rptCriteria = request.getAttribute("myCriteria") != null ? (CorisReportCaseStayLookupReportCriteria)request.getAttribute("myCriteria") : null;
	String courtType = (String) request.getAttribute("courtType");
	String locnCode = (String) request.getAttribute("locnCode");
	Date stayDateFrom = null;
	if (request.getAttribute("stayDateFrom") != null) {
		stayDateFrom = (Date) request.getAttribute("stayDateFrom");
	}
	Date stayDateTo = null;
	if (request.getAttribute("stayDateTo") != null) {
		stayDateTo = (Date) request.getAttribute("stayDateTo");
	}
	String caseType = (String) request.getAttribute("caseType");
	String stayReason = (String) request.getAttribute("stayReason");
	if (rptCriteria != null) {
		courtType = rptCriteria.getCourtType();
		locnCode = rptCriteria.getLocnCode();
		stayDateFrom = rptCriteria.getStayDateFrom();
		stayDateTo = rptCriteria.getStayDateTo();
		caseType = rptCriteria.getCaseType();
		stayReason = rptCriteria.getStayReason();
	}
	List<LocationBO> distLocationList = (List<LocationBO>) request.getAttribute("distLocationList");
	List<LocationBO> justLocationList = (List<LocationBO>) request.getAttribute("justLocationList");
	List<CaseTypeBO> caseTypeList = (List<CaseTypeBO>) request.getAttribute("caseTypeList");
	List<StayTypeBO> stayReasonList = (List<StayTypeBO>) request.getAttribute("stayReasonList");
	List<StayBO> caseStayList = (List<StayBO>) request.getAttribute("caseStayList");
	StringBuilder sb = new StringBuilder();
	if(distLocationList != null){
		for(LocationBO temp : distLocationList){
			sb.append("<option value=\"").append(temp.get(LocationBO.LOCNCODE)).append("\">").append(temp.get(LocationBO.LOCNDESCR)).append("</option>");
		}
	}
	String districtLocationOptions = sb.toString();
	
	sb = new StringBuilder();
	if(justLocationList != null){
		for(LocationBO temp : justLocationList){
			sb.append("<option value=\"").append(temp.get(LocationBO.LOCNCODE)).append("\">").append(temp.get(LocationBO.LOCNDESCR)).append("</option>");
		}
	}
	String justiceLocationOptions = sb.toString();
	SimpleDateFormat dateFormat = Constants.dateFormatCoris;
	URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisReportCaseStayLookupServlet");
	
	String paramsViewExcel = "mode=saveAsExcel";
	
	
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

<%-- loading screen --%>
    <div id="loadingContainer">
        <div id="loadingCard">
            <div id="loadingMessage">Loading ...</div>
        </div>
    </div>
    
    <!-- main content -->
    <main>

		<div class="container-fluid">
			
			<div class="card m-2">
					
				
				<div class="card-header">
					<strong>Search By</strong>
				</div>
				
				<div class="card-body">
					<form id="searchCaseStayForm" name="searchCaseStayForm">
					<input type = "hidden" name="mode" value = "searchCaseStay">
						<div class="form-row">
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Court Type <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="courtType" name="courtType" onchange="loadLocation(this.value)" required>
											<option value="D" <%=("D".equals(courtType))?"selected":"" %>>District</option>
											<option value="J" <%=("J".equals(courtType))?"selected":"" %>>Justice</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Location <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="locnCode" name="locnCode" required onchange="changeJudgeComm();">
											<% if("D".equals(courtType) && distLocationList != null){
												for(LocationBO temp : distLocationList){
													%>
													
											 <option value="<%= temp.get(LocationBO.LOCNCODE) %>" <%=temp.get(LocationBO.LOCNCODE).equals(locnCode) ? "selected" : ""  %>><%= temp.get(LocationBO.LOCNDESCR) %></option>
													<% 
													}
												} else if("J".equals(courtType) && justLocationList != null){
													for(LocationBO temp : justLocationList){
													%>
											 <option value="<%= temp.get(LocationBO.LOCNCODE) %>" <%=temp.get(LocationBO.LOCNCODE).equals(locnCode) ? "selected" : ""  %>><%= temp.get(LocationBO.LOCNDESCR) %></option>
											 	<% }
											 	}
											 %>
										</select>
									</div>
								</div>
							</div>
						
							
							
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Stay Begin Date From/To</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="stayDateFrom" name="stayDateFrom" value = "<%=(TextUtil.printDate(stayDateFrom))%>" required>
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="stayDateTo" name="stayDateTo" value = "<%=(TextUtil.printDate(stayDateTo))%>" required>
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Case Type <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="caseType" name="caseType">
											<option value="">All Case Types</option>
											<% for(CaseTypeBO caseTypeBO : caseTypeList){ %>
											<option value = "<%=TextUtil.print(caseTypeBO.get(CaseTypeBO.CASETYPE)) %>" <%=TextUtil.isSelected(caseType, caseTypeBO.get(CaseTypeBO.CASETYPE)) %> ><%= caseTypeBO.get(CaseTypeBO.DESCR) %></option>
											<% } %>
										</select>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Judge/Commissioner:<span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="judgeCommId" name="judgeCommId"></select>
									</div>
								</div>
							</div>
						
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Stay Reason <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="stayReason" name="stayReason">
											<option value="">All Stay Reasons</option>
											<% for(StayTypeBO stayTypeBO : stayReasonList){ %>
											<option value="<%= stayTypeBO.get(StayTypeBO.STAYCODE) %>" <%= TextUtil.isSelected(stayReason, stayTypeBO.get(StayTypeBO.STAYCODE)) %>><%= stayTypeBO.get(StayTypeBO.DESCR) %></option>
											<% } %>
										</select>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onClick="clearForm();">Clear</button>
								<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn" onclick="validateForm();">Search</button>
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
								<% int colspan5 = 7; %>
								<th>Case Number</th>
								<th>Case Type</th>
								<th>Stay Begin</th>
								<th>Stay End</th>
								<th>Reason</th>
								<th>Judge</th>
								<th>Commissioner</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (caseStayList != null) {
									String caseNum = "";
									Date stayBeginDate = null;
									Date stayEndDate = null;
									stayReason = "";
									for(StayBO stayBO : caseStayList){
										StringBuilder judge = new StringBuilder();
										StringBuilder commissioner = new StringBuilder();
										caseNum = (String) stayBO.get(KaseBO.CASENUM);
										caseType = (String) stayBO.get("case_type_descr");
										if (stayBO.get(KaseBO.ASSNJUDGEID) != null && (Integer) stayBO.get(KaseBO.ASSNJUDGEID) > 0){
											judge.append((String) stayBO.get("judge_last_name")); 
											if(!TextUtil.isEmpty((String) stayBO.get("judge_first_name"))){
												judge.append(", ").append(stayBO.get("judge_first_name"));
											}
										}	
										if (stayBO.get(KaseBO.ASSNCOMMID) != null && (Integer) stayBO.get(KaseBO.ASSNCOMMID) > 0) {
											commissioner.append((String) stayBO.get("commissioner_last_name")); 
											if(!TextUtil.isEmpty((String) stayBO.get("commissioner_first_name"))){
												commissioner.append(", ").append(stayBO.get("commissioner_first_name"));
											}
										}
										stayBeginDate = (Date) stayBO.getBeginDate();
										stayReason = (String) stayBO.get("stay_code_descr");
										stayEndDate = (Date) stayBO.getEndDate();
										
								 %>
										<tr>
											<td>
												<a href="javascript:void(0);" title="View Case History All" onclick="displayCaseHistory('<%= CaseHistoryAllUtil.getEncryptedUrl(caseNum, rptCriteria.getCourtType(), rptCriteria.getLocnCode(), rptCriteria.getLogName()) %>');">
													<%=TextUtil.print(caseNum) %>									
												</a>
											</td>
											<td><%=TextUtil.print(caseType) %></td>
											<% if(stayBeginDate != null){ %>
												<td data-sort="<%=(stayBeginDate != null)?dateFormat.format(stayBeginDate):"" %>"><%=dateFormat.format(stayBeginDate) %></td>
												<% }else{ %>
												<td><%= TextUtil.printDate(stayBeginDate) %></td>
												<%} %>
											<% if(stayEndDate != null){ %>
												<td data-sort="<%=(stayEndDate != null)?dateFormat.format(stayEndDate):"" %>"><%=dateFormat.format(stayEndDate) %></td>
												<% }else{ %>
												<td><%= TextUtil.printDate(stayEndDate) %></td>
												<%} %>
											<td><%=TextUtil.print(stayReason) %></td>
											<td><%=TextUtil.print(judge.toString()) %></td>
											<td><%=TextUtil.print(commissioner.toString()) %></td>
										</tr>
										<% }
										}
									 %>
						</tbody>
					</table>
					<br/>
					<br/>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='mb-1 col-24'>
							<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="closeBtn" onclick="appUX.pop.getSelfHandle().close();">Close</button>
							<div class="btn-group float-right" role="group">
								<button id="btnEmail" type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Email As</button>
								<div class="dropdown-menu" aria-labelledby="btnEmail" x-placement="bottom-start"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
									<a class="dropdown-item" href="#" onclick="emailReport('pdf');">PDF</a> 
									<a class="dropdown-item" href="#" onclick="emailReport('excel');">Excel</a>
								</div>
							</div>
							<div class="btn-group float-right" role="group">
								<button id="btnSave" type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Save As</button>
								<div class="dropdown-menu" aria-labelledby="btnSave" x-placement="bottom-start"
									style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
									<a class="dropdown-item" href="#" onclick="saveReport('pdf');">PDF</a> 
									<a class="dropdown-item" href="#" onclick="saveReport('excel');">Excel</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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
	<script src="/CorisWEB/js/corisReportCaseStayLookup.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script>
		function loadLocation(courtType){
			$('#locnCode').find('option').remove();
			$('#judgeCommId').val('');
			if(courtType == "J"){
				$('#locnCode').append('<%= justiceLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			} else if(courtType == "D") {
				$('#locnCode').append('<%= districtLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			}
			changeJudgeComm();
		}
		
		function validateForm() {
			$('#stayDateFrom').removeClass("is-invalid is-valid");
			$('#stayDateTo').removeClass("is-invalid is-valid");
			var valid = true;
			var stayBeginFrom = $('#stayDateFrom').val();
			var stayBeginTo = $('#stayDateTo').val();
			var message = "";
			if(stayBeginFrom == null ||  stayBeginFrom == ""){
				valid = false;
				message = "Stay From Date is Required";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateFrom').removeClass("is-valid").addClass("is-invalid");
			} else if(stayBeginTo == null ||  stayBeginTo == ""){
				valid = false;
				message = "Stay To Date is Required";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			
			var tempStayBeginFrom = new Date(stayBeginFrom);
			var tempStayBeginTo = new Date(stayBeginTo);
			if(tempStayBeginFrom > tempStayBeginTo){
				valid = false;
				message = "'To' date must be greater than 'From' date.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			var maxStayBeginToDate = tempStayBeginFrom.setFullYear(tempStayBeginFrom.getFullYear() + 1);
			if(tempStayBeginTo > maxStayBeginToDate){
				valid = false;
				message = "Date Searches must be within a year";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			submitForm(valid, message);
		}
		
		function submitForm(valid, message) {
			if (valid) {
				$('#loadingContainer').show();
				$('#searchCaseStayForm').submit();
			}
		}
		
		function saveReport(fmt){
			var dataTable = $('#resultsTable').DataTable();
			var order = dataTable.order();
			window.open("/CorisWEB/CorisReportCaseStayLookupServlet?mode=save&format="+fmt+"&locnCode=<%=locnCode%>&courtType=<%=courtType%>&" + $('#searchCaseStayForm').serialize());
			
		}
		
		function emailReport(fmt){
			var url = '/CorisWEB/CaseStayLookupServlet?mode=email&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=' + fmt;
			appUX.ajax.call(url, 
				function (err, data, xhr) {
					var jsonObj = JSON.parse(data);
					if(jsonObj && jsonObj.errorMessage){
						appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
					}else {
						appUX.pop.declare("Success", "Report Emailed successfully", 400, 'auto', appUX.pop.styles.SUCCESS);
					}
				}, 
				'POST', 
				'&' + $('#searchCaseStayForm').serialize()
			);
		}
		
		function changeJudgeComm(){
			var courtType = $('#courtType').val();
			var locnCode = $('#locnCode').val();
			appUX.ajax.call("/CorisWEB/CorisReportCaseStayLookupServlet",
			function (err, data, xhr) {
				var $judgeCommDropdown = $("#judgeCommId");
				$judgeCommDropdown.empty();
				$judgeCommDropdown.append("<option value=''>All Judges/Commissioners</option>")
				
				var jsonObj = JSON.parse(data);
				for (var i=0; i < jsonObj.judgeCommList.length; i++) {
				 var lastName = jsonObj.judgeCommList[i].lastName;
				 var firstName = jsonObj.judgeCommList[i].firstName;
				 var useridSrl = jsonObj.judgeCommList[i].useridSrl;
				 var type = jsonObj.judgeCommList[i].type;
				 var selected='';
				 if(<%=rptCriteria.getJudgeCommId()%> == useridSrl){
				 	selected='selected';
				 }
				 $judgeCommDropdown.append("<option value='"+ useridSrl + "' " + selected + " >" + lastName + ", " + firstName + " (" + type + ")" +"</option>");
				}
			
			},
			'POST',
			'mode=changeJudgeList&courtType=' + courtType + '&locnCode=' + locnCode
			);
		}
		
		//this is to display the Case History All
		function displayCaseHistory(encryptedUrl){
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
		
		function clearForm(){
			$('#stayDateFrom').val("");
			$('#stayDateTo').val("");
			$('#caseType').val("");
			$('#stayReason').val("");
			$('#judgeCommId').val("");
		}
	</script>
</body>
</html>
<%

%>