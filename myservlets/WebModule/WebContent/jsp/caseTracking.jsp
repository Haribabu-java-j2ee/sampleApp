<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.trackingtype.TrackingTypeBO"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
SimpleDateFormat dateFormat = Constants.dateFormatCoris;
SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
String caseNum = TextUtil.getParamAsString(request, "caseNum");
String locnCode = TextUtil.getParamAsString(request, "locnCode");
String courtType = TextUtil.getParamAsString(request, "courtType");
String locnCodeDescr = TextUtil.getParamAsString(request, "locnCodeDescr");
String caseTitle = TextUtil.getParamAsString(request, "caseTitle");
if(TextUtil.isEmpty(caseTitle)){
	caseTitle = (String) request.getAttribute("caseTitle");
}
List<TrackingBO> trackingList = (List<TrackingBO>) request.getAttribute("trackingList");
int colspanTracking = 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>CORIS - Case Tracking</title>
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
					<strong>Case: <%=TextUtil.print(caseNum) %><%=(!TextUtil.isEmpty(caseTitle))?" - "+TextUtil.print(caseTitle):"" %> <span class="float-right"><%=TextUtil.print(locnCodeDescr) %></span></strong>
				</div>
				
				<div class="card-body">
					<button type="button" title="Add" onclick="addTracking();" class="float-right btn btn-primary mb-4">Add</button>
					<table id="resultsTableTracking" class="table hover row-border compact">
						<thead class="text-light bg-dark">
							<tr>
								<% colspanTracking = 7; //keep this updated as table changes so that the last column with icons is not orderable %>
								<th>Reason</th>
								<th>Review Date</th>
								<th>Clerk</th>
								<th>Tracking Started</th>
								<th>Tracking Ended</th>
								<th>Ended User</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							if(trackingList != null){
								if(trackingList.size() > 0){
									int tempIntCaseNum = 0;
									String trackCode = "";
									String trackDescr = "";
									Date createDatetime;
									Date endDatetime;
									Date reviewDate;
									int clerkId = 0;
									String clerkIdName = "";
									int endUseridSrl = 0;
									String endUseridSrlName = "";
									for(TrackingBO temp : trackingList){
										tempIntCaseNum = temp.getIntCaseNum();
										trackCode = temp.getTrackCode();
										trackDescr = (String) temp.get(TrackingTypeBO.DESCR);
										createDatetime = temp.getCreateDatetime();
										endDatetime = temp.getEndDatetime();
										reviewDate = temp.getReviewDate();
										clerkId = temp.getClerkId();
										endUseridSrl = temp.getEndUseridSrl();
										clerkIdName = (String) temp.get("CLERKLOGNAME");							
										endUseridSrlName = (String) temp.get("ENDCLERKLOGNAME");
										%>
										<tr>
											<td <%=(endDatetime != null)?" class='text-muted'":""%>><%=TextUtil.print(trackDescr)%></td>
											<td data-sort="<%= TextUtil.printDate(reviewDate,"yyyyMMdd")%>" <%=(endDatetime != null)?" class='text-muted'":""%>><%=(reviewDate != null)?TextUtil.print(dateFormat.format(reviewDate)):""%></td>
											<td <%=(endDatetime != null)?" class='text-muted'":""%>><%=TextUtil.print(clerkIdName)%></td>
											<td <%=(endDatetime != null)?" class='text-muted'":""%>><%=(createDatetime != null)?TextUtil.print(dateFormat.format(createDatetime)):""%></td>
											<td <%=(endDatetime != null)?" class='text-muted'":""%>><%=(endDatetime != null)?TextUtil.print(dateFormat.format(endDatetime)):""%></td>
											<td <%=(endDatetime != null)?" class='text-muted'":""%>><%=TextUtil.print(endUseridSrlName)%></td>
											<td style='white-space: nowrap;'>
												<% if(endDatetime == null){ %>
													<i title="Update" class="text-warning fas fa-edit fa-lg" onclick="updateTracking('<%=TextUtil.print(dateFormat2.format(createDatetime))%>', <%=TextUtil.print(tempIntCaseNum)%>, '<%=TextUtil.print(trackCode)%>', '<%=TextUtil.print(dateFormat.format(reviewDate))%>');"></i>
													<i title="End" class="text-danger fas fa-ban fa-lg" onclick='endTracking("<%=TextUtil.print(dateFormat2.format(createDatetime))%>", <%=TextUtil.print(tempIntCaseNum)%>, "<%=TextUtil.print(trackCode)%>", "<%=TextUtil.print(dateFormat.format(reviewDate))%>");'></i>
												<% } %>
											</td>
										</tr>
										<%													
									}
									trackCode = null;
									trackDescr = null;
									createDatetime = null;
									endDatetime = null;
									reviewDate = null;
									clerkIdName = null;
									endUseridSrlName = null;
								}
							}
							%>
						</tbody>
					</table>

					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='col-24'>
							<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 mb-2 float-right" id="closeTrackingBtn" onclick="closePop();">Close</button>
						</div>
					</div>
					
				</div>
				
			</div>

    	</div>

    </main>

    <!-- footer -->
    <footer></footer>

    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){
	
		    $('#resultsTableTracking').DataTable( {
		        "retrieve": true,
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
				"oLanguage": {
					"sSearch": "Filter results:"
				},
				order: [[1, 'desc']],
				"columnDefs": [
    				{ "orderable": false, "targets": <%= colspanTracking - 1 %> },
    			]
		    } );
			$('#resultsTableTracking tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
			
			$("svg").on( "mouseenter", function() { //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
				$(this).css("cursor", "pointer" );
			});
			$("svg").on( "mouseleave", function() {
				$(this).css("cursor", "default" );
			});
			
		});
		
		var parentCornId = appUX.pop.getSelfHandle().id;
		
		function addTracking(){
			var cornId = "addTracking";
			var title = "Add Tracking";
			var url = "/CorisWEB/CaseTrackingServlet?mode=add&locnCode=<%=locnCode%>&locnCodeDescr=<%=locnCodeDescr%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&caseTitle=<%=caseTitle%>&parentCornId="+parentCornId;
			var width = 400;
			var height = 370;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);		
		}

		function updateTracking(createDatetime, intCaseNum, trackCode, reviewDate){
			var cornId = "editTracking";
			var title = "Edit Tracking";
			var url = "/CorisWEB/CaseTrackingServlet?mode=edit&locnCode=<%=locnCode%>&locnCodeDescr=<%=locnCodeDescr%>&courtType=<%=courtType%>&reviewDate="+reviewDate+"&createDatetime="+createDatetime+"&intCaseNum="+intCaseNum+"&trackCode="+trackCode+"&caseNum=<%=caseNum%>&caseTitle=<%=caseTitle%>&parentCornId="+parentCornId;
			var width = 400;
			var height = 370;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);		
		}
		
		function endTracking(createDatetime, intCaseNum, trackCode, reviewDate){
			var title = "End Tracking";
			var message = 'Are you sure?';
			var trueText = "Yes, End Tracking";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.WARNING;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
			
			function confirmCallback(action) {
				if (action) {
					var url = "/CorisWEB/CaseTrackingServlet";
					appUX.ajax.call(url, 
						function (err, data, xhr) { 
							var jsonObj = JSON.parse(data);
							if (err) {
								message = "There was an error. "+xhr.responseText;
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							}
							if (jsonObj && jsonObj.error && jsonObj.errorMessage != null) {
								message = jsonObj.errorMessage;
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							} else {
								message = "Changes have been saved.";
								appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
								if (trackCode === "RTS"){
									insertTuaTracking(intCaseNum);
								} else{
									refreshParent();
								}
								corn.close();
							}
						}, 
						'POST', 
						"mode=disable&createDatetime="+createDatetime+"&intCaseNum="+intCaseNum+"&trackCode="+trackCode+"&locnCode=<%=locnCode%>&locnCodeDescr=<%=locnCodeDescr%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&caseTitle=<%=caseTitle%>&parentCornId="+parentCornId
					);
				}else{
					corn.close();
				}
			}
		}

		function insertTuaTracking(intCaseNum){
			var title = "Set Under Advisement Tracking";
			var message = 'Would you like to set tracking for Under Advisement for 60 days?';
			var trueText = "Yes, Create Tracking";
			var falseText = "No";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.SECONDARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
			
			function confirmCallback(action) {
				if (action) {
					var url = "/CorisWEB/CaseTrackingServlet";
				    var today = new Date();
				    var reviewDate = new Date();
				    reviewDate.setDate(today.getDate()+60);
					reviewDate = (reviewDate.getMonth() + 1) + '/' + reviewDate.getDate() + '/' +  reviewDate.getFullYear();
					
					appUX.ajax.call(url, 
						function (err, data, xhr) { 
							var jsonObj = JSON.parse(data);
							if (err) {
								message = "There was an error. "+xhr.responseText;
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							}
							if (jsonObj && jsonObj.error && jsonObj.errorMessage != null) {
								message = jsonObj.errorMessage;
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							} else {
								message = "Changes have been saved.";
								appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
								refreshParent();
								corn.close();
							}
						}, 
						'POST', 
						"mode=save&action=add&reviewDate="+reviewDate+"&intCaseNum="+intCaseNum+"&trackCode=TUA"+"&locnCode=<%=locnCode%>&locnCodeDescr=<%=locnCodeDescr%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&caseTitle=<%=caseTitle%>&parentCornId="+parentCornId
					);
				}else{
					corn.close();
				}
			}
		}


		function refreshParent() {
			appUX.pop.refreshCornFrame(appUX.pop.getSelfHandle().id);
		}
	
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}

	</script>
</body>
</html>
<%
//cleanup
courtType = null;
caseNum = null;
locnCode = null;
locnCodeDescr = null;
caseTitle = null;
dateFormat = null;
dateFormat2 = null;
trackingList = null;
%>