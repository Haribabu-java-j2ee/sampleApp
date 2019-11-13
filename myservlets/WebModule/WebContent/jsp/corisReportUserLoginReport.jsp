<%@page import="gov.utcourts.corisweb.report.CorisReportUserLoginReportCriteria"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>  
<%
	CorisReportUserLoginReportCriteria rptCriteria = request.getAttribute("myCriteria") != null ? (CorisReportUserLoginReportCriteria)request.getAttribute("myCriteria") : null;
	String courtType = (String) request.getAttribute("courtType");
	String locnCode = (String) request.getAttribute("locnCode");
	String status = (String) request.getAttribute("status");
	Date loginFrom = (Date) request.getAttribute("loginFrom");
	Date loginTo = (Date) request.getAttribute("loginTo");
	String logName = (String) request.getAttribute("logName");
	
	if (rptCriteria != null) {
		courtType = rptCriteria.getCourtType();
		locnCode = rptCriteria.getLocnCode();
		status = rptCriteria.getStatus();
		loginFrom = rptCriteria.getLoginFrom();
		loginTo = rptCriteria.getLoginTo();
		logName = rptCriteria.getLogName();
	}
	
	List<LocationBO> distLocationList = (List<LocationBO>) request.getAttribute("distLocationList");
	List<LocationBO> justLocationList = (List<LocationBO>) request.getAttribute("justLocationList");
	List<LocationBO> allLocationList = (List<LocationBO>) request.getAttribute("locationList");
	List<PersonnelBO> personnelList = (List<PersonnelBO>) request.getAttribute("personnelList");
	List<PersonnelBO> userLoginList = (List<PersonnelBO>) request.getAttribute("userLoginList");
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
					<strong>Search By</strong>
				</div>
				<div class="card-body">
					<form id="userLoginSearchForm" name="userLoginSearchForm">
					<input type = "hidden" name="mode" value = "searchUserLogin">
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
										<select class="form-control" id="locnCode" name="locnCode" required onchange="changePersonnel()">
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
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Active/Inactive <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="status" name="status" onchange="changePersonnel()">
											<option value="A" >All</option>
											<option value="N" <%=("N".equals(status))?"selected":"" %>>Active</option>
											<option value="Y" <%=("Y".equals(status))?"selected":"" %>>Inactive</option>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Users <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="logName" name="logName">
											 <option value = "">All Users</option>
											 <% for(PersonnelBO temp : personnelList) {%>
											 	<option value="<%= temp.get(PersonnelBO.LOGNAME)%>" <%=temp.get(PersonnelBO.LOGNAME).equals(logName) ? "selected" : ""  %>><%= temp.get(PersonnelBO.LASTNAME) + ", " + temp.get(PersonnelBO.FIRSTNAME) + " (" + temp.get(PersonnelBO.LOGNAME) + ")"%></option>
											 <% } %>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>From/To</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="loginFrom" name="loginFrom" value="<%=(TextUtil.printDate(loginFrom))%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="loginTo" name="loginTo" value="<%=(TextUtil.printDate(loginTo))%>">
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
								<% int colspan5 = 5; %>
								<th>Last Name</th>
								<th>First Name</th>
								<th>Log Name</th>
								<th>Date/Time</th>
								<th>Location</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (userLoginList != null) {
									String courtTitle = "";
									for(PersonnelBO personnelBO : userLoginList){
										courtTitle = (String) personnelBO.get(ProfileBO.COURTTITLE);
							 %>
						
							<tr>
								<td><%=TextUtil.print(personnelBO.getLastName()) %></td>
								<td><%=TextUtil.print(personnelBO.getFirstName()) %></td>
								<td><%=TextUtil.print(personnelBO.getLogname()) %></td>
								<td><%=TextUtil.printDatetime(personnelBO.getLastLogin()) %></td>
								<td><%=TextUtil.print(courtTitle) %></td>
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
	
	<script>
		$(document).ready(function(){
			$('#loadingContainer').hide();
			$('#resultsTable').DataTable( {
		    	"retrieve": true,
		    	"stateSave": true,
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
		        "columnDefs": [
    				{ "type": "date", "targets": 2},
    				{ "type": "date", "targets": 3}
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
			changePersonnel();
			$('#loadingContainer').hide();
		});
		
		function submitForm(valid, message) {
			if (valid) {
				$('#loadingContainer').show();
				$('#userLoginSearchForm').submit();
			}
		}
		
		function validateForm() {
			$('#loginFrom').removeClass("is-invalid is-valid");
			$('#loginTo').removeClass("is-invalid is-valid");
			var valid = true;
			var message = "";
			var loginFrom = $('#loginFrom').val();
			var loginTo = $('#loginTo').val();
			if (loginFrom == null || loginFrom == "") {
				valid = false;
				message = "Login From Date is Required";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#loginFrom').removeClass("is-valid").addClass("is-invalid");
			}
			if (loginTo == null || loginTo == "") {
				valid = false;
				message = "Login To Date is Required";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#loginTo').removeClass("is-valid").addClass("is-invalid");
			}
			submitForm(valid, message);
		}
		
		function submitForm(valid, message) {
			if (valid) {
				$('#loadingContainer').show();
				$('#userLoginSearchForm').submit();
			}
		}
		
		function saveReport(fmt){
			var dataTable = $('#resultsTable').DataTable();
			var order = dataTable.order();
			window.open("/CorisWEB/CorisReportUserLoginReportServlet?mode=save&format="+fmt+"&locnCode=<%=locnCode%>&courtType=<%=courtType%>&" + $('#userLoginSearchForm').serialize());
			
		}
		
		function loadLocation(courtType){
			$('#locnCode').find('option').remove();
			$('#judgeComm').val('');
			if(courtType == "J"){
				$('#locnCode').append("<option value = null>All Locations</option>")
				$('#locnCode').append('<%= justiceLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			} else if(courtType == "D") {
				$('#locnCode').append("<option value = null>All Locations</option>")
				$('#locnCode').append('<%= districtLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			}
		}
		
		function emailReport(fmt){
			var url = '/CorisWEB/CorisReportUserLoginReportServlet?mode=email&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=' + fmt;
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
		
		function changePersonnel(){
			$('#loadingContainer').show();
			var courtType = $('#courtType').val();
			var locnCode = $('#locnCode').val();
			var status = $('#status').val();
			appUX.ajax.call("/CorisWEB/CorisReportUserLoginReportServlet",
			function (err, data, xhr) {
				var $logNameDropdown = $("#logName");
				$logNameDropdown.empty();
				$logNameDropdown.append("<option value=null>All Personnel</option>")
				
				var jsonObj = JSON.parse(data);
				for (var i=0; i < jsonObj.personnelList.length; i++) {
				 var lastName = jsonObj.personnelList[i].lastName;
				 var firstName = jsonObj.personnelList[i].firstName;
				 var selectedLogName = jsonObj.personnelList[i].selectedLogName;
				 var logName = jsonObj.personnelList[i].logName;
				 var selected='';
			  	if ('<%=rptCriteria.getSelectedLogName()%>' == selectedLogName){
				 	selected = 'selected';
				 }
				 $logNameDropdown.append("<option value='"+ logName + "' " + selected + " >" + lastName + ", " + firstName + " (" + logName + ")"+"</option>");
				}
			
			},
			'POST',
			'mode=changePersonnelList&courtType=' + courtType + '&locnCode=' + locnCode + '&status=' + status
			);
			$('#loadingContainer').hide();
		}
		
		function clearForm(){
			$('#status').val("null");
			$('#logName').val("null");
			$('#loginFrom').val("");
			$('#loginTo').val("");
		}
	</script>
</body>
</html>