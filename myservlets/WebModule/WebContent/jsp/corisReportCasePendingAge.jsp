
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.trackingtype.TrackingTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="gov.utcourts.coriscommon.util.DateUtil"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%> 
<%@page import="gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria"%>
<%@page import="gov.utcourts.corisweb.servlet.CorisReportCasePendingAgeServlet"%>
<%
	String courtType = (String) request.getAttribute("courtType");
	if (TextUtil.isEmpty(courtType))
		courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);  	// default to the user's courtType
		
	String locnCode = (String) request.getAttribute("locnCode");
	if (TextUtil.isEmpty(locnCode))
		locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE);  	// default to the user's locnCode
	
	String includeCases = URLEncryption.getParamAsString(request, "includeCases");
	boolean includeUnassigned = URLEncryption.getParamAsBoolean(request, "includeUnassigned");
	Date pendingDate = URLEncryption.getParamAsDate(request, "pendingDate");
	
	StringBuilder districtLocations = new StringBuilder();
	List<LocationBO> distLocationList = (List<LocationBO>) request.getAttribute("distLocationList");
	if (distLocationList != null){
		for (LocationBO distLocation : distLocationList) {
			districtLocations.append("<option value=\"")
			.append(distLocation.get(LocationBO.LOCNCODE))
			.append(distLocation.get(LocationBO.LOCNCODE).equals(locnCode) ? " \"selected" : "")
			.append("\">")
			.append(distLocation.get(LocationBO.LOCNDESCR))
			.append("</option>");
		}
	}
	String districtLocationOptions = districtLocations.toString();
	districtLocations = null;
	
	StringBuilder justiceLocations = new StringBuilder();
	List<LocationBO> justLocationList = (List<LocationBO>) request.getAttribute("justLocationList");
	if (justLocationList != null) {
		for (LocationBO justLocation : justLocationList) {
			justiceLocations.append("<option value=\"")
			.append(justLocation.get(LocationBO.LOCNCODE))
			.append(justLocation.get(LocationBO.LOCNCODE).equals(locnCode) ? "selected" : "")
			.append("\">").append(justLocation.get(LocationBO.LOCNDESCR))
			.append("</option>");
		}
	}
	String justiceLocationOptions = justiceLocations.toString();
	justiceLocations = null;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Age of Pending Caseload Report</title>
    
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
    
	<main>
		<div class="container-fluid">
			
			<div class="card m-2">
					
				<div class="card-header">
					<strong>Search By</strong>
				</div>
				
				<div class="card-body">
				
					<form id="searchForm" name="searchForm">
						<div class="form-row">
							<div class="form-group col-md-15 col-lg-12">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases Pending as of Date</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="pendingDate" name="pendingDate" value = "<%= TextUtil.printDate(pendingDate) %>">
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
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="m_active" name="m_active">
											<option value="A" <%= ("all".equals(includeCases)) ? "selected" : "" %>>All Cases</option>
											<option value="V" <%= ("active".equals(includeCases)) ? "selected" : "" %>>Active Cases Only</option>
											<option value="I" <%= ("inactive".equals(includeCases)) ? "selected" : "" %>>Inactive Cases Only</option>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group col-md-12 col-lg-12">
								<div class="row align-items-center">
									<div class="col-sm-11 col-md-12">
										<input id="includeUnassigned" name="includeUnassigned" type="checkbox" <%= includeUnassigned ? "checked" : ""%>/>
										<label for="includeUnassigned" class="control-label text-lg-left"><strong>Include Unassigned Cases</strong></label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-8 col-lg-8">
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
							
							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<label class="control-label text-sm-right"><strong>Location <span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="locnCode" name="locnCode" onchange="changeJudgeComm();">
											<%= districtLocationOptions %>
											<%= justiceLocationOptions %>
										</select>
									</div>
								</div>
							</div>
						
							<div class="form-group col-md-8 col-lg-8">
								<div class="row align-items-center">
									<label class="control-label text-lg-right"><strong>Judge/Commissioner:<span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="judgeComm" name="judgeComm" multiple></select>
									</div>
								</div>
							</div>
							
						</div>
						
					</form>
				</div>
				
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<div class='mb-1 col-24'>
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="closeBtn" onclick="appUX.pop.getSelfHandle().close();">Close</button>
						<div class="btn-group float-right" role="group">
							<button id="btnEmail" type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Email As</button>
							<div class="dropdown-menu" aria-labelledby="btnEmail" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
								<a class="dropdown-item" href="#" onclick="emailAs('pdf');">PDF</a> 
								<a class="dropdown-item" href="#" onclick="emailAs('xlsx');">Excel</a>
							</div>
						</div>
						<div class="btn-group float-right" role="group">
							<button id="btnSave" type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Save As</button>
							<div class="dropdown-menu" aria-labelledby="btnSave" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
								<a class="dropdown-item" href="#" onclick="saveAs('pdf');">PDF</a> 
								<a class="dropdown-item" href="#" onclick="saveAs('xlsx');">Excel</a>
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
	
	<script src="/CorisWEB/js/forge.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/coris-common.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script>
		
		$(document).ready(function() {
			
			$('[id^=datetimepicker]').datetimepicker({
				format: 'MM/DD/YYYY',
				icons: {
					previous: "fa fa-angle-left",
					next: "fa fa-angle-right"
				}
			});
			
			$('#searchResultsTable').DataTable( {
		    	"retrieve": true,
		    	"paging": true,
		    	"info": true,
		    	"searching": true,
		        "lengthMenu": [[100, 50, 10, 1], [100, 50, 10, 1]]
		    } );
			
			changeJudgeComm();
			
			$('#loadingContainer').hide();
			
		}); 
		
		function closePop() {
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}

		function loadLocation(courtType){
			$('#locnCode').find('option').remove();
			$('#judgeComm').val('');
			if (courtType == "J") {
				$('#locnCode').append('<%= justiceLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			} else if (courtType == "D") {
				$('#locnCode').append('<%= districtLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			}
			changeJudgeComm();
		}
		
		function changeJudgeComm() {
			$("#judgeComm").val('');
			
			var courtType = $('#courtType').val();
			var locnCode = $('#locnCode').val();
			appUX.ajax.call("/CorisWEB/CorisReportCasePendingAgeServlet",
				function (err, data, xhr) {
					var $judgeCommDropdown = $("#judgeComm");
					$judgeCommDropdown.empty();
					$judgeCommDropdown.append("<option value=''>All Judges/Commissioners</option>")
					
					var jsonObj = JSON.parse(data);
					for (var i=0; i < jsonObj.judgeCommList.length; i++) {
					 	var lastName = jsonObj.judgeCommList[i].lastName;
					 	var firstName = jsonObj.judgeCommList[i].firstName;
					 	var logName = jsonObj.judgeCommList[i].logName;
					 	var useridSrl = jsonObj.judgeCommList[i].useridSrl;
					 	var type = jsonObj.judgeCommList[i].type;
					 	var judgeName = lastName;
					 	if (firstName) {
					 		judgeName += ", " + firstName;
					 	}
					 	$judgeCommDropdown.append("<option value='" + type + useridSrl + "'>" + judgeName + " (" + type + ")</option>");
					}
				
				},
				'POST',
				'mode=displaysearchform&courtType=' + courtType + '&locnCode=' + locnCode
			);
		}
		
		function isValid() {
			var validationError = '';
			if ($('#pendingDate').val() == '') {
				validationError = "Cases Pending as of Date must have a valid date."
			}
			if (validationError == '') {
				return true;
			} else {
				appUX.pop.declare("Age of Pending Caseload Report", validationError, 300, 'auto', appUX.pop.styles.DANGER);
				
				return false;
			}
		}
		
		function saveAs(format) {
			if (isValid()) {
				var multipleJudges = $('#judgeComm').val();
				windowPpenUrlEncrypt('/CorisWEB/CorisReportCasePendingAgeServlet', 'mode=save&format=' + format + '&multipleJudges=' + multipleJudges + '&' + $('#searchForm').serialize());
			}
		}
		
		function emailAs(format) {
			if (isValid()) {
				var table = $('#searchResultsTable').DataTable();
				var orderBy = table.order();
				
				appUX.ajax.call("/CorisWEB/CorisReportCasePendingAgeServlet",
					function (err, data, xhr) {
						var jsonObj = JSON.parse(data); 
						if (!jsonObj.success) {
							appUX.pop.declare("Email", "Email could not be sent. " + jsonObj.errorMessage, 300, 'auto', appUX.pop.styles.DANGER);					
						} else {
						 	appUX.pop.declare("Email", "Email will be delivered shortly", 300, 'auto', appUX.pop.styles.SUCCESS);
						}
					},
					'POST',
					'mode=email&format=' + format + "&orderBy=" + JSON.stringify(orderBy) + '&' + $('#searchForm').serialize()
				);
			}
		}
		
	</script>
</body>
</html>