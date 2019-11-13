<%@page import="gov.utcourts.corisweb.report.CorisReportLateFilingReportCriteria"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.report.CorisAttorneyCasesReportCriteria"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.charge.ChargeBO" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>  
<%@page import="gov.utcourts.notifications.util.CalendarUtil"%>

<%
	CorisReportLateFilingReportCriteria rptCriteria = request.getAttribute("myCriteria") != null ? (CorisReportLateFilingReportCriteria)request.getAttribute("myCriteria") : null;
	String courtType = (String) request.getAttribute("courtType");
	String locnCode = (String) request.getAttribute("locnCode");
	Date caseFilingFrom = (Date) request.getAttribute("caseFilingFrom");
	Date caseFilingTo = (Date) request.getAttribute("caseFilingTo");
	Date violationDateFrom = (Date) request.getAttribute("violationDateFrom");
	Date violationDateTo = (Date) request.getAttribute("violationDateTo");
	String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
	
	if (rptCriteria != null) {
		courtType = rptCriteria.getCourtType();
		locnCode = rptCriteria.getLocnCode();
		caseFilingFrom = rptCriteria.getCaseFilingFrom();
		caseFilingTo = rptCriteria.getCaseFilingTo();
		violationDateFrom = rptCriteria.getViolationDateFrom();
		violationDateTo = rptCriteria.getViolationDateTo();
	}
	URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisReportLateFilingServlet");
	List<LocationBO> distLocationList = (List<LocationBO>) request.getAttribute("distLocationList");
	List<LocationBO> justLocationList = (List<LocationBO>) request.getAttribute("justLocationList");
	List<LocationBO> allLocationList = (List<LocationBO>) request.getAttribute("locationList");
	List<KaseBO> lateCaseFilingList = (List<KaseBO>) request.getAttribute("lateCaseFilingList");
	
	
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
					<span><strong>Late Filing Report</strong></span>
				</div>
				
				<div class="card-header">
					<strong>Filter By</strong>
				</div>
				
				<div class="card-body">
					<form id="lateFilingForm" name="lateFilingForm">
					<input type = "hidden" name="mode" value = "searchLateFiling">
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
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Locations<span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="locnCode" name="locnCode" required>
											<% if("D".equals(courtType) && distLocationList != null){
													%>
													<option value = null>All Locations</option>
													<%
												for(LocationBO temp : distLocationList){
													%>
											 	<option value="<%= temp.get(LocationBO.LOCNCODE) %>" <%=temp.get(LocationBO.LOCNCODE).equals(locnCode) ? "selected" : ""  %>><%= temp.get(LocationBO.LOCNDESCR) %></option>
													<% 
													}
												} else if("J".equals(courtType) && justLocationList != null){
													%>
													<option value = null>All Locations</option>
													<%
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
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Case Filing Date From/To</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="caseFilingFrom" name="caseFilingFrom" value="<%=(TextUtil.printDate(caseFilingFrom))%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="caseFilingTo" name="caseFilingTo" value="<%=(TextUtil.printDate(caseFilingTo))%>">
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
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Violation Date From/To</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="violationDateFrom" name="violationDateFrom" value="<%=(TextUtil.printDate(violationDateFrom))%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="violationDateTo" name="violationDateTo" value="<%=(TextUtil.printDate(violationDateTo))%>">
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
							<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onclick="clearForm()">Clear</button>
								<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn" onclick="validateForm()">Search</button>
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
								<th>Case Number</th>
								<th>Name</th>
								<th>Citation</th>
								<th>Case Filed Date</th>
								<th>Violation Date</th>
								<th>Difference</th>
							</tr>
						</thead>
						<tbody>
							<% 
								if (lateCaseFilingList != null) {
									Date filingDate = null;
									Date violationDate = null;
									for (KaseBO kaseBO : lateCaseFilingList) {
										String partyName = (String) kaseBO.get(PartyBO.LASTNAME);
										if(!TextUtil.isEmpty((String) kaseBO.get(PartyBO.FIRSTNAME))){
													partyName += ", " + kaseBO.get(PartyBO.FIRSTNAME);
											}
							%>
								<tr>
									<td>
										<a href="javascript:void(0);" title="View Case History All" onclick="displayCaseHistory('<%= CaseHistoryAllUtil.getEncryptedUrl(kaseBO.getCaseNum(), rptCriteria.getCourtType(), rptCriteria.getLocnCode(), rptCriteria.getLogName()) %>');">
														<%=TextUtil.print(kaseBO.getCaseNum()) %>									
										</a>
									</td>
									<td><%=TextUtil.print(partyName) %></td>
									<td><%=TextUtil.print(kaseBO.get(CrimCaseBO.CITNUM)) %></td>
									<td><%=TextUtil.printDate(kaseBO.getFilingDate()) %></td>
									<td><%=TextUtil.printDate((Date)kaseBO.get(ChargeBO.VIOLDATETIME)) %></td>
									<td><%=Math.round(CalendarUtil.diffDays(kaseBO.getFilingDate(), (Date) kaseBO.get(ChargeBO.VIOLDATETIME))) %></td>
								</tr>
								<%
									}
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
	<script>
		$(document).ready(function(){
			$('#loadingContainer').hide();
		    $('#resultsTable').DataTable( {
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
		        "retrieve": true,
				"columnDefs": [
    				{ "type": "date", "targets": 3},
    				{ "type": "date", "targets": 4}
				]
		    } );
			$('#resultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
	
			$('[id^=datetimepicker]').datetimepicker({ 
				format: 'MM/DD/YYYY',			
				icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
			});
			
			$("svg").on( "mouseenter", function() { //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
				$(this).css("cursor", "pointer" );
			});
			$("svg").on( "mouseleave", function() {
				$(this).css("cursor", "default" );
			});
		});
		
		function validateForm() {
			$('#caseFilingFrom').removeClass("is-invalid is-valid");
			$('#caseFilingTo').removeClass("is-invalid is-valid");
			$('#violationDateFrom').removeClass("is-invalid is-valid");
			$('#violationDateTo').removeClass("is-invalid is-valid");
			var valid = true;
			var message = "";
			var caseFilingFrom = $('#caseFilingFrom').val();
			var caseFilingTo = $('#caseFilingTo').val();
			var violationDateFrom = $('#violationDateFrom').val();
			var violationDateTo = $('#violationDateTo').val();
			if (((caseFilingFrom == null || caseFilingFrom == "") && (caseFilingTo == null || caseFilingTo == "")) && ((violationDateFrom == null || violationDateFrom == "") && (violationDateTo == null || violationDateTo == ""))) {
				valid = false;
				message = "Date search range required: Please enter either a Filing Date or Violation Date";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			} else if ((caseFilingFrom != null && (caseFilingTo == null || caseFilingTo == "")) && violationDateFrom != null && (violationDateTo == null || violationDateTo == "")) {
				valid = false;
				message = "Please enter a To: date within 1 year";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			}
			var tempCaseFilingFrom = new Date(caseFilingFrom);
			var tempCaseFilingTo = new Date(caseFilingTo);
			if(tempCaseFilingFrom > tempCaseFilingTo){
				valid = false;
				message = "'To' date must be greater than 'From' date.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			var maxCaseFilingToDate = tempCaseFilingFrom.setFullYear(tempCaseFilingFrom.getFullYear() + 1);
			if(tempCaseFilingTo > maxCaseFilingToDate){
				valid = false;
				message = "Date Searches must be within a year";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			var tempViolationDateFrom = new Date(violationDateFrom);
			var tempViolationDateTo = new Date(violationDateTo);
			if(tempViolationDateFrom > tempViolationDateTo){
				valid = false;
				message = "'To' date must be greater than 'From' date.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			var maxViolationToDate = tempViolationDateFrom.setFullYear(tempViolationDateFrom.getFullYear() + 1);
			if(tempViolationDateTo > maxViolationToDate){
				valid = false;
				message = "Date Searches must be within a year";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#stayDateTo').removeClass("is-valid").addClass("is-invalid");
			}
			submitForm(valid, message)
		}
		
		function submitForm(valid,message) {
			if (valid) {
				$('#loadingContainer').show();
				$('#lateFilingForm').submit();	
			}
		}
		
		function saveReport(fmt){
			var dataTable = $('#resultsTable').DataTable();
			var order = dataTable.order();
			window.open("/CorisWEB/CorisReportLateFilingServlet?mode=save&format="+fmt+"&locnCode=<%=locnCode%>&courtType=<%=courtType%>&" + $('#lateFilingForm').serialize());
			
		}
		
		function emailReport(fmt){
			var url = '/CorisWEB/CorisReportLateFilingServlet?mode=email&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=' + fmt;
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
				'&' + $('#userLoginSearchForm').serialize()
			);
		}
		
		function loadLocation(courtType){
			$('#locnCode').find('option').remove();
			$('#judgeComm').val('');
			if(courtType == "J"){
				$('#locnCode').append('<%= justiceLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			} else if(courtType == "D") {
				$('#locnCode').append('<%= districtLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			}
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
			$('#caseFilingDateFrom').val("");
			$('#caseFilingDateTo').val("");
			$('#violationDateFrom').val("");
			$('#violationDateTo').val("");
		}
		
	</script>
</body>
</html>
<%

%>