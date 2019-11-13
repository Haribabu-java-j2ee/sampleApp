<!DOCTYPE HTML>
<%@page import="gov.utcourts.corisweb.report.CorisNoOtnReportSearchCriteria"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<html>
<head>
<title>No OTN Report</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<style>
.cursor-pointer {
	cursor: pointer;
}
body.waiting * {
    cursor: progress;
}
</style>
<%
	CorisNoOtnReportSearchCriteria criteria = request.getAttribute("rptCriteria")==null?new CorisNoOtnReportSearchCriteria(request):(CorisNoOtnReportSearchCriteria)request.getAttribute("rptCriteria");
 %>
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
				<strong>No OTN Report</strong>
			</div>
			<div class="card-body">
				<form id="searchForm" name="searchForm">
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Start Date:<span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<div class="col input-group date" id='datetimepicker1'>
											<input type="text" class="form-control" id="startDate" name="startDate"> <span class="input-group-text input-group-addon"> <span
												class="fa fa-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13">
										<input type="checkbox" id="allCourts" name="allCourts" /><label for="allCourts">All District Courts</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>End Date:<span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13">
										<div class="col input-group date" id='datetimepicker2'>
											<input type="text" class="form-control" id="endDate" name="endDate"> <span class="input-group-text input-group-addon"> <span class="fa fa-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13">
										<input type="checkbox" id="localDb" name="allLocalDb" /><label for="allLocalDb">All on Local Database</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
								</div>
							</div>
						</div>
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13">
										<input type="checkbox" id="thisCourt" name="thisCourtOnly" checked /><label for="thisCourtOnly">This Court Site Only</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				<!-- fourth row: buttons -->
					<div class="form-row">
						<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
							<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onclick="clearForm();">Clear</button>
							<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn" onclick="search();">Search</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="card-header card-footer">
				<strong>No OTN or Citation:</strong>--><a href="#summary">Summary</a>
			</div>
			<!-- Results holding DIV element  -->
			<div class="card-body" id="reportResultsDiv"></div>
			<br /> <br />
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
							<a class="dropdown-item" href="#" onclick="javascript: saveReport('pdf');">PDF</a> 
							<a class="dropdown-item" href="#" onclick="saveReport('xlsx');">Excel</a>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
	</main>
	<!-- footer -->
    <footer></footer>

	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/coris-common.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('[id^=datetimepicker]').datetimepicker({
				format: 'MM/DD/YYYY',
				icons: {
					previous: "fa fa-angle-left",
					next: "fa fa-angle-right"
				}
			});
			
			$('#reportResultsDiv').load('jsp/corisReportNoOtnResults.jsp');
			$('#loadingContainer').hide();

		}); 
		
		function search() {		
			if(validateReportDate()){
				$('body').addClass('waiting');
				$('#loadingContainer').show();
				var dataTable = $('#searchResultsTable').DataTable();
				var order = dataTable.order();
				var url = '/CorisWEB/CorisReportNoOtnServlet?mode=find&' + $('#searchForm').serialize() 
						+ '&locnCode=<%=criteria.getLocnCode() %>&courtType=<%=criteria.getCourtType() %>&orderBy=' + JSON.stringify(order);
				$('#reportResultsDiv').load(url, function(responseTxt, statusTxt, xhr){
						$('body').removeClass('waiting');
						$('#loadingContainer').hide();
						if(statusTxt=="success"){
							try{
								var jsonObj = JSON.parse(responseTxt);
								if(jsonObj && jsonObj.errorMessage){
									appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
								}
							}catch(e){
								console.log('response is not a json object.');
							}
						}
						});
			}else {
				appUX.pop.alert('Valid report date range (<=31 days) is required.', 400, 'auto', appUX.pop.styles.DANGER);
			}
			
		}
		
		function saveReport(fmt){
			
			if(validateReportDate()){
				var dataTable = $('#searchResultsTable').DataTable();
				var order = dataTable.order();
				window.open("/CorisWEB/CorisReportNoOtnServlet?mode=save&locnCode=<%=criteria.getLocnCode()%>&courtType=<%=criteria.getCourtType()%>&format=" + fmt 
							+ "&" + $('#searchForm').serialize() + "&orderBy=" + JSON.stringify(order));
			} else {
				appUX.pop.alert("Valid report date range (<=31 days) is required.", 400, 'auto', appUX.pop.styles.DANGER);
				$('#startDate').focus();
			}
		}
		
		function emailReport(fmt){
			if(validateReportDate()){
				$('body').addClass('waiting');
				/* $('#loadingContainer').show(); */
				var dataTable = $('#searchResultsTable').DataTable();
				var order = dataTable.order();
				var url = '/CorisWEB/CorisReportNoOtnServlet?mode=email&locnCode=<%=criteria.getLocnCode()%>&courtType=<%=criteria.getCourtType()%>&format=' + fmt
							+ "&orderBy=" + JSON.stringify(order);
				appUX.ajax.call(url, function (err, data, xhr) {
						$('body').removeClass('waiting');
						/* $('#loadingContainer').hide(); */
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
			} else {
				appUX.pop.alert("Valid report date range (<=31 days) is required.", 400, 'auto', appUX.pop.styles.DANGER);
				$('#startDate').focus();
			}
		}
		
		function validateReportDate(){
			var startDate = new Date($('#startDate').val());
			var endDate = new Date($('#endDate').val())
			var startDateValid = startDate != "Invalid Date" && !isNaN(startDate) && startDate.getTime() <= new Date().getTime();
			var endDateValid = endDate != "Invalid Date" && !isNaN(endDate);
			if(endDateValid && startDateValid && (endDate.getTime() - startDate.getTime())/86400000 > 31 || startDate.getTime() > endDate.getTime() ){
				endDateValid = false;
			}
			
			return startDateValid && endDateValid;
			
		}
		
	</script>
</body>
</html>