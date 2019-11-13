<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.trackingtype.TrackingTypeBO"%>
<%@page import="java.util.Date"%>
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
String mode = TextUtil.getParamAsString(request, "mode");
int intCaseNum = TextUtil.getParamAsInt(request, "intCaseNum");
String caseNum = TextUtil.getParamAsString(request, "caseNum");
String caseTitle = TextUtil.getParamAsString(request, "caseTitle");
String trackCode = TextUtil.getParamAsString(request, "trackCode");
Date reviewDate = TextUtil.getParamAsDate(request, "reviewDate");
Date createDatetime = new Date();
if("edit".equals(mode)){
	createDatetime = URLEncryption.getParamAsDate(request, "createDatetime", "yyyy-MM-dd HH:mm:ss.S");
}
String parentCornId = TextUtil.getParamAsString(request, "parentCornId");
String courtType = TextUtil.getParamAsString(request, "courtType");
String locnCode = TextUtil.getParamAsString(request, "locnCode");
String locnCodeDescr = TextUtil.getParamAsString(request, "locnCodeDescr");
List<TrackingTypeBO> trackingTypeList = (List<TrackingTypeBO>) request.getAttribute("trackingTypeList");
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
					<strong>
					<%=("add".equals(mode))?"Add Tracking":"Edit Tracking" %>
					</strong>
				</div>
				
				<div class="card-body">
					<form id="formName" name="formName">
						<% if("edit".equals(mode)){ %>
							<input type="hidden" id="createDatetime" name="createDatetime" value="<%=("edit".equals(mode))?TextUtil.print(dateFormat2.format(createDatetime)):""%>">
						<% } %>
						<div class="form-row mt-4">
							<div class="form-group col-md-13 offset-lg-2 col-lg-10">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right">Tracking Code<span class="text-danger">*</span></label>
									<div class="col-sm-12 col-md-13">
										<select class="form-control" id="trackingCode" name="trackingCode" required <%=("edit".equals(mode))?"disabled":"" %>>
											<option value=""></option>
											<% 
											if(trackingTypeList.size() > 0){ 
												String tempTrackingCode = "";
												String tempTrackingDescr = "";
												for(TrackingTypeBO temp : trackingTypeList){
													tempTrackingCode = temp.getTrackCode();
													tempTrackingDescr = temp.getDescr();
													%>
													<option value="<%=TextUtil.print(tempTrackingCode)%>" <%=("edit".equals(mode) && tempTrackingCode.equals(trackCode))?"selected":"" %>><%=TextUtil.print(tempTrackingDescr)%></option>
													<%
												} 
												tempTrackingCode = null;
												tempTrackingDescr = null;
											} 
											%>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-13 offset-lg-2 col-lg-10">
								<div class="row align-items-center">
									<label for="fieldName" class="col-sm-8 col-md-9 control-label text-sm-right">Review Date<span class="text-danger">*</span></label>
									<div class="col-sm-12 col-md-13">
										<div class="row">
											<div class="col input-group date" id='datetimepicker1'>
												<input type="text" class="form-control" id="reviewDate" name="reviewDate" value="<%=("edit".equals(mode))?TextUtil.print(dateFormat.format(reviewDate)):""%>" maxlength="10" required>
												<span class="input-group-text input-group-addon">
													<span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</form>

					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='col-24'>
							<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 mb-2 float-right" id="cancelTrackingBtn" onclick="closePop();">Cancel</button>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 mb-2 float-right" id="saveTrackingBtn" onclick="validateForm();">Save</button>
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

			$('[id^=datetimepicker]').datetimepicker( { 
				format: 'MM/DD/YYYY',			
				icons: {
		            previous: "fa fa-angle-left",
		            next: "fa fa-angle-right"
		        }
			} );
			
		});
		
		function validateForm(){
		
		
			$('#trackingCode').removeClass("is-invalid is-valid"); //no red or green
			$('#reviewDate').removeClass("is-invalid is-valid"); //no red or green
			var trackingCode = $('#trackingCode').val();
			var reviewDate = $('#reviewDate').val();
			var valid = true;
			var message = "";
			if(trackingCode == "" || trackingCode == null){
 				valid = false;
				message += "Tracking Code is required.<br>";
				$('#trackingCode').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}			
			if(reviewDate == "" || reviewDate == null){
				valid = false;
				message += "Review Date is required.<br>";
				$('#reviewDate').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			var today = moment();
			var yesterday = moment(today).subtract(1, 'day');
			var threeYears = moment(today).add(3, 'years');
			var tempReviewDate = new Date(reviewDate); //sets this to midnight of reviewDate
			if(tempReviewDate < yesterday){ // we want this to include today so we'll use yesterday (for the midnight issue)
				valid = false;
				message += "Review Date must be today or greater than today.<br>";
				$('#reviewDate').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if(tempReviewDate > threeYears){ //must use tempReviewDate for the midnight issue
				var title = "Confirm Date";
				var message2 = 'Review Date is greater than 3 years. Would you like to continue?';
				var trueText = "Continue";
				var falseText = "Cancel";
				var width = 300;
				var height = "auto";
				var style = appUX.pop.styles.WARNING;
				var corn = appUX.pop.confirm(title, message2, trueText, falseText, width, height, style, confirmCallback);
				function confirmCallback(action) {
					valid = action;
					corn.close();
					submitForm(valid, message);
				}
			}else{
				submitForm(valid, message);
			}
		}
		
		function submitForm(valid, message){
			if(valid){
			 	saveTrackingBtn.disabled = true;
				appUX.ajax.call("/CorisWEB/CaseTrackingServlet", 
					function (err, data, xhr) { 
						var jsonObj = JSON.parse(data);
						if(err){
							message = "Error: Changes were not saved.";
							appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
						}
						if (jsonObj && jsonObj.error && jsonObj.errorMessage != null) {
							appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							appUX.pop.refreshCornFrame('<%=parentCornId%>', true);
						} else {
							appUX.pop.declare("Success", "Changes have been saved.", 300, 'auto', appUX.pop.styles.SUCCESS);
							appUX.pop.refreshCornFrame('<%=parentCornId%>', true);
						}
						closePop();
					}, 
					'POST', 
					'mode=save&action=<%=mode%>&courtType=<%=courtType%>&locnCode=<%=locnCode%>&caseNum=<%=caseNum%>&createDatetime='+$("#createDatetime").val()+'&trackCode='+$("#trackingCode").val()+'&reviewDate='+$("#reviewDate").val()
				);			
			}else{
				if(message != ""){
					appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				}
			}
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
dateFormat = null;
dateFormat2 = null;
mode = null;
trackCode = null;
reviewDate = null;
createDatetime = null;
parentCornId = null;
trackingTypeList = null;
caseNum = null;
caseTitle = null;
courtType = null;
locnCode = null;
locnCodeDescr = null;
%>