<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
SimpleDateFormat dateFormat = Constants.dateFormatCoris;
String logName = (String) request.getAttribute("logName");
String locnCode = (String) request.getAttribute("locnCode");
String courtType = (String) request.getAttribute("courtType");
String startDate = (String) request.getAttribute("startDate");
String endDate = (String) request.getAttribute("endDate");
String startJournal = (String) request.getAttribute("startJournal");
String endJournal = (String) request.getAttribute("endJournal");
List<LocationBO> locationDistrictList = (List<LocationBO>) request.getAttribute("locationDistrictList");
List<LocationBO> locationJusticeList = (List<LocationBO>) request.getAttribute("locationJusticeList");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Unclaimed Property Report</title>
    
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<style>
		.cursor-pointer{
			cursor: pointer;
		}
	</style>
	
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
					<strong>Unclaimed Property Report</strong>
				</div>
				
				<div class="card-body">
					
					<form id="searchForm" name="searchForm" onsubmit="searchForm(event, false);">
					
						<div id="stateEndDateContent" class= "row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="row align-items-center">
										<label id="daterange" name="dateRange" class="col-sm-9 col-md-10 control-label text-sm-right"><strong>Journal Start/End Date<span class="text-danger">*</span></strong></label>
										<div class="col-sm-11 col-md-13">
											<div class="row">
												<div class="col input-group date" id="datetimepicker1">
													<input type="text" class="form-control" id="startDate" name="startDate" placeholder="mm/dd/yyyy" value="<%=(startDate != null)?startDate:""%>">
													<span class="input-group-text input-group-addon">
														<span class="fa fa-calendar"></span>
													</span>
												</div>
												<div class="col input-group date" id="datetimepicker2">
													<input type="text" class="form-control" id="endDate" name="endDate" placeholder="mm/dd/yyyy" onblur="validate()" value="<%=(endDate != null)?endDate:""%>">
													<span class="input-group-text input-group-addon">
														<span class="fa fa-calendar"></span>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<div class="row align-items-center">
										<label id="journalRange" name="journalRange" class="col-sm-9 col-md-10 control-label text-sm-right"><strong>Journal Start/End Number<span class="text-danger">*</span></strong></label>
										<div class="col-sm-11 col-md-13">
											<div class="row">
												<div class="col input-group " id="startJournal1">
													<input type="text" class="form-control" id="startJournal" name="startJournal" value="<%=(startJournal != null)?startJournal:""%>">
												</div>
												<div class="col input-group " id="startJournal2">
													<input type="text" class="form-control" id="endJournal" name="endJournal" value="<%=(endJournal != null)?endJournal:""%>" >
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div id="locncourtcontent" class= "row">				
							<div class="col-md-12">
								<div class="form-group">
									<div class="row align-items-center">
										<label class="col-sm-9 col-md-10 control-label text-sm-right"><strong>Court Type<span class="text-danger">*</span></strong></label>
										<div class="col-sm-11 col-md-13" >
											<select class="form-control" id="courtType" name="courtType" onchange="loadLocation($(this).val());" required>
												<option value="D" <%=("D".equals(courtType))?"selected":"" %>>District</option>
												<option value="J" <%=("J".equals(courtType))?"selected":"" %>>Justice</option>
											</select>
										</div>
									</div>
								</div>
							</div>	
								
							<div class="col-md-12">
								<div class="form-group">
									<div class="row align-items-center">
										<label class="col-sm-9 col-md-10 control-label text-sm-right"><strong>Location<span class="text-danger">*</span></strong></label>
										<div class="col-sm-11 col-md-13" >
											<select class="form-control" id="locnCode" name="locnCode" required>
											</select>
										</div>
									</div>
								</div>
							</div>
														
						</div>

						<div id="buttons" class="row">				
							<div class="form-group col clearfix">
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onclick="clearForm();">Clear</button>
								<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn" onclick="search();">Search</button>
							</div>
						</div>
						
					</form>
				</div>
				
				
				<div class="card-header card-footer"><strong>Results</strong></div>
				<div class="card-body" id="reportResultsDiv"></div>
				<div><br><br></div>
				
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<div class='mb-1 col-24'>
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="closeBtn" onclick="appUX.pop.getSelfHandle().close();">Close</button>
						<div class="btn-group float-right" role="group">
							<button id="btnEmail" type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Email As</button>
							<div class="dropdown-menu" aria-labelledby="btnEmail" x-placement="bottom-start"
								style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
								<a class="dropdown-item" href="#" onclick="emailReport('pdf');">PDF</a> 
								<a class="dropdown-item" href="#" onclick="emailReport('xlsx');">Excel</a>
							</div>
						</div>
						<div class="btn-group float-right" role="group">
							<button id="btnSave" type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Save As</button>
							<div class="dropdown-menu" aria-labelledby="btnSave" x-placement="bottom-start"
								style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
								<a class="dropdown-item" href="#" onclick="saveReport('pdf');">PDF</a> 
								<a class="dropdown-item" href="#" onclick="saveReport('xlsx');">Excel</a>
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
					 <option value="<%=TextUtil.print(locationBO.getLocnCode())%>"><%=TextUtil.print(locationBO.getLocnDescr())%></option>
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
					 <option value="<%=TextUtil.print(locationBO.getLocnCode())%>"><%=TextUtil.print(locationBO.getLocnDescr())%></option>
					<%
				}
			}
			%>
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
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/coris-common.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/forge.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/coris-common.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script>
		$(document).ready(function(){

			$('[id^=datetimepicker]').datetimepicker({ 
				format: 'MM/DD/YYYY',			
				icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
			});
		
		    $('#searchResultsTable').DataTable( {
		    	"retrieve": true,
		    	"stateSave": true,
		    	"paging": true,
		    	"info": true,
		    	"searching": true,
		        "lengthMenu": [[100, 50, 10, 1], [100, 50, 10, 1]]
		    } );
		    
			$('#searchResultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});

			$('#loadingContainer').hide();
			
			loadLocation('<%=courtType%>');
		});
		
		var parentCornId = appUX.pop.getSelfHandle().id;

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		
		function clearForm(){
			$('#startDate').val("");
			$('#endDate').val("");
			$('#startJournal').val("");
			$('#endJournal').val("");
		}
		
		function search() {
		
		
			if (($('#startDate').val() != '' && $('#endDate').val() != '') || ($('#startJournal').val() != '' && $('#endJournal').val() != '')) {
			
				// clear table results
				$('#searchResultsTable').dataTable().fnClearTable(); ;
	
				// busy indicator  
				$('#loadingContainer').show();
				
				var table = $('#searchResultsTable').DataTable();
				var orderBy = table.order();
				
				$('#reportResultsDiv').load('/CorisWEB/CorisReportUnclaimedPropertyServlet?mode=find&orderBy=' + JSON.stringify(orderBy) + '&' + $('#searchForm').serialize(), 
					function(responseTxt, statusTxt, xhr) {
						var sessionTimedOut = false;
						if (responseTxt == undefined) {
							sessionTimedOut = true;
						} else {
							var jsonObj = JSON.parse(responseTxt);
							if (jsonObj.success == undefined) {
								sessionTimedOut = true;
							} else if (!jsonObj.success) {
								appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
								$('#loadingContainer').hide();						
							}
						}
						
						if (sessionTimedOut) {
							$('#loadingContainer').hide();
							appUX.pop.alert("Session timed out.", 400, 'auto', appUX.pop.styles.DANGER);
							window.parent.location = '\CorisWEB\LogoutServlet';
						}
	  				}
	  			);
	  			
	  		} else {
	  			appUX.pop.alert("Dates Start/End or Journal Start/End are required.", 400, 'auto', appUX.pop.styles.DANGER);
	  		}
	  		
		}
		
		function validate(){
			var	startDate = new Date($('#startDateId').val());
			var endDate = new Date($('#endDateId').val());
			if (startDate > endDate){
				$('#startDateId').val("");
				var message = "[Start Date] must be prior to the [End date].";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			} else {
				var differences = ((endDate.getTime() - startDate.getTime())/ (1000 * 3600 * 24));
				if (differences > 31){
					$('#startDateId').val("");
					$('#endDateId').val("");
					var message = "One month limit on date searches.";
					appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				}
			}
		}
		

		function loadLocation(courtType) {
			if (courtType == "D") {
				$('#locnCode').html($('#locationDistrictOptions').html());
			} else if (courtType == "J") {
				$('#locnCode').html($('#locationJusticeOptions').html());
			}
			$('#locnCode').val("<%=locnCode%>");
			
	
		}
		
		function saveReport(format){
			var table = $('#searchResultsTable').DataTable();
			var orderBy = table.order();
			windowOpenUrlEncrypt("/CorisWEB/CorisReportUnclaimedPropertyServlet", "mode=save&orderBy=" + JSON.stringify(orderBy) + "&format="+format+"&"+$('#searchForm').serialize());
		}
		
		function emailReport(format){
			var url = '/CorisWEB/CorisReportUnclaimedPropertyServlet?mode=email&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=' + format;
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
				'&' + $('#searchForm').serialize()
			);
		}		
</script>

</body>
</html>
<%
	dateFormat = null;
	logName = null;
	locnCode = null;
	courtType = null;
	startDate = null;
	endDate = null;
	startJournal = null;
	endJournal = null;
	locationDistrictList = null;
	locationJusticeList = null;
%>