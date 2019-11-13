
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="gov.utcourts.coriscommon.util.DateUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>  
<%
	String courtType = (String) request.getAttribute("courtType");
	String locnCode = (String) request.getAttribute("locnCode");
	Date dateFrom = (Date) request.getAttribute("dateFrom");
	Date dateTo = (Date) request.getAttribute("dateTo");
	
	List<TrackingBO> searchResults = (List<TrackingBO>) request.getAttribute("searchResults");
	
	StringBuilder sb = new StringBuilder();
	List<LocationBO> distLocationList = (List<LocationBO>) request.getAttribute("distLocationList");
	if (distLocationList != null){
		for (LocationBO temp : distLocationList) {
			sb.append("<option value=\"").append(temp.get(LocationBO.LOCNCODE)).append("\">").append(temp.get(LocationBO.LOCNDESCR)).append("</option>");
		}
	}
	String districtLocationOptions = sb.toString();
	
	sb = new StringBuilder();
	List<LocationBO> justLocationList = (List<LocationBO>) request.getAttribute("justLocationList");
	if (justLocationList != null) {
		for (LocationBO temp : justLocationList) {
			sb.append("<option value=\"").append(temp.get(LocationBO.LOCNCODE)).append("\">").append(temp.get(LocationBO.LOCNDESCR)).append("</option>");
		}
	}
	String justiceLocationOptions = sb.toString();
	
	SimpleDateFormat dateFormat = Constants.dateFormatCoris;
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
				
					<form id="searchForm" name="searchForm">
					<input type="hidden" name="mode" value="search">
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
											<% if ("D".equals(courtType) && distLocationList != null) {
													for (LocationBO temp : distLocationList) {
											%>
												 		<option value="<%= temp.get(LocationBO.LOCNCODE) %>" <%=temp.get(LocationBO.LOCNCODE).equals(locnCode) ? "selected" : ""  %>><%= temp.get(LocationBO.LOCNDESCR) %></option>
											<% 
													}
												} else if ("J".equals(courtType) && justLocationList != null) {
													for (LocationBO temp : justLocationList) {
											%>
											 			<option value="<%= temp.get(LocationBO.LOCNCODE) %>" <%=temp.get(LocationBO.LOCNCODE).equals(locnCode) ? "selected" : ""  %>><%= temp.get(LocationBO.LOCNDESCR) %></option>
											<%
											 		}
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
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right"><strong>From/To</strong></label>
									<div class="col-sm-11 col-md-12">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="dateFrom" name="dateFrom" value = "<%=(TextUtil.printDate(dateFrom))%>">
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
											<div class="col input-group date" id='datetimepicker2'>
												<input type="text" class="form-control" id="dateTo" name="dateTo" value = "<%=(TextUtil.printDate(dateTo))%>">
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
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Judge/Commissioner:<span class="text-danger">*</span></strong></label>
									<div class="col-sm-11 col-md-12">
										<select class="form-control" id="judgeComm" name="judgeComm"></select>
									</div>
								</div>
							</div>
							
						</div>
						
						<div class="form-row">
						
							<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn">Clear</button>
								<button type="submit" class="btn btn-primary ml-2 float-right" id="searchBtn" >Search</button>
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
								<th>Review Date</th>
								<th>Case Number</th>
								<th>Name</th>
								<th>Set Date</th>
								<th>Clerk</th>
								<th>Judge / Comm</th>
								<th>Aged Days</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (searchResults != null) {
									Date setDate = null;
									int agedDays = 0;
									for (TrackingBO result : searchResults) {
										StringBuilder judge = new StringBuilder();
										StringBuilder commissioner = new StringBuilder();
										
										if ("J".equals((String) result.get("judge_type"))) {
											judge.append((String) result.get("judge_last_name")); 
											if (!TextUtil.isEmpty((String) result.get("judge_first_name"))) {
												judge.append(", ").append(result.get("judge_first_name"));
											}
											
										}
										if ("C".equals((String) result.get("commissioner_type"))) {
											commissioner.append((String) result.get("commissioner_last_name")); 
											if (!TextUtil.isEmpty((String) result.get("commissioner_first_name"))) {
												commissioner.append(", ").append(result.get("commissioner_first_name"));
											}
											
										}
										setDate = (Date) result.get(TrackingBO.CREATEDATETIME);
										agedDays = DateUtil.getDaysBetween(new Date(), setDate);
							 %>
										<tr>
											<td><%= TextUtil.printDate((Date) result.get(TrackingBO.REVIEWDATE)) %></td>
											<td><%= TextUtil.print(result.get(KaseBO.CASENUM)) %></td>
											<td><%= TextUtil.print(result.get(ProfileBO.COURTTITLE)) %></td>
											<% if (setDate != null) { %>
												<td data-sort="<%= (setDate != null) ? dateFormat.format(setDate) : "" %>"><%= dateFormat.format(setDate) %></td>
											<% } else { %>
												<td><%= TextUtil.printDate(setDate) %></td>
											<% } %>
											<td><%= TextUtil.print(result.get("clerk_logname")) %></td>
											<td><%= TextUtil.print(result.get("judge_logname")) %></td>
											<td> <%=agedDays %></td>
										</tr>
							<% 		
									}	// for
								}  // if
					 		%>
						</tbody>
				
					</table>
					<br/>
					<br/>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='mb-1 col-24'>
							<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="closeBtn" onclick="closePop();">Close</button>
							<button id="btnGroupDrop1" type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Email As</button>
								<<div class="dropdown-menu" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
									<a class="dropdown-item" href="#">PDF</a>
									<a class="dropdown-item" href="#">Excel</a>
								</div>
							<button id="btnGroupDrop2" type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Save As</button>
								<div class="dropdown-menu" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 21px, 0px);">
									<a class="dropdown-item" href="#">PDF</a>
									<a class="dropdown-item" href="#">Excel</a>
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
	<script src="/CorisWEB/js/caseStayLookup.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<script>
		$(document).ready(function(){
	
		    $('#resultsTable').DataTable( {
		    	"retrieve": true,
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
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
			changeJudgeComm();
			$('#loadingContainer').hide();
		});
		
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
		}
		
		function changeJudgeComm(){
			$("#judgeComm").val('');
			
			var courtType = $('#courtType').val();
			var locnCode = $('#locnCode').val();
			appUX.ajax.call("/CorisWEB/ReportTakenUnderAdvisementServlet",
			function (err, data, xhr) {
				var $judgeCommDropdown = $("#judgeComm");
				$judgeCommDropdown.empty();
				$judgeCommDropdown.append("<option value=''>All Judges/Commissioners</option>")
				
				var jsonObj = JSON.parse(data);
				for (var i=0; i < jsonObj.judgeCommList.length; i++) {
				 var lastName = jsonObj.judgeCommList[i].lastName;
				 var firstName = jsonObj.judgeCommList[i].firstName;
				 var useridSrl = jsonObj.judgeCommList[i].useridSrl;
				 var type = jsonObj.judgeCommList[i].type;
				 $judgeCommDropdown.append("<option value='"+ useridSrl + "'>" + lastName + ", " + firstName + " (" + type + ")" +"</option>");
				}
			
			},
			'POST',
			'mode=changeJudgeList&courtType=' + courtType + '&locnCode=' + locnCode
			);
		}
		
	</script>
</body>
</html>
<%

%>