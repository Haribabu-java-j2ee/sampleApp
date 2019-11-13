<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.documenttype.DocumentTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.documenttypeprofile.DocumentTypeProfileBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.document.DocumentBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Document Report</title>
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
	List<PersonnelBO> personnels = request.getAttribute("personnels")==null? new ArrayList<PersonnelBO>(): (List<PersonnelBO>)request.getAttribute("personnels"); 
	String locnCode = URLEncryption.getParamAsString(request, "locnCode");
	String courtType = URLEncryption.getParamAsString(request, "courtType");
%>
</head>

<body>
	<div id="loadingContainer">
        <div id="loadingCard">
            <div id="loadingMessage">Loading ...</div>
        </div>
    </div>
	<main>
	<div class="container-fluid">

		<div class="card m-2">
			<div class="card-header">
				<strong>Report of Documents</strong>
			</div>
			<div class="card-body">
				<form id="searchForm" name="searchForm">
					<!-- first row -->
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Doc User Types:<span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<select class="form-control" id="userType" name="userType" onchange="syncClerks(this.value);">
											<option value="A">All</option>
											<option value="K">Clerk</option>
											<option value="J">Judge</option>
											<option value="C">Commissioner</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Doc Users:<span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13" id="idDiv">
										<select class="form-control" id="clerkId" name="clerkId">
											<!-- <option value="0">All</option> -->
											<%
												for (PersonnelBO person : personnels) {
											%>
											<option value="<%=person.getUseridSrl() %>"><%=TextUtil.print(person.getLastName()) + (TextUtil.isEmpty(person.getFirstName()) ? "" : ", " + person.getFirstName())%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- second row -->
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Beginning Date:<span class="text-danger"></span>
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
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Ending Date:<span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13">
										<div class="col input-group date" id='datetimepicker2'>
											<input type="text" class="form-control" id="endDate" name="endDate"> <span class="input-group-text input-group-addon"> <span class="fa fa-calendar"></span></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- third row -->
					<div class="form-row">
						<!-- <div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Order By:<span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<div class="row">
											<select class="form-control" id="orderBy1" name="orderBy1">
												<option value="docDate">Document Date</option>
												<option value="caseNum">Case Number</option>
												<option value="logName">Logname</option>
												<option value="title">Title</option>
												<option value="docType">Document Type</option>
												<option value="caseClass">Case Class</option>
												<option value="docClass">Document Class</option>
											</select>
										</div>
										<div class="row">
											<select class="form-control" id="orderBy2" name="orderBy2">
												<option value="docDate">Document Date</option>
												<option value="caseNum">Case Number</option>
												<option value="logName">Logname</option>
												<option value="title">Title</option>
												<option value="docType">Document Type</option>
												<option value="caseClass">Case Class</option>
												<option value="docClass">Document Class</option>
											</select>
										</div>
										<div class="row">
											<select class="form-control" id="orderBy3" name="orderBy3">
												<option value="docDate">Document Date</option>
												<option value="caseNum">Case Number</option>
												<option value="logName">Logname</option>
												<option value="title">Title</option>
												<option value="docType">Document Type</option>
												<option value="caseClass">Case Class</option>
												<option value="docClass">Document Class</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div> -->
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Doc Image:<span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13">
										<!-- <div class="row"> -->
											<input type="checkbox" id="withoutImageOnly" name="withoutImageOnly" /><label for="withoutImageOnly">Documents without Images (Only)</label>
										<!-- </div>
										<div class="row">
											<input type="checkbox" id="spreadSheet" name="spreadSheet" /><label for="spreadSheet">Spreadsheet Format</label>
										</div> -->
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- fourth row: buttons -->
					<div class="form-row">
						<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
							<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onclick="clearForm();">Clear</button>
							<button type="button" class="btn btn-primary ml-2 float-right" id="searchBtn" onclick="searchDocs();">Search</button>
						</div>
					</div>
				</form>
			</div>

			<div class="card-header card-footer">
				<strong>Results</strong>
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
			
			$('#reportResultsDiv').load('jsp/corisReportDocumentResults.jsp');
			$('#loadingContainer').hide();

		}); 
		
		function syncClerks(clerkType){
			var opt=''; //'<option value="0">All</option>';
			if('A'==clerkType){
				<%for (PersonnelBO person : personnels) {%>
					opt +='<option value="<%=person.getUseridSrl()%>"><%=TextUtil.print(person.getLastName()) + (TextUtil.isEmpty(person.getFirstName()) ? "" : ", " + person.getFirstName())%></option>';
				<%}%>
			}else if('K'==clerkType){
				<%for (PersonnelBO person : personnels) { 
					if("K".equalsIgnoreCase(person.getType())) {%>
					opt +='<option value="<%=person.getUseridSrl()%>"><%=TextUtil.print(person.getLastName()) + (TextUtil.isEmpty(person.getFirstName()) ? "" : ", " + person.getFirstName())%></option>';
				<%}
				}%>
			}else if('J'==clerkType){
				<%for (PersonnelBO person : personnels) { 
					if("J".equalsIgnoreCase(person.getType())) {%>
					opt +='<option value="<%=person.getUseridSrl()%>"><%=TextUtil.print(person.getLastName()) + (TextUtil.isEmpty(person.getFirstName()) ? "" : ", " + person.getFirstName())%></option>';
				<%}
				}%>
			}else if('C'==clerkType){
				<%for (PersonnelBO person : personnels) { 
					if("C".equalsIgnoreCase(person.getType())) {%>
					opt +='<option value="<%=person.getUseridSrl()%>"><%=TextUtil.print(person.getLastName()) + (TextUtil.isEmpty(person.getFirstName()) ? "" : ", " + person.getFirstName())%></option>';
				<%}
				}%>
		}

			$('#clerkId').html(opt);
		}

		function searchDocs() {
			if (validateReportDate()) {
				$('body').addClass('waiting');
				$('#loadingContainer').show();
				var dataTable = $('#searchResultsTable').DataTable();
				var order = dataTable.order();
				var url = '/CorisWEB/CorisReportDocumentServlet?mode=find&'+ $('#searchForm').serialize()
						+ '&locnCode=<%=locnCode%>&courtType=<%=courtType%>&orderBy=' + JSON.stringify(order);
				$('#reportResultsDiv').load(url,function(responseTxt, statusTxt, xhr){
						$('#loadingContainer').hide();
						$('body').removeClass('waiting');
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
			} else {
				appUX.pop.alert("Valid report date range (<=31 days) is required.", 400, 'auto', appUX.pop.styles.DANGER);
				$('#startDate').focus();
			}
		}

		function clearForm() {
			$('#startDate').val('');
			$('#endDate').val('');
		}
		
		function saveReport(fmt){
			if (validateReportDate()) {
				var dataTable = $('#searchResultsTable').DataTable();
				var order = dataTable.order();
				window.open("/CorisWEB/CorisReportDocumentServlet?mode=save&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=" + fmt 
							+ "&" + $('#searchForm').serialize() + "&orderBy=" + JSON.stringify(order));
			} else {
				appUX.pop.alert("Valid report date range (<=31 days) is required.", 400, 'auto', appUX.pop.styles.DANGER);
				$('#startDate').focus();
			}
		}
		
		function emailReport(fmt){
			if (validateReportDate()) {
				$('body').addClass('waiting');
				var dataTable = $('#searchResultsTable').DataTable();
				var order = dataTable.order();
				var url = '/CorisWEB/CorisReportDocumentServlet?mode=email&locnCode=<%=locnCode%>&courtType=<%=courtType%>&format=' + fmt
							+ "&orderBy=" + JSON.stringify(order);
				appUX.ajax.call(url, function (err, data, xhr) {
						$('body').removeClass('waiting');
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