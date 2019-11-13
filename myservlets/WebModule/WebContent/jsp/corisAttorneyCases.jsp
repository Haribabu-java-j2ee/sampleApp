
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
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>  
<%
	
	String errorMessage = (String) request.getAttribute("errorMessage");
	CorisAttorneyCasesReportCriteria rptCriteria = request.getAttribute("myCriteria") != null ? (CorisAttorneyCasesReportCriteria)request.getAttribute("myCriteria") : null;
	SimpleDateFormat dateFormat = Constants.dateFormatCoris;
	String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
	List<AttyPartyBO> caseList = (List<AttyPartyBO>) request.getAttribute("caseList");
	List<AttorneyBO> attorneyName = (List<AttorneyBO>) request.getAttribute("attorneyName");
	String courtType = (String) request.getAttribute("courtType");
	String locnCode = (String) request.getAttribute("locnCode");
  	int barNum = TextUtil.getParamAsInt(request, "barNum");
	String barState = TextUtil.getParamAsString(request, "barState");
	String caseNum = TextUtil.getParamAsString(request, "caseNum");
	Boolean includeDetached;
	Date attachDatetimeStart = null;
	if(request.getAttribute("attachDatetimeStart")  != null){
		attachDatetimeStart = (Date) request.getAttribute("attachDatetimeStart");
	};
	Date attachDatetimeEnd = null;
		if(request.getAttribute("attachDatetimeEnd")  != null){
			attachDatetimeEnd = (Date) request.getAttribute("attachDatetimeEnd");
		}
	Date caseFiledFrom = null;
		if(request.getAttribute("caseFiledFrom") != null){
			caseFiledFrom = (Date) request.getAttribute("caseFiledFrom");
		}
	Date caseFiledTo = null;
		if(request.getAttribute("caseFiledTo") != null){
			caseFiledTo = (Date) request.getAttribute("caseFiledTo");
		}
	if (rptCriteria != null) {
		courtType = rptCriteria.getCourtType();
		locnCode = rptCriteria.getLocnCode();
		caseNum = rptCriteria.getCaseNum();
		attachDatetimeStart = rptCriteria.getAttachDatetimeStart();
		attachDatetimeEnd = rptCriteria.getAttachDatetimeEnd();
		caseFiledFrom = rptCriteria.getCasesFiledFrom();
		caseFiledTo = rptCriteria.getCasesFiledTo();
		includeDetached = rptCriteria.isIncludeDetached();
		
	}
	List<LocationBO> distLocationList = (List<LocationBO>) request.getAttribute("distLocationList");
	List<LocationBO> justLocationList = (List<LocationBO>) request.getAttribute("justLocationList");
	List<AttyPartyBO> attorneyCasesList = (List<AttyPartyBO>) request.getAttribute("attorneyCasesList");
	
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
	
	URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyCasesServlet");

	String paramsViewPDF = "mode=getPDF";
	String paramsEmailPDF = "mode=email";
	String paramsAttorneyDetach = "mode=detach";
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
			
			<% if (!TextUtil.isEmpty(TextUtil.print(errorMessage))) { %>
			<div class="alert alert-danger text-center m-2"><%=TextUtil.print(errorMessage) %></div>
			<% } %>

			<div class="card m-2">
				
				<div class="card-header">
					<% for(AttorneyBO attorneyBO : attorneyName){
										String attorney = (String) attorneyBO.get(AttorneyBO.FIRSTNAME);
										if(!TextUtil.isEmpty((String) attorneyBO.get(AttorneyBO.LASTNAME))){
												attorney += " " + attorneyBO.get(AttorneyBO.LASTNAME);
										}
				 	%>	
					<span><strong>Cases for Attorney:</strong> <%=attorney %></span> <span class="float-right"><strong>Bar Number / State:</strong> <%=barNum %> / <%=barState %></span>
					<% } %>
				</div>
				
				<div class="card-header">
					<strong>Filter By</strong>
				</div>
				
				<div class="card-body">
					<form id="searchCaseForm" name="searchCaseForm">
					<input type = "hidden" name="mode" value = "searchAttorneyCases">
					<input type = "hidden" name= "barNum" value = "<%=barNum %>">
					<input type = "hidden" name= "barState" value = "<%=barState %>">
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
										<select class="form-control" id="locnCode" name="locnCode" required>
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
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Case Number</strong></label>
									<div class="col-sm-11 col-md-12">
										<input type="text" class="form-control" id="caseNum" name="caseNum" value="<%=(TextUtil.print(caseNum)) %>" maxlength="9">
									</div>
								</div>
							</div>
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Attach Date Start/End</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="attachDatetimeStart" name="attachDatetimeStart" value="<%=(TextUtil.printDate(attachDatetimeStart))%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="attachDatetimeEnd" name="attachDatetimeEnd" value="<%=(TextUtil.printDate(attachDatetimeEnd))%>">
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
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases Filed From/To</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="caseFiledFrom" name="caseFiledFrom" value="<%=(TextUtil.printDate(caseFiledFrom))%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="caseFiledTo" name="caseFiledTo" value="<%=(TextUtil.printDate(caseFiledTo))%>">
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
									<div class="col-sm-11 col-md-12 offset-sm-8 offset-md-9">
										<div class="form-check form-check-inline">
											<input type="checkbox" class="form-check-input" id="checkboxIncludeDetached" name="checkboxIncludeDetached" <%=(rptCriteria.isIncludeDetached())?"checked":"" %>>
											<label class="form-check-label"><strong>Include Detached</strong></label>
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
								<% int colspan5 = 9; //keep this updated as table changes so that the last column is not orderable %>
								<th>Case Number</th>
								<th>Case Type</th>
								<th>Party Type</th>
								<th>Party Name</th>
								<th>Assigned Judge</th>
								<th>Case Filing Date</th>
								<th>Attach Date</th>
								<th>Detach Date</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<% 
								if(attorneyCasesList != null){ 
								int attyPartyId = 0;
								String firstName = "";
								String lastName = "";
								Date caseFilingDate = null;
								Date attachDatetime = null;
								Date detachDatetime = null;
								
								
									for(AttyPartyBO attyPartyBO : attorneyCasesList){
										firstName = (String) attyPartyBO.get(AttorneyBO.FIRSTNAME);
										lastName = (String) attyPartyBO.get(AttorneyBO.LASTNAME);
										String caseType = (String) attyPartyBO.get(KaseBO.CASETYPE);
										attyPartyId = (Integer) attyPartyBO.get(AttyPartyBO.ATTYPARTYID);
										String attyCaseNum = (String) attyPartyBO.get(KaseBO.CASENUM);
										String partyName = (String) attyPartyBO.get(PartyBO.LASTNAME);
										String judgeFirstName = (String) attyPartyBO.get(PersonnelBO.FIRSTNAME);
										String judge = (String) attyPartyBO.get(PersonnelBO.FIRSTNAME);
										caseFilingDate = (Date) attyPartyBO.get(KaseBO.FILINGDATE);
										attachDatetime = (Date) attyPartyBO.get(AttyPartyBO.ATTACHDATETIME);
										detachDatetime = (Date) attyPartyBO.get(AttyPartyBO.DETACHDATETIME);
										if(!TextUtil.isEmpty((String) attyPartyBO.get(PersonnelBO.FIRSTNAME))){
												judge += " " + attyPartyBO.get(PersonnelBO.LASTNAME);
										}
										if(!TextUtil.isEmpty((String) attyPartyBO.get(PartyBO.FIRSTNAME))){
												partyName += ", " + attyPartyBO.get(PartyBO.FIRSTNAME);
										}		
							%>
										<tr>
											<td>
												<a href="javascript:void(0);" title="View Case History All" onclick="displayCaseHistory('<%= CaseHistoryAllUtil.getEncryptedUrl(attyCaseNum, courtType, locnCode, logName) %>');">
													<%=TextUtil.print(attyCaseNum) %>									
												</a>
											</td>
											<td><%=TextUtil.print(caseType) %></td>
											<td><%=TextUtil.print(attyPartyBO.getPartyCode()) %></td>
											<td><%=TextUtil.print(partyName) %></td>
											<td><%=TextUtil.print(judge) %></td>
											<td><%=dateFormat.format(caseFilingDate) %></td>
											<% if(attachDatetime != null){ %>
											<td data-sort="<%=(attachDatetime != null)?dateFormat.format(attachDatetime):"" %>"><%=dateFormat.format(attachDatetime) %></td>
											<% }else{ %>
											<td><%= TextUtil.printDate(attachDatetime) %></td>
											<%} %>
											<% if(detachDatetime != null){ %>
											<td data-sort="<%=(detachDatetime != null)?dateFormat.format(detachDatetime):"" %>"><%=dateFormat.format(detachDatetime) %></td>
											<% }else{ %>
											<td><%= TextUtil.printDate(detachDatetime) %></td>
											<%} %>
											<td nowrap>
											<% if(attyPartyBO.getDetachDatetime() == null){ %>
												<i title="Detach Attorney from Case <%=attyCaseNum %>" onclick='attorneyDetach("<%=TextUtil.print(courtType)%>", "<%=locnCode%>", <%=attyCaseNum %>, <%=attyPartyId %>, $("#checkboxIncludeDetached").is(":checked"), $("#attachDatetimeStart").val(), $("#attachDatetimeEnd").val());' class="text-danger fas fa-user-slash fa-lg">
											<% }else{ %>
												<i title="Attorney is Detached" class="text-muted fas fa-user-slash fa-lg"></i>
											<% } %>
											</td>
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
    				{ "orderable": false, "targets": <%= colspan5 - 1 %> },
    				{ "type": "date", "targets": 5},
    				{ "type": "date", "targets": 6}
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
			
			<%
			if(attorneyCasesList.size() == Constants.MAX_RESULTS){
				%>
				var message = "Results exceed <%=Constants.MAX_RESULTS %>. Please use search to filter results.";
				appUX.pop.declare("Warning", message, 300, 'auto', appUX.pop.styles.WARNING);
				<%
			}
			%>
	
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function saveReport(fmt){
			var dataTable = $('#resultsTable').DataTable();
			var order = dataTable.order();
			window.open("/CorisWEB/CorisAttorneyCasesServlet?mode=save&format="+fmt+"&locnCode=<%=locnCode%>&courtType=<%=courtType%>&" + $('#searchCaseForm').serialize());
		}
		
		function emailReport(fmt){
			var url = '/CorisWEB/CorisAttorneyCasesServlet?mode=email&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=' + fmt;
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
				'&' + $('#searchCaseForm').serialize()
			);
		}
		
		function clearForm(){
			$('#caseNum').val("");
			$('#attachDatetimeStart').val("");
			$('#attachDatetimeEnd').val("");
			$('#caseFiledFrom').val("");
			$('#caseFiledTo').val("");
			$('#checkboxIncludeDetached').prop( "checked", false );
			$('#caseNum').focus();
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
		
		function validateForm() {
			$('#caseFiledFrom').removeClass("is-invalid is-valid");
			$('#caseFiledTo').removeClass("is-invalid is-valid");
			var valid = true;
			var caseFiledFrom = $('#caseFiledFrom').val();
			var caseFiledTo = $('#caseFiledTo').val();
			var message = "";
			if(caseFiledFrom == null ||  caseFiledFrom == ""){
				valid = false;
				message = "Case Filed From Date is Required";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#caseFiledFrom').removeClass("is-valid").addClass("is-invalid");
			} else if(caseFiledTo == null ||  caseFiledTo == ""){
				valid = false;
				message = "Case Filed To Date is Required";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#caseFiledTo').removeClass("is-valid").addClass("is-invalid");
			}
			
			var tempCaseFiledFrom = new Date(caseFiledFrom);
			var tempCaseFiledTo = new Date(caseFiledTo);
			if(tempCaseFiledFrom > tempCaseFiledTo){
				valid = false;
				message = "'To' date must be greater than 'From' date.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#caseFiledTo').removeClass("is-valid").addClass("is-invalid");
			}
			var maxCaseFiledToDate = tempCaseFiledFrom.setFullYear(tempCaseFiledFrom.getFullYear() + 3);
			if(tempCaseFiledTo > maxCaseFiledToDate){
				valid = false;
				message = "Date Searches must be within 3 years";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#caseFiledTo').removeClass("is-valid").addClass("is-invalid");
			}
			submitForm(valid, message);
		}
		
		function submitForm(valid, message) {
			if (valid) {
				$('#loadingContainer').show();
				$('#searchCaseForm').submit();
			}
		}
		
		var parentCornId = appUX.pop.getSelfHandle().id;
		
		function attorneyDetach(courtType, locnCode, attyCaseNum, attyPartyId, includeDetached, attachDatetimeStart, attachDatetimeEnd){
			var title = "Detach Attorney";
			var message = "Are you sure?";
			var trueText = "Detach";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.DANGER;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
			
			function confirmCallback(action) {
				corn.close();
				if (action) {
					appUX.ajax.call("<%=urlCryptor.urlEncrypt(paramsAttorneyDetach) %>", 
						function (err, data, xhr) { 
							var jsonObj = JSON.parse(data);
							if (err) {
								message = "There was an error. "+xhr.responseText;
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							}else if (jsonObj.error) {
								message = jsonObj.errorMessage;
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							} else {
								message = "Changes have been saved.";
								appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
								$('#searchCaseForm').submit();
								corn.close();
							}
						}, 
						'POST', 
						'&attyPartyId='+attyPartyId
					);
				}else{
					corn.close();
				}
			}
		}
		
		//this is to display the Case History All PDF
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
		
	</script>
</body>
</html>
<%

%>